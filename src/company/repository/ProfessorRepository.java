package company.repository;

import company.domain.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {

    DatabaseRepository dbRepository = new DatabaseRepository();

    public List<Professor> carregaProfessores() {
        List<Professor> professores = new ArrayList<Professor>();
        String sql = "SELECT IDPROFESSOR, NOMEPROFESSOR FROM PROFESSOR;";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("IDPROFESSOR"));
                professor.setNome(rs.getString("NOMEPROFESSOR"));

                professores.add(professor);

                // Shows teacher
//                System.out.println(rs.getInt("IDPROFESSOR") + ") Nome: " + professor.getNome() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return (professores);
    }

    public void insereProfessor(Professor professor) {

        String sql = "INSERT INTO PROFESSOR(NOMEPROFESSOR) VALUES (?)";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professor.getNome());
            pstmt.executeUpdate();

            System.out.println("\n[*] Success saving the teacher.\n");
            dbRepository.disconnect(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Professor findProfessorById(Integer id) {
        Professor professor = new Professor();
        String sql = "SELECT IDPROFESSOR, NOMEPROFESSOR FROM PROFESSOR";

        try (Connection conn = dbRepository.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                professor.setId(rs.getInt("IDPROFESSOR"));

                if(professor.getId() == id) {
                    professor.setNome(rs.getString("NOMEPROFESSOR"));
                    return professor;
                }

//                 Shows teacher
                System.out.println(rs.getInt("IDPROFESSOR") + ") Nome: " + professor.getNome() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return null;
    }

    public void updateProfessor(Professor professor) throws SQLException {

        PreparedStatement pstmt = null;
        DatabaseRepository dbRepository = new DatabaseRepository();
        Connection conn = dbRepository.connect();

        String sql = "UPDATE PROFESSOR SET NOMEPROFESSOR = ? WHERE IDPROFESSOR = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, professor.getNome());
            pstmt.setInt(2, professor.getId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    public void deleteProfessor(Integer id) {
        String sql = "DELETE FROM PROFESSOR WHERE IDPROFESSOR = ?";

        try (Connection conn = dbRepository.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
