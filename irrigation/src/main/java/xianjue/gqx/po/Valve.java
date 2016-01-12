package xianjue.gqx.po;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="valve")

public class Valve implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5773760490716691306L;
	public static final int OFF_STATE = 0;
	public static final int ON_STATE = 1;
	
	private Integer id;
	private String zmac;
	private Integer zorder;//在zigbee中的顺序
	private String name;
	private Integer state;//开关状态
	private String valve_type;//开关类型
	private String group_id;//轮灌组id
	private Timestamp update_time;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getZmac() {
		return zmac;
	}
	public void setZmac(String zmac) {
		this.zmac = zmac;
	}
	public Integer getZorder() {
		return zorder;
	}
	public void setZorder(Integer zorder) {
		this.zorder = zorder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getValve_type() {
		return valve_type;
	}
	public void setValve_type(String valve_type) {
		this.valve_type = valve_type;
	}
	
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}



}
