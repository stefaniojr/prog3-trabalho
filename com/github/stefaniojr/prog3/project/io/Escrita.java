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

public class Escrita implements Serializable {
    private static final long serialVersionUID = 1348633635465464574L;
    private static final String SEPARADOR = ";";

    // Cabeçalhos conhecidos dos arquivos de saída.
    private static final String CABECALHO_VISAOGERAL = "Período" + SEPARADOR + "Código Disciplina" + SEPARADOR
            + "Disciplina" + SEPARADOR + "Docente Responsável" + SEPARADOR + "E-mail Docente" + SEPARADOR
            + "Qtd. Estudantes" + SEPARADOR + "Qtd. Atividades";
    private static final String CABECALHO_DOCENTES = "Docente" + SEPARADOR + "Qtd. Disciplinas" + SEPARADOR
            + "Qtd. Períodos" + SEPARADOR + "Média Atividades/Disciplina" + SEPARADOR + "% Síncronas" + SEPARADOR
            + "% Assíncronas" + SEPARADOR + "Média de Notas";
    private static final String CABECALHO_ESTUDANTES = "Matrícula" + SEPARADOR + "Nome" + SEPARADOR
            + "Média Disciplinas/Período" + SEPARADOR + "Média Avaliações/Disciplina" + SEPARADOR
            + "Média Notas Avaliações";
    private static final String CABECALHO_DISCIPLINAS = "Docente" + SEPARADOR + "Período" + SEPARADOR + "Código"
            + SEPARADOR + "Nome" + SEPARADOR + "Qtd. Atividades" + SEPARADOR + "% Síncronas" + SEPARADOR
            + "% Assíncronas" + SEPARADOR + "CH" + SEPARADOR + "Datas Avaliações";

    // Variáveis para tratamento das saídas de relatórios.
    private File saidaVisaoGeral;
    private File saidaDocentes;
    private File saidaEstudantes;
    private File saidaDisciplinas;

    public Escrita(File saidaVisaoGeral, File saidaDocentes, File saidaEstudantes, File saidaDisciplinas) {
        this.saidaVisaoGeral = saidaVisaoGeral;
        this.saidaDocentes = saidaDocentes;
        this.saidaEstudantes = saidaEstudantes;
        this.saidaDisciplinas = saidaDisciplinas;
    }

    // Método responsável por gerar o relatório 1-visao-geral.csv.
    public void relatarVisaoGeral(List<Periodo> periodos) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaVisaoGeral)) {
            out.printf("%s%n", CABECALHO_VISAOGERAL);
            // Itera o HashMap de disciplinas extraindo as informações requeridas através de
            // métodos específicos da classe.
            for (Periodo periodo : periodos) {
                List<Disciplina> disciplinas = periodo.obterDisciplinasPorNome();
                for (Disciplina disciplina : disciplinas) {
                    out.printf("%s%s%s%s%s%s%s%s%s%s%d%s%d%n", periodo.obterRef(), SEPARADOR, disciplina.obterCodigo(),
                            SEPARADOR, disciplina.obterNome(), SEPARADOR, disciplina.obterDocente().obterNome(),
                            SEPARADOR, disciplina.obterDocente().obterEmail(), SEPARADOR,
                            disciplina.obterNumeroDeAlunosMatriculados(), SEPARADOR,
                            disciplina.obterNumeroDeAtividades());
                }
            }

        }
    }

    // Método responsável por gerar o relatório 2-docentes.csv.
    public void relatarDocentes(List<Docente> docentes) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaDocentes)) {
            out.printf("%s%n", CABECALHO_DOCENTES);
            // Itera o HashMap de docentes extraindo as informações requeridas através de
            // métodos específicos da classe.
            for (Docente docente : docentes) {
                // Método responsável por já calcular todas as estatísticas requeridas e deixar
                // salvas em atributos da classe, prontinhas para serem chamadas pelos getters.
                docente.calcularEstatisticasDeDocente();
                out.printf("%s%s%d%s%d%s%.1f%s%d%s%s%d%s%s%.1f%n", docente.obterNome(), SEPARADOR,
                        docente.obterNumeroDeDisciplinas(), SEPARADOR, docente.obterNumeroDePeriodos(), SEPARADOR,
                        docente.obterMediaAtividadesPorDisciplina(), SEPARADOR,
                        docente.obterPercentualAtividadesSincronas(), "%", SEPARADOR,
                        docente.obterPercentualAtividadesAssincronas(), "%", SEPARADOR, docente.obterMediaAvaliacoes());
            }

        }
    }

    // Método responsável por gerar o relatório 3-estudantes.csv.
    public void relatarEstudantes(List<Estudante> estudantes) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaEstudantes)) {
            out.printf("%s%n", CABECALHO_ESTUDANTES);
            // Itera o HashMap de estudantes extraindo as informações requeridas através de
            // métodos específicos da classe.
            for (Estudante estudante : estudantes) {
                // Nota: diferentemente da classe Docente, aqui NÃO temos um método específico
                // que
                // carrega estatísticas, pois as estatísticas para estudantes são pedidas em
                // menor número.
                out.printf("%d%s%s%s%.1f%s%.1f%s%.1f%n", estudante.obterMatricula(), SEPARADOR, estudante.obterNome(),
                        SEPARADOR, estudante.obterMediaDeDisciplinasPorPeriodo(), SEPARADOR,
                        estudante.obterMediaDeAvaliacoesPorDisciplina(), SEPARADOR, estudante.obterMediaNotas());
            }

        }
    }

    // Método responsável por gerar o relatório 4-disciplinas.csv.
    public void relatarDisciplinas(List<Periodo> periodos) throws IOException {
        try (PrintWriter out = new PrintWriter(saidaDisciplinas)) {
            out.printf("%s%n", CABECALHO_DISCIPLINAS);
            // Itera o HashMap de disciplinas extraindo as informações requeridas através de
            // métodos específicos da classe.
            for (Periodo periodo : periodos) {
                List<Disciplina> disciplinas = periodo.obterDisciplinasPorCodigo();
                for (Disciplina disciplina : disciplinas) {
                    out.printf("%s%s%s%s%s%s%s%s%d%s%d%s%s%d%s%s%d%s%s%n", disciplina.obterDocente().obterRef(),
                            SEPARADOR, periodo.obterRef(), SEPARADOR, disciplina.obterCodigo(), SEPARADOR,
                            disciplina.obterNome(), SEPARADOR, disciplina.obterNumeroDeAtividades(), SEPARADOR,
                            disciplina.obterPercentualAtividadesSincronas(), "%", SEPARADOR,
                            disciplina.obterPercentualAtividadesAssincronas(), "%", SEPARADOR,
                            disciplina.obterCargaHorariaDisciplina(), SEPARADOR, disciplina.obterDataAvaliacoes());
                }

            }
        }
    }
}