public class Avaliacao {
    Estudante estudante;
    float nota;

    public Avaliacao(Estudante estudante, float nota){
        this.estudante = estudante;
        this.nota = nota;
      }
    
    public Estudante obterAvaliador(){
        return estudante;
    }

    public float obterNota(){
        return nota;
    }
}
