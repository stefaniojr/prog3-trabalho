package com.github.stefaniojr.prog3.project.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

import com.github.stefaniojr.prog3.project.domain.Disciplina;
import com.github.stefaniojr.prog3.project.domain.Docente;
import com.github.stefaniojr.prog3.project.domain.Estudante;
import com.github.stefaniojr.prog3.project.domain.Periodo;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Escrita implements Serializable {

    // Bloco de cabeçalhos conhecidos dos arquivos de saída:
    private static final String SEPARADOR = ";";

    private static final String CABECALHO_VISAOGERAL = "Período" + SEPARADOR + "Código Disciplina" + SEPARADOR
            + "Disciplina" + SEPARADOR + "Docente Responsável" + SEPARADOR + "E-mail Docente" + SEPARADOR
            + "Qtd. Estudantes" + SEPARADOR + "Qtd. Atividades";
    private static final String CABECALHO_DOCENTES = "Login Institucional" + SEPARADOR + "Nome Completo" + SEPARADOR
            + "Endereço Web";
    private static final String CABECALHO_ESTUDANTES = "Matrícula" + SEPARADOR + "Nome Completo";
    private static final String CABECALHO_DISCIPLINAS = "Disciplina (Código-Período)" + SEPARADOR
            + "Estudante (Matrícula)";
    // private static final String CABECALHO_PERIODOS = "Ano" + SEPARADOR +
    // "Semestre";
    // private static final String CABECALHO_DOCENTES = "Login Institucional" +
    // SEPARADOR + "Nome Completo" + SEPARADOR + "Endereço Web";
    // private static final String CABECALHO_OFERTA = "Período" + SEPARADOR +
    // "Código" + SEPARADOR + "Nome" + SEPARADOR + "Docente Responsável";
    // private static final String CABECALHO_ESTUDANTES = "Matrícula" + SEPARADOR +
    // "Nome Completo";
    // private static final String CABECALHO_MATRICULAS = "Disciplina
    // (Código-Período)" + SEPARADOR + "Estudante (Matrícula)";
    // private static final String CABECALHO_ATIVIDADES = "Disciplina
    // (Código-Período)" + SEPARADOR + "Nome" + SEPARADOR + "Tipo" + SEPARADOR +
    // "Data / Prazo" + SEPARADOR + "Hora" + SEPARADOR + "Materiais / Conteúdo" +
    // SEPARADOR + "Tamanho Máximo Grupos" + SEPARADOR + "Carga Horária Esperada";
    // private static final String CABECALHO_NOTAS = "Disciplina (Código-Período)" +
    // SEPARADOR + "Estudante (Matrícula)" + SEPARADOR + "# Atividade" + SEPARADOR +
    // "Nota";

    // Bloco de variáveis para tratamento das saídas de relatórios e/ou serialização
    // e desserialização:
    private File arquivoSerializacao;
    private File saidaVisaoGeral;
    private File saidaDocentes;
    private File saidaEstudantes;
    private File saidaDisciplinas;

    public Escrita(File arquivoSerializacao, File saidaVisaoGeral, File saidaDocentes, File saidaEstudantes,
            File saidaDisciplinas) {
        this.arquivoSerializacao = arquivoSerializacao;
        this.saidaVisaoGeral = saidaVisaoGeral;
        this.saidaDocentes = saidaDocentes;
        this.saidaEstudantes = saidaEstudantes;
        this.saidaDisciplinas = saidaDisciplinas;
    }

    public void relatarVisaoGeral(Map<String, Periodo> periodos) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaVisaoGeral)) {
            out.printf("%s%n", CABECALHO_VISAOGERAL);

            for (String chaveP : periodos.keySet()) {
                Map<String, Disciplina> disciplinas = periodos.get(chaveP).obterDisciplinas();
                out.printf("%s%s", periodos.get(chaveP).obterRef(), SEPARADOR);
                for (String chaveD : disciplinas.keySet()) {
                    out.printf("%s%s%s%s%s%s%d%s%d%n", disciplinas.get(chaveD).obterCodigo(), SEPARADOR,
                            disciplinas.get(chaveD).obterNome(), SEPARADOR,
                            disciplinas.get(chaveD).obterDocente().obterNome(), SEPARADOR,
                            disciplinas.get(chaveD).obterNumeroDeAlunosMatriculados(), SEPARADOR,
                            disciplinas.get(chaveD).obterNumeroDeAtividades());
                }
            }
        }
    }

    public void relatarDocentes(Map<String, Docente> docentes) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaDocentes)) {
            out.printf("%s%n", CABECALHO_DOCENTES);

            for (String chave : docentes.keySet()) {
                out.printf("%s%s%s%s%s%n", docentes.get(chave).obterLogin(), SEPARADOR, docentes.get(chave).obterNome(),
                        SEPARADOR, docentes.get(chave).obterSite());
            }
        }
    }

    public void relatarEstudantes(Map<Integer, Estudante> estudantes) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaEstudantes)) {
            out.printf("%s%n", CABECALHO_ESTUDANTES);

            for (Integer chave : estudantes.keySet()){
                out.printf("%d%s%s%n", estudantes.get(chave).obterMatricula(), SEPARADOR, estudantes.get(chave).obterNome());
            }
        }
    }

    public void relatarDisciplinas(Map<String, Disciplina> disciplinas) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaDisciplinas)) {
            out.printf("%s%n", CABECALHO_DISCIPLINAS);

            for (String chaveD : disciplinas.keySet()) {
                Map<Integer, Estudante> estudantes =  disciplinas.get(chaveD).obterEstudantes();
                out.printf("%s%s", disciplinas.get(chaveD).obterRef(), SEPARADOR);
                for (Integer chaveE : estudantes.keySet()){
                    out.printf("%d%n", estudantes.get(chaveE).obterMatricula());
                }
            }
        }
    }

    /** Relatorios messages */
    public void notFound(String isso) {
        System.out.println("Nao ha " + isso + " cadastrados(as)! :(");
    }

    public void titleRelatorio(String isso) {
        System.out.println(isso + " cadastrados(as):");
    }

    public void showSomething(String something) {
        System.out.println(something);
    }

    public void docenteCadastrado(String nome, int nPeriodos, float mediaAtividades, float percentualSincrona,
            float mediaAvaliacoesEmAtividades) {
        System.out.println("____________________________________");
        System.out.println("Docente: " + nome);
        System.out.println(
                "Numero de periodos: " + nPeriodos + " | Media de atividades por disciplina:  " + mediaAtividades);
        System.out.println("Percentual de atividades sincronas: " + percentualSincrona
                + " | Media de avaliacoes recebidas em atividades:  " + mediaAvaliacoesEmAtividades);
        System.out.println("____________________________________");
    }

    public void disciplinaCadastrada(String ref, String nomeDisciplina, String nomeDocente, String emailDocente,
            int nEstudantes, int nAtividades) {
        System.out.println("____________________________________");

        System.out.println("- " + ref + " | Disciplina: " + nomeDisciplina);
        System.out.println("Docente responsavel: " + nomeDocente + " | E-mail do docente: " + emailDocente);
        System.out.println("Numero de estudantes matriculados: " + nEstudantes + " | Numero de atividades propostas: "
                + nAtividades);

        System.out.println("____________________________________");
    }

    public void estudanteCadastrado(int matricula, String nome, float mediaDisciplinasPeriodo,
            float mediaAvaliacoesDisciplina, float mediaNotas) {
        System.out.println("____________________________________");

        System.out.println("- Matricula: " + matricula + " | Estudante: " + nome);
        System.out.println("- Media de disciplinas por periodo: " + mediaDisciplinasPeriodo
                + " | Media de avaliacoes por disciplinas: " + mediaAvaliacoesDisciplina
                + " | Media das notas avaliadas: " + mediaNotas);

        System.out.println("____________________________________");
    }

    public void avaliacaoCadastrada(String nome, float nota) {
        System.out.println("- Estudante " + nome + " avaliou essa atividade com nota " + nota + ".");
    }

    public void atividadeCadastrada(int numero, String nome, String sincronismo) {
        System.out.println("- Codigo: " + numero + " | Atividade: " + nome + " | Sicronismo: " + sincronismo);
    }

    public void atividades(Map<Integer, Atividade> atividades) {
        System.out.println("Atividades:");
        for (Integer chave : atividades.keySet()) {
            System.out
                    .println(atividades.get(chave).obterNumeroSequencial() + " - " + atividades.get(chave).obterNome());
        }

    }

    public void disciplinasDeDocente(String periodo, String codigo, String nome, int nAtividades,
            float percentualSincronas, int cargaHoraria, Map<Integer, Atividade> atividadesAvaliativas) {
        System.out.println("____________________________________");

        System.out.println("- Periodo" + periodo + " | Codigo: " + codigo + " | Nome: " + nome);
        System.out.println("Numero de atividades: " + nAtividades + " | Percentual de atividades sincronas: "
                + percentualSincronas + " | Carga Horaria: " + cargaHoraria);
        atividades(atividadesAvaliativas);
        System.out.println("____________________________________");
    }

    /** Fim relatorios messages */

    /** Feedback messages */
    public void sucess(String isso) {
        System.out.println(isso + " cadastrado(a) com sucesso!");
    }

    public void foi() {
        System.out.println("foooi!");
    }

    public void error(String isso) {
        System.out.println("Nao foi possivel cadastrar o(a) " + isso + "!");
    }

    public void encontrado(String isso) {
        System.out.println(isso + " encontrado(a)!");
    }

    public void saindo(String disso) {
        System.out.println("Saindo do(a) " + disso + "...");
    }

    public void finalizado(String s) {
        System.out.println(s + " finalizado(a)!");
    }

    /** Fim feedback messages */

    /** Controle de erros messages */
    public void invalidData(String s) {
        System.out.println("Dado invalido: " + s);
    }

    public void invalidReferencia(String s) {
        System.out.println("Referencia invalida: " + s);
    }

    public void naoEncontrado() {
        System.out.println("Nao encontrado!");
    }

    public void problemInt() {
        System.out.println("Nao encontrado!");
    }

    public void cadastroRepetido(String s) {
        System.out.println("Cadastro repetido: " + s);
    }

    public void matriculaRepetida(int matricula, String disciplina) {
        System.out.println("Matricula repetida: " + matricula + " em " + disciplina + ".");
    }

    public void avaliacaoRepetida(int matricula, String disciplina, int numeroAtividade) {
        System.out.println("Avaliacao repetida: estudante " + matricula + " para atividade " + numeroAtividade + " de "
                + disciplina + ".");
    }

    public void dataFormatoErrado() {
        System.out.println("Formato incorreto de data.");
    }

    public void erroIO() {
        System.out.println("Erro de I/O.");
    }

    /** Fim controle de erros messages */
}