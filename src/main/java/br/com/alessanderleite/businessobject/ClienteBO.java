package br.com.alessanderleite.businessobject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintDefinitionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.alessanderleite.api.IpVigilanteClient;
import br.com.alessanderleite.api.MetaweatherClient;
import br.com.alessanderleite.model.Cliente;
import br.com.alessanderleite.model.Clima;
import br.com.alessanderleite.model.Geolocalizacao;
import br.com.alessanderleite.model.Historico;
import br.com.alessanderleite.model.Localidade;
import br.com.alessanderleite.service.ClienteService;
import br.com.alessanderleite.valueobject.ClienteVO;
import br.com.alessanderleite.valueobject.GeolocalizacaoVO;
import br.com.alessanderleite.valueobject.HistoricoVO;
import br.com.alessanderleite.valueobject.RetornoVO;

public class ClienteBO {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteBO.class); 
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private IpVigilanteClient ipVigilante;
	
	@Autowired
	private MetaweatherClient metaweather;
	
	public RetornoVO salvar(Cliente cliente) {
		
		if (!Optional.ofNullable(cliente.getId()).isPresent()) {
			cliente = new Cliente(cliente.getNome(), cliente.getIdade());
			
			Historico historico = new Historico();
			
			Localidade localidade = ipVigilante.getLocalidade();
			
			List<Geolocalizacao> listaGeolocalizacao = metaweather.obterLocalizacao(String.format("%s,%s", localidade.getData().getLatitude(), localidade.getData().getLocalidades()));
			
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String data = simpleDateFormat.format(new Date());
			
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
