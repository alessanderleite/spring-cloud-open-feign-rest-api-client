package br.com.alessanderleite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alessanderleite.model.Localidade;
import br.com.alessanderleite.repository.LocalidadeRepository;

@Service
@CacheConfig(cacheNames = "localidade")
public class LocalidadeServiceImpl implements LocalidadeService {

	@Autowired
	LocalidadeRepository localidadeRepository;
	
	@Override
	@Cacheable(unless = "#result==null")
	public Iterable<Localidade> listAll() {
		return localidadeRepository.findAll();
	}

	@Override
	@Cacheable(key = "#id", unless = "#result==null")
	@Transactional(readOnly = true)
	public Localidade getById(Integer id) {
		return localidadeRepository.findById(id).orElseThrow(null);
	}

	@Override
	@CachePut(key = "#entity.id", unless = "#result==null")
	public Localidade save(Localidade entity) {
		if (entity.getId() == null) {
			return localidadeRepository.save(entity);
		}
		return null;
	}

	@Override
	@CacheEvict(key = "#id")
	public void delete(Integer id) {
		localidadeRepository.deleteById(id);
	}

	@Override
	@CachePut(key = "#entity.id", unless = "#result==null")
	public Localidade update(Localidade entity) {
		Localidade localidade = getById(entity.getId());
		if (localidade != null) {
			return localidadeRepository.save(entity);
		}
		return null;
	}

}
