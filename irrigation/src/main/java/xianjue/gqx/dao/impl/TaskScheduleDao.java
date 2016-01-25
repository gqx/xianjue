package xianjue.gqx.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.po.TaskSchedule;

@Component("taskScheduleDao")
@Scope("prototype")
public class TaskScheduleDao extends BaseDaoImpl{
	
	public int create(TaskSchedule taskSchedule){
		Session session = getCurrentSession();
		
		session.save(taskSchedule);
		return taskSchedule.getId();
	}
	
	public int update(TaskSchedule taskSchedule){
		Session session = getCurrentSession();
		
		session.update(taskSchedule);
		return taskSchedule.getId();
	}

	public TaskSchedule getById(int id) {
		
		Session session = getCurrentSession();
		
		return (TaskSchedule) session.get(TaskSchedule.class, id);
	}

	public List<TaskSchedule> getAll() {
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from TaskSchedule");
		@SuppressWarnings("unchecked")
		List<TaskSchedule> list = query.list();
		
		
		return list;
	}
	
	public void delete(int id){
		Session session = getCurrentSession();
		
		Query query = session.createQuery("delete from TaskSchedule where id = ?");
		query.setInteger(0, id);
		query.executeUpdate();
	}
	
	public List<TaskSchedule> getByTaskType(String taskType) {
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from TaskSchedule where task_type = ?");
		query.setString(0, taskType);
		@SuppressWarnings("unchecked")
		List<TaskSchedule> list = query.list();
		
		return list;
	}
}
