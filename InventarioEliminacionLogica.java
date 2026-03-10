import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InventarioEliminacionLogica {
    // Método para mostrar contenido de un archivo
    public static void mostrarArchivo(String nombreArchivo) {
        System.out.println("Contenido del archivo: " + nombreArchivo);
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        System.out.println("-------------------------");
    }

    // Métodos a implementar
    // 1. Marcar un registro como eliminado agregando '#' al inicio si coincide con el producto indicado
    public static void marcarEliminado(String nombreArchivo, String productoEliminar) {
        // Completar implementación
        File archivo = new File(nombreArchivo);
        File temp = new File("temp.txt");

        try (
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp))
        ) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");

                if (partes[0].equalsIgnoreCase(productoEliminar) && !linea.startsWith("#")) {
                    bw.write("#" + linea);
                } else {
                    bw.write(linea);
                }

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Reemplazar archivo original
        archivo.delete();
        temp.renameTo(archivo);
    }

    // 2. Crear un nuevo archivo sin los registros marcados como eliminados
    public static void crearArchivoSinEliminados(String archivoOriginal, String archivoNuevo) {
        // Completar implementación
        File original = new File(archivoOriginal);
    File nuevo = new File(archivoNuevo);

    try (
        BufferedReader br = new BufferedReader(new FileReader(original));
        BufferedWriter bw = new BufferedWriter(new FileWriter(nuevo))
    ) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (!linea.startsWith("#")) {
                bw.write(linea);
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String archivo = "inventario.txt";

        mostrarArchivo(archivo);

        System.out.print("Ingrese el nombre del producto para eliminar lógicamente: ");
        String productoEliminar = sc.nextLine();

        marcarEliminado(archivo, productoEliminar);
        mostrarArchivo(archivo);

        String archivoActualizado = "inventario_actualizado.txt";
        crearArchivoSinEliminados(archivo, archivoActualizado);
        mostrarArchivo(archivoActualizado);

        sc.close();
    }
}
