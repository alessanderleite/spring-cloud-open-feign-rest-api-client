package br.com.alessanderleite.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localidade")
public class Localidade {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String status;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "id_date", referencedColumnName = "id", nullable = false)})
	private Data data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localidade")
	private Set<Historico> historicos = new HashSet<Historico>(0);

	public Localidade() {}

	public Localidade(Integer id, String status, Data data) {
		this.id = id;
		this.status = status;
		this.data = data;
	}

	public Localidade(Integer id, Data data) {
		this.id = id;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Set<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(Set<Historico> historicos) {
		this.historicos = historicos;
	}

	@Override
	public String toString() {
		return "Localidade [id=" + id + ", status=" + status + ", historicos=" + historicos + "]";
	}
}
