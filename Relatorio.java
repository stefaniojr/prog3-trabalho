import java.util.*;

public class Relatorio {

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
                escrever.showSomething(Integer.toString(chave));
                escrever.showSomething(atividades.get(chave).obterNome());
                escrever.showSomething(atividades.get(chave).obterSincronismo());
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
                escrever.disciplinaCadastrada(disciplinas.get(chave).obterRef(), disciplinas.get(chave).obterNome(),
                        disciplinas.get(chave).obterDocente().obterNome(),
                        disciplinas.get(chave).obterDocente().obterLogin(),
                        disciplinas.get(chave).obterNumeroDeAlunosMatriculados(),
                        disciplinas.get(chave).obterNumeroDeAtividades());
            }
        }

    }

    public void estatisticasDocentes(Escrita escrever, Map<String, Docente> docentes) {
        if (docentes.size() == 0) {
            escrever.notFound("docentes");
        } else {
            escrever.titleRelatorio("Docentes");
            for (String chave : docentes.keySet()) {
                docentes.get(chave).calcularEstatisticasDeDocente();
                escrever.docenteCadastrado(docentes.get(chave).obterNome(), docentes.get(chave).obterNumeroDePeriodos(),
                        docentes.get(chave).obterMediaAtividadesPorDisciplina(),
                        docentes.get(chave).obterPercentualAtividadesSincronas(),
                        docentes.get(chave).obterMediaAvaliacoes());
            }
        }
    }

    public void estatisticasEstudantes(Escrita escrever, Map<Integer, Estudante> estudantes) {
        if (estudantes.size() == 0) {
            escrever.notFound("estudantes");
        } else {
            escrever.titleRelatorio("Estudantes");
            for (Integer chave : estudantes.keySet()) {
                escrever.estudanteCadastrado(estudantes.get(chave).obterRef(), estudantes.get(chave).obterNome(),
                        estudantes.get(chave).obterMediaDeDisciplinasPorPeriodo(),
                        estudantes.get(chave).obterMediaDeAvaliacoesPorDisciplina(),
                        estudantes.get(chave).obterMediaNotas());
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
                escrever.disciplinasDeDocente(disciplinas.get(chave).obterPeriodo().obterRef(),
                        disciplinas.get(chave).obterCodigo(), disciplinas.get(chave).obterNome(),
                        disciplinas.get(chave).obterNumeroDeAtividades(),
                        disciplinas.get(chave).obterPercentualAtividadesSincronas(),
                        disciplinas.get(chave).obterCargaHorariaDisciplina(),
                        disciplinas.get(chave).obterAtividadesAvaliativas());
            }
        }
    }

}
