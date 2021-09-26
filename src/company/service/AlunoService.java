package company.service;

import company.domain.Aluno;
import company.domain.Exercicio;
import company.domain.Professor;
import company.repository.AlunoRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoService {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    AlunoRepository alunoRepository = new AlunoRepository();
    
    public List<Aluno> carregaAlunos() {
        return alunoRepository.carregaAlunos();
    }
    
    public Aluno cadastraAluno(Professor professor) throws IOException {

        Scanner sc = new Scanner(System.in);

        Aluno aluno = new Aluno();

        System.out.print("Digite o nome do aluno: ");
        aluno.setNome(in.readLine());

        System.out.print("Informe o cpf do aluno: ");
        aluno.setCpf(in.readLine());

        aluno.setProfessor(professor);

        alunoRepository.insertAluno(aluno);;

        return aluno;
    }

    public void printAluno(List<Aluno> alunos) {
        System.out.println("\n******* Alunos Cadastrados *******");
        for (Aluno aluno : alunos) {
            System.out.println(aluno.getId() + ") Nome: " + 
            aluno.getNome() + " - CPF: " + aluno.getCpf() +
            " - Professor: " + aluno.getProfessor().getNome());
        }
    }

    public Aluno retornaAluno(List<Aluno> alunos, String nome) {

        for (Aluno a : alunos) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }

        return null;
    }

    public void insereExercicioAluno(Aluno aluno, List<Exercicio> exercicios) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ExercicioService exercicioService = new ExercicioService();

        List<Exercicio> exercicioList = new ArrayList<>();

        exercicioService.printExercicio(exercicios);
        System.out.print("\nInforme o nome do exercício: ");
        String nomeExercicio = in.readLine();

        Exercicio exercicio = exercicioService.retornaExercicio(exercicios, nomeExercicio);
        alunoRepository.insereExercicioAluno(aluno.getId(), exercicio.getId());

        if(exercicio != null) {
            exercicioList.add(exercicio);
            aluno.setExercicios(exercicioList);
        }
    }

    public Aluno findAlunoById(Integer id) {
        return alunoRepository.findAlunoById(id);
    }

    public void updateAluno(Aluno aluno) throws IOException, SQLException {

        System.out.print("Informe o novo nome para o aluno: ");
        aluno.setNome(in.readLine());

        System.out.print("Informe o CPF para o aluno: ");
        aluno.setCpf(in.readLine());

        alunoRepository.updateAluno(aluno);
    }

    public void deleteAluno(Integer id) {
        alunoRepository.deleteAluno(id);
    }
}
