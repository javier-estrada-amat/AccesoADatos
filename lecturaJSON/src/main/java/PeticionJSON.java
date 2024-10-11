import com.google.gson.Gson;
import model.Producto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PeticionJSON {


    public void procesarPeticion() {

        String urlString = "https://dummyjson.com/products";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linea = null;

            StringBuffer stringBuffer = new StringBuffer();

            while ((linea = bufferedReader.readLine()) != null) {
                stringBuffer.append(linea);
            }

            JSONObject peticionProducto = new JSONObject(stringBuffer.toString());
            JSONArray listaProductos = peticionProducto.getJSONArray("products");

            ArrayList<String> categorias = new ArrayList<>();

            for (Object item : listaProductos) {
                JSONObject producto = (JSONObject) item;
                System.out.println(producto.getString("title"));
                System.out.println(producto.getDouble("price"));

                JSONArray tags = producto.getJSONArray("tags");
                System.out.println("Las categorias del producto son: ");
                for (Object tag : tags) {
                    //System.out.println("\t"+tag);
                    categorias.add(tag.toString());
                }
            }

            // System.out.println(listaProductos);
            //System.out.println(peticionProducto);

        } catch (MalformedURLException e) {
            System.out.println("No es una web, por favor intentalo de nuevo");
        } catch (IOException e) {
            System.out.println("Error en la pagina, no responde");
        }

    }

    public void metodoMenu() {

        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1. Leer");
            System.out.println("2. Buscar");
            System.out.println("3. Filtrar");
            System.out.println("4. Exportar");
            System.out.println("5. Salir");
            System.out.println("Que opcion quieres realizar");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Leer JSON");
                    try {
                        URL url = new URL("https://dummyjson.com/products");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
                        JSONArray jsonArray = jsonObject.getJSONArray("products");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            String title = product.getString("title");
                            String description = product.getString("description");
                            double price = product.getDouble("price");
                            System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                                    , title, price, description);
                        }


                    } catch (MalformedURLException e) {
                        System.out.println("No se encuentra la URL");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.println("Buscar elementos");
                    System.out.println("Cual es el id del elemento que buscas");
                    int id = sc.nextInt();
                    filtrarPorId(id);
                    break;
                case 3:
                    System.out.println("Filtrar");
                    System.out.println("Introduce precio maximo ");
                    int maxA = sc.nextInt();
                    System.out.println("Introduce precio Minimo");
                    int minA = sc.nextInt();
                    filtrarPrecio(maxA, minA);
                    break;
                case 4:
                    System.out.println("Exportar");
                    exportarDatos();
                    break;
            }

        } while (opcion != 5);


    }

    private void filtrarPorId(int id) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject product = jsonArray.getJSONObject(i);

                if (id == product.getInt("id")) {
                    String title = product.getString("title");
                    String description = product.getString("description");
                    double price = product.getDouble("price");
                    System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                            , title, price, description);
                }
            }


        } catch (MalformedURLException e) {
            System.out.println("No se encuentra la URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void filtrarPrecio(int min, int max) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject product = jsonArray.getJSONObject(i);
                double price = product.getDouble("price");

                if (price > max && price > min) {
                    String title = product.getString("title");
                    String description = product.getString("description");
                    System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                            , title, price, description);
                }
            }


        } catch (MalformedURLException e) {
            System.out.println("No se encuentra la URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void exportarDatos() {
        // File -> FileWriter -> PrintWriter

        File file = new File("src/main/java/resources/productos.txt");
        PrintWriter printWriter = null;


        try {
            printWriter = new PrintWriter(new FileWriter(file));
            // Leer JSON -> iterar por producto -> escribe una linea

            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());

            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for (int i = 0; i < jsonArray.length(); i++) {
               // JSONObject object = jsonArray.getJSONObject(i);
                Producto producto = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),Producto.class);
               /* String title = object.getString("title");
                String description = object.getString("description");
                double price = object.getDouble("price");
                int stock = object.getInt("stock");
                int id = object.getInt("id"); */
               // Producto producto = new Producto(title,description,id,stock,price);
                String exportacionProducto = String.format("title:%s price:%.2f stock:%d", producto.getTitle(), producto.getPrice(),
                        producto.getStock());
              //  printWriter.println(exportacionProducto);
                System.out.println(exportacionProducto);
            }
            System.out.println("Exportación completada");

        } catch (MalformedURLException e) {
            System.out.println("Error en url");
        } catch (IOException e) {
            System.out.println("Error en la creacion de escritura");
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }


    }

}
