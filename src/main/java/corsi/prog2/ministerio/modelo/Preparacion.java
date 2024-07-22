package corsi.prog2.ministerio.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Preparacion")
@DiscriminatorValue("Preparacion")
public class Preparacion extends Item {

    public Preparacion() {
    }

    public Preparacion(String descripcion) {
        super(descripcion);
    }

}
