import java.io.IOException;
import java.util.*;
import java.io.File;

public class Menu {

    Escrita escrever = new Escrita();
    Cadastro cadastro = new Cadastro();
    Relatorio relatorios = new Relatorio();

    public void menuPrincipal() {
        escrever.mostrarMenu();
    }

    public void subMenu1(Leitura ler, Map<String, Periodo> periodos) {
        int opcao;

        relatorios.periodosCadastrados(escrever, periodos);

        do {

            escrever.mostrarSubMenu("PERIODO");

            opcao = ler.inteiro();

            if (opcao == 1) {
                cadastro.periodo(ler, periodos);
            }

        } while (opcao != 2);

        return;

    }

    public void subMenu2(Leitura ler, Map<String, Docente> docentes) {
        int opcao;

        relatorios.docentesCadastrados(escrever, docentes);

        do {

            escrever.mostrarSubMenu("DOCENTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.docente(ler, docentes);
            }

        } while (opcao != 2);

        return;
    }

    public void subMenu3(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas) {
        int opcao;
        do {

            escrever.mostrarSubMenu("DISCIPLINA");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.disciplina(ler, periodos, docentes, disciplinas);
            }

        } while (opcao != 2);

        return;
    }

    public void subMenu4(Leitura ler, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu("ESTUDANTE");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.estudante(ler, estudantes);
            }

        } while (opcao != 2);

        return;
    }

    public void subMenu5(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu("ESTUDANTE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.estudanteEmDisciplina(ler, disciplinas, estudantes);
            }

        } while (opcao != 2);

        return;
    }

    public void subMenu6(Leitura ler, Map<String, Disciplina> disciplinas) {
        int opcao;

        do {

            escrever.mostrarSubMenu("ATIVIDADE");

            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.atividadeEmDisciplina(ler, disciplinas);
            }

            // else if (opcao == 2) {
            // escrever.digiteRef("disciplina");
            // String disciplinaRef = ler.cadeiaCaract();
            // disciplinas.get(disciplinaRef).exibirAtividades();
            // }

        } while (opcao != 2);

        return;
    }

    public void subMenu7(Leitura ler, Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {

            escrever.mostrarSubMenu("AVALIACAO");
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                cadastro.avaliacaoEmAtividade(ler, disciplinas, estudantes);
            }

            // else if (opcao == 2) {
            // escrever.digiteRef("disciplina");
            // String disciplinaRef = ler.cadeiaCaract();
            // escrever.digiteRef("atividade");
            // int atividadeRef = ler.inteiro();
            // ler.cadeiaCaract();
            // Atividade atividade =
            // disciplinas.get(disciplinaRef).obterAtividade(atividadeRef);
            // atividade.exibirAvaliacoes();
            // }

        } while (opcao != 2);

        return;
    }

    public void subMenu8(Leitura ler, Map<String, Periodo> periodos, Map<String, Docente> docentes,
            Map<String, Disciplina> disciplinas, Map<Integer, Estudante> estudantes) {
        int opcao;

        do {
            escrever.mostrarSubMenuRelatorios();
            opcao = ler.inteiro();
            ler.cadeiaCaract();

            if (opcao == 1) {
                relatorios.periodosCadastrados(escrever, periodos);
                escrever.digiteRef("periodo");
                String periodoRef = ler.cadeiaCaract();
                Periodo periodo = periodos.get(periodoRef);
                relatorios.estatisticasPeriodo(ler, escrever, periodo);
            }

            else if (opcao == 2) {
                relatorios.estatisticasDocentes(escrever, docentes);
            }

            else if (opcao == 3) {
                relatorios.estatisticasEstudantes(escrever, estudantes);
            }

            else if (opcao == 4) {
                relatorios.docentesCadastrados(escrever, docentes);
                escrever.digiteRef("docente");
                String docenteRef = ler.cadeiaCaract();
                Docente docente = docentes.get(docenteRef);
                relatorios.estatisticasDisciplinasDeDocente(escrever, docente);
            }

        } while (opcao != 5);

        return;
    }

    public void subMenu9(Leitura ler, String arquivoSerializacao, Main aplicacao)
            throws IOException {
        int opcao;

        escrever.showAsterisks();
        escrever.desejaRealmente();
        escrever.yesOrNoMenu();

        opcao = ler.inteiro();
        ler.cadeiaCaract();

        if (opcao == 1) {

            Serializar salvar = new Serializar(new File(arquivoSerializacao));
            salvar.iniciarSerializacao(aplicacao);
            return;
        }

        else if (opcao == 2) {
            return;
        }

        return;
    }

    public void subMenu10(Leitura ler, String arquivoSerializacao, Main aplicacao)
            throws IOException, ClassNotFoundException {
        int opcao;

        escrever.showAsterisks();
        escrever.desejaRealmente();
        escrever.yesOrNoMenu();

        opcao = ler.inteiro();
        ler.cadeiaCaract();

        if (opcao == 1) {
            Desserializar carregar = new Desserializar(new File(arquivoSerializacao));
            aplicacao = carregar.iniciarDesserializacao();
            return;
        }

        else if (opcao == 2) {
            return;
        }

        return;
    }

}