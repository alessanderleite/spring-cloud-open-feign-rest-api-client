package br.com.alessanderleite.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "data")
public class Data {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String ipv4;
	private String continent_name;
	private String country_name;
	private String subdivision_1_name;
	private String subdivision_2_name;
	private String city_name;
	private String latitude;
	private String longitude;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "data")
	private Set<Localidade> localidades = new HashSet<Localidade>(0);

	public Data() {}

	public Data(Integer id, String ipv4, String continent_name, String country_name, String subdivision_1_name,
			String subdivision_2_name, String city_name, String latitude, String longitude,
			Set<Localidade> localidades) {
		this.id = id;
		this.ipv4 = ipv4;
		this.continent_name = continent_name;
		this.country_name = country_name;
		this.subdivision_1_name = subdivision_1_name;
		this.subdivision_2_name = subdivision_2_name;
		this.city_name = city_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.localidades = localidades;
	}

	public Data(String ipv4, String continent_name, String country_name, String city_name, String latitude,
			String longitude) {
		super();
		this.ipv4 = ipv4;
		this.continent_name = continent_name;
		this.country_name = country_name;
		this.city_name = city_name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getContinent_name() {
		return continent_name;
	}

	public void setContinent_name(String continent_name) {
		this.continent_name = continent_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getSubdivision_1_name() {
		return subdivision_1_name;
	}

	public void setSubdivision_1_name(String subdivision_1_name) {
		this.subdivision_1_name = subdivision_1_name;
	}

	public String getSubdivision_2_name() {
		return subdivision_2_name;
	}

	public void setSubdivision_2_name(String subdivision_2_name) {
		this.subdivision_2_name = subdivision_2_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Set<Localidade> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(Set<Localidade> localidades) {
		this.localidades = localidades;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", ipv4=" + ipv4 + ", continent_name=" + continent_name + ", country_name="
				+ country_name + ", subdivision_1_name=" + subdivision_1_name + ", subdivision_2_name="
				+ subdivision_2_name + ", city_name=" + city_name + ", latitude=" + latitude + ", longitude="
				+ longitude + ", localidades=" + localidades + "]";
	}
}
