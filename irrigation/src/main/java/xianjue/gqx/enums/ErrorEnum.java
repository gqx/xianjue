package xianjue.gqx.enums;

public enum ErrorEnum {
	GPRS_EXISTS("GPRS_EXISTS","gprs mac �Ѿ�����"),
	GPRS_NOT_EXISTS("GPRS_NOT_EXISTS","gpr mac �����ڣ����������趨��"),
	ZIGBEE_EXISTS("ZIGBEE_EXISTS","zigbee mac �Ѿ�����"),
	DB_ERROR("DB_ERROR","���ݿ����"),
	ZIGBEE_NOT_EXISTS("ZIGBEE_NOT_EXISTS","zigbee ������, ���������趨��"),
	ZIGBEE_TYPE_WRONG("ZIGBEE_TYPE_WRONG","zigbee ���ʹ���"),
	ZIGBEE_MAC_WRONG("ZIGBEE_MAC_WRONG","zigbee mac ����"),
	
	NOT_ALL_TASK_STOP("NOT_ALL_TASK_STOP","������������ִ��,�޷�����������"),
	NO_TASK_CONFIG("NO_TASK","û����������");
	
	private String code;
	private String desc;
	
	ErrorEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public ErrorEnum getEnum(String code){
		for(ErrorEnum error : ErrorEnum.values()){
			if(error.code.equals(code)){
				return error;
			}
		}
		return null;
	}
	
}
