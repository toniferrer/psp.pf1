import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pbFill = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "FiltreLog");

        //Redirigir els errors del fill cap a un fitxer físic
        File arxiuErrors = new File("errors_filtre.log");
        pbFill.redirectError(arxiuErrors);

        //Inicia el process fill
        Process fill = pbFill.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(fill.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fill.getOutputStream()));

        //Obrim el fitxer de prova per llegir
        BufferedReader prova = new BufferedReader(new FileReader("src/prova.txt"));

        //Llegim el fitxer línia per línia i enviem el contingut al fill
        String linia;
        while ((linia = prova.readLine()) != null){
            out.write(linia);
            out.newLine();
        }

        prova.close();
        out.flush();
        out.close();

        String resultat = in.readLine();
        System.out.println(resultat);

        in.close();
        fill.waitFor();

        //Capturem el exit value del fill y mostrem segons el valor el resultat
        int valorSortida = fill.exitValue();

        if(valorSortida == 1){
            System.out.println("Error: texte buit");
        }else{
            System.out.println("Tot correcte.");
        }
    }
}
