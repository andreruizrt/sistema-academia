package com.company.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseRepository {

  public Connection connect() {
    Connection conn = null;

    try {
      String url = "jdbc:sqlite:/home/andreruxx/Desktop/java-application/sistema-academia/src/database/database";

      conn = DriverManager.getConnection(url);

      System.out.println("[*] Connection success.");
    } catch (Exception e) {
      System.out.println(e.getMessage());
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
