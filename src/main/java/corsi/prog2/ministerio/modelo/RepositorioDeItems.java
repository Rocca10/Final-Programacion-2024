package corsi.prog2.ministerio.modelo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeItems extends JpaRepository<Item, Long> {

    public List<Item> findByPrecioNotNull();

    public List<Item> findByTipo(String tipo);
}
