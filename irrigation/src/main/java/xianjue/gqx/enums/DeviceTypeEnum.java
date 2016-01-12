package xianjue.gqx.enums;

/**
 * 设备类型枚举
 * @author gqx
 *
 */
public enum DeviceTypeEnum {
	
	LIGHT((byte)0x00,"LIGHT"),
	WATER_VALVE((byte)0x01,"WATER_VALVE"),
	ELECTRIC_MACHINE((byte)0x02,"ELECTRIC_MACHINE");
	
	private byte code;
	private String desc;
	
	DeviceTypeEnum(byte code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public byte getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static DeviceTypeEnum getEnum(byte code){
		for(DeviceTypeEnum e:DeviceTypeEnum.values()){
			if(e.getCode() == code){
				return e;
			}
		}
		return null;
	}
}
