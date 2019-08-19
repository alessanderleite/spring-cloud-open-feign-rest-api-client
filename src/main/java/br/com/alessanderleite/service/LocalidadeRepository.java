package br.com.alessanderleite.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessanderleite.model.Localidade;

public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>{

}
