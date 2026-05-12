/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autogestionestudiantil;

/**
 *
 * @author benja
 */
public class materiaCuatrimestral extends Materia_1{
    // Constructor
    public materiaCuatrimestral(String nombre, String codigo, int cuatrimestre, int anio) {
        super(nombre, codigo, cuatrimestre, anio);
    }

    public int getCuatrimestresRequeridos() {
        return 1;
    }

    public double getUmbralRegularidad() {
        return 75.0;
    }
 
    @Override
    public void mostrarResumen() {
        System.out.println("---- Materia Cuatrimestral ----");
        System.out.println("Nombre       : " + getNombre());
        System.out.println("Codigo       : " + getCodigo());
        System.out.println("Cuatrimestre : " + getCuatrimestre() + "°");
        System.out.println("Anio         : " + getAnio());
        System.out.println("Tipo         : Cuatrimestral (1 cuatrimestre, regularidad >= 75%)");
        System.out.println("-------------------------------");
    }
}
