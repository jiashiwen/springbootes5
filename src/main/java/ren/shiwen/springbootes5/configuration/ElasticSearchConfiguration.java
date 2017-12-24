package ren.shiwen.springbootes5.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.Resource;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import static org.apache.commons.lang.StringUtils.*;

/**
 * 配置elasticsearch transport client
 * 参考文献：https://github.com/spring-projects/spring-data-elasticsearch/blob/master/src/main/java/org/springframework/data/elasticsearch/client/TransportClientFactoryBean.java
 * 
 * @author Jiashiwen
 *
 */
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfiguration {
	@Resource
	private Environment environment;

	private String clusterNodes = "127.0.0.1:9300";
	private String clusterName = "elasticsearch";
	private Boolean clientTransportSniff = true;
	private Boolean clientIgnoreClusterName = Boolean.FALSE;
	private String clientPingTimeout = "5s";
	private String clientNodesSamplerInterval = "5s";
	private TransportClient client;
	static final String COLON = ":";
	static final String COMMA = ",";

	@Bean
	public Client esclient() throws NumberFormatException, UnknownHostException {

		/*
		 * 配置client settings
		 */
		client = new PreBuiltTransportClient(settings());

		/*
		 * 配置集群节点
		 */
		for (String clusterNode : split(clusterNodes, COMMA)) {
			String hostName = substringBeforeLast(clusterNode, COLON);
			String port = substringAfterLast(clusterNode, COLON);
			System.out.println(hostName);
			System.out.println(port);
			Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
			Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");

			client.addTransportAddress(
					new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));

		}

		System.out.println(client.connectedNodes());
		return client;

	}

	/*
	 * 设置client属性
	 */
	private Settings settings() {
		return Settings.builder().put("cluster.name", clusterName).build();
		// .put("client.transport.ignore_cluster_name", clientIgnoreClusterName)
		// .put("client.transport.ping_timeout", clientPingTimeout)
		// .put("client.transport.nodes_sampler_interval",
		// clientNodesSamplerInterval).build();
	}

	public String getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public Boolean getClientTransportSniff() {
		return clientTransportSniff;
	}

	public void setClientTransportSniff(Boolean clientTransportSniff) {
		this.clientTransportSniff = clientTransportSniff;
	}

	public Boolean getClientIgnoreClusterName() {
		return clientIgnoreClusterName;
	}

	public void setClientIgnoreClusterName(Boolean clientIgnoreClusterName) {
		this.clientIgnoreClusterName = clientIgnoreClusterName;
	}

	public String getClientPingTimeout() {
		return clientPingTimeout;
	}

	public void setClientPingTimeout(String clientPingTimeout) {
		this.clientPingTimeout = clientPingTimeout;
	}

	public String getClientNodesSamplerInterval() {
		return clientNodesSamplerInterval;
	}

	public void setClientNodesSamplerInterval(String clientNodesSamplerInterval) {
		this.clientNodesSamplerInterval = clientNodesSamplerInterval;
	}

}
