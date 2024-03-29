package br.com.alessanderleite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clima")
public class Clima {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;
	
	private String weather_state_name;
	private String weather_state_abbr;
	private String wind_direction_compass;
	private String created;
	private String applicable_date;
	private String min_temp;
	private String max_temp;
	private String the_temp;
	private String wind_speed;
	private String wind_direction;
	private String air_pressure;
	private String humidity;
	private String visibility;
	private String predictability;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWeather_state_name() {
		return weather_state_name;
	}
	public void setWeather_state_name(String weather_state_name) {
		this.weather_state_name = weather_state_name;
	}
	public String getWeather_state_abbr() {
		return weather_state_abbr;
	}
	public void setWeather_state_abbr(String weather_state_abbr) {
		this.weather_state_abbr = weather_state_abbr;
	}
	public String getWind_direction_compass() {
		return wind_direction_compass;
	}
	public void setWind_direction_compass(String wind_direction_compass) {
		this.wind_direction_compass = wind_direction_compass;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getApplicable_date() {
		return applicable_date;
	}
	public void setApplicable_date(String applicable_date) {
		this.applicable_date = applicable_date;
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
	public String getThe_temp() {
		return the_temp;
	}
	public void setThe_temp(String the_temp) {
		this.the_temp = the_temp;
	}
	public String getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(String wind_speed) {
		this.wind_speed = wind_speed;
	}
	public String getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}
	public String getAir_pressure() {
		return air_pressure;
	}
	public void setAir_pressure(String air_pressure) {
		this.air_pressure = air_pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getPredictability() {
		return predictability;
	}
	public void setPredictability(String predictability) {
		this.predictability = predictability;
	}
}
