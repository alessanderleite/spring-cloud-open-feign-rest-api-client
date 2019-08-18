package br.com.alessanderleite.model;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "historico")
public class Historico {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "historico")
	private Set<Cliente> clientes = new HashSet<Cliente>(0);

	private Localidade localidade;
	
	private String min_temp;
	private String max_temp;
	
	public Historico() {
		this.min_temp = null;
		this.max_temp = null;
	}

	public Historico(Integer id, Localidade localidade, String min_temp, String max_temp) {
		this.id = id;
		this.localidade = localidade;
		this.min_temp = min_temp;
		this.max_temp = max_temp;
	}

	public Historico(Integer id, String min_temp, String max_temp) {
		super();
		this.id = id;
		this.min_temp = min_temp;
		this.max_temp = max_temp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", clientes=" + clientes + ", min_temp=" + min_temp + ", max_temp=" + max_temp
				+ "]";
	}
}
