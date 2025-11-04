package coding.test_20250929;

public class problem10 {

	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (i+j==10) {
					break;
				}
				System.out.print(i+j);
			}
			System.out.println();
		}

	}

}
