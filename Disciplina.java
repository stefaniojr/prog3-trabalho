import java.util.*;

public class Disciplina {
  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  Escrita escrever = new Escrita();
  Info info = new Info();

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

  public void adicionarAula(String nome, String sincronismo, Disciplina disciplina, String data) {
    
    atividades.put(this.numeroAtividade, new Aula(nome, sincronismo, this, this.numeroAtividade, data));
    this.numeroAtividade = this.numeroAtividade + 1;
    
  }

  public void adicionarEstudo(String nome, String sincronismo, Disciplina disciplina, Map<String, String> conteudos) {
    
    atividades.put(this.numeroAtividade, new Estudo(nome, sincronismo, this, this.numeroAtividade, conteudos));
    this.numeroAtividade = this.numeroAtividade + 1;
    
  }

  public void adicionarTrabalho(String nome, String sincronismo, Disciplina disciplina, String prazo, int nIntegrantes, int cargaHoraria) {
    
    atividades.put(this.numeroAtividade, new Trabalho(nome, sincronismo, this, this.numeroAtividade, prazo, nIntegrantes, cargaHoraria));
    this.numeroAtividade = this.numeroAtividade + 1;
    
  }

  public void adicionarProva(String nome, String sincronismo, Disciplina disciplina, String data, List<String> conteudos) {
    
    atividades.put(this.numeroAtividade, new Prova(nome, sincronismo, this, this.numeroAtividade, data, conteudos));
    this.numeroAtividade = this.numeroAtividade + 1;
    
  }

  public void exibirAtividades() {
    info.atividadesCadastradas(escrever, atividades);
  }
  
  public void exibirEstudantes() {
    info.estudantesCadastrados(escrever, estudantes);
  }

  

}
