import java.util.*;

public class Cadastro {

    Escrita escrever = new Escrita();

    public void periodo(Leitura ler, Map<String, Periodo> periodos) {
        escrever.digiteAno();
        int ano = ler.inteiro();
        char semestre = ler.caract();
        periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));
    }

    public void docente(Leitura ler, Map<String, Docente> docentes) {
        escrever.digiteNome("docente");
        String nome = ler.cadeiaCaract();

        escrever.digiteLogin();
        String login = ler.cadeiaCaract();
        String semSite = "Docente sem site.";

        escrever.possuiSite();

        char possuiSite = ler.caract();
        ler.cadeiaCaract();

        if (possuiSite == 'S') {
            escrever.digiteSite("docente");
            String comSite = ler.cadeiaCaract();
            docentes.put(login, new Docente(login, nome, comSite));
        } else {
            docentes.put(login, new Docente(login, nome, semSite));
        }
    }

    public void disciplina(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {
        escrever.digiteCodigoDisciplina();
        String codigo = ler.cadeiaCaract();
        escrever.digiteNome("disciplina");
        String nome = ler.cadeiaCaract();
        escrever.digitePeriodo();
        String periodoRef = ler.cadeiaCaract();
        escrever.digiteRef("docente");
        String docenteRef = ler.cadeiaCaract();

        Periodo periodo = periodos.get(periodoRef);
        Docente docente = docentes.get(docenteRef);

        disciplinas.put(codigo + "-" + periodoRef, new Disciplina(codigo, nome, periodo, docente));

        periodo.adicionarDisciplina(disciplinas.get(codigo + "-" + periodoRef));
        docente.adicionarDisciplina(disciplinas.get(codigo + "-" + periodoRef));
    }

    public void estudante(Leitura ler, Map<Integer, Estudante> estudantes) {
        escrever.digiteMatricula();
        int matricula = ler.inteiro();
        ler.cadeiaCaract();
        escrever.digiteNome("estudante");
        String nome = ler.cadeiaCaract();

        estudantes.put(matricula, new Estudante(matricula, nome));
    }

    public void estudanteEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas,
            Map<Integer, Estudante> estudantes) {
        escrever.digiteRef("estudante");
        int estudanteRef = ler.inteiro();
        ler.cadeiaCaract();

        Estudante estudante = estudantes.get(estudanteRef);

        escrever.digiteRef("disciplina");
        String disciplinaRef = ler.cadeiaCaract();

        Disciplina disciplina = disciplinas.get(disciplinaRef);

        estudante.adicionarDisciplina(disciplina);
        disciplina.adicionarEstudante(estudante);
    }

    public void atividadeEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas) {
        escrever.digiteNome("atividade");
        String nome = ler.cadeiaCaract();

        escrever.digiteSincronismo();
        String sincronismo = ler.cadeiaCaract();

        if (!(sincronismo.equals("sincrona"))) {
            sincronismo = "assincrona";
        }

        escrever.digiteRef("disciplina");
        String disciplinaRef = ler.cadeiaCaract();

        Disciplina disciplina = disciplinas.get(disciplinaRef);

        disciplina.adicionarAtividade(nome, sincronismo);
    }

    public void avaliacaoEmAtividade(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        escrever.digiteRef("estudante");
        int estudanteRef = ler.inteiro();
        ler.cadeiaCaract();

        Estudante estudante = estudantes.get(estudanteRef);

        escrever.digiteRef("disciplina");
        String disciplinaRef = ler.cadeiaCaract();

        Disciplina disciplina = disciplinas.get(disciplinaRef);

        escrever.digiteRef("ativididade");
        int numeroAtividade = ler.inteiro();
        ler.cadeiaCaract();

        Atividade atividade = disciplina.obterAtividade(numeroAtividade);

        escrever.digiteNota("estudante");
        float notaAtividade = ler.flutuante();
        ler.cadeiaCaract();

        atividade.avaliarAtividade(estudante, notaAtividade);
    }
}
