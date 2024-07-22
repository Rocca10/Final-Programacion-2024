package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.Item;
import corsi.prog2.ministerio.modelo.Preparacion;
import corsi.prog2.ministerio.modelo.RepositorioDeItems;
import corsi.prog2.ministerio.modelo.RepositorioDeUsuarios;
import corsi.prog2.ministerio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMPreparaciones {

    @Autowired
    private RepositorioDeItems ri;
    @Autowired
    private RepositorioDeUsuarios ru;

    @PostMapping("/nueva-preparacion")
    public String nuevaPreparacion(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "usuario") String usuarioId) {

        Preparacion nueva = new Preparacion(descripcion);
        ri.saveAndFlush(nueva);

        Usuario usuario;
        usuario = ru.findById(usuarioId).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#food";
    }

    @PostMapping("/editar-borrar-preparacion")
    public String editarBorrarPreparacion(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "usuario") String usuarioId,
            @RequestParam(name = "accion") String accion) {

        if (accion.equals("Borrar")) {
            ri.deleteById(Long.parseLong(id));
        } else if (accion.equals("Editar")) {
            Item editado = ri.findById(Long.parseLong(id)).get();
            editado.setDescripcion(descripcion);
            ri.saveAndFlush(editado);
        }

        Usuario usuario;
        usuario = ru.findById(usuarioId).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#food";
    }

    @PostMapping("/cotizar-preparacion")
    public String cotizarPreparacion(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "precio") String precio,
            @RequestParam(name = "admin") String admin) {

        Item editado = ri.findById(Long.parseLong(id)).get();
        editado.setPrecio(precio.isEmpty() ? null : Double.parseDouble(precio));
        ri.saveAndFlush(editado);

        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#food";
    }
}
