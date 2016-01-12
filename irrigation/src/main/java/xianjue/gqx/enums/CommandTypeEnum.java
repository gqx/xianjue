package xianjue.gqx.enums;


/**
 * ��gprsͨ�ŵ�����
 * @author gqx
 *
 */
public enum CommandTypeEnum {
	// ����gprs����ʱ������
	GPRS_STRAT((byte) 0xD1,"GPRS_STRAT"),
	// ����zigbee���Ӷˣ�����ʱ������
	ZIGBEE_START((byte) 0xD2,"ZIGBEE_START"),
	// ����zigbee���ܶˣ��ϴ�������,������
	FROM_MAINZIGBEE((byte) 0xD3,"FROM_MAINZIGBEE"),
	// ����zigbee���Ӷˣ�ˮ��״̬���󱨾�����
	PRESSURE_ZIGBEE((byte) 0xD4,"PRESSURE_ZIGBEE"),
	// ������gprs���ſ�������
	TO_GPRS_CMD((byte) 0xD5,"TO_GPRS_CMD"),
	// zigbee����
	ZIGBEE_RESPONSE((byte) 0xD6,"ZIGBEE_RESPONSE");
	
	private byte code;
	private String desc;
	
	CommandTypeEnum(byte code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public byte getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static CommandTypeEnum getEnum(byte code){
		for(CommandTypeEnum cmd:CommandTypeEnum.values()){
			if(cmd.getCode() == code){
				return cmd;
			}
		}
		return null;
	}
}
