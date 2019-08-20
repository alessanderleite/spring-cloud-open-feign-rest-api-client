package br.com.alessanderleite.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import br.com.alessanderleite.api.IpVigilanteClient;
import br.com.alessanderleite.api.MetaweatherClient;

@Configuration
@EnableFeignClients(clients = {IpVigilanteClient.class, MetaweatherClient.class})
public class FeignConfig {

}
