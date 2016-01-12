package xianjue.gqx.listener.handler;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.GprsDao;
import xianjue.gqx.dao.impl.ZigbeeDao;
import xianjue.gqx.po.Zigbee;
import xianjue.gqx.protocol.cmd.receive.ZigbeePumpStartCmd;
import xianjue.gqx.util.AttributeName;

@Component("zigbeePumpHandler")
public class ZigbeePumpHandler {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Resource(name = "gprsDao")
	private GprsDao gprsDao;
	
	@Resource(name = "zigbeeDao")
	private ZigbeeDao zigbeeDao;
	
	
	/**
	 * zigbee开启，更新zigbee状态
	 * @param cmd
	 * @param ip
	 * @return
	 */
	@Transactional
	public boolean start(ZigbeePumpStartCmd cmd, String ip) {
		logger.info("zigbee start: gmac["+cmd.getGprsMacStr()+"] zmac["+cmd.getZigbeeMacStr()+"]");
		Zigbee zigbee = zigbeeDao.getZigbeeByZigbeeMac(cmd.getZigbeeMacStr());
		if(zigbee != null){
			zigbee.setState(AttributeName.ZIGBEE_CONNECTED);
			zigbeeDao.updateZigbee(zigbee);
			return true;
		}else{
			logger.error("zigbee start error: 此zigbee没有配置");
			return false;
		}

	}
}
