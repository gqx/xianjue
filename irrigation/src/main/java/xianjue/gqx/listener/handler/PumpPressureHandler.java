package xianjue.gqx.listener.handler;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.PumpPressureDao;
import xianjue.gqx.po.PumpPressure;
import xianjue.gqx.protocol.cmd.receive.PumpPressureCmd;

@Component("pumpPressureHandler")
public class PumpPressureHandler {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Resource(name="pumpPressureDao")
	private PumpPressureDao pumpPressureDao;
	
	@Transactional
	public void addPumpPressure(PumpPressureCmd cmd){
		PumpPressure pumpPressure = new PumpPressure();
		pumpPressure.setPressure(cmd.getPressure());
		pumpPressure.setGmac(cmd.getGprsMacStr());
		pumpPressureDao.createPump(pumpPressure);
		logger.info("addPumpPressure# pressure["+cmd.getPressure()+"] gmac["+cmd.getGprsMacStr()+"]");
	}
}
