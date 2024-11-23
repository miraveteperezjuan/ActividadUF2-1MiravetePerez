import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {

        //Creación del socket y streams
        //Crea un nuevo socket para conectarse al servidor que se encuentra en localhost
        try (Socket socket = new Socket("localhost", 12345);
             // ObjectOutputStream salida: Este objeto permite enviar objetos a través del socket.
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());

             //ObjectInputStream entrada: Este objeto permite leer objetos que llegan a través del socket.
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            //BufferedReader: Crea un lector para leer datos de entrada desde la consola (teclado).
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce el código del artículo (2 caracteres): ");

            //ReadLine(): Lee una línea de texto ingresada por el usuario.
            String codigoArticulo = bufferedReader.readLine();

            salida.writeObject(codigoArticulo); // Enviar el código del producto

            Object respuesta = entrada.readObject(); // Recibir la respuesta
            //Este objeto podría ser un producto o un error

            //instanceof Producto: Verifica si el objeto recibido del servidor es una instancia de la clase
            if (respuesta instanceof Producto) {
                System.out.println("Información del producto: " + respuesta);
            } else {
                System.out.println(respuesta); // Mensaje de error o información no encontrada
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

}
