import java.util.*;

public class Menu {

    Escrita escrever = new Escrita();

    public void menuPrincipal() {
        escrever.mostrarMenu();
    }

    public void subMenu1(Leitura ler, Map<String, Periodo> periodos) {
        int opcao;

        do {

            if (periodos.size() == 0) {
                escrever.notFound("periodos");
            } else {
                escrever.titleRelatorio("Periodos");
                for (String chave : periodos.keySet()) {
                    escrever.showSomething(chave);
                }

            }

            escrever.mostrarSubMenu(escrever, "PERIODO");
            opcao = ler.inteiro();

            if (opcao == 1) {
                escrever.digiteAno();
                int ano = ler.inteiro();
                char semestre = ler.caract();
                periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));
            }

        } while (opcao != 2);

        return;

    }

    public void subMenu2(Leitura ler, Map<String, Docente> docentes) {
        int opcao;

        do {

            if (docentes.size() == 0) {
                escrever.notFound("docentes");
            } else {
                escrever.titleRelatorio("Docentes");
                for (String chave : docentes.keySet()) {
                    escrever.showSomething(chave);
                }
            }

            escrever.mostrarSubMenu(escrever, "DOCENTE");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {

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

        } while (opcao != 2);

        return;
    }

    public void subMenu3(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {
        int opcao;
        do {

            if (disciplinas.size() == 0) {
                escrever.notFound("disciplinas");

            } else {
                escrever.titleRelatorio("Disciplinas");
                for (String chave : disciplinas.keySet()) {
                    escrever.showSomething(chave);
                }
            }

            escrever.mostrarSubMenu(escrever, "DISCIPLINA");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {

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

                Disciplina disciplina = disciplinas.put(codigo + "-" + periodoRef,
                        new Disciplina(codigo, nome, periodo, docente));

                periodo.adicionarDisciplina(disciplina);
                docente.adicionarDisciplina(disciplina);

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu4(Leitura ler, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            if (estudantes.size() == 0) {
                escrever.notFound("estudantes");
            } else {
                escrever.titleRelatorio("Estudantes");
                for (Integer chave : estudantes.keySet()) {
                    escrever.showSomething(Integer.toString(chave));
                }
            }

            escrever.mostrarSubMenu(escrever, "ESTUDANTE");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {

                escrever.digiteMatricula();
                int matricula = ler.inteiro();
                ler.cadeiaCaract();
                escrever.digiteNome("estudante");
                String nome = ler.cadeiaCaract();

                estudantes.put(matricula, new Estudante(matricula, nome));

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu5(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "ESTUDANTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {

                escrever.digiteRef("estudante");
                int estudanteRef = ler.inteiro();
                ler.cadeiaCaract();

                Estudante estudante = estudantes.get(estudanteRef);

                estudante.exibirDisciplinas();

                escrever.digiteRef("disciplina");
                String disciplinaRef = ler.cadeiaCaract();

                Disciplina disciplina = disciplinas.get(disciplinaRef);

                estudante.adicionarDisciplina(disciplina);
                disciplina.adicionarEstudante(estudante);

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu6(Leitura ler, Map<String, Disciplina> disciplinas) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "ATIVIDADE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {

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

        } while (opcao != 2);

        return;
    }

    public void subMenu7(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "AVALIACAO");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
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

        } while (opcao != 2);

        return;
    }

}