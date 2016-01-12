package xianjue.gqx.exception;

import xianjue.gqx.enums.ErrorEnum;

public class GreenHouseException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private String errorDesc;

	public GreenHouseException(String errorCode,String errorDesc){
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public GreenHouseException(ErrorEnum error){
		this.errorCode = error.getCode();
		this.errorDesc = error.getDesc();
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	
}
