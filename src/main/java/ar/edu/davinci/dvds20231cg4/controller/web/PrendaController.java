package ar.edu.davinci.dvds20231cg4.controller.web;

import ar.edu.davinci.dvds20231cg4.controller.TiendaApp;
import ar.edu.davinci.dvds20231cg4.domain.Prenda;
import ar.edu.davinci.dvds20231cg4.domain.PrendaLiquidacion;
import ar.edu.davinci.dvds20231cg4.domain.PrendaNueva;
import ar.edu.davinci.dvds20231cg4.domain.PrendaPromocion;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.mapper.PrendaMapper;
import ar.edu.davinci.dvds20231cg4.service.PrendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class PrendaController extends TiendaApp {
    private final Logger LOGGER = LoggerFactory.getLogger(PrendaController.class);

    private final PrendaMapper prendaMapper = PrendaMapper.instance;
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


    @RequestMapping(value = "/prendas/edit/LIQUIDACION/{id}", method = RequestMethod.POST)
    public String showEditPrendaPage(@ModelAttribute("prenda") PrendaLiquidacion prendaLiquidacion, @PathVariable(name = "id") Long prendaId) {
        LOGGER.info("GET - showEditPrendaPage - /prendas/edit/{id}");
        LOGGER.info("prendaId: " + prendaId);
        PrendaLiquidacion updatePrenda = null;
        try {

            updatePrenda = (PrendaLiquidacion) prendaService.findById(prendaId);
            if (Objects.nonNull(updatePrenda)) {
                updatePrenda.setDescripcion(prendaLiquidacion.getDescripcion());
                updatePrenda.setTipo(prendaLiquidacion.getTipo());
                updatePrenda.setPrecioBase(prendaLiquidacion.getPrecioBase());
                updatePrenda.setPorcentajeDescuento(prendaLiquidacion.getPorcentajeDescuento());

            }
        } catch (Exception e) {
            LOGGER.error("ERROR AL TRAER LA PRENDA");
            e.printStackTrace();
        }
        try {
            prendaService.update(updatePrenda);
        } catch (Exception ex) {
            LOGGER.error("ERROR al actualizar la prenda");
            ex.printStackTrace();
        }

        return "redirect:/tienda/prendas/list";
    }

    @RequestMapping(value = "/prendas/edit/PROMOCION/{id}", method = RequestMethod.POST)
    public String showEditPrendaPage(@ModelAttribute("prenda") PrendaPromocion prendaPromocion, @PathVariable(name = "id") Long prendaId) {
        LOGGER.info("GET - showEditPrendaPage - /prendas/edit/{id}");
        LOGGER.info("prendaId: " + prendaId);
        PrendaPromocion updatePrenda = null;
        try {

            updatePrenda = (PrendaPromocion) prendaService.findById(prendaId);
            if (Objects.nonNull(updatePrenda)) {
                updatePrenda.setDescripcion(prendaPromocion.getDescripcion());
                updatePrenda.setTipo(prendaPromocion.getTipo());
                updatePrenda.setPrecioBase(prendaPromocion.getPrecioBase());
                updatePrenda.setValorDescuento(prendaPromocion.getValorDescuento());
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AL TRAER LA PRENDA");
            e.printStackTrace();
        }
        try {
            prendaService.update(updatePrenda);
        } catch (Exception ex) {
            LOGGER.error("ERROR al actualizar la prenda");
            ex.printStackTrace();
        }

        return "redirect:/tienda/prendas/list";
    }
    @RequestMapping(value = "/prendas/edit/NUEVA/{id}", method = RequestMethod.POST)
    public String showEditPrendaPage(@ModelAttribute("prenda") PrendaNueva prendaNueva, @PathVariable(name = "id") Long prendaId) {
        LOGGER.info("GET - showEditPrendaPage - /prendas/edit/{id}");
        LOGGER.info("prendaId: " + prendaId);
        PrendaNueva updatePrenda = null;
        try {

            updatePrenda = (PrendaNueva) prendaService.findById(prendaId);
            if (Objects.nonNull(updatePrenda)) {
                updatePrenda.setDescripcion(prendaNueva.getDescripcion());
                updatePrenda.setTipo(prendaNueva.getTipo());
                updatePrenda.setPrecioBase(prendaNueva.getPrecioBase());
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AL TRAER LA PRENDA");
            e.printStackTrace();
        }
        try {
            prendaService.update(updatePrenda);
        } catch (Exception ex) {
            LOGGER.error("ERROR al actualizar la prenda");
            ex.printStackTrace();
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
