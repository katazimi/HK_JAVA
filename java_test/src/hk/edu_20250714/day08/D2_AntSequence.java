package hk.edu_20250714.day08;

import java.util.Scanner;

public class D2_AntSequence {
	public static int level;
	public static String start = "1";
	public static String s = "";
	public static int count = 1;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("레벨 입력 : ");
			level = sc.nextInt();
		}
		antPrint(level);
	}

	public static void antPrint(int num) {
		System.out.println(start);
		for (int i=1; i<level; i++) {
			D2_AntSequence.numberCount();
		}
	}

	public static void numberCount() {
		for (int i=0; i<start.length(); i++) {
			if (i+1 == start.length()) {
				s = s + start.substring(i,i+1) + count;
				count = 1;
			}
			else if (!start.substring(i,i+1).equals(start.substring(i+1, i+2))){
				s = s + start.substring(i,i+1) + count;
				count = 1;
			}
			else if (start.substring(i,i+1).equals(start.substring(i+1, i+2))) {
				count++;
			}
		}
		System.out.println(s);
		start = new String(s);
		s = "";
	}

}
