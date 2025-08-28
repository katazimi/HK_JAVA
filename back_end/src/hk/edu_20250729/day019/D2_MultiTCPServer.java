package hk.edu_20250729.day019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class D2_MultiTCPServer {
	
	public static void main(String[] args) {
		Socket clientSocket = null; //클라이언트 소켓
		ServerSocket serverSocket = null;
		
		//서버 소켓을 생성하자
		try {
			serverSocket = new ServerSocket(9595);
			System.out.println("Server is running~");
			while(true) {
				//클라이언트에서 요청이 오면 승인절차를 진행하고(accept 실행)
				//--> 클라이언트 소켓객체를 얻어옴
				clientSocket = serverSocket.accept();
				System.out.println("클라이언트 연결됨: "+clientSocket.getInetAddress().getHostName());
				new D2_MultiTCPServer().new ServerThread(clientSocket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (clientSocket != null)
					clientSocket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//내부클래스로 스레드 작성
	class ServerThread extends Thread {
		
		Socket clientSocket = null; //클라이언트 소켓
		
		public ServerThread() {}
		
		public ServerThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}
		
		@Override
		public void run() {
			PrintWriter out = null; //클라이언트로 출력할때 사용할 객체
			BufferedReader in = null; //서버로 읽어들일때 사용할 객체
			
			try {
				//스레드로 실행할 코드 -----
				//클라이언트 소켓으로 데이터 보낼때 사용할 outputStream 객체
				//true: autoflush 설정
				out = new PrintWriter(clientSocket.getOutputStream(),true);
				//클라이언트 소켓에서 전달된 데이터를 읽어들일 inputStream객체 생성
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				String inputLine;
				while((inputLine=in.readLine())!=null) {
					System.out.println("클라이언트로부터 전달받은 메세지: " + inputLine);
					out.println(inputLine);
				}
				//스레드로 실행할 코드 -----
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null)
						out.close();
					if (in != null)
						in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
