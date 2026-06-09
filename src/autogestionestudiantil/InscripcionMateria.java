/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benja
 */
package autogestionestudiantil;
import java.util.ArrayList;

public class InscripcionMateria implements Evaluable {
    private Materia_1 materia;
    private int totalClases;
    private int clasesAsistidas;
    private ArrayList<Double> notas;;
    
    InscripcionMateria(Materia_1 materia){
       this.totalClases = 0;
        this.clasesAsistidas = 0;
        this.notas = new ArrayList<>();
        this.materia = materia;
    }
    
    public boolean registrarAsistencia(boolean presente){
    this.totalClases++;
    if (presente){
            this.clasesAsistidas++;
        }
    double porcentaje = getPorcentajeAsistencia();
    
    System.out.println("Registro de Asistencia: " + materia.getNombre());
        System.out.println("Asistencia actual: " + porcentaje);
    
    if (porcentaje < 75) {
            System.out.println("ALERTA CRITICA: El estudiante tiene menos del 75%. Condición: LIBRE.");
        } else if (porcentaje < 80) {
            System.out.println("ADVERTENCIA: Asistencia por debajo del 80% (Zona de riesgo).");
        }
    return true;
    }
   
    public void agregarNota(double nota){
        if (nota < 0 && nota > 10){
            System.out.println("Error: la nota solo puede tener un valor entre 0-10");
            return;
        }
        if (this.notas.size() >= 5){
            System.out.println("Error: no se pueden registrar mas de 5 notas por materia");
            return;
        }
        this.notas.add(nota);
        System.out.println("Nota registrada con exito");
        System.out.println("Parcial/TP " + notas.size() + ": " + nota + " - " + (nota >= 6 ? "Aprobado" : "Desaprobado"));
 
        System.out.print("Notas en " + materia.getNombre() + ": ");
        for (int i = 0; i < notas.size(); i++) 
        {
            System.out.printf("%.2f%s", notas.get(i), i < notas.size() - 1 ? " | " : "");
        }
        System.out.println();
        System.out.printf("Promedio actualizado: ", getPromedio());
 
        int restantes = 5 - notas.size();
        if (restantes == 0) 
        {
            System.out.println("Se alcanzo el maximo de 5 notas para esta materia.");
        } else 
        {
            System.out.println("Podes agregar " + restantes + " nota/s mas.");
        }
    }
    
    public Materia_1 getMateria() { return materia; }    
    public int getTotalClases() { return totalClases; }
    public int getClasesAsistidas() { return clasesAsistidas; }
    public ArrayList<Double> getNotas() { return new ArrayList<>(notas); }
     
    public double getPorcentajeAsistencia() {
        if (totalClases == 0) return 0.0;
        return ((double) clasesAsistidas / totalClases) * 100;
    }
    
    @Override
    public boolean estaAprobada(){
        if (getPromedio() >= 6 && getCondicion().equals("Regular")){
            System.out.println("La condicion de la materia es regular");
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getCondicion() {
        if (getPorcentajeAsistencia() >= 75) {
            return "Regular";
        } else {
            return "Libre";
        }
    }

    @Override
    public double getPromedio() {
        if (notas == null || notas.isEmpty()) {
        return 0.0;
    }
    double suma = 0;
    for (double nota : notas) {
        suma += nota;
    }
    return suma / notas.size();
    }

    public String toTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append(materia.getCodigo()).append(";");
        sb.append(materia.getNombre()).append(";");
        sb.append(materia.getCuatrimestre()).append(";");
        sb.append(materia.getAnio()).append(";");
        sb.append(totalClases).append(";");
        sb.append(clasesAsistidas).append(";");
        for (int i = 0; i < notas.size(); i++) {
            sb.append(notas.get(i));
            if (i < notas.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    public static InscripcionMateria fromTexto(String linea) {
        String[] partes = linea.split(";");
        String codigo = partes[0];
        String nombre = partes[1];
        int cuatrimestre = Integer.parseInt(partes[2]);
        int anio = Integer.parseInt(partes[3]);
        int totalClases = Integer.parseInt(partes[4]);
        int clasesAsistidas = Integer.parseInt(partes[5]);

        Materia_1 materia = Materia_1.fromTexto(nombre + ";" + codigo + ";" + cuatrimestre + ";" + anio);
        InscripcionMateria ins = new InscripcionMateria(materia);

        for (int i = 0; i < clasesAsistidas; i++) {
            ins.registrarAsistencia(true);
        }
        for (int i = 0; i < (totalClases - clasesAsistidas); i++) {
            ins.registrarAsistencia(false);
        }

        if (partes.length > 6 && !partes[6].isEmpty()) {
            String[] notasArr = partes[6].split(",");
            for (String nota : notasArr) {
                ins.agregarNota(Double.parseDouble(nota));
            }
        }
        return ins;
    }
}
