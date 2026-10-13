import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiltreLog {
    public static void main(String[] args) throws IOException {
        String entrada;
        int resultat = 0;
        boolean esBuit = true;
        String paraula = "ERROR";

        //Si detecta un argument, substituïm el valor
        if(args.length > 0) paraula = args[0];

        //Llegir el pare
        BufferedReader brPare = new BufferedReader(new InputStreamReader(System.in));

        //Detecta si la entrada és error i els comptabilitza
        while((entrada = brPare.readLine()) != null){
            if(entrada.contains(paraula)) resultat++;
            if(!entrada.isBlank()) esBuit = false;
        }

        //Mostrar, acabar i enviar 1 o 0 en funció de si el text està buit o no
        if(esBuit){
            System.err.println("Error: Text buit");
            System.exit(1);
        }else{
            System.out.println(resultat);
            System.exit(0);
        }

    }
}
