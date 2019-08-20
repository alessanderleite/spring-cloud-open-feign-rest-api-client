package br.com.alessanderleite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessanderleite.model.Historico;
import br.com.alessanderleite.repository.HistoricoRepository;

@Service
public class HistoricoServiceImpl implements HistoricoService {

	@Autowired
	HistoricoRepository historicoRepository;

	@Override
	public Iterable<Historico> listAll() {
		return historicoRepository.findAll();
	}

	@Override
	public Historico getById(Integer id) {
		return historicoRepository.findById(id).orElseThrow(null);
	}

	@Override
	public Historico save(Historico entity) {
		if (entity.getId() == null) {
			return historicoRepository.save(entity);	
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		historicoRepository.deleteById(id);
	}

	@Override
	public Historico update(Historico entity) {
		Historico historico = getById(entity.getId());
		if (historico != null) {
			return historicoRepository.save(entity);
		}
		return null;
	}
	
	
}
