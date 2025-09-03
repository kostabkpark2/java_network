package madang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Madang {

	public static void main(String[] args) throws SQLException {
		// 1. 관리자 : 새로운 책을 구매했을 때 구매한 책의 내역을 입력한다.
		// 책이름, 출판사, 가격
		// 2. 관리자 : 새로운 고객이 찾아왔을 때 고객 정보를 입력한다.
		// 고객명, 주소, 전화번호
		// 3. 관리자 : 기존 고객이 책을 구매했을 때 주문 정보를 입력한다.
		// 고객, 책, 판매가격, 판매일자
		// 4. MENU : 1 - 책 (등록, 조회, 수정--> 가격만 수정, 삭제 --> 주문내역이 존재하면 삭제할 수 없음)
		// 2 - 고객 (등록, 조회, 수정--> 주소, 전화번호만 수정, 삭제 --> 주문내역이 존재하면 삭제할 수 없음)
		// 3 - 주문 (등록, 조회, 수정 --> 판매가격만 수정, 삭제 --> 당일 등록한 자료만 삭제 가능, 과거의 자료는 삭제할 수 없음)
		// 0 - 종료
		Scanner in = new Scanner(System.in);
		Connection conn = MakeConnection.getConnection();

		String menu = "";
		while (true) {
			System.out.println("==================================");
			System.out.println("1. 도서 | 2. 고객 | 3. 주문 | 0. 종료");
			System.out.println("==================================");
			System.out.print("메뉴를 선택하세요 : ");
			menu = in.nextLine();
			if (menu.equals("0")) {
				break;
			}
			switch (menu) {
			case "1" -> bookManagement(in, conn);
			case "2" -> customerManagement(in, conn);
			case "3" -> orderManagement(in, conn);
			}
		}

		conn.close();
		System.out.println("프로그램 종료합니다.!");

	} // end of main

	// methods {}
	private static Object bookManagement(Scanner in, Connection conn) throws SQLException {
		String menu = "";
		while (true) {
			System.out.println("도서관리 메뉴입니다!");
			System.out.println("==============================================");
			System.out.println("1. 등록 | 2. 조회 | 3. 수정 | 4. 삭제 | 5. 이전으로");
			System.out.println("==============================================");
			System.out.print("menu > ");
			menu = in.nextLine();
			if (menu.equals("5")) {
				break;
			}
			switch (menu) {
			case "1" -> createBook();
			case "2" -> listBook(conn);
			case "3" -> updateBook();
			case "4" -> deleteBook();
			}
		}

		return null;
	}

	private static Object customerManagement(Scanner in, Connection conn) {
		System.out.println("고객관리 메뉴입니다!");
		return null;
	}

	private static Object orderManagement(Scanner in, Connection conn) {
		System.out.println("주문관리 메뉴입니다!");
		// TODO Auto-generated method stub
		return null;
	}

	private static void createBook() {
	}

	private static void listBook(Connection conn) throws SQLException {
		String sql = "select * from book";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print(rs.getString(2));
			System.out.print(rs.getString(3));
			System.out.println(rs.getInt(4));
		}
		stmt.close();
	}

	private static void updateBook() {
	}

	private static void deleteBook() {
	}

	// 화면 클리어 메서드
	public static void clearScreen() {
		try {
			// 파워쉘에서도 작동하는 방법
			if (System.getProperty("").toLowerCase().contains("windows")) {
				// 윈도우에서는 ProcessBuilder 사용
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				// 다른 OS에서는 ANSI 이스케이프 코드
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			// 실패하면 단순히 줄바꿈으로 대체
			for (int i = 0; i < 30; i++) {
				System.out.println();
			}
		}
	}

} // end of class
