package hk.edu_20250730.day20;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class D1_UDPServer {

	public static void main(String[] args) {
		try(DatagramSocket socket = new DatagramSocket(5000)) {
			byte[] buffer = new byte[512];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			System.out.println("Server is listening on port 5000");
			while (true) {
				//클라이언트에서 전송된 데이터를 패킷으로 받는다.
				socket.receive(packet);
				//받은 패킷에서 데이터와 패킷 길이를 구해서 문자열로 변환
				String received = new String(packet.getData(),0,packet.getLength());
				System.out.println("받은 메세지: "+received);
				
				// 서버에서 받은 데이터를 클라이언트에게 다시 보내자~
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				System.out.println("address: "+address+", port: "+port);
				packet = new DatagramPacket(buffer, buffer.length, address, port);
				socket.send(packet); //서버에서 클라이언트로 데이터 보냄
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
