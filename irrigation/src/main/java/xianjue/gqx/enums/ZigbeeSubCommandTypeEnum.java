package xianjue.gqx.enums;

/**
 * 与zigbee通信的子命令
 * @author gqx
 *
 */
public enum ZigbeeSubCommandTypeEnum {
	VATER_VALVE_START((byte)0x00,"水阀启动"),
	PUMP_START((byte)0x01,"水泵启动"),
	PRESSURE_DATA((byte)0x02,"压力值上传");
	
	private byte code;
	private String desc;
	
	ZigbeeSubCommandTypeEnum(byte code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public byte getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static ZigbeeSubCommandTypeEnum getEnum(byte code){
		for(ZigbeeSubCommandTypeEnum cmd:ZigbeeSubCommandTypeEnum.values()){
			if(cmd.getCode() == code){
				return cmd;
			}
		}
		return null;
	}
}
