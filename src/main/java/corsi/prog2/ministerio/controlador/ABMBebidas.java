package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.Bebida;
import corsi.prog2.ministerio.modelo.Item;
import corsi.prog2.ministerio.modelo.RepositorioDeItems;
import corsi.prog2.ministerio.modelo.RepositorioDeUsuarios;
import corsi.prog2.ministerio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMBebidas {

    @Autowired
    private RepositorioDeItems ri;
    @Autowired
    private RepositorioDeUsuarios ru;

    @PostMapping("/nueva-bebida")
    public String nuevaBebida(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "descripcion") String nombre,
            @RequestParam(name = "precio") String precio,
            @RequestParam(name = "admin") String admin) {

        Bebida nueva = new Bebida(nombre, null);

        if (!precio.isEmpty()) {
            nueva.setPrecio(Double.parseDouble(precio));
        }
        
        ri.saveAndFlush(nueva);
        
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#drink";
    }
    
    @PostMapping("/editar-borrar-bebida")
    public String editarBorrarBebida(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "descripcion") String nombre,
            @RequestParam(name = "precio") String precio,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion) {
        if(accion.equals("Borrar")) {
            ri.deleteById(Long.parseLong(id));
        } else if(accion.equals("Editar")) {
            Item editado = ri.findById(Long.parseLong(id)).get();
            editado.setDescripcion(nombre);
            editado.setPrecio(precio.isEmpty() ? null : Double.parseDouble(precio));
            ri.saveAndFlush(editado);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#drink";
    }
}
