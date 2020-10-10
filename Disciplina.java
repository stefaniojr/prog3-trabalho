import java.util.*;

public class Disciplina {
  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  Escrita escrever = new Escrita();
  Relatorio relatorio = new Relatorio();

  Map<Integer, Estudante> estudantes = new HashMap<>();
  Map<Integer, Atividade> atividades = new HashMap<>();

  int numeroAtividade = 1;

  public Disciplina(String codigo, String nome, Periodo periodo, Docente docente) {
    this.codigo = codigo;
    this.nome = nome;
    this.periodo = periodo;
    this.docente = docente;
  }

  public String obterRef() {
    return codigo + "-" + periodo.obterRef();
  }

  public String obterCodigo() {
    return codigo;
  }

  public String obterNome() {
    return nome;
  }

  public Periodo obterPeriodo() {
    return periodo;
  }

  public Docente obterDocente() {
    return docente;
  }

  public Atividade obterAtividade(int numeroAtividade) {
    return atividades.get(numeroAtividade);
  }


  public void adicionarEstudante(Estudante estudante) {
    estudantes.put(estudante.obterRef(), estudante);
  }

  public boolean adicionarAtividade(String nome, String sincronismo) {
    
    atividades.put(this.numeroAtividade, new Atividade(nome, sincronismo, this, this.numeroAtividade));
    this.numeroAtividade = this.numeroAtividade + 1;
    
    return true;
    
  }

  public void exibirAtividades() {
    relatorio.atividadesCadastradas(escrever, atividades);
  }
  
  public void exibirEstudantes() {
    relatorio.estudantesCadastrados(escrever, estudantes);
  }

  

}
