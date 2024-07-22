package corsi.prog2.ministerio.modelo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeComandas extends JpaRepository<Comanda, Long> {

    public List<Comanda> findByVigilante(Usuario vigilante);

    public List<Comanda> findByMesa(Integer mesa);

    public List<Comanda> findByLista(boolean lista);
    
    public List<Comanda> findByVigilanteAndLista(Usuario vigilante, boolean lista);
    
    public Long countByVigilante(Usuario vigilante);

    public Long countByItems(Item item);
    
}
