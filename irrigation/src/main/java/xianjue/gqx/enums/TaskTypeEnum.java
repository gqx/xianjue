package xianjue.gqx.enums;

public enum TaskTypeEnum {

	CHECK_STATUS("CHECK_STATUS","���ʪ��״̬����"),
	INIT_IRRIGATION("INIT_IRRIGATION","��ʼ���ֹ�"),
	RUN_IRRIGATION("RUN_IRRIGATION","ִ���ֹ�"),
	FINISH_IRRIGATION("FINISH_IRRIGATION","��ֹ�ֹ�");
	
	private String code;
	private String desc;
	
	TaskTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public TaskTypeEnum getEnum(String code){
		for(TaskTypeEnum error : TaskTypeEnum.values()){
			if(error.code.equals(code)){
				return error;
			}
		}
		return null;
	}
}
