package xianjue.gqx.dao;

import java.util.Date;
import java.util.List;

import xianjue.gqx.po.Historydata;

public interface HistoryDataDao {
	public int createHistorydata(Integer gid,Integer illumination,Integer temperature,Integer humidity);
	public List<Historydata> getTodayByGid(Integer gid);
	public List<Historydata> getByDateAndGid(Date date,Integer gid);
}
