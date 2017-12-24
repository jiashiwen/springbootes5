package ren.shiwen.springbootes5.controller;

import static org.apache.commons.lang.StringUtils.split;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ren.shiwen.springbootes5.service.esadmin.EsClusterAdminService;

@RestController
@RequestMapping(value = "/esclusteradmin")
public class EsClusterAdminController {
	static final String COMMA = ",";
	@Resource
	private EsClusterAdminService esclusteradmin;

	@RequestMapping(value = "flush")
	public void FlushAll(@RequestParam(value = "source", required = false) String indices) throws UnknownHostException {
		if (indices == null) {
			esclusteradmin.RefreshAll();
		}else{
			esclusteradmin.RefreshIndecesByNames(split(indices, COMMA));
		
		}
	}

}
