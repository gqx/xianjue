package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.po.PumpPressure;

@Component("pumpPressureDao")
@Scope("prototype")
public class PumpPressureDao extends BaseDaoImpl{
	
	public int createPump(PumpPressure pumpPressure){
		Session session = getCurrentSession();
		
		session.save(pumpPressure);
		return pumpPressure.getId();
	}
	
	public int update(PumpPressure pumpPressure){
		Session session = getCurrentSession();
		
		session.update(pumpPressure);
		return pumpPressure.getId();
	}

	public PumpPressure getById(int id) {
		
		Session session = getCurrentSession();
		
		return (PumpPressure) session.get(PumpPressure.class, id);
	}

	public List<PumpPressure> getAll() {
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from PumpPressure");
		@SuppressWarnings("unchecked")
		List<PumpPressure> list = query.list();
		
		
		return list;
	}
	
	public void deletePumpPressure(int id){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("delete from PumpPressure where id = ?");
		query.setInteger(0, id);
		query.executeUpdate();
	}
}
