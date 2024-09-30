package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ABMDetenidos {

    @Autowired
    private RepositorioDeDetenidos rd;

    @Autowired
    private RepositorioDeUsuarios ru;

    @GetMapping("/detenidos")
    public String listarDetenidos(Model model) {
        model.addAttribute("detenidos", rd.findAll());  // Pasa la lista de detenidos al modelo
        return "detenidos";  // Retorna el nombre de la plantilla Thymeleaf donde quieres mostrar los detenidos (admin.html)
    }

    @PostMapping("/nuevo-detenido")
    public String nuevaEntidad(final RedirectAttributes redirectAttributes,
                               @RequestParam(name = "codigo") String codigo,
                               @RequestParam(name = "nombre") String nombre,
                               @RequestParam(name = "admin") String admin) {

        Detenido detenido = new Detenido();

        detenido.setCodigo(codigo);
        detenido.setNombre(nombre);


        rd.saveAndFlush(detenido);

        Usuario usuario = ru.findById(admin).orElse(null);  // Obtener el usuario admin
        redirectAttributes.addFlashAttribute("usuario", usuario);


        return "redirect:/login#detenidos";
    }


    @PostMapping("/editar-borrar-detenidos")
    public String editarBorrarEntidad(final RedirectAttributes redirectAttributes,
                                      @RequestParam(name = "id") Long id,
                                      @RequestParam(name = "codigo") String codigo,
                                      @RequestParam(name = "nombre") String nombre,
                                      @RequestParam(name = "accion") String accion,
                                      @RequestParam(name = "admin") String admin) {
        if (accion.equals("Borrar")) {
            rd.deleteById(id);
        } else if (accion.equals("Editar")) {
            Detenido editado = rd.findById(id).orElse(null);
            if (editado != null) {
                editado.setCodigo(codigo);
                editado.setNombre(nombre);
                rd.saveAndFlush(editado);
            }
        }

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#detenidos";
    }


}
