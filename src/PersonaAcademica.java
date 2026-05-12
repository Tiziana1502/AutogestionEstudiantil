/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;
import java.util.ArrayList;
   
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in); 
        
    }
    abstract public class PersonaAcademica {
    //Atributos
    private String nombre;
    private String legajo;
    
    //Constructor
    PersonaAcademica(String nombre, String legajo){
        this.nombre = nombre;
        this.legajo = legajo;
    }
    //Getters
    public String GetNombre() {return nombre;}
    public String getLegajo() {return legajo;}
    
    //Setters
    public void SetNombre(String nombre){
        if (nombre != null && !nombre.isEmpty()){
            this.nombre = nombre;
        }
        else {
            System.out.println("Error: el nombre no puede estar vacio");
        }
    }
    public void SetLegajo(String legajo){
        if (legajo != null){
            this.legajo = legajo;
        }
        else {
            System.out.println("Error: el legajo no puede ser nulo");
        }
    }
    //metodos
    public abstract void mostrarResumen();
    
}
