import java.io.*;
import java.net.*;
import java.util.TreeMap;

public class Servidor {
    private static TreeMap<String, Producto> productos;
    //Se inicializa un TreeMap para almacenar los productos disponibles.

    private static boolean ejecutando = true; // Bandera para controlar el bucle del servidor

    public static void main(String[] args) {
        // Inicializar los productos
        productos = new TreeMap<>();
        productos.put("PL", new Producto("Peras limoneras", 14, 5f));
        productos.put("PC", new Producto("Peras conferencia", 12, 7f));
        productos.put("PN", new Producto("Plátano canario", 5, 2.5f));
        productos.put("BN", new Producto("Bananas", 7, 1.3f));
        productos.put("TP", new Producto("Tomates tipo pera", 8, 1.7f));
        productos.put("TR", new Producto("Tomates Raf", 7, 5.3f));
        productos.put("UN", new Producto("Uvas negras", 8, 3.2f));
        productos.put("UB", new Producto("Uvas blancas", 5, 2.7f));
        productos.put("PT", new Producto("Picotas", 8, 4.3f));
        productos.put("CR", new Producto("Ciruelas rojas", 10, 2.8f));
        productos.put("MR", new Producto("Melocotones rojos", 3, 2.5f));
        productos.put("MA", new Producto("Melocotones amarillos", 4, 3.2f));

        // Iniciar el servidor
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            //Se crea un servidor que escucha en el puerto 12345.
            System.out.println("Servidor iniciado y esperando conexiones...");

            // Bucle controlado por la bandera
            while (ejecutando) {
                Socket clienteSocket = serverSocket.accept();
                //l servidor entra en un bucle continuo mientras la bandera ejecutando sea true.
                System.out.println("Cliente conectado.");

                // Pasar la gestión del cliente a un hilo
                HiloCliente cliente = new HiloCliente(clienteSocket, productos);
                cliente.start(); //Se espera conexiones entrantes
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }

        System.out.println("Servidor detenido.");
    }

    //Metodo para detener el server
    public static void detenerServidor() {
        ejecutando = false;
    }
}
