package com.company.service;

import com.company.domain.Aluno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AlunoService {

    public Aluno cadastraAluno() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DatabaseService databaseService = new DatabaseService();
        Scanner sc = new Scanner(System.in);

        Aluno aluno = new Aluno();

        System.out.print("Digite o nome do aluno: ");
        aluno.setNome(in.readLine());

        System.out.print("Informe o cpf do aluno: ");
        aluno.setCpf(in.readLine());

        databaseService.inserirAluno(aluno.getNome(),
         aluno.getCpf());

        return aluno;
    }

    //TODO: Add print members 
}
