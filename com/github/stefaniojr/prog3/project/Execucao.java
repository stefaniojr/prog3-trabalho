package com.github.stefaniojr.prog3.project;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

import com.github.stefaniojr.prog3.project.domain.*;
import com.github.stefaniojr.prog3.project.io.*;
import com.github.stefaniojr.prog3.project.report.*;

public class Execucao implements Serializable {

    Cadastro cadastro = new Cadastro();
    Relatorio relatorios = new Relatorio();

    Map<String, Periodo> periodos = new HashMap<>();
    Map<String, Docente> docentes = new HashMap<>();
    Map<String, Disciplina> disciplinas = new HashMap<>();
    Map<Integer, Estudante> estudantes = new HashMap<>();

    public boolean menuPrincipal(Leitura ler, Escrita escrever) {
        int opcao = 0;
        do {
            boolean keepGoing = false;
            do {
                try {
                    escrever.mostrarMenu();
                    opcao = ler.inteiro();
                    ler.cadeiaCaract();
                    keepGoing = false;
                } catch (InputMismatchException e) {
                    escrever.opcaoInvalida();
                    ler.cadeiaCaract();
                    keepGoing = true;
                }
            } while (keepGoing);
            
            keepGoing = false;

            if (opcao == 1)
                subMenu1(ler, escrever);
            else if (opcao == 2)
                subMenu2(ler, escrever);
            else if (opcao == 3)
                subMenu3(ler, escrever);
            else if (opcao == 4)
                subMenu4(ler, escrever);
            else if (opcao == 5)
                subMenu5(ler, escrever);
            else if (opcao == 6)
                subMenu6(ler, escrever);
            else if (opcao == 7)
                subMenu7(ler, escrever);
            else if (opcao == 8)
                subMenu8(ler, escrever);
            else if (opcao == 9)
                return true;
            else if (opcao == 10) {
                escrever.saindo("programa");
                escrever.finalizado("Programa");
            }

        } while (opcao != 10);

        return false;
    }

    public void subMenu1(Leitura ler, Escrita escrever) {
        int opcao;

        do {
            escrever.mostrarSubMenu("PERIODO");
            opcao = ler.inteiro();

            if (opcao == 1)
                cadastro.periodo(ler, periodos);

        } while (opcao != 2);

        return;

    }

    public void subMenu2(Leitura ler, Escrita escrever) {
        int opcao;

        do {
            escrever.mostrarSubMenu("DOCENTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1)
                cadastro.docente(ler, docentes);

        } while (opcao != 2);

        return;
    }

    public void subMenu3(Leitura ler, Escrita escrever) {
        int opcao;
        do {
            escrever.mostrarSubMenu("DISCIPLINA");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1)
                cadastro.disciplina(ler, periodos, docentes, disciplinas);

        } while (opcao != 2);

        return;
    }

    public void subMenu4(Leitura ler, Escrita escrever) {
        int opcao;

        do {

            escrever.mostrarSubMenu("ESTUDANTE");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1)
                cadastro.estudante(ler, estudantes);

        } while (opcao != 2);

        return;
    }

    public void subMenu5(Leitura ler, Escrita escrever) {
        int opcao;

        do {

            escrever.mostrarSubMenu("ESTUDANTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1)
                cadastro.estudanteEmDisciplina(ler, disciplinas, estudantes);

        } while (opcao != 2);

        return;
    }

    public void subMenu6(Leitura ler, Escrita escrever) {
        int opcao;

        do {
            escrever.mostrarSubMenu("ATIVIDADE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1)
                cadastro.atividadeEmDisciplina(ler, disciplinas);

        } while (opcao != 2);

        return;
    }

    public void subMenu7(Leitura ler, Escrita escrever) {
        int opcao;

        do {

            escrever.mostrarSubMenu("AVALIACAO");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1)
                cadastro.avaliacaoEmAtividade(ler, disciplinas, estudantes);

        } while (opcao != 2);

        return;
    }

    public void subMenu8(Leitura ler, Escrita escrever) {
        Pattern patternLogin = Pattern.compile("^[a-z]+(?:\\.[a-z]?)?\\.[a-z]+$");
        Pattern patternPeriodo = Pattern.compile("\\d{4}/[A-Z 0-9]{1}");
        int opcao;
        String periodoRef = null;
        String docenteRef = null;

        do {
            escrever.mostrarSubMenuRelatorios();
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                boolean keepGoing = false;
                do {
                    try {
                        relatorios.periodosCadastrados(escrever, periodos);
                        escrever.digiteRef("periodo");
                        periodoRef = ler.cadeiaCaract();
                        if (!patternPeriodo.matcher(periodoRef).matches())
                            throw new IllegalArgumentException();
                        Periodo periodo = periodos.get(periodoRef);
                        relatorios.estatisticasPeriodo(ler, escrever, periodo);
                        keepGoing = false;
                    } catch (IllegalArgumentException e) {
                        escrever.invalidReferencia(periodoRef);
                        keepGoing = true;
                    } catch (NullPointerException e) {
                        escrever.naoEncontrado();
                        keepGoing = true;
                    }
                } while (keepGoing);

            }

            else if (opcao == 2)
                relatorios.estatisticasDocentes(escrever, docentes);

            else if (opcao == 3)
                relatorios.estatisticasEstudantes(escrever, estudantes);

            else if (opcao == 4) {
                boolean keepGoing = false;
                do {
                    try {
                        relatorios.docentesCadastrados(escrever, docentes);
                        escrever.digiteRef("docente");
                        docenteRef = ler.cadeiaCaract();
                        if (!patternLogin.matcher(docenteRef).matches())
                            throw new IllegalArgumentException();
                        Docente docente = docentes.get(docenteRef);
                        relatorios.estatisticasDisciplinasDeDocente(escrever, docente);
                        keepGoing = false;
                    } catch (IllegalArgumentException e) {
                        escrever.invalidReferencia(docenteRef);
                        keepGoing = true;
                    } catch (NullPointerException e) {
                        escrever.naoEncontrado();
                        keepGoing = true;
                    }
                } while (keepGoing);
            }

        } while (opcao != 5);

        return;
    }

    public List<Periodo> exportarPeriodos() {
        return new ArrayList<Periodo>(periodos.values());
    }

    public List<Docente> exportarDocentes() {
        return new ArrayList<Docente>(docentes.values());
    }

    public List<Disciplina> exportarDisciplinas() {
        return new ArrayList<Disciplina>(disciplinas.values());
    }

    public List<Estudante> exportarEstudantes() {
        return new ArrayList<Estudante>(estudantes.values());
    }

    public void restaurarPeriodos(List<Periodo> periodos) {
        for (Periodo periodo : periodos)
            this.periodos.put(periodo.obterRef(), periodo);
    }

    public void restaurarDocentes(List<Docente> docentes) {
        for (Docente docente : docentes)
            this.docentes.put(docente.obterRef(), docente);
    }

    public void restaurarDisciplinas(List<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas)
            this.disciplinas.put(disciplina.obterRef(), disciplina);
    }

    public void restaurarEstudantes(List<Estudante> estudantes) {
        for (Estudante estudante : estudantes)
            this.estudantes.put(estudante.obterRef(), estudante);
    }

}
