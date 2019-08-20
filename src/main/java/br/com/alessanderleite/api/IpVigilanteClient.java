package br.com.alessanderleite.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.context.support.StaticApplicationContext; 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alessanderleite.model.Localidade;

@FeignClient(name = "IpVigilanteClient", url = "https://ipvigilante.com/")
public interface IpVigilanteClient {

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	Localidade getLocalidade();
}
