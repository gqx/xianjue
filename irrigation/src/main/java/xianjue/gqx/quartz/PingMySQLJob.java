package xianjue.gqx.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xianjue.gqx.dao.PingMySQLDao;

public class PingMySQLJob {
	@Resource(name="pingMySQLDao")
	private PingMySQLDao pingMySQLDao;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void execute(){
		logger.info("ping my sql");
		pingMySQLDao.pingMySql();
	}
}
