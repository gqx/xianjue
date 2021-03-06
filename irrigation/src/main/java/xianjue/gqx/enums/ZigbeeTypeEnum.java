package xianjue.gqx.enums;

/**
 * zigbee类型
 * @author gqx
 *
 */
public enum ZigbeeTypeEnum {
	VATER_VALVE(0,"水阀"),
	PUMP(1,"水泵"),
	PRESSURE(2,"压力传感器");
	
	private int code;
	private String desc;
	
	ZigbeeTypeEnum(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static ZigbeeTypeEnum getEnum(int code){
		for(ZigbeeTypeEnum cmd:ZigbeeTypeEnum.values()){
			if(cmd.getCode() == code){
				return cmd;
			}
		}
		return null;
	}
}
