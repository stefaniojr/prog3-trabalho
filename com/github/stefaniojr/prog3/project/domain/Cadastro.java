package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.math.BigInteger;
import java.text.ParseException;

import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Cadastro implements Serializable {
    private static final long serialVersionUID = 1348633635465464573L;
    // Patterns para conferência de dados de entrada.
    private static final Pattern PATTERN_ANO = Pattern.compile("\\d{4}");
    private static final Pattern PATTERN_SEMESTRE = Pattern.compile("[A-Z 0-9]{1}");
    private static final Pattern PATTERN_CODIGO = Pattern.compile("[A-Z]{3}\\d{5}");
    private static final Pattern PATTERN_PERIODO = Pattern.compile("\\d{4}/[A-Z 0-9]{1}");
    private static final Pattern PATTERN_DISCIPLINA = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
    private static final Pattern PATTERN_ATIVIDADE = Pattern.compile("[AETP]");
    private static final Pattern PATTERN_INTEGER = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
    private static final Pattern PATTERN_DATA = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
    private static final Pattern PATTERN_HORA = Pattern.compile("^\\d{2}:\\d{2}$");

    /**
     * Registro de períodos em HashMap através de dados armazenados em vetor de
     * String em ordem de dados conhecida.
     *
     * @param String
     * @param HashMap
     * @throws RunTimeException
     */
    public void periodos(String[] dados, Map<String, Periodo> periodos) throws RuntimeException {
        // Registra dados do vetor de string em HashMap de forma iterativa: primeiro
        // dado sendo o ano e segundo dado sendo o semestre.
        for (int i = 0; i < dados.length; i = i + 2) {
            String anoStr = dados[i];
            if (!PATTERN_ANO.matcher(anoStr).matches()) {
                System.out.println("Dado inválido: " + anoStr + ".");
                throw new IllegalArgumentException();
            }

            int ano = Integer.parseInt(anoStr);

            String semestreStr = dados[i + 1];
            if (!PATTERN_SEMESTRE.matcher(semestreStr).matches()) {
                System.out.print("Dado inválido: " + semestreStr + ".");
                throw new IllegalArgumentException();
            }

            char semestre = semestreStr.charAt(0);

            if (periodos.containsKey(ano + "/" + semestre)) {
                System.out.println("Cadastro repetido: " + ano + "/" + semestre + ".");
                throw new IllegalArgumentException();
            }

            periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));
        }

    }

    /**
     * Registro de docentes em HashMap através de dados armazenados em vetor de
     * String em ordem de dados conhecida.
     *
     * @param String 
     * @param HashMap 
     * @throws RunTimeException 
     */
    public void docentes(String[] dados, Map<String, Docente> docentes) throws RuntimeException {
        // Registra dados do vetor de string em HashMap de forma iterativa: primeiro
        // dado sendo o login institucional, segundo dado sendo o nome completo e
        // terceiro dado o endereço web do docente.
        for (int i = 0; i < dados.length; i = i + 3) {
            String login = dados[i];

            if (docentes.containsKey(login)) {
                System.out.println("Cadastro repetido: " + login + ".");
                throw new IllegalArgumentException();
            }

            String nome = dados[i + 1];

            String site = dados[i + 2];

            docentes.put(login, new Docente(login, nome, site));
        }

    }

    /**
     * Registro de disciplinas em HashMap através de dados armazenados em vetor de
     * String em ordem de dados conhecida.
     * 
     * @param String
     * @param HashMap
     * @throws RunTimeException
     * 
     */
    public void disciplinas(String[] dados, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) throws RuntimeException {
        // Registra dados do vetor de string em HashMaps de forma iterativa: primeiro
        // dado sendo o período, segundo dado sendo o código da disciplina, terceiro
        // dado sendo o nome da disciplina e quarto dado o docente responsável.
        for (int i = 0; i < dados.length; i = i + 4) {
            String periodoRef = dados[i];
            if (!PATTERN_PERIODO.matcher(periodoRef).matches()) {
                System.out.println("Dado inválido: " + periodoRef + ".");
                throw new IllegalArgumentException();
            }

            Periodo periodo = periodos.get(periodoRef);

            if (periodo == null) {
                System.out.println("Referência inválida: " + periodoRef + ".");
                throw new NullPointerException();
            }

            String codigo = dados[i + 1];
            if (!PATTERN_CODIGO.matcher(codigo).matches()) {
                System.out.println("Dado inválido: " + codigo + ".");
                throw new IllegalArgumentException();
            }

            if (disciplinas.containsKey(codigo + "-" + periodoRef)) {
                System.out.println("Cadastro repetido: " + codigo + "-" + periodoRef + ".");
                throw new IllegalArgumentException();
            }

            String nome = dados[i + 2];

            String docenteRef = dados[i + 3];
            Docente docente = docentes.get(docenteRef);
            if (docente == null) {
                System.out.println("Referência inválida: " + docenteRef + ".");
                throw new NullPointerException();
            }

            disciplinas.put(codigo + "-" + periodoRef, new Disciplina(codigo, nome, periodo, docente));
            Disciplina disciplina = disciplinas.get(codigo + "-" + periodoRef);
            periodo.adicionarDisciplina(disciplina);
            docente.adicionarDisciplina(disciplina);
            docente.adicionarPeriodo(periodo);
        }
    }

    /**
     * Registro de disciplinas em HashMap através de dados armazenados em vetor de
     * String em ordem de dados conhecida.
     * 
     * @param String
     * @param HashMap
     * @param Estudante
     * 
     */
    public void estudantes(String[] dados, Map<BigInteger, Estudante> estudantes) {
        BigInteger matricula;
        // Registra dados do vetor de string em HashMap de forma iterativa: primeiro
        // dado sendo a matrícula do estudante e segundo dado sendo o nome completo do
        // estudante.

        for (int i = 0; i < dados.length; i = i + 2) {
            String estudanteRef = dados[i];
            if (!PATTERN_INTEGER.matcher(estudanteRef).matches()) {
                System.out.println("Dado inválido: " + estudanteRef + ".");
                throw new IllegalArgumentException();
            }
            matricula = new BigInteger(estudanteRef);
            if (estudantes.containsKey(matricula)) {
                System.out.println("Cadastro repetido: " + matricula + ".");
                throw new IllegalArgumentException();
            }

            String nome = dados[i + 1];

            estudantes.put(matricula, new Estudante(matricula, nome));
        }

    }

    /**
     * Registro de estudantes em atributo do HashMap de disciplina previamente
     * preenchido através de dados armazenados em vetor de String em ordem de dados
     * conhecida.
     * 
     * @param String
     * @param HashMap
     * @param Disciplina
     * @param HashMap
     * @param Estudante
     * @throws NullPointerException
     * 
     */
    public void estudanteEmDisciplina(String[] dados, Map<String, Disciplina> disciplinas,
            Map<BigInteger, Estudante> estudantes) throws NullPointerException {
        BigInteger matricula;
        // Registra dados do vetor de string em atributos de HashMaps de forma
        // iterativa: primeiro dado sendo a disciplina e segundo dado sendo a matrícula
        // do estudante.

        for (int i = 0; i < dados.length; i = i + 2) {
            String disciplinaRef = dados[i];
            if (!PATTERN_DISCIPLINA.matcher(disciplinaRef).matches()) {
                System.out.println("Dado inválido: " + disciplinaRef + ".");
                throw new IllegalArgumentException();
            }
            Disciplina disciplina = disciplinas.get(disciplinaRef);
            if (disciplina == null) {
                System.out.println("Referência inválida: " + disciplinaRef + ".");
                throw new NullPointerException();
            }

            String estudanteRef = dados[i + 1];
            if (!PATTERN_INTEGER.matcher(estudanteRef).matches()) {
                System.out.println("Dado inválido: " + estudanteRef + ".");
                throw new IllegalArgumentException();
            }
            matricula = new BigInteger(estudanteRef);
            Estudante estudante = estudantes.get(matricula);
            if (estudante == null) {
                System.out.println("Referência inválida: " + estudanteRef + ".");
                throw new NullPointerException();
            }
            if (disciplina.jaMatriculado(matricula)) {
                System.out.println("Matrícula repetida: " + matricula + " em " + disciplina.obterRef() + ".");
                throw new IllegalArgumentException();
            }

            estudante.adicionarDisciplina(disciplina);
            estudante.adicionarPeriodo(disciplina.obterPeriodo());
            disciplina.adicionarEstudante(estudante);
        }
    }

    /**
     * Registro de atividades em atributo (HashMap de atividades) do HashMap de
     * disciplina previamente preenchido através de dados armazenados em vetor de
     * String em ordem de dados conhecida.
     * 
     * @param String
     * @param HashMap
     * @throws RunTimeException
     * @throws ParseException
     */
    public void atividadesEmDisciplina(String[] dados, Map<String, Disciplina> disciplinas)
            throws RuntimeException, ParseException {
        // Registra dados do vetor de string em atributo de HashMaps de forma iterativa:
        // primeiro dado sendo a disciplina, segundo dado sendo o nome da atividade,
        // terceiro dado sendo o tipo de atividade, quarto dado sendo a data (se
        // houver), quinto dado sendo a hora (se houver), sexto dado sendo os materiais
        // ou conteúdos (se houver), sétimo dado sendo o número máximo de integrantes
        // que um grupo pode ter (se houver) e oitavo dado sendo a carga horária
        // esperada (se houver).

        for (int i = 0; i < dados.length; i = i + 8) {
            String disciplinaRef = dados[i];
            if (!PATTERN_DISCIPLINA.matcher(disciplinaRef).matches()) {
                System.out.println("Dado inválido: " + disciplinaRef + ".");
                throw new IllegalArgumentException();
            }
            Disciplina disciplina = disciplinas.get(disciplinaRef);
            if (disciplina == null) {
                System.out.println("Referência inválida: " + disciplinaRef + ".");
                throw new NullPointerException();
            }

            String nome = dados[i + 1];

            String tipo = dados[i + 2];
            if (!PATTERN_ATIVIDADE.matcher(tipo).matches()) {
                System.out.println("Dado inválido: " + tipo + ".");
                throw new IllegalArgumentException();
            }

            String data = dados[i + 3];
            if (!PATTERN_DATA.matcher(data).matches() && !data.equals("")) {
                System.out.println("Dado inválido: " + data + ".");
                throw new IllegalArgumentException();
            }

            String hora = dados[i + 4];
            if (!PATTERN_HORA.matcher(hora).matches() && !(hora.equals("") || hora.equals(" "))) {
                System.out.println("Dado inválido: " + hora + ".");
                throw new IllegalArgumentException();
            }

            String conteudo = dados[i + 5];

            String tamMaxGrupo = dados[i + 6];
            if (!PATTERN_INTEGER.matcher(tamMaxGrupo).matches()
                    && !(tamMaxGrupo.equals("") || tamMaxGrupo.equals(" "))) {
                System.out.println("Dado inválido: " + tamMaxGrupo + ".");
                throw new IllegalArgumentException();
            }

            String cargaHoraria = dados[i + 7];
            if (!PATTERN_INTEGER.matcher(cargaHoraria).matches() && !cargaHoraria.equals("")
                    && !cargaHoraria.equals(" ")) {
                System.out.println("Dado inválido: " + cargaHoraria + ".");
                throw new IllegalArgumentException();
            }

            if (tipo.equals("A"))
                disciplina.adicionarAula(nome, "sincrona", disciplina, data + "-" + hora, tipo.charAt(0));
            else if (tipo.equals("E"))
                disciplina.adicionarEstudo(nome, "assincrona", disciplina, conteudo, tipo.charAt(0));
            else if (tipo.equals("T"))
                disciplina.adicionarTrabalho(nome, "assincrona", disciplina, data, tamMaxGrupo, cargaHoraria,
                        tipo.charAt(0));
            else if (tipo.equals("P"))
                disciplina.adicionarProva(nome, "sincrona", disciplina, data, hora, conteudo, tipo.charAt(0));
        }
    }

    /**
     * Registro de avaliações realizadas por alunos em atributo (HashMap de
     * avaliações) do HashMap de disciplina previamente preenchido através de dados
     * armazenados em vetor de String em ordem de dados conhecida.
     * 
     * @param String
     * @param HashMap
     * @param Disciplina
     * @param HashMap
     * @param Estudante
     * @throws RunTimeException
     * 
     */
    public void avaliacoesEmAtividade(String[] dados, Map<String, Disciplina> disciplinas,
            Map<BigInteger, Estudante> estudantes) throws RuntimeException {
        double notaAtividade = 0;
        String notaAtividadeStr = null;
        // Registra dados do vetor de string em atributo de HashMaps de forma iterativa:
        // primeiro dado sendo a disciplina, segundo dado sendo a matrícula do
        // estudante, terceiro dado sendo a referência para atividade e quarto dado a
        // nota.
        try {
            for (int i = 0; i < dados.length; i = i + 4) {
                String disciplinaRef = dados[i];
                if (!PATTERN_DISCIPLINA.matcher(disciplinaRef).matches()) {
                    System.out.println("Dado inválido: " + disciplinaRef + ".");
                    throw new IllegalArgumentException();
                }

                Disciplina disciplina = disciplinas.get(disciplinaRef);
                if (disciplina == null) {
                    System.out.println("Referência inválida: " + disciplinaRef + ".");
                    throw new NullPointerException();
                }

                String estudanteRef = dados[i + 1];
                if (!PATTERN_INTEGER.matcher(estudanteRef).matches()) {
                    System.out.println("Dado inválido: " + estudanteRef + ".");
                    throw new IllegalArgumentException();
                }

                BigInteger matricula = new BigInteger(estudanteRef);
                Estudante estudante = estudantes.get(matricula);
                if (estudante == null) {
                    System.out.println("Referência inválida: " + estudanteRef + ".");
                    throw new NullPointerException();
                }

                String numeroAtividadeStr = dados[i + 2];
                if (!PATTERN_INTEGER.matcher(numeroAtividadeStr).matches()) {
                    System.out.println("Dado inválido: " + numeroAtividadeStr + ".");
                    throw new IllegalArgumentException();
                }
                int numeroAtividade = Integer.parseInt(numeroAtividadeStr);
                Atividade atividade = disciplina.obterAtividade(numeroAtividade);
                if (atividade == null) {
                    System.out.println("Referência inválida: " + numeroAtividadeStr + ".");
                    throw new NullPointerException();
                }

                notaAtividadeStr = dados[i + 3];
                notaAtividadeStr = notaAtividadeStr.replace(",", ".");
                notaAtividade = Double.parseDouble(notaAtividadeStr);

                if (atividade.encontrarAvaliacao(estudante) != null) {
                    System.out.println("Avaliação repetida: estudante " + matricula + " para atividade "
                            + numeroAtividade + " de " + disciplina.obterRef() + ".");
                    throw new IllegalArgumentException();
                }

                atividade.avaliarAtividade(estudante, notaAtividade);
                estudante.adicionarAvaliacao(atividade, atividade.encontrarAvaliacao(estudante));
            }

        } catch (NumberFormatException e) {
            System.out.println("Dado inválido: " + notaAtividadeStr + ".");
            throw new RuntimeException();
        }
    }
}