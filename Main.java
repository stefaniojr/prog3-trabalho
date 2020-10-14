import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.io.File;

public class Main implements Serializable {
  private static final long serialVersionUID = 1L;
  private static String arquivoSerializacao = "";

  /** Listas auxiliares para realizar restauração ou exportação. O uso de listas foi optado para economizar o espaço em disco do arquivo serializado. */
  List<Periodo> periodos;
  List<Docente> docentes;
  List<Disciplina> disciplinas;
  List<Estudante> estudantes;

  public static void main(String[] args) throws Exception, ParseException, IOException, ClassNotFoundException, NotSerializableException {
    Main aplicacao = null;
    int opcao;

    Execucao exe = new Execucao();

    /** Para escrita e leitura */
    Escrita escrever = new Escrita();
    Leitura ler = new Leitura();
    ler.iniciarLeitura();

    System.out.println("Deseja restaurar uma aplicacao antiga?");
    escrever.yesOrNoMenu();
    opcao = ler.inteiro();
    ler.cadeiaCaract();

    if (opcao == 1){
      escrever.digiteNomeArquivo();
      arquivoSerializacao = ler.cadeiaCaract();

      Desserializar carregar = new Desserializar(new File(arquivoSerializacao));
      aplicacao = carregar.iniciarDesserializacao();
      aplicacao.execute(ler, escrever, exe, true);
    } else if (opcao == 2) {
      aplicacao = new Main();
      aplicacao.execute(ler, escrever, exe, false);
    }
    
    ler.finalizarLeitura();
    return;
  }


  public void execute(Leitura ler, Escrita escrever, Execucao exe, Boolean backup) throws IOException {
    
    if(backup){
      exe.restaurarPeriodos(periodos);
      exe.restaurarDocentes(docentes);
      exe.restaurarDisciplinas(disciplinas);
      exe.restaurarEstudantes(estudantes);
    }
    boolean serializar = exe.menuPrincipal(ler, escrever);

    if (serializar)
      salvarEmDisco(ler, escrever, exe);
  }

  public void salvarEmDisco(Leitura ler, Escrita escrever, Execucao exe) throws IOException {
    periodos = null;
    docentes = null;
    disciplinas = null;
    estudantes = null;
    periodos = exe.exportarPeriodos();
    docentes = exe.exportarDocentes();
    disciplinas = exe.exportarDisciplinas();
    estudantes= exe.exportarEstudantes();
    
    escrever.digiteNomeArquivo();
    ler.cadeiaCaract();
    arquivoSerializacao = ler.cadeiaCaract();

    Serializar salvar = new Serializar(new File(arquivoSerializacao));
    salvar.iniciarSerializacao(this);
  }

}
