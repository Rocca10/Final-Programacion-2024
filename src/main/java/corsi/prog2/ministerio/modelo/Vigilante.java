package corsi.prog2.ministerio.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Vigilante")
@DiscriminatorValue("Vigilante")
public class Vigilante extends Usuario {

    public Vigilante() {
    }

    public Vigilante(String codigo, String nombre, String password) {
        super(codigo, nombre, password);
    }
    
}
