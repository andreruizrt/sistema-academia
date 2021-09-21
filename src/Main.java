import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
        System.out.println("| 3 - Sair                          |");
    }

    private static void printaMenuFuncionario() {
        System.out.println("***** MENU DO FUNCIONÁRIO *****");
        System.out.println("| 1 - Cadastrar Professor           | 2 - Cadastrar Aluno");
        System.out.println("| 3 - Listar Professor              | 4 - Listar Aluno");
        System.out.println("| 5 - Cadastrar exercícios no aluno | 6 - Sair");
    }

    private static void MenuFuncionario() {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            printaMenuFuncionario();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("[*] cadastrar professor");
                    break;
                case 2:
                    System.out.println("[*] cadastrar aluno");
                    break;
                case 3:
                    System.out.println("[*] listar Professor");
                    break;
                case 4:
                    System.out.println("[*] listar aluno");
                    break;
                case 5:
                    System.out.println("[*] cadastrar exercícios no aluno");
                    break;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção errada!");
            }

        } while (option != 6);
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
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção errada!");
            }

        } while (option != 3);
    }

}
