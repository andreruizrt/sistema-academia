package com.company.service;

import com.company.domain.Exercicio;
import com.company.repository.DatabaseRepository;
import com.company.repository.ExercicioRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExercicioService {

    public Exercicio cadastraExercicio() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ExercicioRepository exercicioRepository = new ExercicioRepository();
        Scanner sc = new Scanner(System.in);

        Exercicio exercicio = new Exercicio();

        System.out.print("Informe o nome do exercício: ");
        exercicio.setNome(in.readLine());

        System.out.print("Digite a quantidade de séries: ");
        exercicio.setSeries(sc.nextInt());

        exercicioRepository.inserirExercicio(exercicio);

        return exercicio;
    }
}
