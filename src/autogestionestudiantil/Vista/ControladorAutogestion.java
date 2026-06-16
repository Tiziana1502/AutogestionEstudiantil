/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autogestionestudiantil.Vista; // <-- Asegura que use la carpeta correcta en minúsculas

// IMPORTAMOS LAS CLASES DEL BACKEND QUE ESTÁN EN LOS OTROS PAQUETES
import autogestionestudiantil.Estudiante;
import autogestionestudiantil.InscripcionMateria;
import autogestionestudiantil.Materia_1;
import autogestionestudiantil.EstudianteDAO;
import autogestionestudiantil.InscMateriaDAO;
import autogestionestudiantil.MateriaDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorAutogestion implements ActionListener {

    private VistaPrincipal vista;
    private Estudiante estudianteActual;
    
    private EstudianteDAO estudianteDAO;
    private InscMateriaDAO inscMateriaDAO;

    public ControladorAutogestion(VistaPrincipal vista) {
        this.vista = vista;
        this.estudianteDAO = new EstudianteDAO();
        this.inscMateriaDAO = new InscMateriaDAO();
        
        inicializarEstudiante();
        
        vista.getCmbAsistencia().setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presente", "Ausente" }));

        // Enlazar componentes de navegación
        this.vista.getBtnPanelPrincipal().addActionListener(this);
        this.vista.getBtnPerfil().addActionListener(this);
        this.vista.getBtnReportes().addActionListener(this);
        this.vista.getjMItemCerrar().addActionListener(this);
        this.vista.getjMItemSituacion().addActionListener(this);
        this.vista.getjMItemMatRiesgo().addActionListener(this);
        this.vista.getjMItemMatAprob().addActionListener(this);

        // Enlazar botones de operaciones
        this.vista.getBtnInscribir().addActionListener(this);
        this.vista.getBtnAsistencia().addActionListener(this);
        this.vista.getBtnNota().addActionListener(this);
        this.vista.getBtnBaja().addActionListener(this);

        refrescarPantallaCompleta();
    }

    private void inicializarEstudiante() {
        ArrayList<Estudiante> lista = estudianteDAO.cargarEstudiantes();
        if (!lista.isEmpty()) {
            this.estudianteActual = lista.get(0);
            ArrayList<InscripcionMateria> inscripciones = inscMateriaDAO.cargarInscripciones();
            for (InscripcionMateria ins : inscripciones) {
                this.estudianteActual.getMaterias().add(ins);
            }
        } else {
            this.estudianteActual = new Estudiante("Agustina Bosco", "12345", "Programacion", 2023);
            ArrayList<Estudiante> guardarLista = new ArrayList<>();
            guardarLista.add(estudianteActual);
            estudianteDAO.guardarEstudiantes(guardarLista);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnPanelPrincipal() || e.getSource() == vista.getBtnPerfil()) {
            vista.mostrarCarta("card2");
        } else if (e.getSource() == vista.getBtnReportes()) {
            vista.mostrarCarta("card3");
            generarReporteGeneral();
        } else if (e.getSource() == vista.getjMItemCerrar()) {
            System.exit(0);
        }
        else if (e.getSource() == vista.getjMItemSituacion()) {
            vista.mostrarCarta("card3");
            generarReporteGeneral();
        } else if (e.getSource() == vista.getjMItemMatRiesgo()) {
            vista.mostrarCarta("card3");
            generarReporteRiesgo();
        } else if (e.getSource() == vista.getjMItemMatAprob()) {
            vista.mostrarCarta("card3");
            generarReporteAprobadas();
        }
        else if (e.getSource() == vista.getBtnInscribir()) {
            ejecutarInscripcion();
        } else if (e.getSource() == vista.getBtnAsistencia()) {
            ejecutarRegistrarAsistencia();
        } else if (e.getSource() == vista.getBtnNota()) {
            ejecutarRegistrarNota();
        } else if (e.getSource() == vista.getBtnBaja()) {
            ejecutarBajaMateria();
        }
    }

    private void ejecutarInscripcion() {
        String nombre = vista.getTxtNombreMateria();
        String codigo = vista.getTxtCodigo();
        String cuatrimestreStr = vista.getTxtCuatrimestre();
        String anioStr = vista.getTxtAnio();

        if (nombre.isEmpty() || codigo.isEmpty() || cuatrimestreStr.isEmpty() || anioStr.isEmpty()) {
            vista.setTextoEstado("Error: Faltan completar campos obligatorios.");
            return;
        }

        try {
            int cuatrimestre = Integer.parseInt(cuatrimestreStr);
            int anio = Integer.parseInt(anioStr);

            Materia_1 nuevaMateria = new Materia_1(nombre, codigo, cuatrimestre, anio);
            estudianteActual.inscribirse(nuevaMateria);
            
            inscMateriaDAO.guardarInscripciones(estudianteActual.getMaterias());
            
            refrescarPantallaCompleta();
            vista.limpiarCamposInscripcion();
            vista.setTextoEstado("Inscripción exitosa de: " + nombre);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            vista.setTextoEstado("Error en el formato de números.");
        }
    }

    private void ejecutarRegistrarAsistencia() {
        int filaSeleccionada = vista.getTablaMaterias().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una materia de la tabla para registrar la asistencia.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigoMateria = vista.getTablaMaterias().getValueAt(filaSeleccionada, 1).toString();
        InscripcionMateria inscripcion = estudianteActual.getInscripcion(codigoMateria);

        if (inscripcion != null) {
            boolean presente = vista.getCmbAsistencia().getSelectedItem().toString().equals("Presente");
            inscripcion.registrarAsistencia(presente);
            
            if (inscripcion.getPorcentajeAsistencia() < 75.0) {
                JOptionPane.showMessageDialog(vista, "ALERTA: Asistencia menor al 75% en " + inscripcion.getMateria().getNombre(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            inscMateriaDAO.guardarInscripciones(estudianteActual.getMaterias());
            refrescarPantallaCompleta();
            vista.setTextoEstado("Asistencia registrada.");
        }
    }

    private void ejecutarRegistrarNota() {
        int filaSeleccionada = vista.getTablaMaterias().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una materia para añadir nota.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String notaStr = vista.getTxtNota();
        if (notaStr.isEmpty()) {
            vista.setTextoEstado("Ingrese un valor de nota.");
            return;
        }

        try {
            double valorNota = Double.parseDouble(notaStr);
            if (valorNota < 0 || valorNota > 10) {
                JOptionPane.showMessageDialog(vista, "La nota debe estar entre 0 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String codigoMateria = vista.getTablaMaterias().getValueAt(filaSeleccionada, 1).toString();
            InscripcionMateria inscripcion = estudianteActual.getInscripcion(codigoMateria);

            if (inscripcion != null) {
                if (inscripcion.getNotas().size() >= 5) {
                    JOptionPane.showMessageDialog(vista, "Máximo 5 notas permitidas.", "Límite", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                inscripcion.agregarNota(valorNota);
                inscMateriaDAO.guardarInscripciones(estudianteActual.getMaterias());
                refrescarPantallaCompleta();
                vista.setTextoEstado("Nota agregó correctamente.");
            }
        } catch (NumberFormatException e) {
            vista.setTextoEstado("Formato de nota incorrecto.");
        }
    }

    private void ejecutarBajaMateria() {
        int filaSeleccionada = vista.getTablaMaterias().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una materia para dar de baja.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreMateria = vista.getTablaMaterias().getValueAt(filaSeleccionada, 0).toString();
        String codigoMateria = vista.getTablaMaterias().getValueAt(filaSeleccionada, 1).toString();

        int confirmacion = JOptionPane.showConfirmDialog(vista, 
                "¿Seguro que desea dar de baja: " + nombreMateria + "?", 
                "Confirmar Baja", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            estudianteActual.darDeBaja(codigoMateria);
            inscMateriaDAO.guardarInscripciones(estudianteActual.getMaterias());
            refrescarPantallaCompleta();
            vista.setTextoEstado("Materia dada de baja.");
        }
    }

    private void refrescarPantallaCompleta() {
        vista.setLabelsPerfil(estudianteActual.getNombre(), estudianteActual.getCarrera(), String.valueOf(estudianteActual.getAnioIngreso()));

        String[] columnas = {"Materia", "Código", "Condición", "Asistencia %", "Promedio"};
        DefaultTableModel modelTable = new DefaultTableModel(columnas, 0);

        ArrayList<InscripcionMateria> controlMaterias = estudianteActual.getMaterias();
        for (InscripcionMateria ins : controlMaterias) {
            Object[] fila = {
                ins.getMateria().getNombre(),
                ins.getMateria().getCodigo(),
                ins.getCondicion(),
                String.format("%.1f%%", ins.getPorcentajeAsistencia()),
                String.format("%.2f", ins.getPromedio())
            };
            modelTable.addRow(fila);
        }
        vista.getTablaMaterias().setModel(modelTable);

        DefaultListModel<String> modelLista = new DefaultListModel<>();
        ArrayList<InscripcionMateria> criticas = estudianteActual.getMateriasCriticas();
        for (InscripcionMateria crit : criticas) {
            modelLista.addElement(crit.getMateria().getNombre() + " (" + String.format("%.1f", crit.getPorcentajeAsistencia()) + "%)");
        }
        vista.getLstAlertas().setModel(modelLista);
    }
    private void generarReporteGeneral() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("       REPORTE DE SITUACIÓN GENERAL       \n");
        sb.append("=========================================\n\n");
        sb.append("Estudiante: ").append(estudianteActual.getNombre()).append("\n");
        sb.append("Carrera: ").append(estudianteActual.getCarrera()).append("\n");
        sb.append("Promedio General: ").append(String.format("%.2f", estudianteActual.getPromedioGeneral())).append("\n\n");
        sb.append("Detalle:\n");
        for (InscripcionMateria ins : estudianteActual.getMaterias()) {
            sb.append(" -> ").append(ins.getMateria().getNombre())
              .append(" | Condición: ").append(ins.getCondicion())
              .append(" | Promedio: ").append(String.format("%.2f", ins.getPromedio())).append("\n");
        }
        vista.getTxtReporte().setText(sb.toString());
    }

    private void generarReporteRiesgo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("       MATERIAS EN ALERTA / RIESGO       \n");
        sb.append("=========================================\n\n");
        ArrayList<InscripcionMateria> criticas = estudianteActual.getMateriasCriticas();
        if (criticas.isEmpty()) {
            sb.append("No se registran materias en riesgo (Asistencia entre 75% y 85%).");
        } else {
            for (InscripcionMateria ins : criticas) {
                sb.append("⚠️ ").append(ins.getMateria().getNombre())
                  .append(" - Asistencia: ").append(String.format("%.1f", ins.getPorcentajeAsistencia())).append("%\n");
            }
        }
        vista.getTxtReporte().setText(sb.toString());
    }

    private void generarReporteAprobadas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("           MATERIAS APROBADAS            \n");
        sb.append("=========================================\n\n");
        int contador = 0;
        for (InscripcionMateria ins : estudianteActual.getMaterias()) {
            if (ins.estaAprobada()) {
                sb.append("✅ ").append(ins.getMateria().getNombre())
                  .append(" | Promedio: ").append(String.format("%.2f", ins.getPromedio())).append("\n");
                contador++;
            }
        }
        if (contador == 0) {
            sb.append("No hay materias aprobadas actualmente.");
        }
        vista.getTxtReporte().setText(sb.toString());
    }
}