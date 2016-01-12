package xianjue.gqx.dao;

import xianjue.gqx.po.User;

public interface UserDao {
	
	public void save(User user);
	public User getByNameAndPwd(String name, String pwd);
}
