package company.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseRepository {

  public Connection connect() {
    Connection conn = null;

    try {
      String url = "jdbc:sqlite:/home/andreruxx/Desktop/ES/sistema-academia/src/database/bancotest";

      conn = DriverManager.getConnection(url);

      System.out.println("[*] Connection success.");
    } catch (SQLException e) {
      System.out.println("First Exception: " + e.getMessage());
    }

    return conn;
  }

  public int disconnect(Connection conn) {
    try {
      conn.close();
      System.out.println("[*] Closing connection.");

      return 0;

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return 1;
    }
  }
}
