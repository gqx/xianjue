package xianjue.gqx.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.PingMySQLDao;

@Component("pingMySQLDao")
public class PingMySQLDaoImpl extends BaseDaoImpl implements PingMySQLDao {

	@Transactional
	@Override
	public void pingMySql() {
		Session session = getCurrentSession();
		Query query = session.createSQLQuery("select * from Gprs limit 0,1");
		query.list();
	}

}
