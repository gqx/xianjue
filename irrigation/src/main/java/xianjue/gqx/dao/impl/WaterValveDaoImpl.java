package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import xianjue.gqx.dao.WaterValveDao;
import xianjue.gqx.po.WaterValve;

@Component("waterValveDao")
public class WaterValveDaoImpl extends BaseDaoImpl implements WaterValveDao{
	@Override
	public List<WaterValve> getByGid(int gid){
		Session session = getCurrentSession();
		Query query = session.createQuery("from WaterValve where gid=?");
		query.setInteger(0, gid);
		List<WaterValve> list = query.list();
		return list;
	}
	@Override
	public int add(WaterValve waterValve){
		Session session = getCurrentSession();
		session.save(waterValve);
		return waterValve.getId();
	}
	@Override
	public int getBiggestGorderByGid(int gid){
		Session session = getCurrentSession();
		Query query = session.createSQLQuery("select gorder from Water_valve where gid=? order by gorder desc");
		query.setInteger(0, gid);
		List<Integer> list = query.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return -1;
		}
	}
	@Override
	public List<WaterValve> getAll(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from WaterValve");
		List<WaterValve> list = query.list();
		return list;
	}
	@Override
	public void update(WaterValve waterValve){
		Session session = getCurrentSession();
		session.update(waterValve);
	}
	@Override
	public WaterValve getById(int id){
		Session session = getCurrentSession();
		return (WaterValve) session.get(WaterValve.class, id);
	}
	@Override
	public void delete(List<Integer> ids){
		Session session = getCurrentSession();
		StringBuilder sb = new StringBuilder("delete from water_valve where id in(");
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
}
