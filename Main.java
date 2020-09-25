import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
      int opcaoSelecionada;
      int opcaoSelecionadaFuncionalidade;

      Scanner in = new Scanner(System.in);
      List<Periodo> periodos = new ArrayList<>();
      List<Docente> docentes = new ArrayList<>();

      do{
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


        if(opcaoSelecionada == 1){

          do {
            System.out.print("\n**********************");
            System.out.println("\nPeriodos cadastrados:");

            if (periodos.size() == 0){
              System.out.println("Nao ha periodos cadastrados!\n");
            } else {
              for (Periodo periodo : periodos) {
                  int ano = periodo.obterAno();
                  char semestre = periodo.obterSemestre();
                  System.out.println("- " + ano + "/" + semestre);
              }
              System.out.print("\n");
            }
            System.out.println("1 - CADASTRAR NOVO PERIODO");
            System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
            System.out.print("Digite uma opcao: ");
            opcaoSelecionadaFuncionalidade = in.nextInt();

            if (opcaoSelecionadaFuncionalidade == 1) {
              System.out.print("Digite o ano acompanhado do semestre (Ex: 2020 E, 2019 1): ");
              int ano = in.nextInt();
              char semestre = in.next().charAt(0);
              periodos.add(new Periodo(ano, semestre));
            }

          }while(opcaoSelecionadaFuncionalidade!=2);
        }

        if(opcaoSelecionada == 2){
          do {

            System.out.print("\n**********************");
            System.out.println("\nDocentes cadastrados:");

            if (docentes.size() == 0){
              System.out.println("Nao ha docentes cadastrados!\n");
            } else {
              for (Docente docente : docentes) {
                  String login = docente.obterLoginDocente();
                  String nome = docente.obterNomeDocente();
                  String site = docente.obterSiteDocente();
                  System.out.println("- Docente: " + nome + " | Login: " + login + " | Site: " + site);
              }
              System.out.print("\n");
            }
            System.out.println("1 - CADASTRAR NOVO DOCENTE");
            System.out.println("2 - VOLTAR AO MENU PRINCIPAL\n");
            System.out.print("Digite uma opcao: ");
            opcaoSelecionadaFuncionalidade = in.nextInt();
            in.nextLine();

            if (opcaoSelecionadaFuncionalidade == 1) {

              System.out.print("Digite o nome do docente: ");
              String nome = in.nextLine();
              System.out.print("Digite o login do docente: ");
              String login = in.nextLine();
              String semSite = "Docente sem site.";

              System.out.print("O docente possui site? (S/N): ");
              char possuiSite = in.next().charAt(0);
              in.nextLine();

              if (possuiSite == 'S'){
                System.out.print("Digite o site do docente: ");
                String comSite = in.nextLine();
                docentes.add(new Docente(login, nome, comSite));
              } else {
                docentes.add(new Docente(login, nome, semSite));
              }

            }

          }while(opcaoSelecionadaFuncionalidade!=2);

        }
        //
        // if(opcaoSelecionada == 3){
        //
        // }
        //
        // if(opcaoSelecionada == 4){
        //
        // }
        //
        // if(opcaoSelecionada == 5){
        //
        // }
        //
        // if(opcaoSelecionada == 6){
        //
        // }
        //
        // if(opcaoSelecionada == 7){
        //
        // }

        else if(opcaoSelecionada == 8){
          System.out.println("Saindo do programa...");
          System.out.println("Programa finalizado!");
        }

      }while(opcaoSelecionada != 8);

      in.close();
      return;
    }


}
