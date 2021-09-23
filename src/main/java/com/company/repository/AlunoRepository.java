package com.company.repository;

import com.company.domain.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    DatabaseRepository dbRepository = new DatabaseRepository();

    public List<Aluno> carregaAlunos() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        String sql = "SELECT ID, NOME, CPF FROM ALUNO";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("NOME"));
                aluno.setCpf(rs.getString("CPF"));

                alunos.add(aluno);

                // Print which student
                System.out.println(rs.getInt("ID") + "\t" + aluno.getNome() + "\t" + aluno.getCpf());
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return (alunos);
    }

    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO ALUNO(NOME,CPF) VALUES(?,?)";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getCpf());
            pstmt.executeUpdate();
            System.out.println("[*] Success to save the student.");
            dbRepository.disconnect(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
