package com.company.repository;

import com.company.domain.Exercicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ExercicioRepository {
    DatabaseRepository dbRepository = new DatabaseRepository();

    public List<Exercicio> carregaExercicios() {
        List<Exercicio> exercicios = new ArrayList<Exercicio>();
        String sql = "SELECT IDEXERCICIO, NOMEEXERCICIO, SERIESEXERCICIO FROM EXERCICIO_TR01";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Exercicio exercicio = new Exercicio();
                exercicio.setId(rs.getInt("IDEXERCICIO"));
                exercicio.setNome(rs.getString("NOMEEXERCICIO"));
                exercicio.setSeries(rs.getInt("SERIESEXERCICIO"));

                exercicios.add(exercicio);

                // Print which student
//                System.out.println(rs.getInt("IDEXERCICIO") + "\t" + exercicio.getNome() + "\t" + exercicio.getSeries());
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return (exercicios);
    }

    public void inserirExercicio(Exercicio exercicio) {
        String sql = "INSERT INTO EXERCICIO_TR01(NOMEEXERCICIO, SERIESEXERCICIO) VALUES(?,?)";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, exercicio.getNome());
            pstmt.setInt(2, exercicio.getSeries());
            pstmt.executeUpdate();

            System.out.println("[*] Success to save the exercise.");

            dbRepository.disconnect(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Exercicio findExercicioById(Integer id) {
        Exercicio exercicio = new Exercicio();
        String sql = "SELECT IDEXERCICIO, NOMEEXERCICIO, SERIESEXERCICIO FROM EXERCICIO_TR01";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                exercicio.setId(rs.getInt("IDEXERCICIO"));

                if (exercicio.getId() == id) {
                    exercicio.setNome(rs.getString("NOMEEXERCICIO"));
                    exercicio.setSeries(rs.getInt("SERIESEXERCICIO"));
                    return exercicio;
                }

                System.out.println(rs.getInt("IDEXERCICIO") + ") Nome: " + exercicio.getNome() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return null;
    }

    public List<Exercicio> retornaExerciciosAluno(int id) {
        List<Exercicio> exercicios = new ArrayList<Exercicio>();
        String sql = "SELECT IDALUNO, IDEXERCICIO FROM ALUNO_EXERCICIO_TR01";
        Exercicio exercicio = new Exercicio();

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getInt("IDALUNO") == id) {
                    exercicio = this.findExercicioById(rs.getInt("IDEXERCICIO"));
                    exercicios.add(exercicio);
                }
            }
            return (exercicios);

            // Print which student
//                System.out.println(rs.getInt("IDEXERCICIO") + "\t" + exercicio.getNome() + "\t" + exercicio.getSeries());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        }
    }

    public void deleteExercicio(int id) {
        String sql = "DELETE FROM EXERCICIO_TR01 WHERE IDEXERCICIO = ?";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateExercicio(Exercicio exercicio) throws SQLException {
        PreparedStatement pstmt = null;
        DatabaseRepository dbRepository = new DatabaseRepository();
        Connection conn = dbRepository.connect();

        String sql = "UPDATE EXERCICIO_TR01 SET NOMEEXERCICIO = ?, SERIESEXERCICIO = ? WHERE IDEXERCICIO = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, exercicio.getNome());
            pstmt.setInt(2, exercicio.getSeries());
            pstmt.setInt(3, exercicio.getId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
