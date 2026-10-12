import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pbFill = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "FiltreLog", "ERROR");
        ProcessBuilder pbFill2 = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "FiltreLog", "WARNING");

        //Redirigir els errors del fill cap a un fitxer físic
        File arxiuErrors = new File("errors_filtre.log");
        pbFill.redirectError(arxiuErrors);

        //Iniciam els dos procesos fills
        Process fill = pbFill.start();
        Process fill2 = pbFill2.start();

        //Declarem els buffers
        BufferedReader in = new BufferedReader(new InputStreamReader(fill.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fill.getOutputStream()));

        BufferedReader in2 = new BufferedReader(new InputStreamReader(fill2.getInputStream()));
        BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fill2.getOutputStream()));

        //Obrim el fitxer de prova per llegir
        BufferedReader prova = new BufferedReader(new FileReader("src/prova.txt"));

        //Llegim el fitxer línia per línia i enviem el contingut al fill
        String linia;
        while ((linia = prova.readLine()) != null){
            out.write(linia);
            out.newLine();

            out2.write(linia);
            out2.newLine();
        }

        prova.close();
        out.flush();
        out2.flush();
        out.close();
        out2.close();

        String resultat = in.readLine();
        String resultat2 = in2.readLine();

        in.close();
        in2.close();
        fill.waitFor();
        fill2.waitFor();

        //Capturem el exit value dels fills y mostrem segons el valor el resultat
        int valorSortida = fill.exitValue();
        int valorSortida2 = fill2.exitValue();

        if(valorSortida == 0 && valorSortida2 == 0){
            valorSortida = 0;
        } else {
            valorSortida = 1;
        }

        System.out.println("RESULTAT: ERRORS=" + resultat + " | WARNINGS=" + resultat2 + " | EXIT_CODE=" + valorSortida);
    }
}
