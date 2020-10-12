import java.io.Serializable;
import java.util.*;

public class Cadastro implements Serializable{
    
    Escrita escrever = new Escrita();
    

    public void periodo(Leitura ler, Map<String, Periodo> periodos) {
        escrever.digiteAno();
        int ano = ler.inteiro();
        char semestre = ler.caract();

        periodos.put(ano + "/" + semestre, new Periodo(ano, semestre));
        
    }

    public void docente(Leitura ler, Map<String, Docente> docentes) {
        escrever.digiteNome("docente");
        String nome = ler.cadeiaCaract();

        escrever.digiteLogin();
        String login = ler.cadeiaCaract();
        String semSite = "Docente sem site.";

        escrever.possuiSite();

        char possuiSite = ler.caract();
        ler.cadeiaCaract();

        if (possuiSite == 'S') {
            escrever.digiteSite("docente");
            String comSite = ler.cadeiaCaract();
            docentes.put(login, new Docente(login, nome, comSite));
        } else {
            docentes.put(login, new Docente(login, nome, semSite));
        }
    }

    public void disciplina(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {
        escrever.digiteCodigoDisciplina();
        String codigo = ler.cadeiaCaract();
        escrever.digiteNome("disciplina");
        String nome = ler.cadeiaCaract();
        escrever.digitePeriodo();
        String periodoRef = ler.cadeiaCaract();
        escrever.digiteRef("docente");
        String docenteRef = ler.cadeiaCaract();

        Periodo periodo = periodos.get(periodoRef);
        Docente docente = docentes.get(docenteRef);

        disciplinas.put(codigo + "-" + periodoRef, new Disciplina(codigo, nome, periodo, docente));

        periodo.adicionarDisciplina(disciplinas.get(codigo + "-" + periodoRef));
        docente.adicionarDisciplina(disciplinas.get(codigo + "-" + periodoRef));
        docente.adicionarPeriodo(periodos.get(periodoRef));
    }

    public void estudante(Leitura ler, Map<Integer, Estudante> estudantes) {
        escrever.digiteMatricula();
        int matricula = ler.inteiro();
        ler.cadeiaCaract();
        escrever.digiteNome("estudante");
        String nome = ler.cadeiaCaract();

        estudantes.put(matricula, new Estudante(matricula, nome));
    }

    public void estudanteEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas,
            Map<Integer, Estudante> estudantes) {
        escrever.digiteRef("estudante");
        int estudanteRef = ler.inteiro();
        ler.cadeiaCaract();

        Estudante estudante = estudantes.get(estudanteRef);

        escrever.digiteRef("disciplina");
        String disciplinaRef = ler.cadeiaCaract();

        Disciplina disciplina = disciplinas.get(disciplinaRef);

        estudante.adicionarDisciplina(disciplina);
        estudante.adicionarPeriodo(disciplina.obterPeriodo());
        disciplina.adicionarEstudante(estudante);
    }

    public void atividadeEmDisciplina(Leitura ler, Map<String, Disciplina> disciplinas) {
        escrever.digiteNome("atividade");
        String nome = ler.cadeiaCaract();

        escrever.digiteTipo();
        int tipo = ler.inteiro();
        ler.cadeiaCaract();
        escrever.digiteRef("disciplina");
        String disciplinaRef = ler.cadeiaCaract();
        Disciplina disciplina = disciplinas.get(disciplinaRef);

        if (tipo == 1){
            escrever.digiteDataComHora();
            String data = ler.cadeiaCaract();
            disciplina.adicionarAula(nome, "sincrona", disciplina, data);
        } else if (tipo == 2){
            Map<String, String> conteudos = new HashMap<>();
            String url;
            String conteudo;

            do{
                escrever.instrucoesAdicionarConteudoAEstudar();
                
                escrever.digiteConteudo();
                conteudo = ler.cadeiaCaract();
                
                if (conteudo.equals("0")){
                    break;
                }
                
                escrever.digiteURL();
                url = ler.cadeiaCaract();
    
                conteudos.put(url, conteudo);
            }while(true);
            
            disciplina.adicionarEstudo(nome, "assincrona", disciplina, conteudos);

        } else if (tipo == 3){
            escrever.digiteData();
            String prazo = ler.cadeiaCaract();
            escrever.digiteIntegrantes();
            int nIntegrantes = ler.inteiro();
            ler.cadeiaCaract();
            escrever.digiteCargaHoraria();
            int cargaHoraria = ler.inteiro();
            ler.cadeiaCaract();

            disciplina.adicionarTrabalho(nome, "assincrona", disciplina, prazo, nIntegrantes, cargaHoraria);

        } else if (tipo == 4){
            List<String> conteudos = new ArrayList<>();
            String conteudo;

            escrever.digiteDataComHora();
            String data = ler.cadeiaCaract();
            
            do{
                escrever.digiteConteudo();
                conteudo = ler.cadeiaCaract();
                
                if (conteudo.equals("0")){
                    break;
                }
                conteudos.add(conteudo);
            }while(true);

            disciplina.adicionarProva(nome, "sincrona", disciplina, data, conteudos);
        }
    }

    public void avaliacaoEmAtividade(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        escrever.digiteRef("estudante");
        int estudanteRef = ler.inteiro();
        ler.cadeiaCaract();

        Estudante estudante = estudantes.get(estudanteRef);

        escrever.digiteRef("disciplina");
        String disciplinaRef = ler.cadeiaCaract();

        Disciplina disciplina = disciplinas.get(disciplinaRef);

        escrever.digiteRef("ativididade");
        int numeroAtividade = ler.inteiro();
        ler.cadeiaCaract();

        Atividade atividade = disciplina.obterAtividade(numeroAtividade);

        escrever.digiteNota("estudante");
        float notaAtividade = ler.flutuante();
        ler.cadeiaCaract();

        atividade.avaliarAtividade(estudante, notaAtividade);
    
        estudante.adicionarAvaliacao(atividade, atividade.encontrarAvaliacao(estudante));
    }
}
