package br.com.alessanderleite.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessanderleite.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Optional<Cliente> findById(Integer id);
}
