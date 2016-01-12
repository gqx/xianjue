package xianjue.gqx.po;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pump_pressure")
public class PumpPressure implements java.io.Serializable{

	private static final long serialVersionUID = -7743893495662709633L;
	public static final int OFF_STATE = 0;
	public static final int ON_STATE = 1;
	
	private Integer id;
	private String gmac;
	private Integer pressure;
	private Timestamp update_time;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getGmac() {
		return gmac;
	}
	public void setGmac(String gmac) {
		this.gmac = gmac;
	}
	public Integer getPressure() {
		return pressure;
	}
	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

}
