package com.github.stefaniojr.prog3.project.report;

import java.io.Serializable;
import java.util.*;
import com.github.stefaniojr.prog3.project.domain.*;
import com.github.stefaniojr.prog3.project.domain.atividades.*;
import com.github.stefaniojr.prog3.project.io.*;

public class Relatorio implements Serializable {

    public void periodosCadastrados(Escrita escrever, Map<String, Periodo> periodos) {
        if (periodos.size() == 0) {
            escrever.notFound("periodos");
        } else {
            escrever.titleRelatorio("Periodos");
            for (String chave : periodos.keySet()) {
                escrever.showSomething(chave);
            }

        }
    }

    public void disciplinasCadastradas(Escrita escrever, Map<String, Disciplina> disciplinas) {
        if (disciplinas.size() == 0) {
            escrever.notFound("disciplinas");

        } else {
            escrever.titleRelatorio("Disciplinas");
            for (String chave : disciplinas.keySet()) {
                escrever.showSomething(disciplinas.get(chave).obterNome());
            }
        }
    }

    public void docentesCadastrados(Escrita escrever, Map<String, Docente> docentes) {
        if (docentes.size() == 0) {
            escrever.notFound("docentes");

        } else {
            escrever.titleRelatorio("Docentes");
            for (String chave : docentes.keySet()) {
                escrever.showSomething(docentes.get(chave).obterNome());
            }
        }
    }

    public void estudantesCadastrados(Escrita escrever, Map<Integer, Estudante> estudantes) {
        if (estudantes.size() == 0) {
            escrever.notFound("estudantes");
        } else {
            escrever.titleRelatorio("Estudantes");
            for (Integer chave : estudantes.keySet()) {
                escrever.showSomething(estudantes.get(chave).obterNome());
            }
        }
    }

    public void atividadesCadastradas(Escrita escrever, Map<Integer, Atividade> atividades) {
        if (atividades.size() == 0) {
            escrever.notFound("atividades");
        } else {
            escrever.titleRelatorio("Atividades");
            for (Integer chave : atividades.keySet()) {
                Atividade atividade = atividades.get(chave);
                escrever.showSomething(Integer.toString(chave));
                escrever.showSomething(atividade.obterNome());
                escrever.showSomething(atividade.obterSincronismo());
                escrever.showAsterisks();
            }
        }
    }

    public void avaliacoesCadastradas(Escrita escrever, List<Avaliacao> avaliacoes) {
        if (avaliacoes.size() == 0) {
            escrever.notFound("avaliacoes");
        } else {
            escrever.titleRelatorio("Avaliacoes");
            for (Avaliacao avaliacao : avaliacoes) {
                escrever.showSomething(avaliacao.obterAvaliador().obterNome());
                escrever.showSomething(Float.toString(avaliacao.obterNota()));
                escrever.showAsterisks();
            }
        }
    }

    public void estatisticasPeriodo(Leitura ler, Escrita escrever, Periodo periodo) {

        Map<String, Disciplina> disciplinas = periodo.obterDisciplinas();

        if (disciplinas.size() == 0) {
            escrever.notFound("disciplinas");
        } else {
            for (String chave : disciplinas.keySet()) {
                Disciplina disciplina = disciplinas.get(chave);
                escrever.disciplinaCadastrada(disciplina.obterRef(), disciplina.obterNome(),
                        disciplina.obterDocente().obterNome(), disciplina.obterDocente().obterLogin(),
                        disciplina.obterNumeroDeAlunosMatriculados(), disciplina.obterNumeroDeAtividades());
            }
        }

    }

    public void estatisticasDocentes(Escrita escrever, Map<String, Docente> docentes) {
        if (docentes.size() == 0) {
            escrever.notFound("docentes");
        } else {
            escrever.titleRelatorio("Docentes");
            for (String chave : docentes.keySet()) {
                Docente docente = docentes.get(chave);
                docente.calcularEstatisticasDeDocente();
                escrever.docenteCadastrado(docente.obterNome(), docente.obterNumeroDePeriodos(),
                        docente.obterMediaAtividadesPorDisciplina(), docente.obterPercentualAtividadesSincronas(),
                        docente.obterMediaAvaliacoes());
            }
        }
    }

    public void estatisticasEstudantes(Escrita escrever, Map<Integer, Estudante> estudantes) {
        if (estudantes.size() == 0) {
            escrever.notFound("estudantes");
        } else {
            escrever.titleRelatorio("Estudantes");
            for (Integer chave : estudantes.keySet()) {
                Estudante estudante = estudantes.get(chave);
                escrever.estudanteCadastrado(estudante.obterRef(), estudante.obterNome(),
                        estudante.obterMediaDeDisciplinasPorPeriodo(), estudante.obterMediaDeAvaliacoesPorDisciplina(),
                        estudante.obterMediaNotas());
            }
        }
    }

    public void estatisticasDisciplinasDeDocente(Escrita escrever, Docente docente) {
        Map<String, Disciplina> disciplinas = new HashMap<>();
        disciplinas = docente.obterDisciplinas();

        if (disciplinas.size() == 0) {
            escrever.notFound("disciplinas");
        } else {
            escrever.titleRelatorio("Disciplinas");
            for (String chave : disciplinas.keySet()) {
                Disciplina disciplina = disciplinas.get(chave);
                escrever.disciplinasDeDocente(disciplina.obterPeriodo().obterRef(), disciplina.obterCodigo(),
                        disciplina.obterNome(), disciplina.obterNumeroDeAtividades(),
                        disciplina.obterPercentualAtividadesSincronas(), disciplina.obterCargaHorariaDisciplina(),
                        disciplina.obterAtividadesAvaliativas());
            }
        }
    }

}
