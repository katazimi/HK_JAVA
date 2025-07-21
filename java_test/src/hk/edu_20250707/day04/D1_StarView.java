package hk.edu_20250707.day04;

public class D1_StarView {

	public static void main(String[] args) {
		/*
		 	★
		 	★★
		 	★★★
		 	★★★★
		 	★★★★★
		 */

		for (int i=0; i<5; i++) {
			for (int j=0; j<i+1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println();

		/*
		 	☆☆☆☆★
		 	☆☆☆★★
		 	☆☆★★★
		 	☆★★★★
		 	★★★★★
		 */
		int num=11;
		for (int i=0; i<num; i++) {
			for (int j=0; j<num-1-i; j++) { //for (int j=5; j>i+1; j--) 이거도 가능
				System.out.print("☆");
			}
			for (int k=0; k<i+1; k++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println();

		/*
		 	☆☆☆☆★
		 	☆☆☆★★★
		 	☆☆★★★★★
		 	☆★★★★★★★
		 	★★★★★★★★★
		 */
		for (int i=0; i<num; i++) {
			for (int j=0; j<num-1-i; j++) {
				System.out.print("☆");
			}
			for (int k=0; k<2*i+1; k++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println();

		/*
		 	★★★★★★★★★
		 	☆★★★★★★★
		 	☆☆★★★★★
		 	☆☆☆★★★
		 	☆☆☆☆★
		 */
		for (int i=0; i<num; i++) {
			for (int j=0; j<i; j++) {
				System.out.print("☆");
			}
			for (int k=0; k<(num*2-1)-2*i; k++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println();

		/*
		 	★★★★★
		 	★★★★☆
		 	★★★☆☆
		 	★★☆☆☆
		 	★☆☆☆☆
		 */
		for (int i=0; i<num; i++) {
			for (int j=0; j<num-i; j++) {
				System.out.print("★");
			}
			for (int k=0; k<i; k++) {
				System.out.print("☆");
			}
			System.out.println();
		}
		System.out.println();
		/*
		 	★★★★★
		 	☆★★★★
		 	☆☆★★★
		 	☆☆☆★★
		 	☆☆☆☆★
		 */
		for (int i=0; i<num; i++) {
			for (int k=0; k<i; k++) {
				System.out.print("☆");
			}
			for (int j=0; j<num-i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println();

		/*
		 	☆☆☆☆★		0-1
		 	☆☆☆★★★		1-3
		 	☆☆★★★★★		2-5
		 	☆★★★★★★★	3-7
		 	☆☆★★★★★		4-5
		 	☆☆☆★★★  	5-3
		 	☆☆☆☆★   	6-1
		 */
		for (int i=0; i<num; i++) {
			if (i < (num+1)/2) {
				for (int j=0; j<num/2-i; j++) {
					System.out.print("☆");
				}
				for (int k=0; k<2*i+1; k++) {
					System.out.print("★");
				}
				System.out.println();
			}
			else {
				for (int j=0; j<i-num/2; j++) {
					System.out.print("☆");
				}
				for (int k=0; k<(num*2-1)-2*i; k++) {
					System.out.print("★");
				}
				System.out.println();

			}
		}
		System.out.println();

		//절대값 활용해서 마름모 구현
		int num2=9; //홀수만 가능
		for (int i=0; i<num2; i++) {
			for (int j = 0; j < Math.abs(num2/2-i); j++) {
				System.out.print("☆");
			}
			for (int k = 0; k < num2-Math.abs(num2-1-i*2); k++) {
				System.out.print("★");
			}
			System.out.println();
		}



	}//main종료

}
