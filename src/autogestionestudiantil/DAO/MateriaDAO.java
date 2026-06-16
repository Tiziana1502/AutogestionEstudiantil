package autogestionestudiantil;

import java.io.*;
import java.util.ArrayList;

/**
 * DAO para persistencia de Materia_1 en archivo de texto.
 * @author Lautaro Agorio
 */
public class MateriaDAO {

    private static final String ARCHIVO = "materias.txt";

    public void guardarMaterias(ArrayList<Materia_1> materias) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Materia_1 m : materias) {
                bw.write(m.toTexto());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar materias: " + e.getMessage());
        }
    }

    public ArrayList<Materia_1> cargarMaterias() {
        ArrayList<Materia_1> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return lista;
        }
        Materia_1.limpiarCodigosUtilizados();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    lista.add(Materia_1.fromTexto(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar materias: " + e.getMessage());
        }
        return lista;
    }
}
