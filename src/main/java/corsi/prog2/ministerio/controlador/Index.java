package corsi.prog2.ministerio.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

    @GetMapping("/")
    public String loginForm() {
        return "index";
    }
}
