package corsi.prog2.ministerio.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Investigador")
@DiscriminatorValue("Investigador")
public class Investigador extends Usuario {

    public Investigador() {
    }

    public Investigador(String codigo, String nombre, String password) {
        super(codigo, nombre, password);
    }
    
}
