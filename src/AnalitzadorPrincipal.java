import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pbFill = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "FiltreLog");
        pbFill.redirectErrorStream(true);
        Process fill = pbFill.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(fill.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fill.getOutputStream()));

        //Llegir un arxiu de prova
        BufferedReader prova = new BufferedReader(new FileReader("src/prova.txt"));

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
    }
}
