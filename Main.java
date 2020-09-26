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

    do {
      System.out.println("\n*************************************************************");
      System.out.println("BEM-VINDX AO PARADE-UFES. DIGITE UMA OPCAO PARA CONTINUAR:");
      System.out.println("*************************************************************\n");
      System.out.println("1 - CADASTRAR PERIODO");
      System.out.println("2 - CADASTRAR DOCENTE");
      System.out.println("3 - CADASTRAR DISCIPLINA");
      System.out.println("4 - CADASTRAR ESTUDANTE");
      System.out.println("5 - MATRICULAR ESTUDANTE EM DISCIPLINA");
      System.out.println("6 - CADASTRAR UMA ATIVIDADE EM UMA DISCIPLINA");
      System.out.println("7 - AVALIAR ATIVIDADE (FEEDBACK DO ESTUDANTE)");
      System.out.println("8 - SAIR DO PROGRAMA\n");
      System.out.print("Digite uma opcao: ");

      opcaoSelecionada = in.nextInt();

      if (opcaoSelecionada == 1) {

        do {
          System.out.println("\n**********************");
          

          if (periodos.size() == 0) {
            System.out.println("Nao ha periodos cadastrados!");
          } else {
            System.out.println("Periodos cadastrados:");
            for (Periodo periodo : periodos) {
              System.out.println("- " + periodo.obterAno() + "/" + periodo.obterSemestre());
            }
            
          }
          System.out.println("\n1 - CADASTRAR NOVO PERIODO");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();

          if (opcaoSelecionadaFuncionalidade == 1) {
            System.out.print("Digite o ano acompanhado do semestre (Ex: 2020 E, 2019 1): ");
            int ano = in.nextInt();
            char semestre = in.next().charAt(0);
            periodos.add(new Periodo(ano, semestre));
          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 2) {
        do {

          System.out.println("\n**********************");     

          if (docentes.size() == 0) {
            System.out.println("Nao ha docentes cadastrados!");
          } else {
            System.out.println("Docentes cadastrados:");
            for (Docente docente : docentes) {
              System.out.println("- Docente: " + docente.obterNome() + " | Login: " + docente.obterLogin() + " | Site: " + docente.obterSite());
            }
            
          }
          System.out.println("\n1 - CADASTRAR NOVO DOCENTE");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            System.out.print("Digite o nome do docente: ");
            String nome = in.nextLine();
            System.out.print("Digite o login institucional do docente: ");
            String login = in.nextLine();
            String semSite = "Docente sem site.";

            System.out.print("O docente possui site? (S/N): ");
            char possuiSite = in.next().charAt(0);
            in.nextLine();

            if (possuiSite == 'S') {
              System.out.print("Digite o site do docente: ");
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

          System.out.println("\n**********************");
          

          if (disciplinas.size() == 0) {
            System.out.println("Nao ha disciplinas cadastradas!");
          } else {
            System.out.println("Disciplinas cadastradas:");
            for (Disciplina disciplina : disciplinas) {
              System.out.println(disciplina.obterRef() + " | Disciplina: " + disciplina.obterNome() + " | Docente responsavel: " + disciplina.obterDocente().obterNome());
              disciplina.obterAtividades();
            }
            
          }
          System.out.println("\n1 - CADASTRAR NOVA DISCIPLINA");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            System.out.print("Digite o codigo da disciplina: ");
            String codigo = in.nextLine();
            System.out.print("Digite o nome da disciplina: ");
            String nome = in.nextLine();
            System.out.print("Digite o período em que será ministrada a disciplina (Ex: 2020/E, 2018/1, etc.): ");
            String periodoRef = in.nextLine();
            System.out.print("Digite o login institucional do docente responsavel (Ex: nome.sobrenome): ");
            String docenteRef = in.nextLine();

            for (Periodo periodo : periodos) {
              if (periodo.obterRef().equals(periodoRef)) {
                for (Docente docente : docentes) {
                  if (docente.obterRef().equals(docenteRef)) {
                    if (disciplinas.add(new Disciplina(codigo, nome, periodo, docente))) {
                      for (Disciplina disciplina: disciplinas){
                        if(disciplina.obterRef().equals(codigo + periodo.obterRef())){
                          periodo.adicionarDisciplina(disciplina);
                          docente.adicionarDisciplina(disciplina);
                        }
                      }
                      System.out.println("\nDisciplina cadastrada com sucesso!");
                    } else {
                      System.out.println("\nNao foi possivel cadastrar a disciplina!");
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

          System.out.println("\n**********************");
          
          if (estudantes.size() == 0) {
            System.out.println("Nao ha estudantes cadastrados!");
          } else {
            System.out.println("Estudantes cadastrados:");
            for (Estudante estudante : estudantes) {
              System.out.println("- Matricula: " + estudante.obterMatricula() + " | Estudante: " + estudante.obterNome());
            }
            
          }
          System.out.println("\n1 - CADASTRAR NOVO ESTUDANTE");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            System.out.print("Digite o numero de matricula do estudante: ");
            int matricula = in.nextInt();
            in.nextLine();
            System.out.print("Digite o nome do estudante: ");
            String nome = in.nextLine();

            estudantes.add(new Estudante(matricula, nome));

          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if (opcaoSelecionada == 5) {
        do {

          System.out.println("\n**********************");

          System.out.println("1 - MATRICULAR ESTUDANTE EM DISCIPLINA");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            System.out.print("Digite o numero de matricula do estudante: ");
            int matricula = in.nextInt();
            in.nextLine();

            for (Estudante estudante : estudantes) {
              if (estudante.obterRef() == matricula) {

                Estudante estudanteEncontrado = estudante;
                System.out.println("Estudante encontrado!");
                
                estudanteEncontrado.exibirDisciplinas();

                System.out.print("\nDigite a disciplina em que deseja cadastrar o referido estudante (codigo-periodo): ");
                String disciplinaRef = in.nextLine();

                for(Disciplina disciplina: disciplinas){
                  if (disciplina.obterRef().equals(disciplinaRef)) {
                    estudanteEncontrado.adicionarDisciplina(disciplina);
                    disciplina.adicionarEstudante(estudanteEncontrado);
                    System.out.println("O estudante " + estudante.obterNome() + " foi cadastrado na disciplina "
                    + disciplina.obterNome() + ".");
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

          System.out.println("\n**********************");

          System.out.println("1 - CADASTRAR ATIVIDADE EM DISCIPLINA");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {

            System.out.print("Digite o nome da atividade: ");
            String nome = in.nextLine();

            System.out.print("Digite o sincronismo da atividade (sincrona ou assincrona): ");
            String sincronismo = in.nextLine();

            if (!(sincronismo.equals("sincrona"))) {
              sincronismo = "assincrona";
            }

            System.out.print("Digite a disciplina em que deseja cadastrar a atividade (codigo-periodo): ");
            String disciplinaRef = in.nextLine();

            for (Disciplina disciplina : disciplinas) {
              if (disciplina.obterRef().equals(disciplinaRef)) {

                if (disciplina.adicionarAtividade(nome, sincronismo)) {
                  System.out.println("\nAtividade cadastrada com sucesso!");
                } else {
                  System.out.println("\nNao foi possivel cadastrar a disciplina!");
                }

                break;
              }

            }
          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      if(opcaoSelecionada == 7){
        do {

          System.out.println("\n**********************");

          System.out.println("1 - AVALIAR ATIVIDADE EM DISCIPLINA");
          System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
          System.out.print("Digite uma opcao: ");
          opcaoSelecionadaFuncionalidade = in.nextInt();
          in.nextLine();

          if (opcaoSelecionadaFuncionalidade == 1) {
            System.out.print("Digite o numero de matricula do estudante: ");
            int matricula = in.nextInt();
            in.nextLine();
            Estudante estudanteAvaliador = null;

            for (Estudante estudante: estudantes){
              if(estudante.obterRef() == matricula){
                estudanteAvaliador = estudante;
              }
            }

            System.out.print("Digite a disciplina em que o estudante deseja avaliar a atividade (codigo-periodo): ");
            String disciplinaRef = in.nextLine();

            for (Disciplina disciplina: disciplinas){
              if(disciplina.obterRef().equals(disciplinaRef)){
                System.out.print("Digite o codigo da atividade: ");
                int numeroAtividade = in.nextInt();
                in.nextLine();
                Atividade atividade = disciplina.obterAtividade(numeroAtividade);

                System.out.print("Nota que o estudante deseja atribuir à atividade: ");
                float notaAtividade = in.nextFloat();
                in.nextLine();

                atividade.avaliarAtividade(estudanteAvaliador, notaAtividade);
              }
            }
            
          }

        } while (opcaoSelecionadaFuncionalidade != 2);
      }

      else if (opcaoSelecionada == 8) {
        System.out.println("Saindo do programa...");
        System.out.println("Programa finalizado!");
      }

    } while (opcaoSelecionada != 8);

    in.close();
    return;
  }

}
