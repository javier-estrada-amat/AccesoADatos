public class Entrada {

    public static void main(String[] args)  {

       /* File file = new File("src/main/java/resources/usuario.txt");

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuffer lecturaCompleta = new StringBuffer();
            String linea = null;

            while ((linea= bufferedReader.readLine())!=null){
                lecturaCompleta.append(linea);
            }


            JSONObject usuario = new JSONObject(lecturaCompleta.toString());
            String nombreUsuario= usuario.getString("nombre");
            JSONArray asignaturas = usuario.getJSONArray("asignaturas");

            System.out.println(asignaturas);

            System.out.println(nombreUsuario);



        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error al leer");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error en el cerrado");
            }
        } */


        PeticionJSON peticionJSON = new PeticionJSON();
      //  peticionJSON.procesarPeticion();
        peticionJSON.metodoMenu();

    }
}
