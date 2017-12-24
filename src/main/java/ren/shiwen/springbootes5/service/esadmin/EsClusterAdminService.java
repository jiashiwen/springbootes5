package ren.shiwen.springbootes5.service.esadmin;

import java.net.UnknownHostException;

public interface EsClusterAdminService {
	public void RefreshAll() throws UnknownHostException;
	public void RefreshIndecesByNames(String[] names) throws UnknownHostException;

}
