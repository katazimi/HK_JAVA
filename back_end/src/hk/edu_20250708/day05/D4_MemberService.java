package hk.edu_20250708.day05;

public class D4_MemberService {
	D4_Member member = new D4_Member();

	public boolean login(String id, String password) {
		if (member.getID() == id && member.getPassword() == password) {
			return true;
		} else {
			return false;
		}
	}

	public void logout(String id) {
		System.out.printf("%s 님이 로그아웃 되었습니다.",member.getID());
	}
}
