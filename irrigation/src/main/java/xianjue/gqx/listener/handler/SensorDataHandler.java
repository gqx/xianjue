package xianjue.gqx.listener.handler;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.HistoryDataDao;
import xianjue.gqx.dao.impl.GprsDao;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.protocol.cmd.receive.SensorCmd;

@Component("sensorDataHandler")
public class SensorDataHandler {

	@Resource(name="historyDataDao")
	private HistoryDataDao historyDataDao;
	@Resource(name = "gprsDao")
	private GprsDao gprsDao;
	private Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 处理gprs发来的传感器数据
	 * @param cmd
	 * @param ip
	 * @return
	 */
	@Transactional
	public boolean update(SensorCmd cmd,String ip) {

		String macStr = cmd.getGprsMacStr();
		
		logger.info("start processing SensorCmd. gprs mac["+macStr+"] illumination["+cmd.getIllumination()+
				"] humidity["+cmd.getHumidity()+"] temperature["+cmd.getTemperature()+"]");
		
		Gprs gprs = gprsDao.getByMac(macStr);
		int gid = gprs.getId();

		if (gid < 0) {
			logger.info("SensorCmd failed:cannot find gprs.");
			return false;
		} 
		
		gprs.setIllumination(cmd.getIllumination());
		gprs.setHumidity(cmd.getHumidity());
		gprs.setTemperature(cmd.getTemperature());
		gprs.setIp(ip);

		gprsDao.update(gprs);
		historyDataDao.createHistorydata(gid,cmd.getIllumination(), cmd.getTemperature(), cmd.getHumidity());
		
		logger.info("finish processing SensorCmd");
		return true;
	}

}
