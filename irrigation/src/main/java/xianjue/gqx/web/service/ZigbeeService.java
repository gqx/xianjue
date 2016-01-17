package xianjue.gqx.web.service;

import java.util.List;

import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.po.Zigbee;

public interface ZigbeeService {
	
	public void createZigbee(String zigbeeMac,String zigbeeName,String gprsMac,int zigbeeType) throws GreenHouseException;
	public void deleteZigbeeByMac(String zigbeeMac)throws GreenHouseException;;
	public void updateZigbee(String oldMac, String newMac, String name)throws GreenHouseException;
	public List<Zigbee> getZigbeeByGprsAndType(String gprsMac,int zigbeeType);


}
