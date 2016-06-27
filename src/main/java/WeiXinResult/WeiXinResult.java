package WeiXinResult;

public class WeiXinResult {

	public String resultCode;
	
	public String message;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public WeiXinResult(String resultCode, String message){
		super();
		this.resultCode = resultCode;
		this.message = message;
	}
}
