package xianjue.gqx.web.service;

import java.util.List;

import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.po.Gprs;

public interface GprsService {
	public void createGprs(String gprsMac,String gprsName) throws GreenHouseException;
	public void changeGprs(String oldMac,String newMac,String newName) throws GreenHouseException;
	public List<Gprs> getAllGprs();
	public void deleteGprs(String gprsMac)throws GreenHouseException;
}
