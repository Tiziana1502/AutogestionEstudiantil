
package autogestionestudiantil;

public interface Evaluable {


    String getCondicion();
    double getPromedio();
    boolean estaAprobada();

  
    default void mostrarEstadoAcademico() {
        System.out.println("Condición: " + getCondicion());
        System.out.printf("Promedio: %.2f%n", getPromedio());
    }
}