package br.com.alessanderleite.businessobject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintDefinitionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alessanderleite.api.IpVigilanteClient;
import br.com.alessanderleite.api.MetaweatherClient;
import br.com.alessanderleite.exception.NotFoundException;
import br.com.alessanderleite.model.Cliente;
import br.com.alessanderleite.model.Clima;
import br.com.alessanderleite.model.Data;
import br.com.alessanderleite.model.Geolocalizacao;
import br.com.alessanderleite.model.Historico;
import br.com.alessanderleite.model.Localidade;
import br.com.alessanderleite.service.ClienteService;
import br.com.alessanderleite.valueobject.ClienteVO;
import br.com.alessanderleite.valueobject.GeolocalizacaoVO;
import br.com.alessanderleite.valueobject.HistoricoVO;
import br.com.alessanderleite.valueobject.RetornoVO;

@Component
public class ClienteBO {
		
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private IpVigilanteClient ipVigilante;
	
	@Autowired
	private MetaweatherClient metaweather;
	
	@Transactional
	public RetornoVO salvar(Cliente cliente) {
		
		if (!Optional.ofNullable(cliente.getId()).isPresent()) {
			cliente = new Cliente(cliente.getNome(), cliente.getIdade());
			
			Historico historico = new Historico();
			
			Localidade localidade = ipVigilante.getLocalidade();
						
			List<Geolocalizacao> listaGeolocalizacao = metaweather.obterLocalizacao(String.format("%s,%s", localidade.getData().getLatitude(), localidade.getData().getLongitude()));
			
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String data = simpleDateFormat.format(new Date()); // return current date
			
			List<Clima> listaClimas = new ArrayList<Clima>();
			
			int i = 0;
			while (listaClimas.size() == 0) {
				listaClimas.addAll(metaweather.obterClima(listaGeolocalizacao.get(i).getWoeid(), data));
				i++;
			}
			
			listaClimas.forEach(clima ->{
				if (historico.getMin_temp() == null || Double.parseDouble(clima.getMin_temp()) < Double.parseDouble(historico.getMin_temp()))
					historico.setMin_temp(clima.getMin_temp());
				
				if (historico.getMax_temp() == null || Double.parseDouble(clima.getMax_temp()) < Double.parseDouble(historico.getMax_temp()))
					historico.setMax_temp(clima.getMax_temp());
			});
			
			historico.getClientes().add(cliente);
			historico.setLocalidade(localidade);
			localidade.getHistoricos().add(historico);
			cliente.setHistorico(historico);
			
			Cliente clienteSave = clienteService.save(cliente);
			
			return buildRetornoVO(clienteSave);
		} else {
			throw new ConstraintDefinitionException();	
		}
	}
	
	public List<RetornoVO> listaClientes() {
		try {
			Iterable<Cliente> clientes = clienteService.listAll();
			List<RetornoVO> lista = new ArrayList<RetornoVO>();
			clientes.forEach(cliente -> lista.add(buildRetornoVO(cliente)));
			return lista;
		} catch (Exception e) {
			throw new NotFoundException("Not Found");
		}
	}
	
	public RetornoVO buscarClienteId(Integer id) {
		try {
			Cliente cliente = clienteService.getById(id);
			return buildRetornoVO(cliente);
		} catch (Exception e) {
			throw new NotFoundException("Not Found");
		}
	}
	
	public void deletar(Integer id) {
		try {
			clienteService.delete(id);
		} catch (Exception e) {
			throw new NotFoundException("Not Found");
		}
	}
	
	public RetornoVO atualizar(RetornoVO clienteVO) {
		Cliente cliente = clienteService.getById(clienteVO.getCliente().getId());
		if (Optional.ofNullable(cliente).isPresent()) {
			
			Cliente clienteAtualizado = new Cliente(
					clienteVO.getCliente().getId(),
					clienteVO.getCliente().getNome(),
					clienteVO.getCliente().getIdade()
			);
			
			Historico historico = new Historico(
					clienteVO.getHistorico().getId(),
					clienteVO.getHistorico().getMin_temp(),
					clienteVO.getHistorico().getMax_temp()
			);
			
			Localidade localidade = new Localidade(
					cliente.getHistorico().getLocalidade().getId(),
					cliente.getHistorico().getLocalidade().getData()
			);
			
			localidade.setData(new Data(
					clienteVO.getHistorico().getLocalidade().getIpv4(),
					clienteVO.getHistorico().getLocalidade().getContinete(),
					clienteVO.getHistorico().getLocalidade().getPais(),
					clienteVO.getHistorico().getLocalidade().getCidade(),
					clienteVO.getHistorico().getLocalidade().getLatitude(),
					clienteVO.getHistorico().getLocalidade().getLongitude()
			));
			
			historico.getClientes().add(clienteAtualizado);
			historico.setLocalidade(localidade);
			localidade.getHistoricos().add(historico);
			clienteAtualizado.setHistorico(historico);
			cliente = clienteService.save(clienteAtualizado);
			return buildRetornoVO(clienteAtualizado);
		} else {
			throw new NotFoundException("Not Found");
		}
	}
	
	private RetornoVO buildRetornoVO(Cliente cliente) {
		if (!Optional.ofNullable(cliente).isPresent()) {
			return null;
		}
		
		RetornoVO retorno = new RetornoVO(new ClienteVO(), new HistoricoVO());
		
		retorno.getCliente().setId(cliente.getId());
		retorno.getCliente().setNome(cliente.getNome());
		retorno.getCliente().setIdade(cliente.getIdade());
		
		retorno.getHistorico().setId(cliente.getHistorico().getId());
		retorno.getHistorico().setMin_temp(cliente.getHistorico().getMin_temp());
		retorno.getHistorico().setMax_temp(cliente.getHistorico().getMax_temp());
		
		GeolocalizacaoVO localizacao = new GeolocalizacaoVO();
		
		localizacao.setId(cliente.getHistorico().getLocalidade().getId());
		localizacao.setCidade(cliente.getHistorico().getLocalidade().getData().getCity_name());
		localizacao.setContinete(cliente.getHistorico().getLocalidade().getData().getContinent_name());
		localizacao.setIpv4(cliente.getHistorico().getLocalidade().getData().getIpv4());
		localizacao.setPais(cliente.getHistorico().getLocalidade().getData().getCountry_name());
		localizacao.setLatitude(cliente.getHistorico().getLocalidade().getData().getLatitude());
		localizacao.setLongitude(cliente.getHistorico().getLocalidade().getData().getLongitude());
		
		retorno.getHistorico().setLocalidade(localizacao);
		
		return retorno;
	}
}
