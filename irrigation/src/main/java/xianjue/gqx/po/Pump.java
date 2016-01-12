package xianjue.gqx.po;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pump")
public class Pump implements java.io.Serializable{

	private static final long serialVersionUID = -311910769228381720L;
	
	public static final int OFF_STATE = 0;
	public static final int ON_STATE = 1;
	
	private Integer id;
	private String zmac;
	private String name;
	private Integer state;
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

}
