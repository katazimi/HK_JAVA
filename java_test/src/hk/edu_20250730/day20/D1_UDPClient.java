package hk.edu_20250730.day20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class D1_UDPClient {
	public static void main(String[] args) {
		String hostname = "localhost";
		int port = 5000;
		
		try(DatagramSocket socket = new DatagramSocket()) {
			//hostname: hankyung.com --> getByName을 사용하면 ip주소를 담 InetAddress 객체를 반환
			InetAddress address = InetAddress.getByName(hostname);
			
			byte[] buffer = new byte[512];
			//키보드로 입력된 데이터를 읽어오기 위해 스트림 생성
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String text="";
			while(true) {
				System.out.print("Enter text: ");
				text = reader.readLine(); //키보드로 입력한 데이터 읽어오기
				buffer = text.getBytes(); //전송하기 위해 문자열을 byte단위로 변환
				//전송할 데이터를 패킷으로 생성
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address,port);
				socket.send(packet);
				
				//서버에서 전송된 데이터를 수신할 패킷 생성
				packet = new DatagramPacket(buffer, buffer.length); //받기 위한 패킷생성
				socket.receive(packet); //데이터 받고
				//받은 패킷에서 데이터 가져와서 문자열로 변환
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Received: " + received);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
