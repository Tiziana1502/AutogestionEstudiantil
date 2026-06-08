/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autogestionestudiantil;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author rossi
 */
public class InscMateriaDAO {        
    private static final String ARCHIVO = "inscripciones.txt"; 
  
    private static final String SEP       = ";";   
    private static final String SEP_NOTAS = ",";  
 
   
    public void guardarInscripciones(ArrayList<InscripcionMateria> inscripciones) { 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) { 
            for (InscripcionMateria ins : inscripciones) {
                bw.write(toTexto(ins));
                bw.newLine();
            } 
        } catch (IOException e) {
            System.out.println("Error al guardar inscripciones: " + e.getMessage());
        }
    } 
   
    public ArrayList<InscripcionMateria> cargarInscripciones() { 
        ArrayList<InscripcionMateria> lista = new ArrayList<>();
       
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return lista;
            
        } 
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
 
            String linea;
            while ((linea = br.readLine()) != null) {
                
                if (linea.isBlank()) continue;
 
                InscripcionMateria ins = fromTexto(linea);
                if (ins != null) {
                    lista.add(ins);
                }
            } 
        } catch (IOException e) {
            System.out.println("Error al cargar inscripciones: " + e.getMessage());
        }  
        
        return lista;
    }
}
