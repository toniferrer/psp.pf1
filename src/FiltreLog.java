import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiltreLog {
    public static void main(String[] args) throws IOException {
        String entrada;
        int resultat = 0;
        boolean esBuit = true;

        BufferedReader brPare = new BufferedReader(new InputStreamReader(System.in));

        //Detecta si la entrada es Error i els contabilitza
        while((entrada = brPare.readLine()) != null){
            if(entrada.contains("ERROR")){
                resultat++;
            }
            if(!entrada.isBlank()){
                esBuit = false;
            }

        }

        if(esBuit){
            System.err.println("Error: Text buit");
            System.exit(1);
        }else{
            System.out.println(resultat);
            System.exit(0);
        }

    }
}
