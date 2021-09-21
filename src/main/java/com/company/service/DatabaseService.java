package com.company.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.domain.Aluno;
import com.company.domain.Exercicio;
import com.company.domain.Professor;

public class DatabaseService {

  private Connection connect() {
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

  public List<Aluno> carregaAlunos() throws IOException {
    List<Aluno> alunos = new ArrayList<Aluno>();
    String sql = "SELECT ID, NOME, CPF FROM ALUNO";

    try (Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Aluno aluno = new Aluno();
        aluno.setNome(rs.getString("NOME"));
        aluno.setCpf(rs.getString("CPF"));

        alunos.add(aluno);

        // Show members
        System.out.println(rs.getInt("ID") + ") Nome: " + aluno.getNome() + "\nCPF: " + aluno.getCpf() + "\n");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return (alunos);
  }

  public List<Professor> carregaProfessores() throws IOException {
    List<Professor> professores = new ArrayList<Professor>();
    String sql = "SELECT ID, NOME FROM PROFESSOR";

    try (Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Professor professor = new Professor();
        professor.setNome(rs.getString("NOME"));

        professores.add(professor);

        // Shows teacher
        System.out.println(rs.getInt("ID") + ") Nome: " + professor.getNome() + "\n");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return (professores);
  }

  public List<Exercicio> carregaExercicios() throws IOException {
    List<Exercicio> exercicios = new ArrayList<Exercicio>();
    String sql = "SELECT ID, NOME, SERIES FROM EXERCICIO";

    try (Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(rs.getString("NOME"));
        exercicio.setSeries(rs.getInt("SERIES"));

        exercicios.add(exercicio);

        // Shows exercises
        System.out
            .println(rs.getInt("ID") + ") Nome: " + exercicio.getNome() + "\nSeries:" + exercicio.getSeries() + "\n");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return (exercicios);
  }

  //TODO: Reload data from the database to the array.
  public void recarregaDados() {}

  public void inserirAluno(String nome, String cpf) {
    String sql = "INSERT INTO ALUNO(NOME,CPF) VALUES(?,?)";

    try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nome);
      pstmt.setString(2, cpf);
      pstmt.executeUpdate();
      System.out.println("\n[*] Success to save the member.\n");
      this.disconnect(conn);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void inserirProfessor(String nome) {
    String sql = "INSERT INTO PROFESSOR(NOME) VALUES(?)";

    try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nome);
      pstmt.executeUpdate();

      System.out.println("\n[*] Success to save the teacher.\n");

      this.disconnect(conn);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void inserirExercicio(String nome, int series) {
    String sql = "INSERT INTO EXERCICIO(NOME,SERIES) VALUES(?,?)";

    try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nome);
      pstmt.setInt(2, series);
      pstmt.executeUpdate();

      System.out.println("\n[*] Success to save the exercise.\n");

      this.disconnect(conn);

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
