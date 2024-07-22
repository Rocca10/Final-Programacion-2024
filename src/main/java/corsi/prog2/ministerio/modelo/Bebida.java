package corsi.prog2.ministerio.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Bebida")
@DiscriminatorValue("Bebida")
public class Bebida extends Item {

    public Bebida() {
    }

    public Bebida(String nombre, Double precio) {
        super(nombre);
        super.setPrecio(precio);
    }

    public String getNombre() {
        return getDescripcion();
    }

    public void setNombre(String nombre) {
        setDescripcion(nombre);
    }

}
