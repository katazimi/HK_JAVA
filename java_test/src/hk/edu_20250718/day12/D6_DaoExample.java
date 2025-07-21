package hk.edu_20250718.day12;

public class D6_DaoExample {

	public static void dbWork(D6_DatabaseAcessObject dao) {
		dao.select();
		dao.insert();
		dao.update();
		dao.delete();
	}

	public static void main(String[] args) {
		dbWork(new D6_OracleDao());
		dbWork(new D6_MySqlDao());
	}
}
