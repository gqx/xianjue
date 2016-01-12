package xianjue.gqx.listener.handler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.WaterValveDao;
import xianjue.gqx.dao.impl.GprsDao;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.po.WaterValve;
import xianjue.gqx.protocol.cmd.receive.ZigbeeResponseWaterValveCmd;
import xianjue.gqx.util.AttributeName;

@Component("waterValveHandler")
public class WaterValveHandler {

	@Resource(name="waterValveDao")
	private WaterValveDao waterValveDao;
	
	@Resource(name="gprsDao")
	private GprsDao gprsDao;
	
	private Logger logger = Logger.getLogger(getClass());
	
	
	@Transactional
	public void updateWaterValve(ZigbeeResponseWaterValveCmd cmd){
	
		String macStr = cmd.getGprsMacStr();
		
		Gprs gprs = gprsDao.getByMac(macStr);
		if(gprs != null){
			int gid = gprs.getId();
			
			List<WaterValve> wvList = waterValveDao.getByGid(gid);
			for(WaterValve wv:wvList){
				int state = cmd.getState(wv.getGorder());
				wv.setState(state != 0?AttributeName.DEVICE_ON:AttributeName.DEVICE_OFF);
				waterValveDao.update(wv);
				logger.info("update light id:"+wv.getId()+" state: "+state);
			}
			
		}		
	}
}
