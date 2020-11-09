package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.math.BigInteger;

import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Cadastro implements Serializable {

    // Bloco de patterns para conferência de entradas:

    public void periodos(String[] dados, Map<String, Periodo> periodos) {
        Pattern patternAno = Pattern.compile("\\d{4}");
        Pattern patternSemestre = Pattern.compile("[A-Z 0-9]{1}");

        for (int i = 0; i < dados.length; i = i + 2) {
            String anoStr = dados[i];
            if (!patternAno.matcher(anoStr).matches())
                throw new IllegalArgumentException("Dado invalido: " + anoStr);
            int ano = Integer.parseInt(anoStr);

            String semestreStr = dados[i + 1];
            if (!patternSemestre.matcher(semestreStr).matches())
                throw new IllegalArgumentException();
            char semestre = semestreStr.charAt(0);

            if (periodos.containsKey(ano + "/" + semestre))
                throw new IllegalArgumentException("Cadastro repetido: " + ano + "/" + semestre);

            periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));
        }

    }

    public void docentes(String[] dados, Map<String, Docente> docentes) {
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");

        for (int i = 0; i < dados.length; i = i + 3) {
            String login = dados[i];
            if (!patternLogin.matcher(login).matches())
                throw new IllegalArgumentException("Dado invalido: " + login);
            if (docentes.containsKey(login))
                throw new IllegalArgumentException("Cadastro repetido: " + login);

            String nome = dados[i + 1];

            String site = dados[i + 2];

            docentes.put(login, new Docente(login, nome, site));
        }

    }

    public void disciplinas(String[] dados, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {

        Pattern patternCodigoDisciplina = Pattern.compile("[A-Z]{3}\\d{5}");
        Pattern patternPeriodo = Pattern.compile("\\d{4}/[A-Z 0-9]{1}");
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");
        String s = null;

        try {
            for (int i = 0; i < dados.length; i = i + 4) {
                String periodoRef = dados[i];
                s = periodoRef;
                if (!patternPeriodo.matcher(periodoRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + periodoRef);
                Periodo periodo = periodos.get(periodoRef);

                String codigo = dados[i + 1];
                if (!patternCodigoDisciplina.matcher(codigo).matches())
                    throw new IllegalArgumentException("Dado invalido: " + codigo);

                if (disciplinas.containsKey(codigo + "-" + periodoRef))
                    throw new IllegalArgumentException("Cadastro repetido: " + codigo + "-" + periodoRef);

                String nome = dados[i + 2];

                String docenteRef = dados[i + 3];
                s = docenteRef;
                if (!patternLogin.matcher(docenteRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + docenteRef);

                Docente docente = docentes.get(docenteRef);

                disciplinas.put(codigo + "-" + periodoRef, new Disciplina(codigo, nome, periodo, docente));
                Disciplina disciplina = disciplinas.get(codigo + "-" + periodoRef);
                periodo.adicionarDisciplina(disciplina);
                docente.adicionarDisciplina(disciplina);
                docente.adicionarPeriodo(periodo);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Referencia invalida: " + s);
        }
    }

    public void estudantes(String[] dados, Map<BigInteger, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        BigInteger matricula;

        try {
            for (int i = 0; i < dados.length; i = i + 2) {
                String estudanteRef = dados[i];
                if (!patternMatricula.matcher(estudanteRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + estudanteRef);
                matricula = new BigInteger(estudanteRef);
            
                if (estudantes.containsKey(matricula))
                    throw new IllegalArgumentException("Cadastro repetido: " + matricula);

                String nome = dados[i + 1];

                estudantes.put(matricula, new Estudante(matricula, nome));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void estudanteEmDisciplina(String[] dados, Map<String, Disciplina> disciplinas,
            Map<BigInteger, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        String s = null;
        BigInteger matricula;

        try {
            for (int i = 0; i < dados.length; i = i + 2) {
                String disciplinaRef = dados[i];
                s = disciplinaRef;
                if (!patternDisciplina.matcher(disciplinaRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + disciplinaRef);
                Disciplina disciplina = disciplinas.get(disciplinaRef);
                
                if (disciplina == null)
                    throw new NullPointerException();

                String estudanteRef = dados[i + 1];
                s = estudanteRef;
                if (!patternMatricula.matcher(estudanteRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + estudanteRef);
                matricula = new BigInteger(estudanteRef);
                
                Estudante estudante = estudantes.get(matricula);

                if (disciplina.jaMatriculado(matricula))
                    throw new IllegalArgumentException(
                            "Matricula repetida: " + matricula + " em " + disciplina.obterRef() + ".");
                estudante.adicionarDisciplina(disciplina);
                estudante.adicionarPeriodo(disciplina.obterPeriodo());
                disciplina.adicionarEstudante(estudante);

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Referencia invalida: " + s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void atividadesEmDisciplina(String[] dados, Map<String, Disciplina> disciplinas) {

        Pattern patternAtividade = Pattern.compile("[AETP]");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        Pattern patternOnlyInteger = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
        Pattern patternData = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Pattern patternHora = Pattern.compile("^\\d{2}:\\d{2}$");
        String s = null;

        try {
            for (int i = 0; i < dados.length; i = i + 8) {
                String disciplinaRef = dados[i];
                s = disciplinaRef;
                if (!patternDisciplina.matcher(disciplinaRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + disciplinaRef);
                Disciplina disciplina = disciplinas.get(disciplinaRef);

                String nome = dados[i + 1];

                String tipo = dados[i + 2];
                if (!patternAtividade.matcher(tipo).matches())
                    throw new IllegalArgumentException("Dado invalido: " + tipo);

                String data = dados[i + 3];
                if (!patternData.matcher(data).matches() && !data.equals(""))
                    throw new IllegalArgumentException("Dado invalido: " + data);

                String hora = dados[i + 4];
                if (!patternHora.matcher(hora).matches() && !hora.equals(""))
                    throw new IllegalArgumentException("Dado invalido: " + hora);

                String conteudo = dados[i + 5];

                String tamMaxGrupo = dados[i + 6];
                if (!patternOnlyInteger.matcher(tamMaxGrupo).matches() && !tamMaxGrupo.equals(""))
                    throw new IllegalArgumentException("Dado invalido: " + tamMaxGrupo);

                String cargaHoraria = dados[i + 7];
                if (!patternOnlyInteger.matcher(cargaHoraria).matches() && !cargaHoraria.equals(""))
                    throw new IllegalArgumentException("Dado invalido: " + cargaHoraria);

                if (!data.equals("") && (tipo.equals("P") || tipo.equals("T")))
                    disciplina.adicionarDataAvaliacao(data);

                if (tipo.equals("A"))
                    disciplina.adicionarAula(nome, "sincrona", disciplina, data + "-" + hora);
                else if (tipo.equals("E"))
                    disciplina.adicionarEstudo(nome, "assincrona", disciplina, conteudo);
                else if (tipo.equals("T"))
                    disciplina.adicionarTrabalho(nome, "assincrona", disciplina, data, tamMaxGrupo, cargaHoraria);
                else if (tipo.equals("P"))
                    disciplina.adicionarProva(nome, "sincrona", disciplina, data, hora, conteudo);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Referencia invalida: " + s);
        }
    }

    public void avaliacoesEmAtividade(String[] dados, Map<String, Disciplina> disciplinas,
            Map<BigInteger, Estudante> estudantes) {
        Pattern patternMatricula = Pattern.compile("\\d{10}");
        Pattern patternDisciplina = Pattern.compile("[A-Z]{3}\\d{5}-\\d{4}/[A-Z 0-9]{1}");
        Pattern patternOnlyInteger = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
        String s = null;
        Float notaAtividade = 0F;
        int nAux = 0;

        try {
            for (int i = 0; i < dados.length; i = i + 4) {
                String disciplinaRef = dados[i];
                s = disciplinaRef;
                if (!patternDisciplina.matcher(disciplinaRef).matches())
                    throw new IllegalArgumentException();
                Disciplina disciplina = disciplinas.get(disciplinaRef);

                String estudanteRef = dados[i + 1];
                s = estudanteRef;
                if (!patternMatricula.matcher(estudanteRef).matches())
                    throw new IllegalArgumentException("Dado invalido: " + estudanteRef);
                BigInteger matricula = new BigInteger(estudanteRef);
                Estudante estudante = estudantes.get(matricula);

                String numeroAtividadeStr = dados[i + 2];
                s = numeroAtividadeStr;
                if (!patternOnlyInteger.matcher(numeroAtividadeStr).matches())
                    throw new IllegalArgumentException("Dado invalido: " + numeroAtividadeStr);
                nAux = Integer.parseInt(numeroAtividadeStr);
                int numeroAtividade = nAux;

                Atividade atividade = disciplina.obterAtividade(numeroAtividade);

                String notaAtividadeStr = dados[i + 3];
                notaAtividadeStr = notaAtividadeStr.replace(",", ".");
                notaAtividade = Float.valueOf(notaAtividadeStr);

                if (atividade.encontrarAvaliacao(estudante) != null)
                    throw new IllegalArgumentException("Avaliacao repetida: estudante " + matricula + " para atividade "
                            + numeroAtividade + " de " + disciplina.obterRef() + ".");

                atividade.avaliarAtividade(estudante, notaAtividade);
                estudante.adicionarAvaliacao(atividade, atividade.encontrarAvaliacao(estudante));
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Referencia invalida: " + s);
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Dado invalido: " + notaAtividade);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Dado invalido: " + nAux);
        }
    }
}