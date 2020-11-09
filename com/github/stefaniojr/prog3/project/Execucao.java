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

    Cadastro cadastrar = new Cadastro();
    Leitura ler = new Leitura();

    // HashMaps com as informações essenciais da aplicação.
    Map<String, Periodo> periodos = new HashMap<>();
    Map<String, Docente> docentes = new HashMap<>();
    Map<String, Disciplina> disciplinas = new HashMap<>();
    Map<BigInteger, Estudante> estudantes = new HashMap<>();

    // Vetores de strings auxiliares para processamento de dados importados de planilhas.
    String[] dadosPeriodos = null;
    String[] dadosDocentes = null;
    String[] dadosDisciplinas = null;
    String[] dadosEstudantes = null;
    String[] dadosMatriculas =  null;
    String[] dadosAtividades = null;
    String[] dadosAvaliacoes = null; 

    // Métodos responsáveis por transformar os HashMaps em Lists. Chamados durante serializações.
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

    // Métodos responsáveis por transformar as Lists em HashMaps. Chamados durante desserializações.

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

    // Método responsável por realizar chamadas aos métodos que geram relatórios.
    public void gerarRelatorios(Escrita escrever) throws IOException {
        escrever.relatarVisaoGeral(disciplinas);
        escrever.relatarDocentes(docentes);
        escrever.relatarEstudantes(estudantes);
        escrever.relatarDisciplinas(disciplinas);
    }

    // Método responsável por carregar planilhas com dados de entrada em vetores de strings a serem manipulados posteriormente.
    public void carregarPlanilhas(String arqPeriodos, String arqDocentes, String arqOferta, String arqEstudantes, String arqMatriculas, String arqAtividades, String arqNotas) throws IOException, ParseException, FileNotFoundException{
      dadosPeriodos = ler.planilha(new File(arqPeriodos));
      dadosDocentes = ler.planilha(new File(arqDocentes));
      dadosDisciplinas = ler.planilha(new File(arqOferta));
      dadosEstudantes = ler.planilha(new File(arqEstudantes));
      dadosMatriculas = ler.planilha(new File(arqMatriculas));
      dadosAtividades = ler.planilha(new File(arqAtividades));
      dadosAvaliacoes = ler.planilha(new File(arqNotas));
    }

    // Métodos responsáveis por cadastrar as informações de entrada no sistema.
    public void cadastrarPeriodos(){
        cadastrar.periodos(dadosPeriodos, periodos);
    } 

    public void cadastrarDocentes(){
        cadastrar.docentes(dadosDocentes, docentes);
    }

    public void cadastrarDisciplinas(){
        cadastrar.disciplinas(dadosDisciplinas, periodos, docentes, disciplinas);
    }

    public void cadastrarEstudantes(){
        cadastrar.estudantes(dadosEstudantes, estudantes);
    }

    public void matricularEstudantes(){
        cadastrar.estudanteEmDisciplina(dadosMatriculas, disciplinas, estudantes);
    }

    public void cadastrarAtividades(){
        cadastrar.atividadesEmDisciplina(dadosAtividades, disciplinas);
    }

    public void cadastrarAvaliacoes(){
        cadastrar.avaliacoesEmAtividade(dadosAvaliacoes, disciplinas, estudantes);
    }
}
