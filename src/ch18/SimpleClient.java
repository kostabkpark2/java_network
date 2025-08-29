package ch18;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("172.30.1.56", 5000);
			OutputStream os = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject("hello");
			oos.flush();
			// oos.writeObject("안녕, 단순 서버야!");
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println("데이터를 보내는 중 오류 발생 !!!");
		}
	}

}
