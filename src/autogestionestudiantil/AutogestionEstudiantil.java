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
        Estudiante alumno = new Estudiante("Ana Garcia", "22001", "Interfaz Grafica", 2023);
 
        int opcion;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
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
 
                case 1: // Ver perfil
                    alumno.mostrarResumen();
                    break;
 
                case 2: // Gestion de materias
                    int opcion2;
                    System.out.println("\n=== GESTION DE MATERIAS ===");
                    System.out.println("1. Inscribirse a una materia");
                    System.out.println("2. Dar de baja una materia");
                    System.out.println("3. Listado de materias inscriptas");
                    System.out.println("4. Buscar materia por codigo o nombre");
                    System.out.println("0. Volver");
                    System.out.print("Opcion: ");
                    try {
                        opcion2 = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Error: opcion invalida.");
                        break;
                    }
 
                    switch (opcion2) {
 
                        case 1: // Inscribirse
                            boolean datoValido = false;
                            while (!datoValido) {
                                try {
                                    System.out.print("\nNombre de la materia: ");
                                    String nombreMateria = sc.nextLine();
                                    System.out.print("Codigo (3 a 10 caracteres): ");
                                    String codigo = sc.nextLine();
                                    System.out.print("Cuatrimestre (1 o 2): ");
                                    int cuatrimestre = Integer.parseInt(sc.nextLine());
                                    System.out.print("Anio: ");
                                    int anio = Integer.parseInt(sc.nextLine());
                                    System.out.println("Tipo: 1. Cuatrimestral  2. Anual");
                                    System.out.print("Opcion: ");
                                    int tipo = Integer.parseInt(sc.nextLine());
 
                                    Materia_1 nuevaMateria;
                                    if (tipo == 2) {
                                        nuevaMateria = new materiaAnual(nombreMateria, codigo, anio);
                                    } else {
                                        nuevaMateria = new materiaCuatrimestral(nombreMateria, codigo, cuatrimestre, anio);
                                    }
                                    alumno.inscribirse(nuevaMateria);
                                    datoValido = true;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\n[ERROR] " + e.getMessage());
                                    System.out.println("Por favor, intente nuevamente.\n");
                                }
                            }
                            break;
 
                        case 2: // Dar de baja
                            System.out.print("\nIngrese el codigo de la materia: ");
                            String codigoBaja = sc.nextLine();
                            alumno.darDeBaja(codigoBaja);
                            break;
 
                        case 3: // Listar materias
                            ArrayList<InscripcionMateria> lista = alumno.getMaterias();
                            System.out.println("\nListado de materias inscriptas:");
                            if (lista.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                for (InscripcionMateria ins : lista) {
                                    System.out.printf("- %-20s | %-8s | Asistencia: %5.1f%% | Promedio: %.2f%n",
                                        ins.getMateria().getNombre(),
                                        ins.getCondicion(),
                                        ins.getPorcentajeAsistencia(),
                                        ins.getPromedio()
                                    );
                                }
                            }
                            break;
 
                        case 4: // Buscar
                            System.out.println("\n1. Buscar por nombre");
                            System.out.println("2. Buscar por codigo");
                            System.out.print("Opcion: ");
                            int opcion3;
                            try {
                                opcion3 = Integer.parseInt(sc.nextLine());
                            } catch (Exception e) {
                                System.out.println("Opcion invalida.");
                                break;
                            }
                            switch (opcion3) {
                                case 1:
                                    System.out.print("\nIngrese el nombre (o parte): ");
                                    String nombre = sc.nextLine();
                                    ArrayList<InscripcionMateria> resultadosNombre = alumno.buscarPorNombre(nombre);
                                    if (resultadosNombre.isEmpty()) {
                                        System.out.println("No se encontraron materias con ese nombre.");
                                    } else {
                                        for (InscripcionMateria ins : resultadosNombre) {
                                            System.out.println("Materia encontrada!");
                                            System.out.println("Nombre  : " + ins.getMateria().getNombre());
                                            System.out.println("Codigo  : " + ins.getMateria().getCodigo());
                                            System.out.printf ("Promedio: %.2f%n", ins.getPromedio());
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.print("\nIngrese el codigo: ");
                                    String codigoBuscar = sc.nextLine();
                                    InscripcionMateria encontrada = alumno.buscarPorCodigo(codigoBuscar);
                                    if (encontrada != null) {
                                        System.out.println("Materia encontrada!");
                                        System.out.println("Nombre  : " + encontrada.getMateria().getNombre());
                                        System.out.println("Codigo  : " + encontrada.getMateria().getCodigo());
                                        System.out.printf ("Promedio: %.2f%n", encontrada.getPromedio());
                                    } else {
                                        System.out.println("No se encontro ninguna materia con ese codigo.");
                                    }
                                    break;
                                default:
                                    System.out.println("Opcion invalida.");
                                    break;
                            }
                            break;
 
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                    break;
 
                case 3: // Registrar asistencia
                    System.out.print("\nIngrese el codigo de la materia: ");
                    String codigoAsis = sc.nextLine();
                    InscripcionMateria insAsis = alumno.getInscripcion(codigoAsis);
                    if (insAsis == null) {
                        System.out.println("[ERROR] No se encontro la materia. Verifique que este inscripto.");
                    } else {
                        System.out.print("El estudiante estuvo presente? (1 = Si / 0 = No): ");
                        try {
                            int presente = Integer.parseInt(sc.nextLine());
                            insAsis.registrarAsistencia(presente == 1);
                        } catch (Exception e) {
                            System.out.println("Error: ingrese 1 o 0.");
                        }
                    }
                    break;
 
                case 4: // Registrar calificacion
                    System.out.print("\nIngrese el codigo de la materia: ");
                    String codigoCali = sc.nextLine();
                    InscripcionMateria insCali = alumno.getInscripcion(codigoCali);
                    if (insCali == null) {
                        System.out.println("[ERROR] No se encontro la materia. Verifique que este inscripto.");
                    } else {
                        System.out.print("Ingrese la nota (0 a 10): ");
                        try {
                            double nota = Double.parseDouble(sc.nextLine());
                            insCali.agregarNota(nota);
                        } catch (Exception e) {
                            System.out.println("Error: ingrese un numero valido.");
                        }
                    }
                    break;
 
                case 5: // Reportes
                    int opcion4;
                    System.out.println("\n=== REPORTES ACADEMICOS ===");
                    System.out.println("1. Reporte de situacion general");
                    System.out.println("2. Reporte de materias en riesgo");
                    System.out.println("3. Reporte de materias aprobadas");
                    System.out.println("4. Ranking de materias (BONUS)");
                    System.out.println("5. Promedio recursivo (BONUS)");
                    System.out.println("6. Polimorfismo con Consultable (BONUS)");
                    System.out.print("Opcion: ");
                    try {
                        opcion4 = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Error: opcion invalida.");
                        break;
                    }
 
                    switch (opcion4) {
 
                        case 1: // Situacion general
                            ArrayList<InscripcionMateria> todasLas = alumno.getMaterias();
                            if (todasLas.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                int regulares = 0, libres = 0, enRiesgo = 0;
                                System.out.println("\n======= REPORTE DE SITUACION GENERAL =======");
                                for (InscripcionMateria ins : todasLas) {
                                    double asistencia = ins.getPorcentajeAsistencia();
                                    String estado;
                                    if (ins.getCondicion().equals("Libre")) {
                                        estado = "Libre";
                                        libres++;
                                    } else if (asistencia <= 85) {
                                        estado = "En riesgo";
                                        enRiesgo++;
                                    } else {
                                        estado = ins.estaAprobada() ? "Aprobada" : "En curso";
                                        regulares++;
                                    }
                                    System.out.printf("- %-20s | %-8s | Asist: %5.1f%% | Prom: %.2f | %s%n",
                                        ins.getMateria().getNombre(),
                                        ins.getCondicion(),
                                        asistencia,
                                        ins.getPromedio(),
                                        estado
                                    );
                                }
                                System.out.println("\n--- RESUMEN ---");
                                System.out.printf("Promedio general : %.2f%n", alumno.getPromedioGeneral());
                                System.out.println("Regulares        : " + regulares);
                                System.out.println("En riesgo        : " + enRiesgo);
                                System.out.println("Libres           : " + libres);
 
                                // Ranking incluido en el reporte general (BONUS)
                                ArrayList<InscripcionMateria> ranking = alumno.getRankingMaterias();
                                System.out.println("\n--- RANKING DE MATERIAS (puntaje descendente) ---");
                                int puesto = 1;
                                for (InscripcionMateria ins : ranking) {
                                    System.out.printf("%d. %-20s | Puntaje: %.2f%n",
                                        puesto, ins.getMateria().getNombre(), ins.getPuntajeRanking());
                                    puesto++;
                                }
                            }
                            break;
 
                        case 2: // Materias en riesgo
                            System.out.println("\n=== MATERIAS EN RIESGO (75% - 85%) ===");
                            ArrayList<InscripcionMateria> riesgo = alumno.getMateriasCriticas();
                            if (riesgo.isEmpty()) {
                                System.out.println("No se encontraron materias en este rango de asistencia.");
                            } else {
                                System.out.printf("%-20s | %-12s | %-10s%n", "Materia", "Asistencia", "Promedio");
                                System.out.println("-----------------------------------------------");
                                for (InscripcionMateria ins : riesgo) {
                                    System.out.printf("%-20s | %10.2f%%  | %.2f%n",
                                        ins.getMateria().getNombre(),
                                        ins.getPorcentajeAsistencia(),
                                        ins.getPromedio()
                                    );
                                }
                                System.out.println("Nota: Estas materias requieren atencion para no quedar libre.");
                            }
                            break;
 
                        case 3: // Materias aprobadas
                            ArrayList<InscripcionMateria> todas = alumno.getMaterias();
                            double sumaPromedios = 0;
                            double notaMax = -1;
                            double notaMin = 11;
                            int contadorAprobadas = 0;
                            System.out.println("\n======= REPORTE DE MATERIAS APROBADAS =======");
                            System.out.printf("%-25s | %-10s | %-10s%n", "Materia", "Promedio", "Condicion");
                            System.out.println("----------------------------------------------------");
                            for (InscripcionMateria ins : todas) {
                                double prom = ins.getPromedio();
                                if (prom >= 6 && ins.getCondicion().equalsIgnoreCase("Regular")) {
                                    System.out.printf("%-25s | %-10.2f | %-10s%n",
                                        ins.getMateria().getNombre(), prom, ins.getCondicion());
                                    sumaPromedios += prom;
                                    if (prom > notaMax) notaMax = prom;
                                    if (prom < notaMin) notaMin = prom;
                                    contadorAprobadas++;
                                }
                            }
                            if (contadorAprobadas > 0) {
                                System.out.println("----------------------------------------------------");
                                System.out.printf("Nota maxima : %.2f%n", notaMax);
                                System.out.printf("Nota minima : %.2f%n", notaMin);
                                System.out.printf("Promedio    : %.2f%n", sumaPromedios / contadorAprobadas);
                                System.out.println("Total aprobadas: " + contadorAprobadas);
                            } else {
                                System.out.println("No hay materias aprobadas aun.");
                            }
                            break;
 
                        case 4: // Ranking BONUS
                            System.out.println("\n=== RANKING DE MATERIAS ===");
                            System.out.println("Puntaje = (Promedio x 0.6) + (Asistencia% x 0.4)");
                            System.out.println("------------------------------------------");
                            ArrayList<InscripcionMateria> rankingBonus = alumno.getRankingMaterias();
                            if (rankingBonus.isEmpty()) {
                                System.out.println("No hay materias inscriptas.");
                            } else {
                                int p = 1;
                                for (InscripcionMateria ins : rankingBonus) {
                                    System.out.printf("%d. %-20s | Prom: %.2f | Asist: %.1f%% | Puntaje: %.2f%n",
                                        p, ins.getMateria().getNombre(),
                                        ins.getPromedio(), ins.getPorcentajeAsistencia(),
                                        ins.getPuntajeRanking()
                                    );
                                    p++;
                                }
                            }
                            break;
 
                        case 5: // Recursividad BONUS
                            ArrayList<InscripcionMateria> mats = alumno.getMaterias();
                            double promedioRec = calcularPromedioRecursivo(mats, 0, 0.0);
                            System.out.println("\n--- Promedio general (calculo recursivo) ---");
                            System.out.printf("Promedio: %.2f%n", promedioRec);
                            break;
 
                        case 6: // Polimorfismo BONUS
                            System.out.println("\n--- Listado con List<Consultable> (Polimorfismo) ---");
                            List<Consultable> consultables = new ArrayList<>();
                            for (InscripcionMateria ins : alumno.getMaterias()) {
                                consultables.add(ins.getMateria()); 
                            }
                            if (consultables.isEmpty()) {
                                System.out.println("No hay materias inscriptas.");
                            } else {
                                for (Consultable c : consultables) {
                                    c.mostrarResumen(); 
                                }
                            }
                            break;
 
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                    break;
 
                case 0:
                    System.out.println("Hasta luego!");
                    break;
 
                default:
                    if (opcion != -1) {
                        System.out.println("Opcion invalida. Intente nuevamente.");
                    }
                    break;
            }
        } while (opcion != 0);
 
        sc.close();
    }

    static double calcularPromedioRecursivo(ArrayList<InscripcionMateria> materias, int index, double acumulado) {
        if (materias.isEmpty()) return 0.0;
        if (index == materias.size()) return acumulado / materias.size();
        return calcularPromedioRecursivo(materias, index + 1, acumulado + materias.get(index).getPromedio());
    }
    
}
