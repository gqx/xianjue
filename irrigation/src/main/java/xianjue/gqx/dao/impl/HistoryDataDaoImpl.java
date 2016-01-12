package xianjue.gqx.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import xianjue.gqx.dao.HistoryDataDao;
import xianjue.gqx.po.Historydata;

@Component("historyDataDao")
public class HistoryDataDaoImpl extends BaseDaoImpl implements HistoryDataDao{

	@Override
	public int createHistorydata(Integer gid,Integer illumination,Integer temperature,Integer humidity){
		Session session = getCurrentSession();
		
		Historydata hd = new Historydata();
		hd.setGid(gid);
		hd.setIllumination(illumination);
		hd.setTemperature(temperature);
		hd.setHumidity(humidity);
		
//		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date=dateformat1.format(new Date());
//		hd.setUpdateTime(date);	
		
		session.save(hd);
		return hd.getId();
	}
	
	@Override
	public List<Historydata> getTodayByGid(Integer gid){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Historydata where gid=? and DATEDIFF(update_time,NOW()) =0");
		query.setInteger(0, gid);
		List<Historydata> list = query.list();
		return list;
	}

	@Override
	public List<Historydata> getByDateAndGid(Date date, Integer gid) {
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Historydata where gid=? and DATEDIFF(update_time,?) =0");
		query.setInteger(0, gid);
		query.setDate(1, date);
		List<Historydata> list = query.list();
		return list;
	}
	
}
