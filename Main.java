import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String nomeArquivo = null;

        if (args.length > 0) {
            nomeArquivo = args[0];
        }
        else {
            Scanner in = new Scanner(System.in);
            System.out.printf("Arquivo de entrada: ");
            nomeArquivo = in.nextLine();
            in.close();
        }

        Scanner scanner = new Scanner(new File(nomeArquivo));

        List<Periodo> periodos = new ArrayList<>();

        while (scanner.hasNextLine()) {
            int ano = scanner.nextInt();
            char semestre = scanner.next().charAt(0);
            periodos.add(new Periodo(ano, semestre));
        }
        scanner.close();

        for (Periodo periodo : periodos) {
            int ano = periodo.obterAno();
            char semestre = periodo.obterSemestre();
            System.out.println(ano + semestre);
      }
    }
}
