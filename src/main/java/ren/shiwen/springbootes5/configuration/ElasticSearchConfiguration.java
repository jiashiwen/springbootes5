package ren.shiwen.springbootes5.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConfigurationProperties(prefix="elasticsearch")
public class ElasticSearchConfiguration {
	@Resource
	private Environment environment;

	@Bean
	public Client esclient() throws UnknownHostException {
		int port = Integer.parseInt(environment.getProperty("elasticsearch.port"));
		Settings setting = Settings.settingsBuilder()
				.put("cluster.name", environment.getProperty("elasticsearch.clustername")).build();
		Client client = TransportClient.builder().settings(setting).build().addTransportAddress(
				new InetSocketTransportAddress(InetAddress.getByName(environment.getProperty("elasticsearch.host")),
						port));

		return client;

	}


}
