package br.com.alessanderleite.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessanderleite.model.Data;

public interface DataRepository extends JpaRepository<Data, Integer>{

}
