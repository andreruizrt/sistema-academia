package com.company.service;

import com.company.domain.Professor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProfessorService {

    public Professor cadastraProfessor() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DatabaseService databaseService = new DatabaseService();
        Scanner sc = new Scanner(System.in);
        
        Professor professor = new Professor();

        System.out.print("Digite o nome do professor: ");
        professor.setNome(in.readLine());

        databaseService.inserirProfessor(professor.getNome());

        return professor;
    }
}
