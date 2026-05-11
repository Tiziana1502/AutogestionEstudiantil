/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autogestionestudiantil;

/**
 *
 * @author benja
 */
public class materiaAnual extends Materia{
   
    public materiaAnual(String nombre, String codigo, int anio) {
        super(nombre, codigo,1, anio);
  
    }
 
    
    public int getCuatrimestresRequeridos() {
        return 2;
    }
 
    public double getUmbralRegularidad() {
        return 70.0;
    }
 
    @Override
    public void mostrarResumen() {
        System.out.println("---- Materia Anual ----");
        System.out.println("Nombre       : " + getNombre());
        System.out.println("Codigo       : " + getCodigo());
        System.out.println("Anio         : " + getAnio());
        System.out.println("Tipo         : Anual (2 cuatrimestres, regularidad >= 70%)");
        System.out.println("-----------------------");
    }
}
