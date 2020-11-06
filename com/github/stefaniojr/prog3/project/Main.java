package com.github.stefaniojr.prog3.project;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.io.File;

import com.github.stefaniojr.prog3.project.domain.*;
import com.github.stefaniojr.prog3.project.io.*;
import com.github.stefaniojr.prog3.project.serializer.*;

public class Main implements Serializable {
  private static final long serialVersionUID = 1L;
  private static String arquivoSerializacao = "";

  /**
   * Listas auxiliares para realizar restauração ou exportação. O uso de listas
   * foi optado para economizar o espaço em disco do arquivo serializado.
   */
  List<Periodo> periodos;
  List<Docente> docentes;
  List<Disciplina> disciplinas;
  List<Estudante> estudantes;

  public static void main(String[] args)
      throws Exception, ParseException, IOException, ClassNotFoundException, NotSerializableException {
    Main aplicacao = null;
    int opcao = 0;

    Execucao exe = new Execucao();

    /** Para escrita e leitura */
    Escrita escrever = new Escrita();
    Leitura ler = new Leitura();
    ler.iniciarLeitura();

    boolean keepGoing = false;
    do {
      boolean subKeepGoing = false;
      do {
        try {
          escrever.desejaRestaurar();
          escrever.yesOrNoMenu();
          opcao = ler.inteiro();
          ler.cadeiaCaract();
          subKeepGoing = false;
        } catch (InputMismatchException e) {
          ler.cadeiaCaract();
          escrever.opcaoInvalida();
          subKeepGoing = true;
        }
      } while (subKeepGoing);

      if (opcao == 1) {
        try {
          escrever.digiteNomeArquivo();
          arquivoSerializacao = ler.cadeiaCaract();
          Desserializar carregar = new Desserializar(new File(arquivoSerializacao));
          aplicacao = carregar.iniciarDesserializacao();
          aplicacao.execute(ler, escrever, exe, true);
          keepGoing = false;
        } catch (IOException e) {
          escrever.erroIO();
          keepGoing = true;
        }

      } else if (opcao == 2) {
        aplicacao = new Main();
        aplicacao.execute(ler, escrever, exe, false);
        keepGoing = false;
      } else {
        escrever.opcaoInvalida();
        keepGoing = true;
      }

    } while (keepGoing);

    ler.finalizarLeitura();
    System.out.println("FINALIZEIII CORRETOOOOOOOOOOOOO");
    return;
  }

  public void execute(Leitura ler, Escrita escrever, Execucao exe, Boolean backup) throws IOException {

    if (backup) {
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
    estudantes = exe.exportarEstudantes();

    boolean keepGoing = false;
    do {
      try {
        escrever.digiteNomeArquivo();
        ler.cadeiaCaract();
        arquivoSerializacao = ler.cadeiaCaract();
        Serializar salvar = new Serializar(new File(arquivoSerializacao));
        salvar.iniciarSerializacao(this);
      } catch (IOException e) {
        escrever.erroIO();
      }
    } while (keepGoing);

  }

}
