package hk.edu_20250729.day019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class D2_TCPClient {

	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null; //서버에서 전달된 메세지를 읽어들일 객체
		BufferedReader userIn = null; //사용자가 키보드로 입력하는 메세지를 읽어들일 객체
		
		try {
			socket = new Socket("192.168.7.58",9595);
			//socket = new Socket("localhost",9595);
			System.out.println("Client:Connection to server...");
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			userIn=new BufferedReader(new InputStreamReader(System.in)); //키보드로 입력되는 내용을 읽어들이기
			
			String inputLine;
			while ((inputLine=userIn.readLine())!=null) {
				out.println(inputLine);
				System.out.println("서버에서 전달된 메세지: "+in.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out!=null)
					out.close();
				if (in!=null)
					in.close();
				if (userIn!=null)
					userIn.close();
				if (socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
