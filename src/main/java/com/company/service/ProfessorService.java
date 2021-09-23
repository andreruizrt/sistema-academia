package com.company.service;

import com.company.repository.ProfessorRepository;
import com.company.domain.Professor;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ProfessorService {

    public Professor cadastraProfessor() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ProfessorRepository professorRepository = new ProfessorRepository();
        Scanner sc = new Scanner(System.in);
        
        Professor professor = new Professor();

        System.out.print("Digite o nome do professor: ");
        professor.setNome(in.readLine());

        professorRepository.inserirProfessor(professor);

        return professor;
    }
}
