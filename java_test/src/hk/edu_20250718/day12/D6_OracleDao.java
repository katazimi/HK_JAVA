package hk.edu_20250718.day12;

public class D6_OracleDao implements D6_DatabaseAcessObject {

	@Override
	public void select() {
		System.out.println("MySql DB에서 검색");

	}

	@Override
	public void insert() {
		System.out.println("MySql DB에서 삽입");

	}

	@Override
	public void update() {
		System.out.println("MySql DB에서 수정");

	}

	@Override
	public void delete() {
		System.out.println("MySql DB에서 삭제");

	}

}
