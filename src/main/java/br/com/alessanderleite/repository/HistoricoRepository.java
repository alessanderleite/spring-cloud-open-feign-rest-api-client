package br.com.alessanderleite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessanderleite.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Integer>{

}
