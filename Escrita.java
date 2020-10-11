public class Escrita {

    public void mostrarMenu(){
        System.out.println("\n*************************************************************");
        System.out.println("OTRUS EARTE SYSTEM v0.0.2 - DIGITE UMA OPCAO PARA CONTINUAR:");
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
    }

    public void showAsterisks(){
        System.out.println("\n**********************");
    }

    /**Submenu messages */
    public void mostrarSubMenu(Escrita escrever, String disso){
        this.showAsterisks();
        this.option1SubMenu(disso);
        this.option2SubMenu();
        this.option3SubMenu();
        this.digiteOpcao();
    }

    public void option1SubMenu(String isso){
        System.out.println("1 - CADASTRAR NOVO(A) " + isso);
    }

    public void option2SubMenu(){
        System.out.println("2 - MOSTRAR INFORMACOES");
    }

    public void option3SubMenu(){
        System.out.println("3 - VOLTAR AO MENU PRINCIPAL");
    }

    public void digiteOpcao(){
        System.out.print("Digite uma opcao: ");
    }
    /**Fim submenu messages */

    /**Relatorios messages */
    public void notFound(String isso){
        System.out.println("Nao ha " + isso +  " cadastrados(as)! :(");
    }

    public void titleRelatorio(String isso){
        System.out.println(isso + " cadastrados(as):");
    }

    public void showSomething(String something){
        System.out.println(something);
    }

    public void docenteCadastrado(String nome, String ref, String site){
        System.out.println("- Docente: " + nome + " | Login: " + ref + " | Site: " + site);
    }

    public void disciplinaCadastrada(String ref, String nomeDisciplina, String nomeDocente){
        System.out.println(ref + " | Disciplina: " + nomeDisciplina
                  + " | Docente responsavel: " + nomeDocente);
    }

    public void estudanteCadastrado(int matricula, String nome){
        System.out.println("- Matricula: " + matricula + " | Estudante: " + nome);
    }

    public void avaliacaoCadastrada(String nome, float nota){
        System.out.println("- Estudante " + nome + " avaliou essa atividade com nota " + nota + ".");
    }

    public void atividadeCadastrada(int numero, String nome, String sincronismo){
        System.out.println("Codigo: " + numero + " | Atividade: " + nome + " | Sicronismo: " + sincronismo);
    }
    /**Fim relatorios messages */


    /**Entradas messages */
    public void digiteAno(){
        System.out.print("Digite o ano acompanhado do semestre (Ex: 2020 E, 2019 1): ");
    }

    public void digiteNome(String disso){
        System.out.print("Digite o nome do(a) " + disso + ": ");
    }

    public void digitePeriodo(){
        System.out.print("Digite o qual periodo (Ex: 2020/E, 2018/1, etc.): ");
    }

    public void digiteRef(String disso){
        System.out.print("Digite a referencia do(a) " + disso + ": ");
    }

    public void digiteMatricula(){
        System.out.print("Digite o numero de matricula do estudante: ");
    }

    public void digiteLogin(){
        System.out.print("Digite o login institucional do docente: ");
    }

    public void digiteSite(String disso){
        System.out.print("Digite o site do " + disso + ": ");
    }
    
    public void possuiSite(){
        System.out.print("O docente possui site? (S/N): "); 
    }

    public void digiteTipo(){
        System.out.println("Tipos de atividade:");
        System.out.println("1 - Aula");
        System.out.println("2 - Estudo");
        System.out.println("3 - Trabalho");
        System.out.println("4 - Prova");
        System.out.print("Digite o tipo de atividade (1, 2, 3 ou 4): ");
    }

    public void digiteNota(String quem){
        System.out.print("Nota que o " + quem +  " estudante deseja atribuir a atividade: ");
    }

    public void digiteData(){
        System.out.print("Digite a data de entrega (Ex: 00/00/0000): ");
    }

    public void digiteDataComHora(){
        System.out.print("Digite a data e o horario (Ex: 00/00/0000-00:00): ");
    }

    public void instrucoesAdicionarConteudoAEstudar(){
        System.out.println("Adicione primeiro um conteudo a ser estudado e, quando solicitado, uma URL.\nPara finalizar a insercao de conteudo, digite 0 ao ser solicitada a insercao de um novo conteudo.");
    }

    public void digiteConteudo(){
        System.out.print("Adicione um conteudo a ser estudado (0 para sair): ");
    }

    public void digiteURL(){
        System.out.print("Adicione uma URL: ");
    }

    public void digiteIntegrantes(){
        System.out.print("Digite o numero de integrantes do grupo: ");
    }

    public void digiteCargaHoraria(){
        System.out.print("Digite o numero da carga horaria prevista a realizacao da atividade: ");
    }

    public void digiteCodigoDisciplina(){
        System.out.print("Digite o codigo da disciplina (Ex: MAT666): ");
    }
    /**Fim entradas messages */

    /**Feedback messages */
    public void sucess(String isso){
        System.out.println(isso + " cadastrado(a) com sucesso!");
    }

    public void error(String isso){
        System.out.println("Nao foi possivel cadastrar o(a) " + isso + "!");
    }

    public void encontrado(String isso){
        System.out.println(isso + " encontrado(a)!");
    }

    public void saindo(String disso){
        System.out.println("Saindo do(a) " + disso + "...");
    }

    public void finalizado(String isso){
        System.out.println(isso + " finalizado(a)!");
    }
    /**Fim feedback messages */
}