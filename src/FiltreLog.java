import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiltreLog {
    public static void main(String[] args) throws IOException {
        BufferedReader brPare = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        int resultat = 0;

        //Detecte si la entrada es Error i els contabilitza
        while((entrada = brPare.readLine()) != null){
            if(entrada.contains("ERROR")){
                resultat++;
            }
        }
        System.out.println(resultat);
    }
}
