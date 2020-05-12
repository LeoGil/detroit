package ads.pipoca.model.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//Conexao Gil 1
	public static Connection getConnection() throws IOException {
		try {
			return DriverManager .getConnection("jdbc:mysql://localhost:3306/pipocadb?"
					+ "useTimezone=true&serverTimezone=UTC&user=root&password=rootp@ssw0rd");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	//Conexao Gil 2
//		public static Connection getConnection() throws IOException {
//			try {
//				return DriverManager .getConnection("jdbc:mysql://localhost:3306/pipocadb?"
//						+ "useTimezone=true&serverTimezone=UTC&user=leonardo&password=root");
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new IOException(e);
//			}
//		}

}
