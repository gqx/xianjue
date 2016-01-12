package xianjue.gqx.listener.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.listener.GprsListener;
import xianjue.gqx.listener.service.GprsListenerService;



@Component("gprsListenerService")
@Scope("prototype")
public class GprsListenerServiceImpl implements GprsListenerService{
	
//	@Resource(name="pingMysql")
//	private Runnable pingMysql;
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void startGprsListener() {
		
		logger.info("starting gprs listener...");
		GprsListener listener = new GprsListener();
		Thread gprsThread = new Thread(listener);
		gprsThread.start();
		logger.info("gprs listener started!!!");
		
		
//		Thread pingMysqlThread = new Thread(pingMysql);
//		pingMysqlThread.start();
		
	}

}
