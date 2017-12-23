package ren.shiwen.springbootes5.controller;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ren.shiwen.springbootes5.configuration.ElasticSearchConfiguration;

@RestController
public class EsConnectionTestController {

	@Resource
	private ElasticSearchConfiguration es;

	@RequestMapping("/testescon")
	public String GetActiveShards() throws UnknownHostException {
		ClusterHealthResponse healths = es.esclient().admin().cluster().prepareHealth().get();

		return String.valueOf(healths.getActiveShards());

	}

}
