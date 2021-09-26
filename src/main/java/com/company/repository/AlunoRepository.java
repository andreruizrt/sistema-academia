package com.company.repository;

import com.company.domain.Aluno;
import com.company.service.ProfessorService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    DatabaseRepository dbRepository = new DatabaseRepository();
    ProfessorService professorService = new ProfessorService();

    public List<Aluno> carregaAlunos() {
        List<Aluno> alunosCursos = new ArrayList<Aluno>();
        String sql = "SELECT IDALUNO, NOMEALUNO, CPFALUNO, IDPROFESSOR FROM ALUNO_TR01";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("IDALUNO"));
                aluno.setNome(rs.getString("NOMEALUNO"));
                aluno.setCpf(rs.getString("CPFALUNO"));
                aluno.setProfessor(professorService.findProfessorById(rs.getInt("IDPROFESSOR")));

                alunosCursos.add(aluno);

                // Shows teacher
//                System.out.println(rs.getInt("IDALUNO") + ") Nome: " + aluno.getNome() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return (alunosCursos);
    }

    public void insertAluno(Aluno aluno) {

        String sql = "INSERT INTO ALUNO_TR01(NOMEALUNO, CPFALUNO, IDPROFESSOR) VALUES (?, ?, ?)";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getCpf());
            pstmt.setInt(3, aluno.getId());
            pstmt.executeUpdate();

            System.out.println("\n[*] Success saving the student.\n");
            dbRepository.disconnect(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Aluno findAlunoById(Integer id) {
        Aluno aluno = new Aluno();
        String sql = "SELECT IDALUNO, NOMEALUNO, CPFALUNO, IDPROFESSOR FROM ALUNO_TR01";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                aluno.setId(rs.getInt("IDALUNO"));

                if(aluno.getId() == id) {
                    aluno.setNome(rs.getString("NOMEALUNO"));
                    aluno.setCpf(rs.getString("CPFALUNO"));
                    return aluno;
                }

                // Shows teacher
//                System.out.println(rs.getInt("IDALUNO") + ") Nome: " + aluno.getDsAluno() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return null;
    }

    public void updateAluno(Aluno aluno) throws SQLException {

        PreparedStatement pstmt = null;
        DatabaseRepository dbRepository = new DatabaseRepository();
        Connection conn = dbRepository.connect();

        String sql = "UPDATE ALUNO_TR01 SET NOMEALUNO = ?, CPFALUNO = ? WHERE IDALUNO = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2,aluno.getCpf());
            pstmt.setInt(3, aluno.getId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    public void deleteAluno(Integer id) {
        String sql = "DELETE FROM ALUNO_TR01 WHERE IDALUNO = ?";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insereExercicioAluno(int idAluno, int idProfessor) {
        String sql = "INSERT INTO ALUNO_EXERCICIO_TR01(IDALUNO, IDEXERCICIO) VALUES (?, ?)";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAluno);
            pstmt.setInt(2, idProfessor);
            pstmt.executeUpdate();

            System.out.println("\n[*] Success saving the student.\n");
            dbRepository.disconnect(conn);

                // Shows teacher
//                System.out.println(rs.getInt("IDALUNO") + ") Nome: " + aluno.getDsAluno() + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
