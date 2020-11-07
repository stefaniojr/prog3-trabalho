package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

import com.github.stefaniojr.prog3.project.io.*;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Cadastro implements Serializable {

    Escrita escrever = new Escrita();

    public void periodo(Leitura ler, Map<String, Periodo> periodos) {
        Pattern patternAno = Pattern.compile("\\d{4}");
        Pattern patternPeriodo = Pattern.compile("[A-Z 0-9]{1}");
        int ano = 0;
        char semestre = '\0';
        String linha = null;
        String s = null;

        try {
            escrever.digiteAno();
            linha = ler.linha();
            if (!patternAno.matcher(linha).matches())
                throw new IllegalArgumentException();
            ano = Integer.parseInt(linha);
            escrever.digiteSemestre();
            linha = ler.linha();
            s = linha;
            if (!patternPeriodo.matcher(linha).matches())
                throw new IllegalArgumentException();
            semestre = linha.charAt(0);
            if (periodos.containsKey(ano + "/" + semestre))
                throw new RuntimeException();
            periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));
        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);
        } catch (RuntimeException e) {
            escrever.cadastroRepetido(ano + "/" + semestre);
        }
    }

    public void docente(Leitura ler, Map<String, Docente> docentes) {
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");
        Pattern patternYesOrNoQuestion = Pattern.compile("[SN]{1}");
        String s = null;
        String login = null;
        String possuiSite = null;
        String semSite = "Docente sem site.";

        try {
            escrever.digiteNome("docente");
            String nome = ler.cadeiaCaract();
            escrever.digiteLogin();
            login = ler.cadeiaCaract();
            s = login;
            if (!patternLogin.matcher(login).matches())
                throw new IllegalArgumentException();
            if (docentes.containsKey(login))
                throw new RuntimeException();

            escrever.possuiSite();
            possuiSite = ler.cadeiaCaract();
            s = possuiSite;
            if (!patternYesOrNoQuestion.matcher(possuiSite).matches())
                throw new IllegalArgumentException();
            if (possuiSite.charAt(0) == 'S') {
                escrever.digiteSite("docente");
                String comSite = ler.cadeiaCaract();
                docentes.put(login, new Docente(login, nome, comSite));
            } else {
                docentes.put(login, new Docente(login, nome, semSite));
            }

        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);

        } catch (RuntimeException e) {
            escrever.cadastroRepetido(s);
        }

    }

    public void disciplina(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {

        Pattern patternCodigoDisciplina = Pattern.compile("[A-Z]{3}\\d{5}");
        Pattern patternPeriodo = Pattern.compile("\\d{4}/[A-Z 0-9]{1}");
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");
        String s = null;
        String codigo = null;
        String periodoRef = null;

        try {
            escrever.digiteNome("disciplina");
            String nome = ler.cadeiaCaract();
            escrever.digiteCodigoDisciplina();
            codigo = ler.cadeiaCaract();
            if (!patternCodigoDisciplina.matcher(codigo).matches())
                throw new IllegalArgumentException();

            escrever.digitePeriodo();
            periodoRef = ler.cadeiaCaract();
            s = periodoRef;
            if (!patternPeriodo.matcher(periodoRef).matches())
                throw new IllegalArgumentException();
            Periodo periodo = null;
            periodo = periodos.get(periodoRef);
            if (disciplinas.containsKey(codigo + "-" + periodoRef))
                throw new RuntimeException();

            String docenteRef = null;
            escrever.digiteRef("docente");
            docenteRef = ler.cadeiaCaract();
            s = docenteRef;
            if (!patternLogin.matcher(docenteRef).matches())
                throw new IllegalArgumentException();
            Docente docente = null;
            docente = docentes.get(docenteRef);

            disciplinas.put(codigo + "-" + periodoRef, new Disciplina(codigo, nome, periodo, docente));
            Disciplina disciplina = disciplinas.get(codigo + "-" + periodoRef);
            periodo.adicionarDisciplina(disciplina);
            docente.adicionarDisciplina(disciplina);
            docente.adicionarPeriodo(periodo);

        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);

        } catch (NullPointerException e) {
            escrever.invalidReferencia(s);

        } catch (RuntimeException e) {
            escrever.cadastroRepetido(codigo + "-" + periodoRef);
        }
    }

    public void estudante(Leitura ler, Map<Integer, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        String linha = null;
        String s = null;
        String nome = null;
        int matricula = 0;

        try {
            escrever.digiteMatricula();
            linha = ler.linha();
            s = linha;
            if (!patternMatricula.matcher(linha).matches())
                throw new IllegalArgumentException();
            matricula = Integer.parseInt(linha);
            if (estudantes.containsKey(matricula))
                throw new RuntimeException();
            escrever.digiteNome("estudante");
            nome = ler.cadeiaCaract();
            estudantes.put(matricula, new Estudante(matricula, nome));

        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);

        } catch (RuntimeException e) {
            escrever.cadastroRepetido(Integer.toString(matricula));
        }
    }

    public void estudanteEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas,
            Map<Integer, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        int estudanteRef = 0;
        String linha = null;
        String s = null;
        String disciplinaRef = null;
        Estudante estudante = null;
        Disciplina disciplina = null;

        try {
            escrever.digiteRef("estudante");
            linha = ler.linha();
            s = linha;
            if (!patternMatricula.matcher(linha).matches())
                throw new IllegalArgumentException();
            estudanteRef = Integer.parseInt(linha);
            estudante = estudantes.get(estudanteRef);

            escrever.digiteRef("disciplina");
            disciplinaRef = ler.cadeiaCaract();
            s = disciplinaRef;
            if (!patternDisciplina.matcher(disciplinaRef).matches())
                throw new IllegalArgumentException();
            disciplina = disciplinas.get(disciplinaRef);

            if (disciplina.jaMatriculado(estudanteRef))
                throw new RuntimeException();

            estudante.adicionarDisciplina(disciplina);
            estudante.adicionarPeriodo(disciplina.obterPeriodo());
            disciplina.adicionarEstudante(estudante);

        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);

        } catch (NullPointerException e) {
            escrever.invalidReferencia(s);

        } catch (RuntimeException e) {
            escrever.matriculaRepetida(estudanteRef, disciplina.obterRef());
        }
    }

    public void atividadeEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas) {

        Pattern patternOption = Pattern.compile("[1-4]{1}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        Pattern patternDataSemHora = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Pattern patternDataComHora = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}-\\d{2}:\\d{2}$");
        int tipo = 0;
        int nIntegrantes = 0;
        int cargaHoraria = 0;
        String linha = null;
        String s = null;
        String disciplinaRef = null;
        Disciplina disciplina = null;
        boolean ehCargaHoraria = false;

        try {
            escrever.digiteNome("atividade");
            String nome = ler.cadeiaCaract();

            escrever.digiteRef("disciplina");
            disciplinaRef = ler.cadeiaCaract();
            linha = disciplinaRef;
            s = linha;
            if (!patternDisciplina.matcher(disciplinaRef).matches())
                throw new IllegalArgumentException();
            disciplina = disciplinas.get(disciplinaRef);

            escrever.digiteTipo();
            linha = ler.linha();
            s = linha;
            if (!patternOption.matcher(linha).matches())
                throw new IllegalArgumentException();
            tipo = Integer.parseInt(linha);
            if (tipo == 1) {
                escrever.digiteDataComHora();
                linha = ler.cadeiaCaract(); // Data.
                s = linha;
                if (!patternDataComHora.matcher(linha).matches())
                    throw new RuntimeException();
                disciplina.adicionarAula(nome, "sincrona", disciplina, linha);
            } else if (tipo == 2) {
                Map<String, String> conteudos = new HashMap<>();
                String url;
                String conteudo;

                do {
                    escrever.instrucoesAdicionarConteudoAEstudar();

                    escrever.digiteConteudo();
                    conteudo = ler.cadeiaCaract();

                    if (conteudo.equals("0")) {
                        break;
                    }

                    escrever.digiteURL();
                    url = ler.cadeiaCaract();

                    conteudos.put(url, conteudo);
                } while (true);

                disciplina.adicionarEstudo(nome, "assincrona", disciplina, conteudos);

            } else if (tipo == 3) {
                escrever.digiteData();
                linha = ler.cadeiaCaract(); // Prazo.
                s = linha;
                if (!patternDataSemHora.matcher(linha).matches())
                    throw new IllegalArgumentException();
                escrever.digiteIntegrantes();
                nIntegrantes = ler.inteiro();
                ler.cadeiaCaract();
                escrever.digiteCargaHoraria();
                ehCargaHoraria = true;
                cargaHoraria = ler.inteiro();
                ler.cadeiaCaract();

                disciplina.adicionarTrabalho(nome, "assincrona", disciplina, linha, nIntegrantes, cargaHoraria);

            } else if (tipo == 4) {
                List<String> conteudos = new ArrayList<>();
                String conteudo;

                escrever.digiteDataComHora();
                linha = ler.cadeiaCaract(); // Data.
                s = linha;

                if (!patternDataComHora.matcher(linha).matches())
                    throw new IllegalArgumentException();

                do {
                    escrever.digiteConteudo();
                    conteudo = ler.cadeiaCaract();
                    if (conteudo.equals("0")) {
                        break;
                    }
                    conteudos.add(conteudo);
                } while (true);

                disciplina.adicionarProva(nome, "sincrona", disciplina, linha, conteudos);
            }

        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);

    
        } catch (InputMismatchException e) {
            if(ehCargaHoraria){
                escrever.invalidData(Integer.toString(cargaHoraria));
            } else {
                escrever.invalidData(Integer.toString(nIntegrantes));
            }
        
        }  catch (NullPointerException e) {
            escrever.invalidReferencia(s);
        }
    }

    public void avaliacaoEmAtividade(Leitura ler, Map<String, Disciplina> disciplinas,
            Map<Integer, Estudante> estudantes) {

        Pattern patternMatricula = Pattern.compile("\\d{10}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");

        boolean ehFloat = false;
        int estudanteRef = 0;
        int numeroAtividade = 0;
        float notaAtividade = 0;
        String disciplinaRef = null;
        String linha = null;
        String s = null;
        Estudante estudante = null;
        Disciplina disciplina = null;
        Atividade atividade = null;
        

        try {
            escrever.digiteRef("estudante");
            linha = ler.linha();
            if (!patternMatricula.matcher(linha).matches())
                throw new IllegalArgumentException();
            estudanteRef = Integer.parseInt(linha);
            estudante = estudantes.get(estudanteRef);

            escrever.digiteRef("disciplina");
            disciplinaRef = ler.cadeiaCaract();
            linha = disciplinaRef;
            s = linha;
            if (!patternDisciplina.matcher(disciplinaRef).matches())
                throw new IllegalArgumentException();
            disciplina = disciplinas.get(disciplinaRef);

            escrever.digiteRef("atividade");
            numeroAtividade = ler.inteiro();
            ler.cadeiaCaract();
            s = Integer.toString(numeroAtividade);
            if (numeroAtividade < 0 && numeroAtividade > 30000)
                throw new IllegalArgumentException();
            atividade = disciplina.obterAtividade(numeroAtividade);

            escrever.digiteNota("estudante");
            ehFloat = true;
            notaAtividade = ler.flutuante();
            ler.cadeiaCaract();

            if (atividade.encontrarAvaliacao(estudante) != null)
                throw new RuntimeException();

            atividade.avaliarAtividade(estudante, notaAtividade);
            estudante.adicionarAvaliacao(atividade, atividade.encontrarAvaliacao(estudante));

        } catch (IllegalArgumentException e) {
            escrever.invalidData(s);

        } catch (NullPointerException e) {
            escrever.invalidReferencia(s);

        } catch (InputMismatchException e) {
            if(ehFloat){
                escrever.invalidData(Float.toString(notaAtividade));
            } else {
                escrever.invalidData(Integer.toString(numeroAtividade));
            }
        
        }  catch (RuntimeException e) {
            escrever.avaliacaoRepetida(estudanteRef, disciplina.obterRef(), numeroAtividade);

        }
    }
}