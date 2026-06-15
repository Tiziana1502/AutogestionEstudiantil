package autogestionestudiantil;

import java.io.*;
import java.util.ArrayList;

/**
 * DAO para persistencia de Estudiante en archivo de texto.
 * @author Lautaro Agorio
 */
public class EstudianteDAO {

    private static final String ARCHIVO = "estudiantes.txt";

    public void guardarEstudiantes(ArrayList<Estudiante> estudiantes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Estudiante e : estudiantes) {
                bw.write(e.toTexto());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar estudiantes: " + e.getMessage());
        }
    }

    public ArrayList<Estudiante> cargarEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    lista.add(Estudiante.fromTexto(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar estudiantes: " + e.getMessage());
        }
        return lista;
    }
}
