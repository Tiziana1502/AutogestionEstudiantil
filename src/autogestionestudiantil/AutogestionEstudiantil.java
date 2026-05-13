/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package autogestionestudiantil;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rossi
 */
public class AutogestionEstudiantil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in); 
              Estudiante alumno = new Estudiante("Agustina Bosco", "22001", "Programacion", 2023); 
      
        int opcion;
        int opcion2;
        do {
            System.out.println(" \n === MENU PRINCIPAL ===");
            System.out.println("1. Ver perfil");
            System.out.println("2. Gestion de materias");
            System.out.println("3. Registrar asistencia");
            System.out.println("4. Registrar calificacion");
            System.out.println("5. Ver reportes");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Error: Debes ingresar una opcion valida.");
                opcion = -1;
                continue;
            }
            switch (opcion) {
                case 1: //Ver perfil
                    alumno.mostrarResumen();
                    break;
 
                case 2: //Gestion de materias
                    System.out.println("\n=== SUB-MENU ===");
                    System.out.println("1. Inscribirse a una materia");
                    System.out.println("2. Dar de baja una materia");
                    System.out.println("3. Listado de materias y sus promedios");
                    System.out.println("4. Buscar materia por codigo o nombre");
                    System.out.println("5. Salir");
                    System.out.print("Opcion: ");
                    opcion2 = Integer.parseInt(sc.nextLine());
                    switch (opcion2) {
                        case 1:
                            boolean datoValido = false;
                            while (!datoValido) {
                                try {
                                    System.out.print("\nIngrese el nombre de la materia: ");
                                    String nombremateria;
                                    nombremateria = sc.nextLine();
                                    System.out.print("Ingrese el codigo de la materia (entre 3 - 10 caracteres): ");
                                    String codigo;
                                    codigo = sc.nextLine();
                                    System.out.print("Ingrese el anio: ");
                                    int anio;
                                    anio = Integer.parseInt(sc.nextLine());
                                    // BONUS herencia: elegir tipo de materia
                                    System.out.println("Tipo de materia:");
                                    System.out.println("1. Cuatrimestral");
                                    System.out.println("2. Anual");
                                    System.out.print("Opcion: ");
                                    int tipo = Integer.parseInt(sc.nextLine());
                                    Materia_1 nuevamateria;
                                    if (tipo == 2) {
                                        // MateriaAnual: no necesita cuatrimestre
                                        nuevamateria = new materiaAnual(nombremateria, codigo, anio);
                                    } else {
                                        System.out.print("Ingrese el cuatrimestre (1 o 2): ");
                                        int cuatrimestre = Integer.parseInt(sc.nextLine());
                                        nuevamateria = new materiaCuatrimestral(nombremateria, codigo, cuatrimestre, anio);
                                    }
                                    datoValido = true;
                                    System.out.println("Materia creada exitosamente!");
                                    alumno.inscribirse(nuevamateria);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\n[ERROR] " + e.getMessage());
                                    System.out.println("Por favor, intente nuevamente.\n");
                                }
                            }
                        break;
                        case 2:
                            System.out.print("\nIngrese el codigo de la materia: ");
                            String codigo;
                            codigo = sc.nextLine();
                            alumno.darDeBaja(codigo);
                        break;
                        case 3:
                            System.out.println("\nListado de materias inscriptas junto a su promedio");
                            ArrayList<InscripcionMateria> lista = alumno.getMaterias();
                            if (lista.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                for (InscripcionMateria ins : lista) {
                                    System.out.println("- " + ins.getMateria().getNombre() + " | Promedio: " + ins.getPromedio());
                                }
                            }
                        break;
                        case 4:
                            System.out.println("\n1. Buscar materia por el nombre");
                            System.out.println("2. Buscar materia por el codigo");
                            System.out.print("Opcion: ");
                            int opcion3;
                            opcion3 = Integer.parseInt(sc.nextLine());
                            switch (opcion3) {
                                case 1: {
                                    System.out.print("\nIngrese el nombre de la materia: ");
                                    String nombre = sc.nextLine();
                                    // buscarPorNombre retorna ArrayList (busqueda parcial)
                                    ArrayList<InscripcionMateria> resultados = alumno.buscarPorNombre(nombre);
                                    if (!resultados.isEmpty()) {
                                        for (InscripcionMateria encontrada : resultados) {
                                            System.out.println("Materia encontrada!");
                                            System.out.println("Codigo: " + encontrada.getMateria().getCodigo());
                                            System.out.println("Promedio actual: " + encontrada.getPromedio());
                                        }
                                    } else {
                                        System.out.println("El alumno no esta inscripto en la materia: " + nombre);
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.print("\nIngrese el codigo de la materia: ");
                                    String codigo2 = sc.nextLine();
                                    InscripcionMateria encontrada = alumno.buscarPorCodigo(codigo2);
                                    if (encontrada != null) {
                                        System.out.println("Materia encontrada!");
                                        System.out.println("Codigo: " + encontrada.getMateria().getCodigo());
                                        System.out.println("Promedio actual: " + encontrada.getPromedio());
                                    } else {
                                        System.out.println("El alumno no esta inscripto en la materia: " + codigo2);
                                    }
                                    break;
                                }
                                default:
                                    System.out.println("Opcion invalida.");
                                    break;
                            }
                        break;
                    }
                    break;
 
                case 3: //Registro de asistencias
                    System.out.print("\nIngrese el codigo de la materia en la cual va a registrar la asistencia: ");
                    String codigoAsis;
                    codigoAsis = sc.nextLine();
                    InscripcionMateria insEncontrada = alumno.getInscripcion(codigoAsis);
                    if (insEncontrada != null) {
                        System.out.print("El estudiante estuvo presente? (1 = Si / 0 = No): ");
                        int presente = Integer.parseInt(sc.nextLine());
                        insEncontrada.registrarAsistencia(presente == 1);
                    } else {
                        System.out.println("[ERROR] No se encontro la materia '" + codigoAsis +
                        "'. Verifique que el alumno este inscripto.");
                    }
                    break;
 
                case 4: //Registro de calificaciones
                    System.out.print("\nIngrese el codigo de la materia: ");
                    String nombre2;
                    nombre2 = sc.nextLine();
                    InscripcionMateria insEncontrada2 = alumno.getInscripcion(nombre2);
                    if (insEncontrada2 != null) {
                        System.out.print("Ingrese la nota (0 a 10): ");
                        double nota = Double.parseDouble(sc.nextLine());
                        insEncontrada2.agregarNota(nota);
                    } else {
                        System.out.println("[ERROR] No se encontro la materia '" + nombre2 +
                        "'. Verifique que el alumno este inscripto.");
                    }
                    break;
 
                case 5: //Reportes academicos
                    System.out.println("\n1. Reporte de situacion general");
                    System.out.println("2. Reporte de materias criticas");
                    System.out.println("3. Reporte de materias aprobadas");
                    System.out.println("4. Promedio general recursivo (BONUS)");
                    System.out.println("5. Polimorfismo con Consultable (BONUS)");
                    System.out.print("Opcion: ");
                    int opcion4;
                    opcion4 = Integer.parseInt(sc.nextLine());
                    switch (opcion4) {
                        case 1:
                            ArrayList<InscripcionMateria> lista2 = alumno.getMaterias();
                            if (lista2.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                int regulares = 0;
                                int libres = 0;
                                int enRiesgo = 0;
                                System.out.println("\n======= REPORTE DE SITUACION GENERAL =======");
                                for (InscripcionMateria ins : lista2) {
                                    String estado;
                                    if (ins.getPorcentajeAsistencia() < 75 || ins.getPromedio() < 4) {
                                        estado = "Libre";
                                        libres++;
                                    } else if (ins.getPromedio() >= 7) {
                                        estado = "Aprobado (Promocionado)";
                                        regulares++;
                                    } else {
                                        estado = "En curso (Regular)";
                                        regulares++;
                                    }
                                    if (ins.getPorcentajeAsistencia() < 80 || ins.getPromedio() < 5) {
                                        enRiesgo++;
                                    }
                                    System.out.println("- " + ins.getMateria().getNombre() +
                                    " | Condicion: " + ins.getCondicion() +
                                    " | Promedio: " + ins.getPromedio() +
                                    " | Estado: " + estado);
                                }
                                System.out.println("\n--- RESUMEN ESTADISTICO ---");
                                System.out.println("Materias Regulares/Aprobadas: " + regulares);
                                System.out.println("Materias Libres: " + libres);
                                System.out.println("Materias en Riesgo: " + enRiesgo);
                                System.out.println("Promedio General: " + alumno.getPromedioGeneral());
 
                                // BONUS Rankeable: ranking de materias por puntaje descendente
                                ArrayList<InscripcionMateria> ranking = alumno.getRankingMaterias();
                                System.out.println("\n--- RANKING DE MATERIAS (Puntaje Descendente) ---");
                                int puesto = 1;
                                for (InscripcionMateria ins : ranking) {
                                    System.out.println(puesto + " - " + ins.getMateria().getNombre() +
                                    " | Puntaje: " + ins.getPuntajeRanking());
                                    puesto++;
                                }
                            }
                        break;
                        case 2:
                            System.out.println("\n=== MATERIAS EN RIESGO DE ASISTENCIA (75% - 85%) ===");
                            ArrayList<InscripcionMateria> riesgo = alumno.getMateriasCriticas();
                            if (riesgo.isEmpty()) {
                                System.out.println("No se encontraron materias en este rango de asistencia.");
                            } else {
                                System.out.printf("%-20s | %-12s | %-10s%n", "Materia", "Asistencia", "Promedio");
                                System.out.println("-------------------------------------------------------");
                                for (InscripcionMateria ins : riesgo) {
                                    System.out.printf("%-20s | %-12.2f%% | %-10.2f%n",
                                        ins.getMateria().getNombre(),
                                        ins.getPorcentajeAsistencia(),
                                        ins.getPromedio());
                                }
                                System.out.println("-------------------------------------------------------");
                                System.out.println("Nota: Estas materias requieren atencion para no quedar libre.");
                            }
                        break;
                        case 3:
                            ArrayList<InscripcionMateria> todas = alumno.getMaterias();
                            double sumaPromedios = 0;
                            double notaMax = -1;
                            double notaMin = 11;
                            int contadorAprobadas = 0;
                            System.out.println("\n======= REPORTE DE MATERIAS APROBADAS =======");
                            System.out.printf("%-25s | %-10s | %-10s%n", "Materia", "Promedio", "Condicion");
                            System.out.println("------------------------------------------------------------");
                            for (InscripcionMateria ins : todas) {
                                double promActual = ins.getPromedio();
                                String condActual = ins.getCondicion();
                                if (promActual >= 6 && condActual.equalsIgnoreCase("Regular")) {
                                    System.out.printf("%-25s | %-10.2f | %-10s%n",
                                    ins.getMateria().getNombre(), promActual, condActual);
                                    sumaPromedios += promActual;
                                    if (promActual > notaMax) notaMax = promActual;
                                    if (promActual < notaMin) notaMin = promActual;
                                    contadorAprobadas++;
                                }
                            }
                            if (contadorAprobadas > 0) {
                                double promedioDelConjunto = sumaPromedios / contadorAprobadas;
                                System.out.println("------------------------------------------------------------");
                                System.out.println("ESTADISTICAS DEL CONJUNTO:");
                                System.out.printf("Nota Maxima:  %.2f%n", notaMax);
                                System.out.printf("Nota Minima:  %.2f%n", notaMin);
                                System.out.printf("Promedio:     %.2f%n", promedioDelConjunto);
                                System.out.println("Total materias aprobadas: " + contadorAprobadas);
                            } else {
                                System.out.println("No se encontraron materias que cumplan con los criterios.");
                            }
                        break;
 
                        // BONUS recursividad
                        case 4:
                            ArrayList<InscripcionMateria> mats = alumno.getMaterias();
                            double promedioRec = calcularPromedioRecursivo(mats, 0, 0.0);
                            System.out.println("\n--- Promedio general (calculo recursivo) ---");
                            System.out.printf("Promedio: %.2f%n", promedioRec);
                        break;
 
                        // BONUS polimorfismo con List<Consultable>
                        case 5:
                            System.out.println("\n--- Listado con List<Consultable> (Polimorfismo) ---");
                            List<Consultable> consultables = new ArrayList<>();
                            for (InscripcionMateria ins : alumno.getMaterias()) {
                                consultables.add(ins.getMateria()); // Materia implementa Consultable
                            }
                            if (consultables.isEmpty()) {
                                System.out.println("No hay materias inscriptas.");
                            } else {
                                // mostrarResumen() se ejecuta segun el tipo real: MateriaCuatrimestral o MateriaAnual
                                for (Consultable c : consultables) {
                                    c.mostrarResumen();
                                }
                            }
                        break;
                    }
                    break;
 
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    if (opcion != -1) {
                        System.out.println("Opcion invalida. Intente nuevamente");
                        break;
                    }
            }
        } while (opcion != 0);
    }
 
    // BONUS recursividad: calculo del promedio general de forma recursiva
    static double calcularPromedioRecursivo(ArrayList<InscripcionMateria> materias, int index, double acumulado) {
        if (materias.isEmpty()) return 0.0;
        if (index == materias.size()) return acumulado / materias.size();
        return calcularPromedioRecursivo(materias, index + 1, acumulado + materias.get(index).getPromedio());
    }
}
