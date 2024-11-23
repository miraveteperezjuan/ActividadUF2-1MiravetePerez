import java.io.*;
import java.net.Socket;
import java.util.TreeMap;

public class HiloCliente extends Thread {
    private Socket socket; //Representa la conexión directa con el cliente.
    private TreeMap<String, Producto> productos; //Un mapa que ordena los produtos.

    public HiloCliente(Socket socket, TreeMap<String, Producto> productos) {
        this.socket = socket; //El socket para la conexión con el cliente.
        this.productos = productos; //Un TreeMap que contiene los productos disponibles en el servidor.
    }

    @Override
    public void run() {
        try (
                //Configura las conexiones de entrada y salida usando ObjectInputStream y ObjectOutputStream.
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream())
        ) {
            //Lee el código del artículo enviado por el cliente y los castea a String
            String codigoArticulo = (String) entrada.readObject(); // Leer el código del artículo
            System.out.println("Código recibido: " + codigoArticulo);

            //Usa el código recibido como clave para buscar en el TreeMap
            Producto producto = productos.get(codigoArticulo);
            if (producto != null) {
                salida.writeObject(producto); // Enviar el producto encontrado al cliente.
            } else {
                //Si el producto existe, lo envía al cliente,
                salida.writeObject("Artículo no encontrado.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                //Se cierra el socket
                socket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}
