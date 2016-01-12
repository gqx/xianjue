package xianjue.gqx.enums;

/**
 * ��zigbeeͨ�ŵ�������
 * @author gqx
 *
 */
public enum ZigbeeSubCommandTypeEnum {
	VATER_VALVE_START((byte)0x00,"ˮ������"),
	PUMP_START((byte)0x01,"ˮ������"),
	PRESSURE_DATA((byte)0x02,"ѹ��ֵ�ϴ�");
	
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
