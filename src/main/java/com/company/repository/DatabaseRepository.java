package com.company.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseRepository {

  public Connection connect() {
    Connection conn = null;

    try {
      String url = "jdbc:sqlite:/home/andreruxx/Desktop/sistema-academia/src/database";

      conn = DriverManager.getConnection(url);

      System.out.println("[*] Connection success.");
    } catch (Exception e) {
      try {
        String url = "jdbc:sqlite:/src/database/database";

      conn = DriverManager.getConnection(url);

      System.out.println("[*] Connection success.");
      } catch (Exception exception) {
        System.out.println("Second Exception: " + e.getMessage());
      }
      System.out.println("First Exception: " +e.getMessage());
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
