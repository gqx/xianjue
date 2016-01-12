package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.po.Zigbee;

@Component("zigbeeDao")
@Scope("prototype")
public class ZigbeeDao extends BaseDaoImpl{
	
	public int createZigbee(Zigbee zigbee){
		Session session = getCurrentSession();
		session.save(zigbee);
		return zigbee.getId();
	}
	
	public void updateZigbee(Zigbee zigbee){
		Session session = getCurrentSession();
		session.update(zigbee);
	}
	
	public void delateZigbee(String zigbeeMac){
		Session session = getCurrentSession();
		Query query = session.createQuery("delete from Zigbee where mac=?");
		query.setString(0, zigbeeMac);
		query.executeUpdate();
	}
	
	public Zigbee getZigbeeByZigbeeMac(String zigbeeMac){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Zigbee where mac=?");
		query.setString(0, zigbeeMac);
		@SuppressWarnings("unchecked")
		List<Zigbee> list = query.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Zigbee> getZigbeeByGprsMac(String gprsMac){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Zigbee where gmac=?");
		query.setString(0, gprsMac);
		@SuppressWarnings("unchecked")
		List<Zigbee> list = query.list();
		return list;
	}
}
