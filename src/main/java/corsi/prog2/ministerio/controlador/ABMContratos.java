package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.Contrato;
import corsi.prog2.ministerio.modelo.RepositorioDeContratos;
import corsi.prog2.ministerio.modelo.RepositorioDeUsuarios;
import corsi.prog2.ministerio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class ABMContratos {

    @Autowired
    private RepositorioDeContratos rc;

    @Autowired
    private RepositorioDeUsuarios ru;

    @GetMapping("/contratos")
    public String listarContratos(Model model) {
        model.addAttribute("contratos", rc.findAll());
        return "contratos";
    }

    @PostMapping("/nuevo-contrato")
    public String nuevoContrato(final RedirectAttributes redirectAttributes,
                                @RequestParam(name = "codigo") String codigo,
                                @RequestParam(name = "edad") Integer edad,
                                @RequestParam(name = "fechaDeContratacion") String fechaDeContratacion, // Se espera un String y se convierte a LocalDate
                                @RequestParam(name = "tieneArma") Boolean tieneArma,
                                @RequestParam(name = "admin") String admin) {

        Contrato contrato = new Contrato();
        contrato.setCodigo(codigo);
        contrato.setEdad(edad);
        contrato.setFechaDeContratacion(LocalDate.parse(fechaDeContratacion)); // Convierte la fecha de String a LocalDate
        contrato.setTieneArma(tieneArma);

        rc.saveAndFlush(contrato);

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#contratos";
    }

    @PostMapping("/editar-borrar-contrato")
    public String editarBorrarContrato(final RedirectAttributes redirectAttributes,
                                       @RequestParam(name = "id") Long id,
                                       @RequestParam(name = "codigo") String codigo,
                                       @RequestParam(name = "edad") Integer edad,
                                       @RequestParam(name = "fechaDeContratacion") String fechaDeContratacion,
                                       @RequestParam(name = "tieneArma") Boolean tieneArma,
                                       @RequestParam(name = "accion") String accion,
                                       @RequestParam(name = "admin") String admin) {
        if (accion.equals("Borrar")) {
            rc.deleteById(id);
        } else if (accion.equals("Editar")) {
            Contrato editado = rc.findById(id).orElse(null);
            if (editado != null) {
                editado.setCodigo(codigo);
                editado.setEdad(edad);
                editado.setFechaDeContratacion(LocalDate.parse(fechaDeContratacion));
                editado.setTieneArma(tieneArma);
                rc.saveAndFlush(editado);
            }
        }

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#contratos";
    }
}
