package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.po.Pump;

@Component("pumpDao")
@Scope("prototype")
public class PumpDao extends BaseDaoImpl{
	
	public int createPump(Pump pump){
		Session session = getCurrentSession();
		
		session.save(pump);
		return pump.getId();
	}
	
	public int update(Pump pump){
		Session session = getCurrentSession();
		
		session.update(pump);
		return pump.getId();
	}

	public Pump getById(int id) {
		
		Session session = getCurrentSession();
		
		return (Pump) session.get(Pump.class, id);
	}

	public List<Pump> getAll() {
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Pump");
		@SuppressWarnings("unchecked")
		List<Pump> list = query.list();
		
		
		return list;
	}
	
	public void deletePump(int id){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("delete from Pump where id = ?");
		query.setInteger(0, id);
		query.executeUpdate();
	}
}
