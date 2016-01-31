package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.po.Gprs;
import xianjue.gqx.util.AttributeName;

@Component("gprsDao")
public class GprsDao extends BaseDaoImpl{
	
	public int createGprs(String mac,String name,String ip,Integer illumination,Integer temperature,Integer humidity){
		Session session = getCurrentSession();
		
		Gprs gprs = new Gprs();
		gprs.setName(name);
		gprs.setMac(mac);
		gprs.setIp(ip);
		gprs.setIllumination(illumination);
		gprs.setTemperature(temperature);
		gprs.setHumidity(humidity);
		gprs.setState(AttributeName.GPRS_INIT);
		
		session.save(gprs);
		return gprs.getId();
	}
	
	public int createGprs(Gprs gprs){
		Session session = getCurrentSession();
		
		session.save(gprs);
		return gprs.getId();
	}
	
	public Gprs getByMac(String mac){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Gprs where mac=?");
		query.setString(0, mac);
		List<?> list = query.list();
		Gprs gprs = null;
		if(list != null && list.size() != 0){
			gprs = (Gprs)list.get(0);
		}
		return gprs;
	}
	
	public int update(Gprs gprs){
		Session session = getCurrentSession();
		
		session.update(gprs);
		return gprs.getId();
	}

	public Gprs getById(int id) {
		
		Session session = getCurrentSession();
		
		return (Gprs) session.get(Gprs.class, id);
	}

	public List<Gprs> getAll() {
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Gprs");
		@SuppressWarnings("unchecked")
		List<Gprs> list = query.list();
		
		
		return list;
	}
	
	public void deleteGprs(String gprsMac){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("delete from Gprs where mac = ?");
		query.setString(0, gprsMac);
		query.executeUpdate();
	}
}
