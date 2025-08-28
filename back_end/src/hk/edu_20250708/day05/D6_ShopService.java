package hk.edu_20250708.day05;

public class D6_ShopService {

	private static D6_ShopService instance = new D6_ShopService();

	private D6_ShopService() {}

	public static D6_ShopService getInstance() {
		if (instance == null) {
			instance = new D6_ShopService();
		}
		return instance;
	}

	public static void main(String[] args) {
		D6_ShopService obj1 = D6_ShopService.getInstance();
		D6_ShopService obj2 = D6_ShopService.getInstance();

		if (obj1 == obj2) {
			System.out.println("같은 ShopService 객체입니다.");
		}
		else {
			System.out.println("다른 ShopService 객체입니다.");
		}
	}
}