package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.Comanda;
import corsi.prog2.ministerio.modelo.Item;
import corsi.prog2.ministerio.modelo.RepositorioDeComandas;
import corsi.prog2.ministerio.modelo.RepositorioDeItems;
import corsi.prog2.ministerio.modelo.RepositorioDeUsuarios;
import corsi.prog2.ministerio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMComandas {

    @Autowired
    private RepositorioDeUsuarios ru;
    @Autowired
    private RepositorioDeComandas rc;
    @Autowired
    private RepositorioDeItems ri;

    @PostMapping("/nueva-comanda")
    public String nueva(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "mesa") String mesa,
            @RequestParam(name = "vigilante") String vigilante) {

        Usuario usuario;
        usuario = ru.findById(vigilante).get();

        Comanda comanda = new Comanda();
        comanda.setVigilante(usuario);
        comanda.setMesa(Integer.parseInt(mesa));

        rc.saveAndFlush(comanda);

        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#order";
    }
    
    @PostMapping("/finalizar-comanda")
    public String finalizar(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "accion") String accion,
            @RequestParam(name = "comanda") String comandaId) {
        Comanda comanda = rc.findById(Long.parseLong(comandaId)).get();
        Usuario usuario = comanda.getVigilante();
        if(accion.equals("Cancelar")) {
            rc.deleteById(comanda.getId());
        } else if(accion.equals("Finalizar")) {
            comanda.setLista(true);
            rc.saveAndFlush(comanda);
        }
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#order";
    }
    
    @PostMapping("/agregar-item-comanda")
    public String agregarItem(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "comanda") String comandaId,
            @RequestParam(name = "item") String itemId
    ) {
        Comanda comanda = rc.findById(Long.parseLong(comandaId)).get();
        Usuario usuario = ru.findById(comanda.getVigilante().getCodigo()).get();
        Item item = ri.findById(Long.parseLong(itemId)).get();
        comanda.getItems().add(item);
        rc.saveAndFlush(comanda);
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#order";
    }
    
    @PostMapping("/quitar-item-comanda")
    public String quitarItem(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "comanda") String comandaId,
            @RequestParam(name = "item") String itemId) {
        Comanda comanda = rc.findById(Long.parseLong(comandaId)).get();
        Item item = ri.findById(Long.parseLong(itemId)).get();
        Usuario usuario = ru.findById(comanda.getVigilante().getCodigo()).get();
        comanda.getItems().remove(item);
        rc.saveAndFlush(comanda);
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#order";
    }
    
}
