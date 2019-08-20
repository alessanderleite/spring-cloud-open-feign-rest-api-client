package br.com.alessanderleite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessanderleite.model.Cliente;
import br.com.alessanderleite.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public Iterable<Cliente> listAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente getById(Integer id) {
		return clienteRepository.findById(id).orElseThrow(null);
	}

	@Override
	public Cliente save(Cliente entity) {
		return clienteRepository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente update(Cliente entity) {
		Cliente cliente = getById(entity.getId());
		if (cliente != null) {
			return clienteRepository.save(entity);
		}
		return null;
	}
}