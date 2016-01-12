package xianjue.gqx.dao;

import java.util.List;

import xianjue.gqx.po.WaterValve;

public interface WaterValveDao {

	List<WaterValve> getByGid(int gid);

	int add(WaterValve waterValve);

	int getBiggestGorderByGid(int gid);

	List<WaterValve> getAll();

	void update(WaterValve waterValve);

	WaterValve getById(int id);

	void delete(List<Integer> ids);

}
