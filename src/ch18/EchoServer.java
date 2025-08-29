package ch18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket connection = null;
		BufferedReader in = null;

		try {
			server = new ServerSocket(5500);
			System.out.println("서버가 5500 번 포트에서 대기 중 ...");
			connection = server.accept();
			System.out.println("메시지 기다리는 중 ...");

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String message = "";

			while ((message = in.readLine()) != null) {
				if (message.contains("끝")) {
					System.out.println("서버 종료");
					in.close();
					connection.close();
					server.close();
					break;
				}
				System.out.println("읽은 메시지 : " + message);
			}
		} catch (Exception e) {
			System.out.println("소켓 연결 및 메시지 수신 중 오류 발생");
		}
	}

}
