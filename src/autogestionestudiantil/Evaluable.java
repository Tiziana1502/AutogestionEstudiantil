
package autogestionestudiantil;

public interface Evaluable {

    // Métodos a implementar por la clase que use esta interfaz
    String getCondicion();
    double getPromedio();
    boolean estaAprobada();

    // Método default: ya tiene cuerpo, no necesita implementarse
    default void mostrarEstadoAcademico() {
        System.out.println("Condición: " + getCondicion());
        System.out.printf("Promedio: %.2f%n", getPromedio());
    }
}