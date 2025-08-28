package hk.edu_20250718.day12;

public class D6_MySqlDao implements D6_DatabaseAcessObject {

	@Override
	public void select() {
		System.out.println("Oracle DB에서 검색");

	}

	@Override
	public void insert() {
		System.out.println("Oracle DB에서 삽입");

	}

	@Override
	public void update() {
		System.out.println("Oracle DB에서 수정");

	}

	@Override
	public void delete() {
		System.out.println("Oracle DB에서 삭제");

	}

}
