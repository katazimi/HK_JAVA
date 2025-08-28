package hk.edu_20250724.day016;

//내가 필요한 Exception 클래스를 정의하려면 Exception 클래스를 상속받아야함
public class D1_UserException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public D1_UserException() {
		this("UserException 오류입니다.");
	}
	
	public D1_UserException(String msg) {
		super(msg);
	}
}
