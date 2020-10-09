import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    int opcaoSelecionada;
    int opcaoSelecionadaFuncionalidade;

    Scanner in = new Scanner(System.in);
    in.useLocale(Locale.US);
    ArrayList<Periodo> periodos = new ArrayList<>();
    ArrayList<Docente> docentes = new ArrayList<>();
    ArrayList<Disciplina> disciplinas = new ArrayList<>();
    ArrayList<Estudante> estudantes = new ArrayList<>();
    Menu menu = new Menu();
    Escrita escrever = new Escrita();

    do {

      menu.imprimirMenu();

      opcaoSelecionada = in.nextInt();

      if (opcaoSelecionada == 1) {

        do {
          escrever.showAsterisks();

          if (periodos.size() == 0) {
            escrever.naoHa("periodos");
          } else {
            escrever.cadastrados("Periodos");
            for (Periodo periodo : periodos) {
              escrever.periodoCadastrado(periodo.obterRef());
            }
          }

          /**Submenu*/
          escrever.cadastrar("PERIODO");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          /**Fim submenu*/
          opcaoSelecionadaFuncionalidade = in.nextInt();

          if (opcaoSelecionadaFuncionalidade == 1) {
            escrever.digiteAno();
            int ano = in.nextInt();
            char semestre = in.next().charAt(0);
            periodos.add(new Periodo(ano, semestre));
          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 2) {
        do {

          escrever.showAsterisks();

          if (docentes.size() == 0) {
            escrever.naoHa("docentes");
          } else {
            escrever.cadastrados("Docentes");
            for (Docente docente : docentes) {
              escrever.docenteCadastrado(docente.obterNome(), docente.obterRef(), docente.obterSite());
            }

          }

          /**Submenu*/
          escrever.cadastrar("DOCENTE");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          /**Fim submenu*/
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            escrever.digiteNome("docente");

            String nome = in.nextLine();
            escrever.digiteLogin();

            String login = in.nextLine();
            String semSite = "Docente sem site.";

            escrever.possuiSite();

            char possuiSite = in.next().charAt(0);
            in.nextLine();

            if (possuiSite == 'S') {
              escrever.digiteSite("docente");
              String comSite = in.nextLine();
              docentes.add(new Docente(login, nome, comSite));
            } else {
              docentes.add(new Docente(login, nome, semSite));
            }

          }

        } while (opcaoSelecionadaFuncionalidade != 2);

      }

      if (opcaoSelecionada == 3) {
        do {

          escrever.showAsterisks();

          if (disciplinas.size() == 0) {
            escrever.naoHa("disciplinas");

          } else {
            escrever.cadastrados("Disciplinas");
            for (Disciplina disciplina : disciplinas) {
              escrever.disciplinaCadastrada(disciplina.obterRef(), disciplina.obterNome(), disciplina.obterDocente().obterNome());              
              
            }

          }

          /**Submenu*/
          escrever.cadastrar("DISCIPLINA");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          /**Fim submenu*/

          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            escrever.digiteCodigoDisciplina();
            String codigo = in.nextLine();
            escrever.digiteNome("disciplina");
            String nome = in.nextLine();
            escrever.digitePeriodo();
            String periodoRef = in.nextLine();
            escrever.digiteRef("docente");
            String docenteRef = in.nextLine();

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

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 4) {
        do {

          escrever.showAsterisks();

          if (estudantes.size() == 0) {
            escrever.naoHa("estudantes");
          } else {
            escrever.cadastrados("Estudantes");
            for (Estudante estudante : estudantes) {
              escrever.estudanteCadastrado(estudante.obterMatricula(), estudante.obterNome());
            }
          }

          // Submenu
          escrever.cadastrar("ESTUDANTE");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          // Fim do submenu
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            escrever.digiteMatricula();
            int matricula = in.nextInt();
            in.nextLine();
            escrever.digiteNome("estudante");
            String nome = in.nextLine();

            estudantes.add(new Estudante(matricula, nome));

          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 5) {
        do {

          escrever.showAsterisks();

          /**Submenu*/
          escrever.cadastrar("ESTUDANTE");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          /**Fim submenu*/

          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            escrever.digiteRef("estudante");
            int matricula = in.nextInt();
            in.nextLine();

            for (Estudante estudante : estudantes) {
              if (estudante.obterRef() == matricula) {

                Estudante estudanteEncontrado = estudante;
                escrever.encontrado("Estudante");

                estudanteEncontrado.exibirDisciplinas();

                escrever.digiteRef("disciplina");
                String disciplinaRef = in.nextLine();

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

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 6) {
        do {

          escrever.showAsterisks();

          /**Submenu*/
          escrever.cadastrar("ATIVIDADE");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          /**Fim submenu*/
          
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            escrever.digiteNome("atividade");
            String nome = in.nextLine();

            escrever.digiteSincronismo();
            String sincronismo = in.nextLine();

            if (!(sincronismo.equals("sincrona"))) {
              sincronismo = "assincrona";
            }

            escrever.digiteRef("disciplina");
            String disciplinaRef = in.nextLine();

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

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 7) {
        do {

          escrever.showAsterisks();

          /**Submenu*/
          escrever.cadastrar("AVALIACAO");
          escrever.voltarMenu();
          escrever.digiteOpcao();
          /**Fim submenu*/
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {
            escrever.digiteRef("estudante");
            int matricula = in.nextInt();
            in.nextLine();
            Estudante estudanteAvaliador = null;

            for (Estudante estudante : estudantes) {
              if (estudante.obterRef() == matricula) {
                estudanteAvaliador = estudante;
              }
            }

            escrever.digiteRef("disciplina");
            String disciplinaRef = in.nextLine();

            for (Disciplina disciplina : disciplinas) {
              if (disciplina.obterRef().equals(disciplinaRef)) {
                escrever.digiteRef("ativididade");
                int numeroAtividade = in.nextInt();
                in.nextLine();
                Atividade atividade = disciplina.obterAtividade(numeroAtividade);
                escrever.digiteNota("estudante");
                float notaAtividade = in.nextFloat();
                in.nextLine();

                atividade.avaliarAtividade(estudanteAvaliador, notaAtividade);
              }
            }

          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      else if (opcaoSelecionada == 8) {
        escrever.saindo("programa");
        escrever.finalizado("Programa");
      }

    } while (opcaoSelecionada != 8);

    in.close();
    return;
  }

}
