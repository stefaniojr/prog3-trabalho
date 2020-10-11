import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

public class Main implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String arquivoSerializacao = "dados.dat";

  public static void main(String[] args) throws Exception, ParseException, IOException, ClassNotFoundException {
    Main aplicacao = null;

    int opcaoSelecionada;

    Map<String, Periodo> periodos = new HashMap<>();
    Map<String, Docente> docentes = new HashMap<>();
    Map<String, Disciplina> disciplinas = new HashMap<>();
    Map<Integer, Estudante> estudantes = new HashMap<>();

    Menu menu = new Menu();

    /** Para escrita e leitura */
    Escrita escrever = new Escrita();
    Leitura ler = new Leitura();

    ler.iniciarLeitura();

    do {

      menu.menuPrincipal();

      opcaoSelecionada = ler.inteiro();

      if (opcaoSelecionada == 1) {
        menu.subMenu1(ler, periodos);
      }

      else if (opcaoSelecionada == 2) {
        menu.subMenu2(ler, docentes);
      }

      else if (opcaoSelecionada == 3) {
        menu.subMenu3(ler, periodos, docentes, disciplinas);
      }

      else if (opcaoSelecionada == 4) {
        menu.subMenu4(ler, estudantes);
      }

      else if (opcaoSelecionada == 5) {
        menu.subMenu5(ler, disciplinas, estudantes);
      }

      else if (opcaoSelecionada == 6) {
        menu.subMenu6(ler, disciplinas);
      }

      else if (opcaoSelecionada == 7) {
        menu.subMenu7(ler, disciplinas, estudantes);
      }

      else if (opcaoSelecionada == 8) {
        menu.subMenu8(ler, periodos, docentes, disciplinas, estudantes);
      }

      else if (opcaoSelecionada == 9) {
        menu.subMenu9(ler, arquivoSerializacao, aplicacao);
      }

      else if (opcaoSelecionada == 10) {
        aplicacao = menu.subMenu10(ler, arquivoSerializacao, aplicacao);
      }

      else if (opcaoSelecionada == 11) {
        escrever.saindo("programa");
        escrever.finalizado("Programa");
      }

    } while (opcaoSelecionada != 11);

    ler.finalizarLeitura();
    return;
  }

}