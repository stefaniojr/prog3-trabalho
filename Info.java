import java.util.*;

public class Info {

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

    public void turmasFormadas(Escrita escrever, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes){
        if (disciplinas.size() == 0 && estudantes.size() == 0) {
            escrever.notFound("turmas");
        } else {
            escrever.titleRelatorio("Turmas");
            for (String chave : disciplinas.keySet()) {
                escrever.showSomething(disciplinas.get(chave).obterNome());
                disciplinas.get(chave).exibirEstudantes();
                escrever.showAsterisks();
            }
        }
    }

}
