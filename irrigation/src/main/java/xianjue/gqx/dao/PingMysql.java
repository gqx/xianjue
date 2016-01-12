package xianjue.gqx.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.BaseDaoImpl;

/**
 * Mysql will disconnect if there are no operations in 8 hours,
 * so do some query every 1 hour to keep the connection.
 */

@Transactional
@Component("pingMysql")
//@Scope("prototype")
public class PingMysql extends BaseDaoImpl implements Runnable{
	private Logger logger = Logger.getLogger(getClass());
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("ping mysql!");
		Session session = getCurrentSession();
		Query query = session.createSQLQuery("select * from Gprs limit 0,1");
		query.list();
		
		try {
			Thread.sleep(1000*60*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
