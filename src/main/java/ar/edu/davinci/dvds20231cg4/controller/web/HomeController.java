package ar.edu.davinci.dvds20231cg4.controller.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ar.edu.davinci.dvds20231cg4.controller.TiendaApp;

@Controller
public class HomeController extends TiendaApp{
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    @GetMapping()
    public String viewHomePage(Model model) {
        LOGGER.info("GET - viewHomePage - /index");
        return "index";
    }
}
