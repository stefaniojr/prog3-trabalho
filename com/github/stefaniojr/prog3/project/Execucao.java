package com.github.stefaniojr.prog3.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.math.BigInteger;

import com.github.stefaniojr.prog3.project.domain.*;
import com.github.stefaniojr.prog3.project.io.*;

public class Execucao implements Serializable {
    private static final long serialVersionUID = 1348633635465464577L;
    Cadastro cadastrar = new Cadastro();
    Leitura ler = new Leitura();

    // HashMaps com as informações essenciais da aplicação.
    Map<String, Periodo> periodos = new HashMap<>();
    Map<String, Docente> docentes = new HashMap<>();
    Map<String, Disciplina> disciplinas = new HashMap<>();
    Map<BigInteger, Estudante> estudantes = new HashMap<>();

    // Vetores de strings auxiliares para processamento de dados importados de
    // planilhas.
    String[] dadosPeriodos = null;
    String[] dadosDocentes = null;
    String[] dadosDisciplinas = null;
    String[] dadosEstudantes = null;
    String[] dadosMatriculas = null;
    String[] dadosAtividades = null;
    String[] dadosAvaliacoes = null;

    // Métodos responsáveis por transformar os HashMaps em Lists. Chamados durante
    // serializações.
    public List<Periodo> hashMapToListPeriodos() {
        List<Periodo> periodosList = new ArrayList<Periodo>(periodos.values());
        Collections.sort(periodosList);
        return periodosList;
    }

    public List<Docente> hashMapToListDocentes() {
        List<Docente> docentesList = new ArrayList<Docente>(docentes.values());
        Collections.sort(docentesList, Collections.reverseOrder());
        return docentesList;
    }

    public List<Disciplina> hashMapToListDisciplinas() {
        List<Disciplina> disciplinasList = new ArrayList<Disciplina>(disciplinas.values());
        Collections.sort(disciplinasList);
        return disciplinasList;
    }

    public List<Estudante> hashMapToListEstudantes() {
        List<Estudante> estudantesList = new ArrayList<Estudante>(estudantes.values());
        Collections.sort(estudantesList);
        return estudantesList;
    }

    // Métodos responsáveis por transformar as Lists em HashMaps. Chamados durante
    // desserializações.
    public void listToHashMapPeriodos(List<Periodo> periodos) {
        for (Periodo periodo : periodos)
            this.periodos.put(periodo.obterRef(), periodo);
    }

    public void listToHashMapDocentes(List<Docente> docentes) {
        for (Docente docente : docentes)
            this.docentes.put(docente.obterRef(), docente);
    }

    public void listToHashMapDisciplinas(List<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas)
            this.disciplinas.put(disciplina.obterRef(), disciplina);
    }

    public void listToHashMapEstudantes(List<Estudante> estudantes) {
        for (Estudante estudante : estudantes)
            this.estudantes.put(estudante.obterRef(), estudante);
    }

    // Método responsável por realizar chamadas aos métodos que geram relatórios.
    public void gerarRelatorios(Escrita escrever) throws IOException {
        escrever.relatarVisaoGeral(hashMapToListPeriodos());
        escrever.relatarDocentes(hashMapToListDocentes());
        escrever.relatarEstudantes(hashMapToListEstudantes());
        escrever.relatarDisciplinas(hashMapToListPeriodos());
    }

    // Método responsável por carregar planilhas com dados de entrada em vetores de
    // strings a serem manipulados posteriormente.
    public void carregarPlanilhas(String arqPeriodos, String arqDocentes, String arqOferta, String arqEstudantes,
            String arqMatriculas, String arqAtividades, String arqNotas)
            throws IOException, ParseException, FileNotFoundException {
        dadosPeriodos = ler.planilha(new File(arqPeriodos));
        dadosDocentes = ler.planilha(new File(arqDocentes));
        dadosDisciplinas = ler.planilha(new File(arqOferta));
        dadosEstudantes = ler.planilha(new File(arqEstudantes));
        dadosMatriculas = ler.planilha(new File(arqMatriculas));
        dadosAtividades = ler.planilha(new File(arqAtividades));
        dadosAvaliacoes = ler.planilha(new File(arqNotas));
    }

    // Métodos responsáveis por cadastrar as informações de entrada no sistema.
    public void cadastrarPeriodos() throws RuntimeException {
        cadastrar.periodos(dadosPeriodos, periodos);
    }

    public void cadastrarDocentes() throws RuntimeException {
        cadastrar.docentes(dadosDocentes, docentes);
    }

    public void cadastrarDisciplinas() throws RuntimeException {
        cadastrar.disciplinas(dadosDisciplinas, periodos, docentes, disciplinas);
    }

    public void cadastrarEstudantes() throws RuntimeException {
        cadastrar.estudantes(dadosEstudantes, estudantes);
    }

    public void matricularEstudantes() throws RuntimeException {
        cadastrar.estudanteEmDisciplina(dadosMatriculas, disciplinas, estudantes);
    }

    public void cadastrarAtividades() throws RuntimeException {
        cadastrar.atividadesEmDisciplina(dadosAtividades, disciplinas);
    }

    public void cadastrarAvaliacoes() throws RuntimeException {
        cadastrar.avaliacoesEmAtividade(dadosAvaliacoes, disciplinas, estudantes);
    }
}
