package ar.edu.davinci.dvds20231cg4.controller.web;
import ar.edu.davinci.dvds20231cg4.domain.PrendaLiquidacion;
import ar.edu.davinci.dvds20231cg4.domain.PrendaNueva;
import ar.edu.davinci.dvds20231cg4.domain.PrendaPromocion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.davinci.dvds20231cg4.controller.TiendaApp;
import ar.edu.davinci.dvds20231cg4.domain.Prenda;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.service.PrendaService;
@Controller
public class PrendaController extends TiendaApp{
    private final Logger LOGGER = LoggerFactory.getLogger(PrendaController.class);
    @Autowired
    private PrendaService prendaService;
    @GetMapping(path = "/prendas/list")
    public String showPrendaPage(Model model) {
        LOGGER.info("GET - showPrendaPage - /prendas/list");
        Pageable pageable = PageRequest.of(0, 20);
        Page<Prenda> prendas = prendaService.list(pageable);
        model.addAttribute("listPrendas", prendas);
        model.addAttribute("pageNumber", prendas.getPageable().getPageNumber());
        model.addAttribute("totalPages", prendas.getTotalPages());
        LOGGER.info("prendas.size: " + prendas.getNumberOfElements());
        return "prendas/list_prendas";
    }
    @GetMapping(path = "/prendas/new")
    public String showNewPrendaPage(Model model) {
        LOGGER.info("GET - showNewPrendaPage - /prendas/new");
        PrendaNueva prendaNueva = new PrendaNueva();
        model.addAttribute("prenda", prendaNueva);
        model.addAttribute("tipoPrendas", prendaService.getTipoPrendas());
        model.addAttribute("tipoEstados", prendaService.getEstadoPrendas());
        LOGGER.info("prendas: " + prendaNueva.toString());
        return "prendas/save_prendas";
    }
    @PostMapping(value = "/prendas/new/NUEVA")
    public String savePrenda(@ModelAttribute("prenda") PrendaNueva prendaNueva) {
        LOGGER.info("POST - savePrenda - /prendas/new");
        LOGGER.info("prenda: " + prendaNueva.toString());
        try {
            if (prendaNueva.getId() == null) {
                prendaService.save(prendaNueva);
            } else {
                prendaService.update(prendaNueva);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "redirect:/tienda/prendas/list";
    }
    @PostMapping(value = "/prendas/new/PROMOCION")
    public String savePrenda(@ModelAttribute("prenda") PrendaPromocion prendaPromocion) {
        LOGGER.info("POST - savePrenda - /prendas/new");
        LOGGER.info("prenda: " + prendaPromocion.toString());
        try {
            if (prendaPromocion.getId() == null) {
                prendaService.save(prendaPromocion);
            } else {
                prendaService.update(prendaPromocion);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "redirect:/tienda/prendas/list";
    }

    @PostMapping(value = "/prendas/new/LIQUIDACION")
    public String savePrenda(@ModelAttribute("prenda") PrendaLiquidacion prendaLiquidacion) {
        LOGGER.info("POST - savePrenda - /prendas/new");
        LOGGER.info("prenda: " + prendaLiquidacion.toString());
        try {
            if (prendaLiquidacion.getId() == null) {
                prendaService.save(prendaLiquidacion);
            } else {
                prendaService.update(prendaLiquidacion);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "redirect:/tienda/prendas/list";
    }
    @RequestMapping(value = "/prendas/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditPrendaPage(@PathVariable(name = "id") Long prendaId) {
        LOGGER.info("GET - showEditPrendaPage - /prendas/edit/{id}");
        LOGGER.info("prenda: " + prendaId);
        ModelAndView mav = new ModelAndView("prendas/edit_prendas");
        Prenda prenda = null;
        try {
            prenda = prendaService.findById(prendaId);
            mav.addObject("prenda", prenda);
            mav.addObject("tipoPrendaActual", prenda.getTipo());

        } catch (BusinessException e) {
            LOGGER.error("ERROR AL TRAER LA PRENDA");
            e.printStackTrace();
        }
        mav.addObject("tipoPrendas", prendaService.getTipoPrendas());
        mav.addObject("tipoEstados", prendaService.getEstadoPrendas());
        return mav;
    }
    @RequestMapping(value = "/prendas/delete/{id}", method = RequestMethod.GET)
    public String deletePrenda(@PathVariable(name = "id") Long prendaId) {
        LOGGER.info("GET - deletePrenda - /prendas/delete/{id}");
        LOGGER.info("prenda: " + prendaId);
        prendaService.delete(prendaId);
        return "redirect:/tienda/prendas/list";
    }
}
