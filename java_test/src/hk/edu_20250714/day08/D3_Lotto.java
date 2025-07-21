package hk.edu_20250714.day08;

import java.util.Arrays;

public class D3_Lotto {
	public int[] lottoNum = new int[6];

	public D3_Lotto() {
		int key; //중복 확인용

		for (int i=0; i<6; i++) {
			while (true) {
				key=0;
				int num = (int)(Math.random()*45 + 1);
				for (int j=0; j<i; j++) {
					if (num == lottoNum[j]) {
						key=1;
						continue;
					}
				}
				if (key==1) { //중복이 확인되면 while 문 다시시작)
					continue;
				}
				lottoNum[i] = num;
				break;
			}
		}
		Arrays.sort(lottoNum); //숫자 정렬
	}
}
