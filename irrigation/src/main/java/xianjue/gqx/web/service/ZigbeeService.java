package xianjue.gqx.web.service;

import xianjue.gqx.exception.GreenHouseException;

public interface ZigbeeService {
	
	public void createZigbee(String zigbeeMac,String zigbeeName,String gprsMac,int zigbeeType) throws GreenHouseException;
	public void updateZigbeeName(String zmac, String name) throws GreenHouseException;
	public void changeZigbeeMac(String oldMac,String newMac) throws GreenHouseException;
	public void deleteZigbeeByMac(String zigbeeMac);

}
