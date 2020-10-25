
# Trabalho de Programação III: OTRUS EARTE SYSTEM v0.0.3


## Sobre esse repositório
Repositório criado com o intuito de facilitar o acompanhamento do trabalho prático da disciplina de Programação III (INF09331), ministrada pelo professor Vítor Souza na Universidade Federal do Espírito santo em um semestre especial de ensino remoto.

## OTRUS EARTE SYSTEM
Proposta de sistema a ser implementada utilizando conceitos de orientação a objetos, em JAVA. 

### Objetivo
Mediante ao contexto pandêmico, faz-se necessário a elaboração de novas ferramentas para auxiliar a todos neste momento. Nesse programa, em específico, é solicitada a demanda de um sistema que gerencie períodos, professores, estudantes, disciplinas, suas atividades e o mais saudoso: o feedback dos estudantes em relação a essas atividades. 

### Primeiros passos
É recomendado que a aplicação seja testada em ambientes similiares aos que os programadores usaram. Sendo assim, JDK8 ou JDK11 são recomendados. No Windows 10, foram utilizados o VSCode para programação e teste, utilizando plug-ins que requerem suporte a JDK11+. Ainda no Windows 10, um terminal ubuntu integrado foi também utilizado juntamente ao JDK8.

### Instruções de compilação e utilização (novas features na versão 0.0.2):
Além da alteração do nome do projeto, a release 0.0.2 traz outras novas features. Logo após compilar o usuário é perguntado se deseja fazer um backup de dados já salvos ou se deseja iniciar uma aplicação do zero. Responda "SIM" para fazer o backup ou "NÃO" para iniciar do zero. Selecionando "SIM" digite em seguida o nome do arquivo (caso selecione "NÃO" sera necessário a inserção de dados para testar o trabalho) e em seguida será aberto o menu com os dados já carregados.

#### Menu principal
- As opções de 1-4 são utilizadas para realizar o cadastro de informações (PERÍODO, DOCENTE, DISCIPLINA e ESTUDANTE) importante prestar atenção no formato utilizado nos exemplos; 
- Opção 5: é realizado a matrícula do estudante em determinada disciplina; 
- Opção 6: o cadastro de uma atividade, selecionar o tipo de atividade, a referência da discinplina, e informações adicionais específicas de cada atividade (como hora e url do utilizado);
- Opção 7: realizar avaliação de determinada atividade, selecione o aluno, disciplina e atividade;
- Opção 8: relatórios de quatro tipos, são eles: 
  1. Visão geral dos períodos acadêmicos;
  2. Estatísticas dos docentes;
  3. Estatísticas dos estudantes;
  4. Estatísticas das disciplinas de um docente.
- Opção 9 realizar a serialização dos dados: digita o nome do arquivo que deseja armazenar os dados.

### Novidades da v0.0.3
A nova release (0.0.3) traz novidades relacionadas à perfomance, modularização, controle de erros e ordenação dos relatórios:
- Relatórios agora são exibidos com alguma ordenação;
- Aumento da performance de execução em função do número de reduzidos de buscas nos hashmaps;
- Controle de erros: entradas inválidas, principalmente. Agora o programa não é abortado nesses casos e uma tratativa é lançada.

### O que vem por aí
Para as próximas releases são esperadas as seguintes features:
- Relatórios agora possuem saídas externas (...);
