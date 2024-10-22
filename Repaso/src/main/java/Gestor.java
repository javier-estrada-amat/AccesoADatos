import java.io.*;

public class Gestor {
    private static File file = new File("src/main/java/prueba.txt");



    public void escrituraTPlano(){

        BufferedWriter bufferedWriter =null;
        FileWriter fileWriter =null;
        PrintWriter printWriter = null;
        try {
           /* fileWriter = new FileWriter(file, true);
            fileWriter.write("Prueba\n");
            fileWriter.write("Prueba\n");
            fileWriter.write("Prueba\n");
            fileWriter.write("Prueba\n");
            fileWriter.write("Prueba\n");
            fileWriter.write("Prueba\n"); */
            /*
            bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            bufferedWriter.write("hola y adios");
            bufferedWriter.newLine();
            */
            printWriter = new PrintWriter(new FileWriter(file,true));
            printWriter.write("hola como estas");

        } catch (IOException e) {
            System.out.println("Error en la puesta en escriutura del fichero");
        }
        finally {
            try {
                //fileWriter.close();
                //bufferedWriter.close();
                printWriter.close();
            }catch (NullPointerException e){
                System.out.println("Error en el cerrado");
            }
        }
    }

    public void lecturaTPlano(){
        // File
        //File -> FileReader -> Lectura caracter a caracter
        // File -> FileReader -> BufferedReader -> linea a linea (hasta encontrar un salto de linea)

        FileReader fileReader =null;
        BufferedReader bufferedReader = null;

        try {
            /*fileReader = new FileReader(file);
            int lectura = -1;
            fileReader.read(); // -1 si no hay nada o el caracter
            while ((lectura= fileReader.read())!= -1){
                System.out.println((char) lectura);
            } */
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea = null;
            while ((bufferedReader.readLine()) != null){
                System.out.println(linea);

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error en la ruta del fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } finally {
            //    fileReader.close();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error en el cerrado");
            }

        }

    }
}
