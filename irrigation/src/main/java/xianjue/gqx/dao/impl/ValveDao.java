package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.po.Valve;

@Component("valveDao")
public class ValveDao extends BaseDaoImpl{
	
	public int createValve(Valve valve){
		Session session = getCurrentSession();
		session.save(valve);
		return valve.getId();
	}
	
	public void updateValve(Valve valve){
		Session session = getCurrentSession();
		session.update(valve);
	}
	
	@SuppressWarnings("unchecked")
	public List<Valve> getValveByGroupId(String groupId){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Valve where group_id=?");
		query.setString(0, groupId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Valve> getValveByZigbeeMac(String zmac){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Valve where zmac=?");
		query.setString(0, zmac);
		return query.list();
	}
	
	public void changeValveByZigbeeMac(String oldZmac, String newZmac){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("update Valve set zmac=? where zmac=?");
		query.setString(0, newZmac);
		query.setString(1, oldZmac);
		
	}
	
	public void deleteByZigbeeMac(String zigbeeMac){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("delete from Valve where zmac=?");
		query.setString(0, zigbeeMac);
		query.executeUpdate();
	}
}
