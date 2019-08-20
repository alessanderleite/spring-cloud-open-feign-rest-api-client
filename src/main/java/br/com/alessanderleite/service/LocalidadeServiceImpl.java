package br.com.alessanderleite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessanderleite.model.Localidade;
import br.com.alessanderleite.repository.LocalidadeRepository;

@Service
public class LocalidadeServiceImpl implements LocalidadeService {

	@Autowired
	LocalidadeRepository localidadeRepository;
	
	@Override
	public Iterable<Localidade> listAll() {
		return localidadeRepository.findAll();
	}

	@Override
	public Localidade getById(Integer id) {
		return localidadeRepository.findById(id).orElseThrow(null);
	}

	@Override
	public Localidade save(Localidade entity) {
		if (entity.getId() == null) {
			return localidadeRepository.save(entity);
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		localidadeRepository.deleteById(id);
	}

	@Override
	public Localidade update(Localidade entity) {
		Localidade localidade = getById(entity.getId());
		if (localidade != null) {
			return localidadeRepository.save(entity);
		}
		return null;
	}

}
