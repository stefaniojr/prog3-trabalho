import java.util.*;

public class Menu {

    Escrita escrever = new Escrita();

    public void menuPrincipal() {
        escrever.mostrarMenu();
    }

    public void subMenu1(Leitura ler, ArrayList<Periodo> periodos) {
        int opcao;

        do {

            if (periodos.size() == 0) {
                escrever.naoHa("periodos");
            } else {
                escrever.cadastrados("Periodos");
                for (Periodo periodo : periodos) {
                    escrever.periodoCadastrado(periodo.obterRef());
                }
            }

            escrever.mostrarSubMenu(escrever, "PERIODO");
            opcao = ler.inteiro();

            if (opcao == 1) {
                escrever.digiteAno();
                int ano = ler.inteiro();
                char semestre = ler.caract();
                periodos.add(new Periodo(ano, semestre));
            }

        } while (opcao != 2);

        return;

    }

    public void subMenu2(Leitura ler, ArrayList<Docente> docentes) {
        int opcao;

        do {

            if (docentes.size() == 0) {
                escrever.naoHa("docentes");
            } else {
                escrever.cadastrados("Docentes");
                for (Docente docente : docentes) {
                    escrever.docenteCadastrado(docente.obterNome(), docente.obterRef(), docente.obterSite());
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
                    docentes.add(new Docente(login, nome, comSite));
                } else {
                    docentes.add(new Docente(login, nome, semSite));
                }

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu3(Leitura ler, ArrayList<Periodo> periodos, ArrayList<Docente> docentes,
            ArrayList<Disciplina> disciplinas) {
        int opcao;
        do {

            if (disciplinas.size() == 0) {
                escrever.naoHa("disciplinas");

            } else {
                escrever.cadastrados("Disciplinas");
                for (Disciplina disciplina : disciplinas) {
                    escrever.disciplinaCadastrada(disciplina.obterRef(), disciplina.obterNome(),
                            disciplina.obterDocente().obterNome());

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

                for (Periodo periodo : periodos) {
                    if (periodo.obterRef().equals(periodoRef)) {
                        for (Docente docente : docentes) {
                            if (docente.obterRef().equals(docenteRef)) {
                                if (disciplinas.add(new Disciplina(codigo, nome, periodo, docente))) {
                                    for (Disciplina disciplina : disciplinas) {
                                        if (disciplina.obterRef().equals(codigo + periodo.obterRef())) {
                                            periodo.adicionarDisciplina(disciplina);
                                            docente.adicionarDisciplina(disciplina);
                                        }
                                    }
                                    escrever.sucess("Disciplina");
                                } else {
                                    escrever.error("disciplina");
                                }
                                break;
                            }
                        }
                        break;
                    }
                }

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu4(Leitura ler, ArrayList<Estudante> estudantes) {
        int opcao;

        do {

            if (estudantes.size() == 0) {
                escrever.naoHa("estudantes");
            } else {
                escrever.cadastrados("Estudantes");
                for (Estudante estudante : estudantes) {
                    escrever.estudanteCadastrado(estudante.obterMatricula(), estudante.obterNome());
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

                estudantes.add(new Estudante(matricula, nome));

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu5(Leitura ler, ArrayList<Disciplina> disciplinas, ArrayList<Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "ESTUDANTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {

                escrever.digiteRef("estudante");
                int matricula = ler.inteiro();
                ler.cadeiaCaract();

                for (Estudante estudante : estudantes) {
                    if (estudante.obterRef() == matricula) {

                        Estudante estudanteEncontrado = estudante;
                        escrever.encontrado("Estudante");

                        estudanteEncontrado.exibirDisciplinas();

                        escrever.digiteRef("disciplina");
                        String disciplinaRef = ler.cadeiaCaract();

                        for (Disciplina disciplina : disciplinas) {
                            if (disciplina.obterRef().equals(disciplinaRef)) {
                                estudanteEncontrado.adicionarDisciplina(disciplina);
                                disciplina.adicionarEstudante(estudanteEncontrado);
                                escrever.sucess("Estudante");
                                break;
                            }
                        }
                        break;
                    }
                }

            }

        } while (opcao != 2);

        return;
    }

    public void subMenu6(Leitura ler, ArrayList<Disciplina> disciplinas) {
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

                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.obterRef().equals(disciplinaRef)) {

                        if (disciplina.adicionarAtividade(nome, sincronismo)) {
                            escrever.sucess("Atividade");
                        } else {
                            escrever.error("atividade");
                        }

                        break;
                    }

                }
            }

        } while (opcao != 2);

        return;
    }

    public void subMenu7(Leitura ler, ArrayList<Disciplina> disciplinas, ArrayList<Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "AVALIACAO");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                escrever.digiteRef("estudante");
                int matricula = ler.inteiro();
                ler.cadeiaCaract();
                Estudante estudanteAvaliador = null;

                for (Estudante estudante : estudantes) {
                    if (estudante.obterRef() == matricula) {
                        estudanteAvaliador = estudante;
                    }
                }

                escrever.digiteRef("disciplina");
                String disciplinaRef = ler.cadeiaCaract();

                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.obterRef().equals(disciplinaRef)) {
                        escrever.digiteRef("ativididade");
                        int numeroAtividade = ler.inteiro();
                        ler.cadeiaCaract();
                        Atividade atividade = disciplina.obterAtividade(numeroAtividade);
                        escrever.digiteNota("estudante");
                        float notaAtividade = ler.flutuante();
                        ler.cadeiaCaract();

                        atividade.avaliarAtividade(estudanteAvaliador, notaAtividade);
                    }
                }

            }

        } while (opcao != 2);

        return;
    }

}
