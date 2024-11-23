import java.io.Serializable;

public class Producto implements Serializable {
    // Es serializable para enviar las instancias a través del socket entre cliente y servidor.

    private String nombre;
    private int stock;
    private double precio;

    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Stock: " + stock + ", Precio: " + precio + "€";
    }
}
