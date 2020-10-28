package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
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

        boolean keepGoing = false;
        do {
            try {
                escrever.digiteAno();
                linha = ler.linha();
                if (!patternAno.matcher(linha).matches())
                    throw new IllegalArgumentException();
                ano = Integer.parseInt(linha);
                escrever.digiteSemestre();
                linha = ler.linha();
                if (!patternPeriodo.matcher(linha).matches())
                    throw new IllegalArgumentException();
                semestre = linha.charAt(0);
                if (periodos.containsKey(ano + "/" + semestre))
                    throw new AlreadyBoundException();
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidData(linha);
                keepGoing = true;
            } catch (AlreadyBoundException e) {
                escrever.cadastroRepetido(ano + "/" + semestre);
                keepGoing = true;
            }
        } while (keepGoing);

        periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));

    }

    public void docente(Leitura ler, Map<String, Docente> docentes) {
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");
        Pattern patternYesOrNoQuestion = Pattern.compile("[SN]{1}");
        String login = null;
        String possuiSite = null;
        String semSite = "Docente sem site.";

        escrever.digiteNome("docente");
        String nome = ler.cadeiaCaract();

        boolean keepGoing = false;
        do {
            try {
                escrever.digiteLogin();
                login = ler.cadeiaCaract();
                if (!patternLogin.matcher(login).matches())
                    throw new IllegalArgumentException();
                if (docentes.containsKey(login))
                    throw new AlreadyBoundException();
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidData(login);
                keepGoing = true;
            } catch (AlreadyBoundException e) {
                escrever.cadastroRepetido(login);
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;
        do {
            try {
                escrever.possuiSite();
                possuiSite = ler.cadeiaCaract();
                if (!patternYesOrNoQuestion.matcher(possuiSite).matches())
                    throw new IllegalArgumentException();
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidData(possuiSite);
                keepGoing = true;
            }
        } while (keepGoing);

        if (possuiSite.charAt(0) == 'S') {
            escrever.digiteSite("docente");
            String comSite = ler.cadeiaCaract();
            docentes.put(login, new Docente(login, nome, comSite));
        } else {
            docentes.put(login, new Docente(login, nome, semSite));
        }

    }

    public void disciplina(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {

        Pattern patternCodigoDisciplina = Pattern.compile("[A-Z]{3}\\d{5}");
        Pattern patternPeriodo = Pattern.compile("\\d{4}/[A-Z 0-9]{1}");
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");
        String periodoRef = null;
        String docenteRef = null;
        String codigo = null;

        Periodo periodo = null;
        Docente docente = null;

        escrever.digiteNome("disciplina");
        String nome = ler.cadeiaCaract();

        boolean keepGoing = false;

        keepGoing = false;
        do {
            try {
                escrever.digiteCodigoDisciplina();
                codigo = ler.cadeiaCaract();
                if (!patternCodigoDisciplina.matcher(codigo).matches())
                    throw new IllegalArgumentException();

                escrever.digitePeriodo();
                periodoRef = ler.cadeiaCaract();
                if (!patternPeriodo.matcher(periodoRef).matches())
                    throw new IllegalArgumentException();

                periodo = periodos.get(periodoRef);

                if (disciplinas.containsKey(codigo + "-" + periodoRef))
                    throw new AlreadyBoundException();
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(periodoRef);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            } catch (AlreadyBoundException e) {
                escrever.cadastroRepetido(codigo + "-" + periodoRef);
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;
        do {
            try {
                escrever.digiteRef("docente");
                docenteRef = ler.cadeiaCaract();
                if (!patternLogin.matcher(docenteRef).matches())
                    throw new IllegalArgumentException();
                docente = docentes.get(docenteRef);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(docenteRef);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        disciplinas.put(codigo + "-" + periodoRef, new Disciplina(codigo, nome, periodo, docente));
        Disciplina disciplina = disciplinas.get(codigo + "-" + periodoRef);
        periodo.adicionarDisciplina(disciplina);
        docente.adicionarDisciplina(disciplina);
        docente.adicionarPeriodo(periodo);
    }

    public void estudante(Leitura ler, Map<Integer, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        String linha = null;
        int matricula = 0;
        String nome = null;
        boolean keepGoing = false;
        do {
            try {
                escrever.digiteMatricula();
                linha = ler.linha();
                if (!patternMatricula.matcher(linha).matches())
                    throw new IllegalArgumentException();
                matricula = Integer.parseInt(linha);
                if (estudantes.containsKey(matricula))
                    throw new AlreadyBoundException();
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(linha);
                keepGoing = true;
            } catch (AlreadyBoundException e) {
                escrever.cadastroRepetido(Integer.toString(matricula));
                keepGoing = true;
            }
        } while (keepGoing);

        escrever.digiteNome("estudante");
        nome = ler.cadeiaCaract();
        estudantes.put(matricula, new Estudante(matricula, nome));
    }

    public void estudanteEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas,
            Map<Integer, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        int estudanteRef = 0;
        String linha = null;
        String disciplinaRef = null;
        Estudante estudante = null;
        Disciplina disciplina = null;
        boolean keepGoing = false;
        do {
            try {
                escrever.digiteRef("estudante");
                linha = ler.linha();
                if (!patternMatricula.matcher(linha).matches())
                    throw new IllegalArgumentException();
                estudanteRef = Integer.parseInt(linha);
                estudante = estudantes.get(estudanteRef);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(linha);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;
        do {
            try {
                escrever.digiteRef("disciplina");
                disciplinaRef = ler.cadeiaCaract();
                if (!patternDisciplina.matcher(disciplinaRef).matches())
                    throw new IllegalArgumentException();
                disciplina = disciplinas.get(disciplinaRef);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(disciplinaRef);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        try {
            if (disciplina.jaMatriculado(estudanteRef))
                throw new AlreadyBoundException();

            estudante.adicionarDisciplina(disciplina);
            estudante.adicionarPeriodo(disciplina.obterPeriodo());
            disciplina.adicionarEstudante(estudante);
        } catch (AlreadyBoundException e) {
            escrever.matriculaRepetida(estudanteRef, disciplina.obterRef());
        }

    }

    public void atividadeEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas) {

        Pattern patternOption = Pattern.compile("[1-4]{1}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        Pattern patternDataSemHora = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Pattern patternDataComHora = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}-\\d{2}:\\d{2}$");
        int tipo = 0;
        String linha = null;
        String disciplinaRef = null;
        Disciplina disciplina = null;

        escrever.digiteNome("atividade");
        String nome = ler.cadeiaCaract();

        boolean keepGoing = false;
        do {
            try {
                escrever.digiteRef("disciplina");
                disciplinaRef = ler.cadeiaCaract();
                if (!patternDisciplina.matcher(disciplinaRef).matches())
                    throw new IllegalArgumentException();
                disciplina = disciplinas.get(disciplinaRef);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(disciplinaRef);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;
        do {
            try {
                escrever.digiteTipo();
                linha = ler.linha();
                if (!patternOption.matcher(linha).matches())
                    throw new IllegalArgumentException();
                tipo = Integer.parseInt(linha);
                if (tipo == 1) {
                    escrever.digiteDataComHora();
                    linha = ler.cadeiaCaract(); // Data.
                    if (!patternDataComHora.matcher(linha).matches())
                        throw new IllegalArgumentException();
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
                    if (!patternDataSemHora.matcher(linha).matches())
                        throw new IllegalArgumentException();
                    escrever.digiteIntegrantes();
                    int nIntegrantes = ler.inteiro();
                    ler.cadeiaCaract();
                    escrever.digiteCargaHoraria();
                    int cargaHoraria = ler.inteiro();
                    ler.cadeiaCaract();
        
                    disciplina.adicionarTrabalho(nome, "assincrona", disciplina, linha, nIntegrantes, cargaHoraria);
        
                } else if (tipo == 4) {
                    List<String> conteudos = new ArrayList<>();
                    String conteudo;
        
                    escrever.digiteDataComHora();
                    linha = ler.cadeiaCaract(); // Data.

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
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidData(linha);
                keepGoing = true;
            }
        } while (keepGoing);

    }

    public void avaliacaoEmAtividade(Leitura ler, Map<String, Disciplina> disciplinas,
            Map<Integer, Estudante> estudantes) {

        Pattern patternMatricula = Pattern.compile("\\d{10}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");

        int estudanteRef = 0;
        int numeroAtividade = 0;
        float notaAtividade = 0;
        String disciplinaRef = null;
        String linha = null;
        Estudante estudante = null;
        Disciplina disciplina = null;
        Atividade atividade = null;

        boolean keepGoing = false;
        do {
            try {
                escrever.digiteRef("estudante");
                linha = ler.linha();
                if (!patternMatricula.matcher(linha).matches())
                    throw new IllegalArgumentException();
                estudanteRef = Integer.parseInt(linha);
                estudante = estudantes.get(estudanteRef);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(linha);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;

        do {
            try {
                escrever.digiteRef("disciplina");
                disciplinaRef = ler.cadeiaCaract();
                if (!patternDisciplina.matcher(disciplinaRef).matches())
                    throw new IllegalArgumentException();
                disciplina = disciplinas.get(disciplinaRef);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(disciplinaRef);
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;
        do {
            try {
                escrever.digiteRef("atividade");
                numeroAtividade = ler.inteiro();
                ler.cadeiaCaract();
                if (numeroAtividade < 0 && numeroAtividade > 1000)
                    throw new IllegalArgumentException();
                atividade = disciplina.obterAtividade(numeroAtividade);
                keepGoing = false;
            } catch (IllegalArgumentException e) {
                escrever.invalidReferencia(Integer.toString(numeroAtividade));
                keepGoing = true;
            } catch (NullPointerException e) {
                escrever.naoEncontrado();
                keepGoing = true;
            }
        } while (keepGoing);

        keepGoing = false;
        do {
            try {
                escrever.digiteNota("estudante");
                notaAtividade = ler.flutuante();
                ler.cadeiaCaract();
                keepGoing = false;
            } catch (InputMismatchException e) {
                escrever.invalidData(String.valueOf(notaAtividade));
                keepGoing = true;
            }
        } while (keepGoing);

        try {
            if (atividade.encontrarAvaliacao(estudante) == null)
                throw new AlreadyBoundException();

            atividade.avaliarAtividade(estudante, notaAtividade);
            estudante.adicionarAvaliacao(atividade, atividade.encontrarAvaliacao(estudante));
        } catch (AlreadyBoundException e) {
            escrever.avaliacaoRepetida(estudanteRef, disciplina.obterRef(), numeroAtividade);
        }
    }
}