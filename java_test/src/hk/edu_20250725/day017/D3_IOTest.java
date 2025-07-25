package hk.edu_20250725.day017;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class D3_IOTest {
	public static void main(String[] args) {
		test01();
	}
	
	//파일을 읽고 출력하기   //파일 경로: /Users/jihoonkang/Desktop/D1_StarView.java
	public static void test01() {
		InputStream in = null; //입력 파이프
		OutputStream out = null; //출력 파이프
		
		try {
			in = new FileInputStream("/Users/jihoonkang/Desktop/D1_StarView.java");
			out = new FileOutputStream("/Users/jihoonkang/Desktop/output_D1_StarView.java");
			int i=0; // byte단위로 읽어서 데이터를 저장할 변수
			while ((i=in.read())!=-1) { //읽어들일 데이터가 없다면 -1을 리턴한다.
				System.out.print((char)i);
				out.write(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//마지막에 실행된 스트림부터 닫아야함.
				if (out!=null) {
					out.close();
				}
				if (in!=null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
