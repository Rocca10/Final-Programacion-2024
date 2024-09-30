package corsi.prog2.ministerio.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "fecha_de_contratacion", nullable = false) // Asegúrate de que el nombre coincida con la base de datos
    private LocalDate fechaDeContratacion;

    @Column(name = "tiene_arma", nullable = false) // Asegúrate de que el nombre coincida con la base de datos
    private Boolean tieneArma;

    // Constructor vacío (requerido por JPA)
    public Contrato() {}

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFechaDeContratacion() {
        return fechaDeContratacion;
    }

    public void setFechaDeContratacion(LocalDate fechaDeContratacion) {
        this.fechaDeContratacion = fechaDeContratacion;
    }

    public Boolean getTieneArma() {
        return tieneArma;
    }

    public void setTieneArma(Boolean tieneArma) {
        this.tieneArma = tieneArma;
    }
}
