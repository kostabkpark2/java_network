package ch18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDemo2 {

	public static void main(String[] args) throws SQLException {
		Connection conn = makeConnection();
		// 3. statement 객체 생성
		Statement stmt = conn.createStatement();

		String sql = "select name, phone from person";
		// 4. sql 문 실행
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
		}

		// 5. 연결 종료
		stmt.close();
		conn.close();

	}

	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul";
		Connection conn = null;

		// 1. 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("데이터베이스 연결중 ...");
			// 2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, "root", "1111");
			System.out.println("데이터베이스 연결 성공 ...");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다....");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패 ...");
		}

		return conn;
	}

}
