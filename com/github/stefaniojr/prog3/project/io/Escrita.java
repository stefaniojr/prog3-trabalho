package com.github.stefaniojr.prog3.project.io;

import java.io.Serializable;
import java.util.*;
import com.github.stefaniojr.prog3.project.domain.atividades.*;


public class Escrita implements Serializable {
    

    public void mostrarMenu() {
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
        System.out.println("8 - RELATORIOS");
        System.out.println("9 - SALVAR (SERIALIZAR DADOS)");
        System.out.println("10 - SAIR DO PROGRAMA\n");
        System.out.print("Digite uma opcao: ");
    }

    public void showAsterisks() {
        System.out.println("\n**********************");
    }

    /** Submenu messages */
    public void mostrarSubMenu(String isso) {
        this.showAsterisks();
        this.option1SubMenu(isso);
        this.voltarMenuPrincipal(2);
        this.digiteOpcao();
    }

    public void desejaRestaurar(){
        System.out.println("Deseja restaurar uma aplicacao antiga?");
    }

    public void opcaoInvalida(){
        System.out.println("Opcao invalida.");
    }

    public void mostrarSubMenuRelatorios() {
        this.showAsterisks();
        this.option1SubMenuRelatorios();
        this.option2SubMenuRelatorios();
        this.option3SubMenuRelatorios();
        this.option4SubMenuRelatorios();
        this.voltarMenuPrincipal(5);
        this.digiteOpcao();
    }

    public void yesOrNoMenu() {
        
        System.out.println("1 - SIM");
        System.out.println("2 - NAO");
        this.digiteOpcao();
    }

    public void option1SubMenu(String isso) {
        System.out.println("1 - CADASTRAR NOVO(A) " + isso);
    }

    public void voltarMenuPrincipal(int option) {
        System.out.println(option + " - VOLTAR AO MENU PRINCIPAL");
    }

    public void option1SubMenuRelatorios() {
        System.out.println("1 - VISAO GERAL DO PERIODO ACADEMICO");
    }

    public void option2SubMenuRelatorios() {
        System.out.println("2 - ESTATISTICAS DOS DOCENTES");
    }

    public void option3SubMenuRelatorios() {
        System.out.println("3 - ESTATISTICAS DOS ESTUDANTES");
    }

    public void option4SubMenuRelatorios() {
        System.out.println("4 - ESTATISTICAS DAS DISCIPLINAS DE UM DOCENTE");
    }

    public void digiteOpcao() {
        System.out.print("Digite uma opcao: ");
    }

    public void desejaRealmente () {
        System.out.println("Deseja realmente realizar essa operacao?");
    }

    /** Fim submenu messages */

    /** Relatorios messages */
    public void notFound(String isso) {
        System.out.println("Nao ha " + isso + " cadastrados(as)! :(");
    }

    public void titleRelatorio(String isso) {
        System.out.println(isso + " cadastrados(as):");
    }

    public void showSomething(String something) {
        System.out.println(something);
    }

    public void docenteCadastrado(String nome, int nPeriodos, float mediaAtividades, float percentualSincrona, float mediaAvaliacoesEmAtividades) {
        System.out.println("____________________________________");
        System.out.println("Docente: " + nome);
        System.out.println("Numero de periodos: " + nPeriodos + " | Media de atividades por disciplina:  " + mediaAtividades);
        System.out.println("Percentual de atividades sincronas: " + percentualSincrona + " | Media de avaliacoes recebidas em atividades:  " + mediaAvaliacoesEmAtividades);
        System.out.println("____________________________________");
    }

    public void disciplinaCadastrada(String ref, String nomeDisciplina, String nomeDocente, String emailDocente,
            int nEstudantes, int nAtividades) {
        System.out.println("____________________________________");

        System.out.println("- " + ref + " | Disciplina: " + nomeDisciplina);
        System.out.println("Docente responsavel: " + nomeDocente + " | E-mail do docente: " + emailDocente);
        System.out.println("Numero de estudantes matriculados: " + nEstudantes + " | Numero de atividades propostas: "
                + nAtividades);

        System.out.println("____________________________________");
    }

    public void estudanteCadastrado(int matricula, String nome, float mediaDisciplinasPeriodo, float mediaAvaliacoesDisciplina, float mediaNotas) {
        System.out.println("____________________________________");

        System.out.println("- Matricula: " + matricula + " | Estudante: " + nome);
        System.out.println("- Media de disciplinas por periodo: " + mediaDisciplinasPeriodo + " | Media de avaliacoes por disciplinas: " + mediaAvaliacoesDisciplina + " | Media das notas avaliadas: " + mediaNotas);

        System.out.println("____________________________________");
    }

    public void avaliacaoCadastrada(String nome, float nota) {
        System.out.println("- Estudante " + nome + " avaliou essa atividade com nota " + nota + ".");
    }

    public void atividadeCadastrada(int numero, String nome, String sincronismo) {
        System.out.println("- Codigo: " + numero + " | Atividade: " + nome + " | Sicronismo: " + sincronismo);
    }

    public void atividades(Map<Integer, Atividade> atividades){
        System.out.println("Atividades:");
        for (Integer chave: atividades.keySet()){
            System.out.println(atividades.get(chave).obterNumeroSequencial() + " - " + atividades.get(chave).obterNome());
        }
        
    }

    public void disciplinasDeDocente(String periodo, String codigo, String nome, int nAtividades, float percentualSincronas, int cargaHoraria, Map<Integer, Atividade> atividadesAvaliativas) {
        System.out.println("____________________________________");

        System.out.println("- Periodo" + periodo + " | Codigo: " + codigo + " | Nome: " + nome);
        System.out.println("Numero de atividades: " + nAtividades + " | Percentual de atividades sincronas: " + percentualSincronas + " | Carga Horaria: " + cargaHoraria);
        atividades(atividadesAvaliativas);
        System.out.println("____________________________________");
    }


    /** Fim relatorios messages */

    /** Entradas messages */
    public void digiteAno() {
        System.out.print("Digite o ano (Ex: 2020, 2019, 1998, etc.): ");
    }

    public void digiteSemestre() {
        System.out.print("Digite o semestre - somente numero ou letra maiuscula (Ex: 1, 2, E, etc.): ");
    }

    public void digiteNome(String disso) {
        System.out.print("Digite o nome do(a) " + disso + ": ");
    }

    public void digitePeriodo() {
        System.out.print("Digite qual o periodo (Ex: 2020/E, 2018/1, etc.): ");
    }

    public void digiteRef(String disso) {
        System.out.print("Digite a referencia do(a) " + disso + ": ");
    }

    public void digiteMatricula() {
        System.out.print("Digite o numero de matricula do estudante (10 digitos): ");
    }

    public void digiteLogin() {
        System.out.print("Digite o login institucional do docente - somente letras minusculas (Ex: vitor.souza OU vitor.e.souza): ");
    }

    public void digiteSite(String disso) {
        System.out.print("Digite o site do " + disso + ": ");
    }

    public void possuiSite() {
        System.out.print("O docente possui site? (S/N): ");
    }

    public void digiteTipo() {
        System.out.println("Tipos de atividade:");
        System.out.println("1 - Aula");
        System.out.println("2 - Estudo");
        System.out.println("3 - Trabalho");
        System.out.println("4 - Prova");
        System.out.print("Digite o tipo de atividade (1, 2, 3 ou 4): ");
    }

    public void digiteNota(String quem) {
        System.out.print("Nota que o " + quem + " estudante deseja atribuir a atividade: ");
    }

    public void digiteData() {
        System.out.print("Digite a data de entrega (Ex: 00/00/0000): ");
    }

    public void digiteDataComHora() {
        System.out.print("Digite a data e o horario (Ex: 00/00/0000-00:00): ");
    }

    public void digiteNomeArquivo(){
        System.out.print("Digite o nome do arquivo: ");
    }

    public void instrucoesAdicionarConteudoAEstudar() {
        System.out.println(
                "Adicione primeiro um conteudo a ser estudado e, quando solicitado, uma URL.\nPara finalizar a insercao de conteudo, digite 0 ao ser solicitada a insercao de um novo conteudo.");
    }

    public void digiteConteudo() {
        System.out.print("Adicione um conteudo a ser estudado (0 para sair): ");
    }

    public void digiteURL() {
        System.out.print("Adicione uma URL: ");
    }

    public void digiteIntegrantes() {
        System.out.print("Digite o numero de integrantes do grupo: ");
    }

    public void digiteCargaHoraria() {
        System.out.print("Digite o numero da carga horaria prevista a realizacao da atividade: ");
    }

    public void digiteCodigoDisciplina() {
        System.out.print("Digite o codigo da disciplina - 3 letras maiusculas seguidas de 5 numeros (Ex: INF09331): ");
    }

    /** Fim entradas messages */

    /** Feedback messages */
    public void sucess(String isso) {
        System.out.println(isso + " cadastrado(a) com sucesso!");
    }

    public void foi() {
        System.out.println("foooi!");
    }

    public void error(String isso) {
        System.out.println("Nao foi possivel cadastrar o(a) " + isso + "!");
    }

    public void encontrado(String isso) {
        System.out.println(isso + " encontrado(a)!");
    }

    public void saindo(String disso) {
        System.out.println("Saindo do(a) " + disso + "...");
    }

    public void finalizado(String isso) {
        System.out.println(isso + " finalizado(a)!");
    }
    /** Fim feedback messages */

    /** Controle de erros messages */
    public void invalidData(String invalid) {
        System.out.println("Dado invalido: " + invalid);
    }

    public void invalidReferencia(String invalid) {
        System.out.println("Referencia invalida: " + invalid);
    }
    public void naoEncontrado() {
        System.out.println("Nao encontrado!");
    }

    public void cadastroRepetido(String disso) {
        System.out.println("Cadastro repetido: " + disso);
    }

    public void matriculaRepetida(int matricula, String disciplina) {
        System.out.println("Matricula repetida: " + matricula + " em " + disciplina + ".");
    }

    public void avaliacaoRepetida(int matricula, String disciplina, int numeroAtividade) {
        System.out.println("Avaliacao repetida: estudante " + matricula + " para atividade " + numeroAtividade + " de " + disciplina + ".");
    }

    public void dataFormatoErrado(){
        System.out.println("Formato incorreto de data.");
    }

    public void erroIO(){
        System.out.println("Erro de I/O.");
    }


    /** Fim controle de erros messages */
}