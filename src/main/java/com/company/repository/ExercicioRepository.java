package com.company.repository;

import com.company.domain.Exercicio;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExercicioRepository {
    DatabaseRepository dbRepository = new DatabaseRepository();

    public List<Exercicio> carregaExercicios() {
        List<Exercicio> exercicios = new ArrayList<Exercicio>();
        String sql = "SELECT ID, NOME, SERIES FROM EXERCICIO";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Exercicio exercicio = new Exercicio();
                exercicio.setNome(rs.getString("NOME"));
                exercicio.setSeries(rs.getInt("SERIES"));

                exercicios.add(exercicio);

                // Print which student
                System.out.println(rs.getInt("ID") + "\t" + exercicio.getNome() + "\t" + exercicio.getSeries());
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return (exercicios);
    }

    public void inserirExercicio(Exercicio exercicio) {
        String sql = "INSERT INTO EXERCICIO(NOME,SERIES) VALUES(?,?)";

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
}
