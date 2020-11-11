package com.github.stefaniojr.prog3.project;

import com.github.stefaniojr.prog3.project.domain.*;
import com.github.stefaniojr.prog3.project.io.*;
import com.github.stefaniojr.prog3.project.serializer.*;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.io.File;

public class Main implements Serializable {
  private static final long serialVersionUID = 1345634635465464574L;
  private static final String ARQUIVO_SERIALIZACAO = "dados.dat";
  private static final String SAIDA_VISAOGERAL = "1-visao-geral.csv";
  private static final String SAIDA_DOCENTES = "2-docentes.csv";
  private static final String SAIDA_ESTUDANTES = "3-estudantes.csv";
  private static final String SAIDA_DISCIPLINAS = "4-disciplinas.csv";

  // Listas auxiliares para realizar restauração ou exportação. O uso de listas
  // foi optado para economizar o espaço em disco do arquivo serializado.

  List<Periodo> periodos;
  List<Docente> docentes;
  List<Estudante> estudantes;
  List<Disciplina> disciplinas;

  public static void main(String[] args) throws NotSerializableException {
    Locale.setDefault(new Locale("pt", "BR"));
    Main aplicacao = null;

    // Variáveis para arquivos de entrada:
    String arqPeriodos = null;
    String arqDocentes = null;
    String arqOferta = null;
    String arqEstudantes = null;
    String arqMatriculas = null;
    String arqAtividades = null;
    String arqNotas = null;

    // Indicadores de serialização e desserialização:
    boolean writeOnly = false;
    boolean readOnly = false;

    // Para escrita em tela e/ou feedbacks no terminal para o usuário.
    Escrita escrever = new Escrita(new File(SAIDA_VISAOGERAL), new File(SAIDA_DOCENTES), new File(SAIDA_ESTUDANTES),
        new File(SAIDA_DISCIPLINAS));

    try {
      // Sequência de códigos por cortesia do professor Vítor Souza do Departamento de
      // Informática (UFES):

      for (int i = 0; i < args.length; i++) {
        // Procura pelo prefixo de períodos e checa se não é o fim do vetor de strings.
        if ("-p".equals(args[i]) && args.length > i + 1)
          arqPeriodos = args[i + 1];

        // Procura pelo prefixo de docentes e checa se não é o fim do vetor de strings.
        else if ("-d".equals(args[i]) && args.length > i + 1)
          arqDocentes = args[i + 1];

        // Procura pelo prefixo de oferta e checa se não é o fim do vetor de strings.
        else if ("-o".equals(args[i]) && args.length > i + 1)
          arqOferta = args[i + 1];

        // Procura pelo prefixo de estudantes e checa se não é o fim do vetor de
        // strings.
        else if ("-e".equals(args[i]) && args.length > i + 1)
          arqEstudantes = args[i + 1];

        // Procura pelo prefixo de matriculas em disciplinas e checa se não é o fim do
        // vetor de strings.
        else if ("-m".equals(args[i]) && args.length > i + 1)
          arqMatriculas = args[i + 1];

        // Procura pelo prefixo de atividades de disciplinas e checa se não é o fim do
        // vetor de strings.
        else if ("-a".equals(args[i]) && args.length > i + 1)
          arqAtividades = args[i + 1];

        // Procura pelo prefixo de avaliações de atividades de disciplinas e checa se
        // não é o fim do vetor de strings.
        else if ("-n".equals(args[i]) && args.length > i + 1)
          arqNotas = args[i + 1];

        // Indicadores de serialização, desserialização ou nenhum dos dois:
        else if ("--write-only".equals(args[i]))
          writeOnly = true;
        else if ("--read-only".equals(args[i]))
          readOnly = true;
      }

      // Inicia uma instância da execução da aplicação.
      Execucao exe = new Execucao();

      /////////// REMOVER DEPOIS, ENQUANTO NÃO BAIXO O ANT
      // arqPeriodos = "periodos.csv";
      // arqDocentes = "docentes.csv";
      // arqOferta = "disciplinas.csv";
      // arqEstudantes = "estudantes.csv";
      // arqMatriculas = "matriculas.csv";
      // arqAtividades = "atividades.csv";
      // arqNotas = "avaliacoes.csv";
      // writeOnly = true;
      ////////

      // Um erro possível é o usuário não especificar os arquivos de escrita no modo
      // de leitura:
      if (!writeOnly && (arqPeriodos == null || arqDocentes == null || arqOferta == null || arqEstudantes == null
          || arqMatriculas == null || arqAtividades == null || arqNotas == null))
        System.out.println(
            "Erro!\nVocê precisa informar TODOS os nomes dos arquivos a serem lidos para que a serialização seja realizada! ;)");

      // Caso a desserialização seja necessária, faz a desserialização e executa a
      // aplicação com esses dados desserialização nas Lists.
      else if (writeOnly) {
        Desserializar carregar = new Desserializar(new File(ARQUIVO_SERIALIZACAO));
        aplicacao = carregar.iniciarDesserializacao();
        aplicacao.execute(escrever, exe, true, readOnly, arqPeriodos, arqDocentes, arqOferta, arqEstudantes,
            arqMatriculas, arqAtividades, arqNotas);
      }

      // Por outro lado, caso uma serialização esteja prestes a ser realizada ou,
      // então, apenas os relatórios serão gerados,
      // inicia uma instância de Main novinha em folha e executa a partir dela.
      else {
        aplicacao = new Main();
        aplicacao.execute(escrever, exe, false, readOnly, arqPeriodos, arqDocentes, arqOferta, arqEstudantes,
            arqMatriculas, arqAtividades, arqNotas);
      }

    } catch (IOException | ParseException | ClassNotFoundException e) {
      System.out.println("Erro de I/O.");
    } catch (RuntimeException e) {
      // e.printStackTrace();
      // Tudo bem! Eu já imprimi a mensagem que eu queria dentro dos métodos. Não
      // quero fazer mais nada aqui. :)
    }
    return;
  }

  public void execute(Escrita escrever, Execucao exe, boolean desserializar, boolean readOnly, String arqPeriodos,
      String arqDocentes, String arqOferta, String arqEstudantes, String arqMatriculas, String arqAtividades,
      String arqNotas) throws IOException, ParseException, RuntimeException {
    // Existem 3 tipos possíveis de execução:

    // 1 - Usuário especificou writeOnly, logo desserializa e gera os relatórios
    // necessários.
    if (desserializar) {
      exe.listToHashMapPeriodos(periodos);
      exe.listToHashMapDocentes(docentes);
      exe.listToHashMapDisciplinas(disciplinas);
      exe.listToHashMapEstudantes(estudantes);
      exe.gerarRelatorios(escrever);

      // 2 - Usuário especificou readOnly, logo carrega dados de planilhas de entrada
      // e serializa em disco.
    } else if (readOnly) {
      exe.carregarPlanilhas(arqPeriodos, arqDocentes, arqOferta, arqEstudantes, arqMatriculas, arqAtividades, arqNotas);
      // Chamada de métodos de cadastro de informações.
      exe.cadastrarPeriodos();
      exe.cadastrarDocentes();
      exe.cadastrarDisciplinas();
      exe.cadastrarEstudantes();
      exe.matricularEstudantes();
      exe.cadastrarAtividades();
      exe.cadastrarAvaliacoes();
      salvarEmDisco(escrever, exe);

      // 3 - Usuário não especificou writeOnly, nem readOnly, logo carrega dados de
      // planilhas de entrada e gera os relatórios necessários, sem o uso de
      // serialização ou desserialização.
    } else {
      exe.carregarPlanilhas(arqPeriodos, arqDocentes, arqOferta, arqEstudantes, arqMatriculas, arqAtividades, arqNotas);
      // Chamada de métodos de cadastro de informações.
      exe.cadastrarPeriodos();
      exe.cadastrarDocentes();
      exe.cadastrarDisciplinas();
      exe.cadastrarEstudantes();
      exe.matricularEstudantes();
      exe.cadastrarAtividades();
      exe.cadastrarAvaliacoes();
      exe.gerarRelatorios(escrever);
    }
  }

  // Método responsável por chamar os métodos que passam HashMaps para Lists e,
  // então, serializa a aplicação.
  public void salvarEmDisco(Escrita escrever, Execucao exe) throws IOException {
    periodos = null;
    docentes = null;
    disciplinas = null;
    estudantes = null;
    periodos = exe.hashMapToListPeriodos();
    docentes = exe.hashMapToListDocentes();
    disciplinas = exe.hashMapToListDisciplinas();
    estudantes = exe.hashMapToListEstudantes();

    Serializar salvar = new Serializar(new File(ARQUIVO_SERIALIZACAO));
    salvar.iniciarSerializacao(this);

  }

}
