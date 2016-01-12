package xianjue.gqx.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import xianjue.gqx.dao.ScheduleDao;
import xianjue.gqx.po.Schedule;

@Component("scheduleDao")
public class ScheduleDaoImpl extends BaseDaoImpl implements ScheduleDao{

	@Override
	public void add(Schedule schedule) {
		Session session = getCurrentSession();
		session.save(schedule);	
	}
	
	@Override
	public List<Schedule> getAll(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Schedule");
		List<Schedule> list = query.list();
		return list;
	}
	@Override
	public void update(Schedule schedule){
		Session session = getCurrentSession();
		session.update(schedule);
	}
	
	@Override
	public Schedule getById(int id){
		Session session = getCurrentSession();
		return (Schedule) session.get(Schedule.class, id);
	}
	@Override
	public void delete(List<Integer> ids){
		Session session = getCurrentSession();
		StringBuilder sb = new StringBuilder("delete from schedule where id in(");
		for(int id : ids){
			sb.append(id);
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");
		
		String sql = sb.toString();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Schedule> getByGidAndType(Integer gid, String type) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from Schedule where gid=? and stype=?");
		query.setInteger(0, gid);
		query.setString(1, type);
		return query.list();
	}

	@Override
	public void delete(int id) {
		Session session = getCurrentSession();
		Query query = session.createSQLQuery("delete from schedule where id=?");
		query.setInteger(0, id);
		query.executeUpdate();
	}

	@Override
	public void delete(Schedule schedule) {
		Session session = getCurrentSession();
		session.delete(schedule);
	}

}
