package com.company.service;

import com.company.domain.Exercicio;
import com.company.repository.ExercicioRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ExercicioService {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    ExercicioRepository exercicioRepository = new ExercicioRepository();
    Scanner sc = new Scanner(System.in);

    public List<Exercicio> carregaExercicios() {
        return exercicioRepository.carregaExercicios();
    }

    public Exercicio cadastraExercicio() throws IOException {
        Scanner sc = new Scanner(System.in);

        Exercicio exercicio = new Exercicio();

        System.out.print("Informe o nome do exercício: ");
        exercicio.setNome(in.readLine());

        System.out.print("Digite a quantidade de séries: ");
        exercicio.setSeries(sc.nextInt());

        exercicioRepository.inserirExercicio(exercicio);

        return exercicio;
    }

    public void printExercicio(List<Exercicio> exercicios) {
        System.out.println("\n******* Lista de Exercícios *******");
        for (Exercicio exercicio : exercicios) {
            System.out.println("Exercício: " + 
            exercicio.getNome() + " - número de séries: " 
            + exercicio.getSeries());
        }
    }

    public Exercicio retornaExercicio(List<Exercicio> exercicios, String nomeExercicio) {
        for ( Exercicio exercicio : exercicios) {
            if(exercicio.getNome().equalsIgnoreCase(nomeExercicio)) {
                return exercicio;
            }
        }
        return null;
    }

    public List<Exercicio> retornaExerciciosAluno(int id) {
        return exercicioRepository.retornaExerciciosAluno(id);
    }

    public void deleteExercicio(int id) {
        exercicioRepository.deleteExercicio(id);
    }

    public void updateExercicio(Exercicio exercicio) throws SQLException, IOException {
        System.out.print("Informe o novo nome para o exercicio: ");
        exercicio.setNome(in.readLine());

        System.out.print("Informe a quantidade de séries: ");
        exercicio.setSeries(sc.nextInt());

        exercicioRepository.updateExercicio(exercicio);
    }

    public Exercicio findExercicio(int id) {
        return exercicioRepository.findExercicioById(id);
    }
}
