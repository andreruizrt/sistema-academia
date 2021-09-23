package com.company.repository;

import com.company.domain.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {

    DatabaseRepository dbRepository = new DatabaseRepository();

    public List<Professor> carregaProfessores() {
        List<Professor> professores = new ArrayList<Professor>();
        String sql = "SELECT ID, NOME FROM PROFESSOR";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("ID"));
                professor.setNome(rs.getString("NOME"));

                professores.add(professor);

                // Print which student
//                System.out.println(rs.getInt("ID") + "\t" + professor.getNome());
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return (professores);
    }

    public void inserirProfessor(Professor professor) {
        String sql = "INSERT INTO PROFESSOR(NOME) VALUES(?)";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professor.getNome());
            pstmt.executeUpdate();

            System.out.println("[*] Success to save the teacher.");

            dbRepository.disconnect(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
