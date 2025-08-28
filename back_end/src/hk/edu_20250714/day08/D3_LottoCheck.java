package hk.edu_20250714.day08;

import java.util.Arrays;

public class D3_LottoCheck {

	public static D3_Lotto lottoNum = new D3_Lotto();
	public static int check = 0;


	public static void check(D3_Lotto a, D3_LottoStore b) {
		String str = "";

		for (int i=0; i<b.lottoStore.length; i++) {
			System.out.println(Arrays.toString(b.lottoStore[i].lottoNum));
		}
		System.out.println("------------------------------");
		System.out.println("당첨번호 맞추기");
		System.out.println("------------------------------");

		for (int i=0; i< b.lottoStore.length; i++) {
			str="";
			for (int j=0; j<6; j++) {
				if (a.lottoNum[j] == b.lottoStore[i].lottoNum[j]) {
					check++;
					str = str +  a.lottoNum[j] + " ";
				}
			}
			if (check == 6) {
				System.out.println(Arrays.toString(b.lottoStore[i].lottoNum) + "\n" + str + "당첨번호 갯수: 6"+ "\n" +"1등");
			} else if (check == 5) {
				System.out.println(Arrays.toString(b.lottoStore[i].lottoNum) + "\n" + str + "당첨번호 갯수: 5"+ "\n" +"2등");
			} else if (check == 4) {
				System.out.println(Arrays.toString(b.lottoStore[i].lottoNum) + "\n" + str + "당첨번호 갯수: 4"+ "\n" +"3등");
			} else if (check == 3) {
				System.out.println(Arrays.toString(b.lottoStore[i].lottoNum) + "\n" + str + "당첨번호 갯수: 3"+ "\n" +"4등");
			} else if (check == 2) {
				System.out.println(Arrays.toString(b.lottoStore[i].lottoNum) + "\n" + str + "당첨번호 갯수: 2"+ "\n" +"5등");
			} else {
				System.out.println(Arrays.toString(b.lottoStore[i].lottoNum) + "\n" + str + "당첨번호 갯수: 1개이하"+ "\n" +"낙첨");
			}
			check = 0;
			System.out.println("------------------------------");
		}
	}

	public static void main(String[] args) {
		D3_LottoStore LS = new D3_LottoStore(5);
		System.out.println("------------------------------");
		System.out.println("당첨번호\n------------------------------\n"+Arrays.toString(lottoNum.lottoNum));
		System.out.println("------------------------------");
		System.out.println("사용자 구매번호");
		System.out.println("------------------------------");

		D3_LottoCheck.check(lottoNum, LS);
	}
}
