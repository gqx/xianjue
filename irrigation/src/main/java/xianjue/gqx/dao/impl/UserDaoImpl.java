package xianjue.gqx.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import xianjue.gqx.dao.UserDao;
import xianjue.gqx.po.User;

@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
	
	@Override
	public void save(User user) {
		User u = new User();
		u.setName("test");
		u.setPassword("123");
		u.setUser_type("common");
		
//		Session session = sessionFactory.getCurrentSession();
		Session session = getCurrentSession();
		session.get(u.getClass(), 1);
		System.out.println("ok");
	}

	@Override
	public User getByNameAndPwd(String name, String pwd) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from User where name=? and password=?");
		query.setString(0, name);
		query.setString(1, pwd);
		return (User) query.uniqueResult();
	}
	
	

}
