
package autogestionestudiantil;

/**
 *
 * @author lauta
 */
public class Materia_1 implements Consultable {
    
   
    private String nombre;
    private String codigo;
    private int cuatrimestre;
    private int anio;
 
    private static java.util.Set<String> codigosUtilizados = new java.util.HashSet<>();

   
   public Materia_1(String nombre, String codigo, int cuatrimestre, int anio) {
        this.nombre = nombre;
        this.anio = anio;
        
        if (codigosUtilizados.contains(codigo)) {
            throw new IllegalArgumentException("El código ya existe.");
        }
        this.codigo = codigo;
        codigosUtilizados.add(codigo);
        if (codigo == null || codigo.trim().length() < 3 || codigo.trim().length() > 10) 
        {
            throw new IllegalArgumentException("El codigo debe tener entre 3 y 10 caracteres.");
        }
        
        if (cuatrimestre != 1 && cuatrimestre != 2) {
            throw new IllegalArgumentException("El cuatrimestre debe ser 1 o 2.");
        }
        this.cuatrimestre = cuatrimestre;
    }
 
 
    public void setCodigo(String codigo) {
        if (codigosUtilizados.contains(codigo)) {
            throw new IllegalArgumentException("El código ya existe.");
        }
        this.codigo = codigo;
        codigosUtilizados.add(codigo);
    }

    public void setCuatrimestre(int cuatrimestre) {
        if (cuatrimestre != 1 && cuatrimestre != 2) {
            throw new IllegalArgumentException("El cuatrimestre debe ser 1 o 2.");
        }
        this.cuatrimestre = cuatrimestre;
    }
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre;
    }
    
    public void setAnio(int anio) { this.anio = anio; }  
    
    public boolean coincideCodigo(String codigoBuscado) {
        return this.codigo.equalsIgnoreCase(codigoBuscado.trim());
    }
  
    public boolean coincideNombre(String textoBuscado) {
        return this.nombre.toLowerCase().contains(textoBuscado.trim().toLowerCase());
    }
    
    //Getters
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public int getCuatrimestre() { return cuatrimestre; }
    public int getAnio() { return anio; }
    
    @Override
    public void mostrarResumen() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Código: " + codigo);
        System.out.println("Cuatrimestre: " + cuatrimestre);
        System.out.println("Año: " + anio);
    }
}
