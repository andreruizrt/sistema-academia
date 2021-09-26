package com.company;

import com.company.domain.Aluno;
import com.company.domain.Exercicio;
import com.company.domain.Professor;

import com.company.service.AlunoService;
import com.company.service.ExercicioService;
import com.company.service.ProfessorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        do {
            System.out.println("1 - Aluno\n2 - Funcionário");
            System.out.print("3 - Sair\nDigite a opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    menuAluno();
                    break;
                case 2:
                    MenuFuncionario();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção errada!");
            }
        } while (opcao != 3);
    }

    private static void printaMenuAluno() {
        System.out.println("***** MENU DO ALUNO *****");
        System.out.println("| 1 - Informações Professor         | 2 - Lista de exercícios");
        System.out.println("| 3 - Voltar                        | 4 - Sair\n");
        System.out.print("Digite a opção: ");
    }

    private static void printaMenuFuncionario() {
        System.out.println("***** MENU DO FUNCIONÁRIO *****");
        System.out.println("| 1 - Cadastrar Professor           | 2 - Cadastrar Aluno");
        System.out.println("| 3 - Listar Professor              | 4 - Listar Aluno");
        System.out.println("| 5 - Cadastrar Exercício           | 6 - Cadastrar Exercício no Aluno");
        System.out.println("| 7 - Atualizar aluno               | 8 - Atualizar Professor");
        System.out.println("| 9 - Atualizar Exercício           | 10 - Deletar Aluno");
        System.out.println("| 11 - Deletar Professor            | 12 - Deletar Exercício");
        System.out.println("| 13 - Voltar                       | 14 - Sair");
        System.out.print("Digite a opção: ");
    }

    private static void MenuFuncionario() throws IOException, SQLException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int option = 0;

        ExercicioService exercicioService = new ExercicioService();
        ProfessorService professorService = new ProfessorService();
        AlunoService alunoService = new AlunoService();

        List<Exercicio> exercicios;
        List<Professor> professores;
        List<Aluno> alunos;

        Exercicio exercicio = new Exercicio();
        Professor professor = new Professor();
        Aluno aluno = new Aluno();

        exercicios = exercicioService.carregaExercicios();
        professores = professorService.carregaProfessores();
        alunos = alunoService.carregaAlunos();

        do {
            printaMenuFuncionario();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("[*] cadastrar professor");
                    professor = professorService.cadastraProfessor();
                    professores.add(professor);
                    break;
                case 2:
                    System.out.println("[*] cadastrar aluno");
                    System.out.print("Digite o id do professor: ");
                    int idProfessor = sc.nextInt();
                    professor = professorService.retornaProfessor(professores, idProfessor);

                    if( professor != null) {
                        aluno = alunoService.cadastraAluno(professor);
                        alunos.add(aluno);
                        System.out.println("Aluno cadastrado com sucesso!\n");
                    } else {
                        System.out.println("Professor não cadastrado.");
                        System.out.println("Não foi possível cadastrar o aluno.\n");
                    }
                    break;
                case 3:
                    System.out.println("[*] listar Professor");
                    professorService.printProfessor(professores);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("[*] listar aluno");
                    alunoService.printAluno(alunos);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("[*] cadastrar exercício");
                    exercicio = exercicioService.cadastraExercicio();
                    exercicios.add(exercicio);
                case 6:
                    System.out.println("[*] cadastrar exercícios no aluno");
                    System.out.print("Informe o nome do aluno: ");
                    String nomeAluno = in.readLine();

                    aluno = alunoService.retornaAluno(alunos, nomeAluno);

                    if(aluno != null) {
                        alunoService.insereExercicioAluno(aluno, exercicios);
                        System.out.println("Aluno cadastrado com sucesso!\n");
                    } else {
                        System.out.println("Aluno não cadastrado.");
                        System.out.println("Não foi possível cadastrar o exercicio.\n");
                    }
                    break;
                case 7:
                    System.out.println("[*] Atualizar aluno");
                    System.out.print("Digite o id do aluno: ");
                    int idAluno = sc.nextInt();

                    aluno = alunoService.findAlunoById(idAluno);

                    if(aluno != null) {
                        alunoService.updateAluno(aluno);
                    } else {
                        System.out.println("Aluno não encontrado!");
                    }
                    break;
                case 8:
                    System.out.println("[*] Atualizar professor");
                    System.out.print("Digite o id do professor: ");
                    idProfessor = sc.nextInt();
                    professor = professorService.findProfessorById(idProfessor);

                    if(professor != null) {
                        professorService.updateProfessor(professor);
                    } else {
                        System.out.println("professor não encontrado!");
                    }
                    break;
                case 9:
                    System.out.println("[*] Atualizar exercício");
                    System.out.print("Digite o id do exercicio: ");
                    int idExercicio = sc.nextInt();

                    exercicio = exercicioService.findExercicio(idExercicio);

                    if(exercicio != null) {
                        exercicioService.updateExercicio(exercicio);
                    } else {
                        System.out.println("Exercício não encontrado!");
                    }
                    break;
                case 10:
                    System.out.println("[*] Deletar aluno");
                    System.out.print("Digite o id do Aluno: ");
                    idAluno = sc.nextInt();

                    alunoService.deleteAluno(idAluno);
                    break;
                case 11:
                    System.out.println("[*] Deletar professor");
                    System.out.print("Digite o id do professor: ");
                    idProfessor = sc.nextInt();

                    professorService.deleteProfessor(idProfessor);
                    break;
                case 12:
                    System.out.println("[*] Deletar exercício");
                    System.out.print("Digite o id do exercicio: ");
                    idExercicio = sc.nextInt();

                    alunoService.deleteAluno(idExercicio);
                    break;
                case 13:
                    System.out.println("[*] Menu anterior...");
                    System.out.println("<-----------");
                    break;
                case 14:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção errada!");
            }

        } while (option != 13);
    }

    private static void menuAluno() {
        Scanner sc = new Scanner(System.in);

        ExercicioService exercicioService = new ExercicioService();
        ProfessorService professorService = new ProfessorService();
        AlunoService alunoService = new AlunoService();

        List<Professor> professores = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();

        Exercicio exercicio = new Exercicio();
        Professor professor = new Professor();
        Aluno aluno = new Aluno();

        professores = professorService.carregaProfessores();

        int option = 0;

        do {
            printaMenuAluno();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("[*] informações do professor");
                    System.out.print("Digite o id do professor: ");
                    int idProfessor = sc.nextInt();
                    Professor professor1 = professorService.retornaProfessor(professores, idProfessor);
                    System.out.println("Nome: " + professor1.getNome() + "\n");
                    break;
                case 2:
                    alunoService.printAluno(alunos);
                    System.out.println("Informe o id do aluno: ");
                    int id = sc.nextInt();
                    aluno = alunoService.findAlunoById(id);
                    System.out.println("Nome do aluno: " + aluno.getNome());
                    List<Exercicio> exerciciosAlunoList = exercicioService.retornaExerciciosAluno(id);
                    exercicioService.printExercicio(exerciciosAlunoList);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("[*] Menu anterior...");
                    System.out.println("<------------");
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção errada!");
            }

        } while (option != 3);
    }
}
