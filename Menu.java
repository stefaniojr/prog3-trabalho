import java.util.*;

public class Menu {

    Escrita escrever = new Escrita();
    Relatorio relatorio = new Relatorio();
    Cadastro cadastro = new Cadastro();

    public void menuPrincipal() {
        escrever.mostrarMenu();
    }

    public void subMenu1(Leitura ler, Map<String, Periodo> periodos) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "PERIODO");

            opcao = ler.inteiro();

            if (opcao == 1) {
                cadastro.periodo(ler, periodos);
            }

            else if (opcao == 2) {
                relatorio.periodosCadastrados(escrever, periodos);
            }

        } while (opcao != 3);

        return;

    }

    public void subMenu2(Leitura ler, Map<String, Docente> docentes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "DOCENTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.docente(ler, docentes);
            }

            else if (opcao == 2) {
                relatorio.docentesCadastrados(escrever, docentes);
            }

        } while (opcao != 3);

        return;
    }

    public void subMenu3(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {
        int opcao;
        do {

            escrever.mostrarSubMenu(escrever, "DISCIPLINA");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.disciplina(ler, periodos, docentes, disciplinas);
            }

            else if (opcao == 2) {
                relatorio.disciplinasCadastradas(escrever, disciplinas);
            }

        } while (opcao != 3);

        return;
    }

    public void subMenu4(Leitura ler, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "ESTUDANTE");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.estudante(ler, estudantes);
            }

            else if (opcao == 2) {
                relatorio.estudantesCadastrados(escrever, estudantes);
            }

        } while (opcao != 3);

        return;
    }

    public void subMenu5(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "ESTUDANTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.estudanteEmDisciplina(ler, disciplinas, estudantes);
            }

            else if (opcao == 2) {
                relatorio.turmasFormadas(escrever, disciplinas, estudantes);
            }
        } while (opcao != 3);

        return;
    }

    public void subMenu6(Leitura ler, Map<String, Disciplina> disciplinas) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "ATIVIDADE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.atividadeEmDisciplina(ler, disciplinas);                
            }

            else if (opcao == 2) {
                escrever.digiteRef("disciplina");
                String disciplinaRef = ler.cadeiaCaract();
                disciplinas.get(disciplinaRef).exibirAtividades();
            }

        } while (opcao != 3);

        return;
    }

    public void subMenu7(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu(escrever, "AVALIACAO");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.avaliacaoEmAtividade(ler, disciplinas, estudantes);
            }

            else if (opcao == 2) {
                escrever.digiteRef("disciplina");
                String disciplinaRef = ler.cadeiaCaract();
                escrever.digiteRef("atividade");
                int atividadeRef = ler.inteiro();
                ler.cadeiaCaract();
                Atividade atividade = disciplinas.get(disciplinaRef).obterAtividade(atividadeRef);
                atividade.exibirAvaliacoes();
            }

        } while (opcao != 3);

        return;
    }

}