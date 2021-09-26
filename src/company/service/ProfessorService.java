package company.service;

import company.domain.Professor;
import company.repository.ProfessorRepository;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ProfessorService {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    ProfessorRepository professorRepository = new ProfessorRepository();

    public List<Professor> carregaProfessores() {
        return professorRepository.carregaProfessores();
    }

    public Professor cadastraProfessor() throws IOException {
        Scanner sc = new Scanner(System.in);
        
        Professor professor = new Professor();

        System.out.print("Digite o nome do professor: ");
        professor.setNome(in.readLine());

        professorRepository.insereProfessor(professor);

        return professor;
    }

    public void printProfessor(List<Professor> professores) {
        System.out.println("\n******* Professores Cadastrados *******");
        for (Professor professor : professores) {
            System.out.println(professor.getId() + ") Nome: " + 
            professor.getNome());
        }
    }

    public Professor retornaProfessor(List<Professor> professores, int id) {
        for (Professor p : professores) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public Professor findProfessorById(Integer id) {
        return professorRepository.findProfessorById(id);
    }

    public void updateProfessor(Professor professor) throws SQLException, IOException {
        System.out.print("Informe o novo nome para o professor: ");
        professor.setNome(in.readLine());

        professorRepository.updateProfessor(professor);
    }

    public void deleteProfessor(Integer id) {
        professorRepository.deleteProfessor(id);
    }
}
