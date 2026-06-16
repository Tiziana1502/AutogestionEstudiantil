# AutogestionEstudiantil
2 Instancia Evaluativa_InterfazGráfica

Este proyecto es una aplicación de escritorio desarrollada en Java utilizando la biblioteca **Swing** para la interfaz gráfica. Implementa el patrón de diseño **MVC (Modelo-Vista-Controlador)** para asegurar un código limpio, y el patrón **DAO (Data Access Object)** para el manejo de persistencia de datos mediante archivos de texto plano (`.txt`).
El sistema permite la simulación de la autogestión académica de un estudiante, incluyendo la inscripción a materias, el registro de asistencias y notas, y la generación de reportes de estado académico.

# Estructura del Proyecto

El código está estructurado en paquetes para respetar la separación de responsabilidades:
* **Modelo:** Entidades principales del sistema (`Estudiante`, `Materia`, `InscripcionMateria`).
* **Vista:** Interfaz gráfica diseñada con NetBeans (`VistaPrincipal`), utilizando layouts como `CardLayout` y `BorderLayout`.
* **Controlador:** Intermediario que captura los eventos de la vista y aplica la lógica de negocio (`ControladorAutogestion`).
* **DAO:** Clases encargadas exclusivamente de la lectura y escritura en archivos físicos (`EstudianteDAO`, `MateriaDAO`, `InscMateriaDAO`).
  
## Aclaración
Aparece Benjamín Loza como contributor ya que utilizamos el mismo repositorio de la primer instancia evaluativa.

## Integrantes y Roles
* **Lautaro Agorio:** Encargado de la capa de datos base. Desarrolló las clases del Modelo y la persistencia de las entidades simples implementando `EstudianteDAO` y `MateriaDAO`.
* **Benjamín Mendez:** Encargado de la base visual. Realizó el diseño del prototipo inicial en Figma y el maquetado estructural de la interfaz Swing mediante NetBeans.
* **Juan Ignacio Giovannetti:** Encargado del flujo de información. Programó la lógica de la Vista y la implementación del Controlador, enlazando los eventos de la interfaz con los métodos del modelo.
* **Tiziana Rossi:** Encargada de integración, persistencia relacional y UX/UI. Desarrolló el `InscripcionMateriaDAO`, finalizó la interfaz gráfica (aplicación de paleta de colores, componentes adicionales, `CartaReportes` y `PerfilEstudiante`) y desarrolló las funcionalidades correspondientes a los **Bonus** (ordenamiento, estadísticas globales, edición directa en tabla y búsqueda con resaltado).

## Desafíos del Desarrollo

Durante el ciclo de desarrollo, el equipo enfrentó y superó varios desafíos técnicos:
1.  **Trabajo colaborativo con NetBeans y Git:** En las etapas iniciales, coordinar los archivos ocultos `.form` con los `.java` requirió establecer reglas estrictas en el `.gitignore` para evitar perder el diseño visual al hacer `pull`.
2.  **Fusión de Ramas (Merge Conflicts):** La integración final de la capa DAO con los Modelos generó conflictos en GitHub que requirieron ser resueltos manualmente mediante línea de comandos para no perder la lógica de ningún integrante.
3.  **Comunicación estricta en MVC:** Evitar que la Vista modificara directamente los archivos de texto (o viceversa) requirió centralizar todo el tráfico de variables a través del `ControladorAutogestion`, especialmente al implementar los Bonus de edición interactiva (`setValueAt()`) y resaltado de búsquedas.

