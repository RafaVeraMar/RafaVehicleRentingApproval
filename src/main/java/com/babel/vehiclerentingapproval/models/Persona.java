package com.babel.vehiclerentingapproval.models;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author enrique.munoz@babelgroup.com
 */
public class Persona {
    /**
     * Identificador de persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    private int personaId;
    /**
     * Parámetro de nombre de persona
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String nombre;
    /**
     * Parámetro de primer apellido de persona
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String apellido1;
    /**
     * Parámetro de segundo apellido de persona
     */
    @Size(max = 50)
    private String apellido2;
    /**
     * Parámetro de tipo Dirección que representa la dirección de domicilio de una persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    private Direccion direccionDomicilio;
    /**
     * Parámetro de tipo Dirección que representa la dirección de notificación de una persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    private Direccion direccionNotificacion;
    /**
     * Parámetro para controlar si la dirección de notificación es la misma que la de domicilio
     */
    private boolean direccionDomicilioSameAsNotificacion = true;
    /**
     * Parámetro referido al nif de una persona
     */
    @Size(max=10)
    @NotNull
    @NotEmpty
    @NotBlank
    private String nif;
    /**
     * Parámetro referido a la fecha de nacimiento de una persona
     */
    @PastOrPresent
    private Date fechaNacimiento;
    /**
     * Parámetro referido a la nacionalidad de una persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    private Pais nacionalidad;
    /**
     * Parámetro referido a la puntuación de una persona
     */
    private int scoring;
    /**
     * Parámetro referido a la fecha en la que se puntúa a una persona
     */
    @PastOrPresent
    private Date fechaScoring;
    /**
     * Parámetro de lista de tipo TelefonoContacto que se refiere a los telefonos de una persona
     */
    private List<TelefonoContacto> telefonos;
    /**
     * Parámetro de lista de tipo ProductoContratado que se refiere a los productos contratados de una persona
     */
    private List<ProductoContratado> productosContratados;
    /**
     * Parámetro referido al email de una persona
     */
    private String email;


    /**
     * Metodo público que se encarga de mostrar el valor de la id de la persona
     * @return devuelve el id de la persona
     */
    public int getPersonaId() {
        return personaId;
    }
    /**
     * Metodo publico que se encarga de darle un valor al id de la persona
     * @param personaId que es el id de la persona
     */
    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }
    /**
     * Metodo público que se encarga de mostrar el nombre de la persona
     * @return devuelve el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo publico que se encarga de darle un valor al nombre de la persona
     * @param nombre que se le quiere poner a la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo público que se encarga de mostrar el primer apellido de la persona
     * @return devuelve el primer apellido de la persona
     */
    public String getApellido1() {
        return apellido1;
    }
    /**
     * Metodo publico que se encarga de darle un valor al primer apellido de la persona
     * @param apellido1 que se le quiere poner a la persona
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    /**
     * Metodo público que se encarga de mostrar el segundo apellido de la persona
     * @return devuelve el segundo apellido de la persona
     */
    public String getApellido2() {
        return apellido2;
    }
    /**
     * Metodo publico que se encarga de darle un valor al segundo apellido de la persona
     * @param apellido2 que se le quiere poner a la persona
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    /**
     * Metodo público que se encarga de mostrar la dirección de domicilio de la persona
     * @return devuelve la dirección de domicilio de la persona
     */
    public Direccion getDireccionDomicilio() {
        return direccionDomicilio;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la dirección de domicilio de la persona
     * @param direccionDomicilio que se le quiere poner a la persona
     */
    public void setDireccionDomicilio(Direccion direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }
    /**
     * Metodo público que se encarga de mostrar la dirección de notificación de la persona
     * @return devuelve la dirección de notificación de la persona
     */
    public Direccion getDireccionNotificacion() {
        return direccionNotificacion;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la dirección de notificación de la persona
     * @param direccionNotificacion que se le quiere poner a la persona
     */
    public void setDireccionNotificacion(Direccion direccionNotificacion) {
        this.direccionNotificacion = direccionNotificacion;
    }
    /**
     * Metodo público que se encarga de mostrar si la dirección de domicilio es igual a la de notificación de la persona
     * @return devuelve si la dirección de domicilio es igual a la de notificación de la persona
     */
    public boolean isDireccionDomicilioSameAsNotificacion() {
        return direccionDomicilioSameAsNotificacion;
    }
    /**
     * Metodo publico que se encarga de darle un valor a si la dirección de domicilio es igual a la de notificación de la persona
     * @param direccionDomicilioSameAsNotificacion para añadirle la misma dirección de notificación que la de dirección de la persona
     */
    public void setDireccionDomicilioSameAsNotificacion(boolean direccionDomicilioSameAsNotificacion) {
        this.direccionDomicilioSameAsNotificacion = direccionDomicilioSameAsNotificacion;
    }
    /**
     * Metodo público que se encarga de mostrar el nif de la persona
     * @return devuelve el nif de la persona
     */
    public String getNif() {
        return nif;
    }
    /**
     * Metodo publico que se encarga de darle un valor al nif de la persona
     * @param nif que se le quiere poner a la persona
     */
    public void setNif(String nif) {
        this.nif = nif;
    }
    /**
     * Metodo público que se encarga de mostrar la fecha de nacimiento de la persona
     * @return devuelve la fecha de nacimiento de la persona
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la fecha de nacimiento de la persona
     * @param fechaNacimiento que se le quiere poner a la persona
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * Metodo público que se encarga de mostrar la nacionalidad de la persona
     * @return devuelve la nacionalidad de la persona
     */
    public Pais getNacionalidad() {
        return nacionalidad;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la nacionalidad de la persona
     * @param nacionalidad que se le quiere poner a la persona
     */
    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    /**
     * Metodo público que se encarga de mostrar la puntuación de la persona
     * @return devuelve la puntuación de la persona
     */
    public int getScoring() {
        return scoring;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la puntuación de la persona
     * @param scoring que se le quiere poner a la persona
     */
    public void setScoring(int scoring) {
        this.scoring = scoring;
    }
    /**
     * Metodo público que se encarga de mostrar la fecha de puntuación de la persona
     * @return devuelve la fecha de puntuación de la persona
     */
    public Date getFechaScoring() {
        return fechaScoring;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la fecha de puntuación de la persona
     * @param fechaScoring que se le quiere poner a la persona
     */
    public void setFechaScoring(Date fechaScoring) {
        this.fechaScoring = fechaScoring;
    }
    /**
     * Metodo público que se encarga de mostrar la lista de telefonos asociados a la persona
     * @return devuelve la lista de telefonos asociados a la persona
     */
    public List<TelefonoContacto> getTelefonos() {
        return telefonos;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la lista de telefonos asociados a la persona
     * @param telefonos que se le quiere asignar a la persona
     */
    public void setTelefonos(List<TelefonoContacto> telefonos) {
        this.telefonos = telefonos;
    }
    /**
     * Metodo público que se encarga de mostrar la lista de productos contratados por la persona
     * @return devuelve la lista de telefonos productos contratados por la persona
     */
    public List<ProductoContratado> getProductosContratados() {
        return productosContratados;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la lista de productos contratados por la persona
     * @param productosContratados que se le quiere asignar a la persona
     */
    public void setProductosContratados(List<ProductoContratado> productosContratados) {
        this.productosContratados = productosContratados;

    }
    /**
     * Metodo público que se encarga de mostrar el email de la persona
     * @return devuelve el email de la persona
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metodo publico que se encarga de darle un valor al email de la persona
     * @param email que se le quiere asignar a la persona
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
   /* @Override
    public String toString() {
        return "Persona{" +
                "personaId=" + getPersonaId() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido1='" + getApellido1() + '\'' +
                ", apellido2='" + getApellido2() + '\'' +
                ", direccionDomicilioId=" + getDireccionDomicilio().getDireccionId() +
                ", direccionNotificacionId=" + getDireccionNotificacion().getDireccionId() +
                ", direccionDomicilioSameAsNotificacion=" + isDireccionDomicilioSameAsNotificacion() +
                ", nif='" + getNif() + '\'' +
                ", fechaNacimiento=" + getFechaNacimiento().toString() +
                ", nacionalidadIsoAlfa2=" + getNacionalidad().getIsoAlfa2() +
                ", scoring=" + getScoring() +
                ", fechaScoring=" + getFechaScoring().toString() +
                ", telefonos=" + getTelefonos().toString() +
                ", productosContratados=" + getProductosContratados().toString() +
                ", email='" + getEmail() + '\'' +
                '}';
    }

    */
}


