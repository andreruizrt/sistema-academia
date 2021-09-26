package com.company.service;

import java.sql.Connection;

import com.company.repository.DatabaseRepository;
public class DatabaseService {
  DatabaseRepository databaseRepository = new DatabaseRepository();

  private Connection connect() {
    return databaseRepository.connect();
  }

  public int disconnect(Connection conn) {
    return databaseRepository.disconnect(conn);
  }
}
