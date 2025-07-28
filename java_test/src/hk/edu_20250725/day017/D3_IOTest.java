package hk.edu_20250725.day017;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class D3_IOTest {
	public static void main(String[] args) {
		//test01();
		//test02();
		//test02_1();
		//test03();
		test04();
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
	
	//filter: 보조스트림을 이용해서 출력하기
	public static void test02() {
		String s = "파일을 기록합니다.";
		String ss = "파일을 문자단위로 기록합니다.";
		OutputStream out = null; //출력 파이프 준비
		OutputStreamWriter ow = null; //filter이며, 문자 인코딩 처리 기능
		BufferedWriter bw = null; //filter이며, 버퍼를 이용한 성능향상
		
		try {
			out = new FileOutputStream("/Users/jihoonkang/Desktop/output_data.java");
//			out.write(s.getBytes("utf-8"));
			
			//File 바이트 출력 스트림 --> 문자 기반 출력 필터
//			ow = new OutputStreamWriter(out,"utf-8");
//			ow.write(ss);
			
			//버퍼를 활용
			ow = new OutputStreamWriter(out, "utf-8");
			bw = new BufferedWriter(ow); //버퍼를 이용해서 성능향상(많은 양의 문자를 모아서 한번에 처리)
			bw.write(ss);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				
				if (ow!=null) 
					ow.close();
				
				if (out != null)
					out.close();
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//filter: 보조스트림을 이용해서 출력하기
	public static void test02_1() {
		String s = "파일을 기록합니다.";
		OutputStream out = null; //출력 파이프 준비
		DataOutputStream dos = null; //filter로 기본타입 데이터를 이진데이터로 출력
		
		try {
			out = new FileOutputStream("/Users/jihoonkang/Desktop/output_data.txt");
			dos = new DataOutputStream(out); //filter 사용
			dos.writeUTF(s); //UTF-8 형식으로 인코딩된 문자열을 출력
							 //문자열을 자동으로 byte 단위로 나눠서 처리
//			dos.writeChars(s);
			
			//filter 미사용
//			byte[] b = s.getBytes();
//			out.write(b);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
				if (out != null)
					out.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//한번에 읽을때 크기를 설정해서 읽고 쓰기
	public static void test03() {
		InputStream in = null;
		OutputStream out = null;
		
		try {
			in = new FileInputStream("/Users/jihoonkang/Desktop/1111.png");
			out = new FileOutputStream("/Users/jihoonkang/Desktop/1111_copy.png");
			
			//10바이트 단위로 읽기
			byte[] b = new byte[10];
			int i=0; //1byte씩 읽을때는 값이 저장-->배열로 바이트를 읽으면 읽은 갯수가 저장됨
			while ((i=in.read(b))!=-1) {
				//out.write(b); // [1,2,3,4,5,6,7,8,9,10]
							  // [11,12,13,14,5,6,7,8,9,10] -> 나머지 데이터 5~10이 같이 출력됨
				
				out.write(b,0,i); //b배열의 0번째부터 읽은 갯수만큼 출력함. --> 위의 방법보다 안정적 -> 이방법을 권장
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Reader와 Writer를 이용해서 키보드로 입력받고, 출력하는 기능
	public static void test04() {
		InputStreamReader in = null;
		OutputStreamWriter out = null;
		System.out.println("입력하세요.");
		
		try {
			in = new InputStreamReader(System.in);
			out = new OutputStreamWriter(System.out);
			
			char[] ch = new char[512];
			int i=0;
			while((i=in.read(ch))!=-1) {
				System.out.print("입력값: ");
				out.write(ch,0,i);
				out.flush(); //강제로 출력시키는 기능: System.out(콘솔로 출력) 다 채워질때까지 출력 X
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out!=null)
					out.close();
				if (in!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
