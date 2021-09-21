package com.company;

import com.company.domain.Aluno;
import com.company.domain.Exercicio;
import com.company.domain.Professor;
import com.company.service.AlunoService;
import com.company.service.DatabaseService;
import com.company.service.ExercicioService;
import com.company.service.ProfessorService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        // Database setup
        DatabaseService databaseService = new DatabaseService();
        
        List<Aluno> alunos = databaseService.carregaAlunos();
        List<Professor> professores = databaseService.carregaProfessores();
        List<Exercicio> exercicios = databaseService.carregaExercicios();
        
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        do {
            System.out.println("\n1) Aluno\n2) Funcionário");
            System.out.print("3) Sair\n\nDigite a opção: ");
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
        System.out.println("\n\t\t***** MENU DO ALUNO *****");
        System.out.println("| 1 - Informações Professor         | 2 - Lista de exercícios");
        System.out.println("| 3 - Voltar                        | 4 - Sair\n");
        System.out.print("Digite a opção: ");
    }

    private static void printaMenuFuncionario() {
        System.out.println("\n\t\t***** MENU DO FUNCIONÁRIO *****");
        System.out.println("| 1 - Cadastrar Professor           | 2 - Cadastrar Aluno");
        System.out.println("| 3 - Cadastrar Exercício           | 4 - Listar Professor");
        System.out.println("| 5 - Listar Aluno                  | 6 - Cadastrar exercícios no aluno");
        System.out.println("| 7 - Voltar                        | 8 - Sair\n");
        System.out.print("Digite a opção: ");
    }
    
    private static void menuAluno() {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            printaMenuAluno();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("[*] informações do professor");
                    break;
                case 2:
                    System.out.println("[*] lista de exercícios");
                    break;
                case 3:
                    System.out.println("<-- Voltar");
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção errada!");
            }

        } while (option != 3);
    }
    
    private static void MenuFuncionario() throws IOException {
        Scanner sc = new Scanner(System.in);

        ProfessorService professorService = new ProfessorService();
        Professor professor = new Professor();

        AlunoService alunoService = new AlunoService();
        Aluno aluno = new Aluno();

        ExercicioService exercicioService = new ExercicioService();
        Exercicio exercicio = new Exercicio();


        int option = 0;
        
        do {
            printaMenuFuncionario();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("[*] cadastrar professor");
                    professorService.cadastraProfessor();
                    break;
                case 2:
                    System.out.println("[*] cadastrar aluno");
                    alunoService.cadastraAluno();
                    break;
                case 3:
                    System.out.println("[*] cadastrar exercício");
                    exercicioService.cadastraExercicio();
                    break;
                case 4:
                    System.out.println("[*] listar Professor");
                    break;
                case 5:
                    System.out.println("[*] listar aluno");
                    break;
                case 6:
                    System.out.println("[*] cadastrar exercícios no aluno");
                    break;
                case 7:
                    System.out.println("<-- Voltar");
                    break;
                case 8:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção errada!");
            }

        } while (option != 7);
    }

}
