import java.util.*;

public class Leitura {
    
    Scanner in; 

    public void iniciarLeitura(){
        this.in = new Scanner(System.in);
        in.useLocale(Locale.US);
    }

    public int inteiro(){
       return this.in.nextInt();
    }

    public float flutuante(){
        return this.in.nextFloat();
    }

    public String cadeiaCaract(){
        return this.in.nextLine();
    }

    public char caract(){
        return this.in.next().charAt(0);
    }

    public void finalizarLeitura(){
        this.in.close();
    }
}
