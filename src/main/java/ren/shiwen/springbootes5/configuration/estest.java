package ren.shiwen.springbootes5.configuration;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class estest implements CommandLineRunner{
	
	@Resource
	private ElasticSearchConfiguration es;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(getClusterHealth());
		
	}

	private int getClusterHealth() throws UnknownHostException{
		ClusterHealthResponse healths = es.esclient().admin().cluster().prepareHealth().get();
		return healths.getActiveShards();
		
	}
}
