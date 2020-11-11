package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import java.text.Collator;
import java.lang.Comparable;

public class Periodo implements Serializable, Comparable<Periodo> {
  private static final long serialVersionUID = 1348633635465464576L;
  private int ano;
  private char semestre;

  // Periodo possui um HashMap com referências para as disciplinas a que ele
  // compõe.
  List<Disciplina> disciplinas = new ArrayList<>();

  Locale locale = new Locale("pt", "BR");
  Collator collator = Collator.getInstance(locale);

  public Periodo(int ano, char semestre) {
    this.ano = ano;
    this.semestre = semestre;
  }

  @Override
  public int compareTo(Periodo o) {
    int cmp = this.ano - o.obterAno();
    if (cmp != 0)
      return cmp;

    return collator.compare(Character.toString(this.semestre), Character.toString(o.obterSemestre()));
  }

  // Getters.
  public String obterRef() {
    return obterAno() + "/" + obterSemestre();
  }

  public int obterAno() {
    return ano;
  }

  public char obterSemestre() {
    return semestre;
  }

  // Registro.
  public void adicionarDisciplina(Disciplina disciplina) {
    disciplinas.add(disciplina);
  }

  // Extras.
  public List<Disciplina> obterDisciplinasPorNome() {
    Collections.sort(disciplinas);
    return disciplinas;
  }

  public List<Disciplina> obterDisciplinasPorCodigo() {
    Collections.sort(disciplinas, new Comparator<Disciplina>() {
      @Override
      public int compare(Disciplina a, Disciplina b) {
        return collator.compare(a.obterCodigo(), b.obterCodigo());
      }
    });
    return disciplinas;
  }

}
