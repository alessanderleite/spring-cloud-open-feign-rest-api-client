package br.com.alessanderleite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geolocalizacao")
public class Geolocalizacao implements Comparable<Geolocalizacao>{
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	private String distance;
	private String title;
	private String location_type; 	
	private String woeid;
	private String latt_long; 		
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getWoeid() {
		return woeid;
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	public String getLatt_long() {
		return latt_long;
	}

	public void setLatt_long(String latt_long) {
		this.latt_long = latt_long;
	}

	@Override
	public int compareTo(Geolocalizacao o) {
		if (Integer.parseInt(this.distance) <= Integer.parseInt(o.distance)) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return "Geolocalizacao [id=" + id + "\n distance=" + distance + "\n title=" + title + "\n location_type="
				+ location_type + "\n woeid=" + woeid + "\n latt_long=" + latt_long + "]";
	}
}
