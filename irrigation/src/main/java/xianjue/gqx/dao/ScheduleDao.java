package xianjue.gqx.dao;

import java.util.List;

import xianjue.gqx.po.Schedule;

public interface ScheduleDao {
	public void add(Schedule schedule);

	public List<Schedule> getAll();

	public void update(Schedule schedule);

	public Schedule getById(int id);

	public void delete(List<Integer> ids);
	public List<Schedule> getByGidAndType(Integer gid, String type);
	public void delete(int id);
	public void delete(Schedule schedule);
	
}
