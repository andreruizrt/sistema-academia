package com.company.service;

import com.company.domain.Aluno;
import com.company.repository.AlunoRepository;
import com.company.repository.DatabaseRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AlunoService {

    public Aluno cadastraAluno() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        AlunoRepository alunoRepository = new AlunoRepository();

        Scanner sc = new Scanner(System.in);

        Aluno aluno = new Aluno();

        System.out.print("Digite o nome do aluno: ");
        aluno.setNome(in.readLine());

        System.out.print("Informe o cpf do aluno: ");
        aluno.setCpf(in.readLine());

        alunoRepository.inserirAluno(aluno);

        return aluno;
    }
}
