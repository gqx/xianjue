package xianjue.gqx.listener.handler;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.GprsDao;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.protocol.cmd.receive.GprsStartCmd;
import xianjue.gqx.util.AttributeName;

@Component("gprsHandler")
public class GprsHandler {

	@Resource(name = "gprsDao")
	private GprsDao gprsDao;

	private Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 处理gprs启动
	 * @param cmd
	 * @param ip
	 * @return
	 */
	@Transactional
	public boolean start(GprsStartCmd cmd, String ip) {

		logger.info("start processing GprsStartCmd. gprs mac["+cmd.getGprsMacStr()+"]");
		
		Gprs gprs = gprsDao.getByMac(cmd.getGprsMacStr());
		//如果gprs已经存在，则更新
		if(gprs == null){
			logger.error("error processing GprsStartCmd, gprs must be configured firstly!");
			return false;
		}
		
		gprs.setIp(ip);
		gprs.setState(AttributeName.GPRS_CONNECTED);
		gprsDao.update(gprs);
		logger.info("finish processing GprsStartCmd");
		return true;
		
	}
	
	public Gprs getGprsByMac(String mac) {
		Gprs gprs = gprsDao.getByMac(mac);
		return gprs;
	}

}
