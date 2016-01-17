package xianjue.gqx.web.service;

import xianjue.gqx.exception.GreenHouseException;

public interface ZigbeeService {
	
	public void createZigbee(String zigbeeMac,String zigbeeName,String gprsMac,int zigbeeType) throws GreenHouseException;
	public void deleteZigbeeByMac(String zigbeeMac)throws GreenHouseException;;
	public void updateZigbee(String oldMac, String newMac, String name)throws GreenHouseException;

}
