package company.service;

import company.repository.DatabaseRepository;

import java.sql.Connection;

public class DatabaseService {
  DatabaseRepository databaseRepository = new DatabaseRepository();

  private Connection connect() {
    return databaseRepository.connect();
  }

  public int disconnect(Connection conn) {
    return databaseRepository.disconnect(conn);
  }
}
