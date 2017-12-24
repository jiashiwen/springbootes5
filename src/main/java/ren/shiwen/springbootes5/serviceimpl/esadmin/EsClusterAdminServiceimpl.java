package ren.shiwen.springbootes5.serviceimpl.esadmin;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ren.shiwen.springbootes5.configuration.ElasticSearchConfiguration;
import ren.shiwen.springbootes5.service.esadmin.EsClusterAdminService;

@Service
public class EsClusterAdminServiceimpl implements EsClusterAdminService {

	@Resource
	private ElasticSearchConfiguration es;

	@Override
	public void RefreshAll() throws UnknownHostException {
		es.esclient().admin().indices().prepareRefresh().get();

	}

	@Override
	public void RefreshIndecesByNames(String[] names) throws UnknownHostException {
		es.esclient().admin().indices().prepareRefresh(names).get();

	}

}
