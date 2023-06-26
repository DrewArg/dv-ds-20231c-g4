package ar.edu.davinci.dvds20231cg4.controller.rest;

import ar.edu.davinci.dvds20231cg4.controller.TiendaAppRest;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertLiquidacionRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertNuevaRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertPromocionRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaLiquidacionResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaNuevaResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaPromocionResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrendaControllerRest extends TiendaAppRest {
    private final Logger LOGGER = LoggerFactory.getLogger(PrendaControllerRest.class);
    @Autowired
    private PrendaService prendaService;
    private final PrendaMapper prendaMapper = PrendaMapper.instance;

    /**
     * Listar Prendas
     */
    @GetMapping(path = "/prendas/all")
    public List<Prenda> getList() {
        LOGGER.info("Listado de todas las prendas");
        return prendaService.list();
    }

    @GetMapping(path = "/prendas")
    public ResponseEntity<Page<PrendaResponse>> getList(Pageable pageable) {
        LOGGER.info("Listar todas las prendas paginadas");
        LOGGER.info("Pageable: " + pageable);

        Page<PrendaResponse> prendaResponse = null;
        Page<Prenda> prendas = null;
        try {
            prendas = prendaService.list(pageable);
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        }
// Mapear Prendas a PrendaRespose
// No lo vamos a hacer
// Podría haber una clase por entidad que haga este pasaje de Objeto
// de Modelo a Objeto DTO
//for (Prenda prenda : prendas) {
// PrendaResponse response = PrendaResponse.builder()
// .id(prenda.getId())
// .descripcion(prenda.getDescripcion())
// .tipo(prenda.getTipo().toString())
// .precioBase(prenda.getPrecioBase())
// .build();
//}
// Mapear Prendas a PrendaResponse con Orika
        try {
//prendaResponse = prendas.map(prenda -> mapper.map(prenda,PrendaResponse.class));

            LOGGER.info(">>>>> prendas >>>> " + prendas.toString());
            showPrendas(prendas);
            //TODO uncomment next line and fix it to make it work
//            prendaResponse = prendas.map(prendaMapper::mapToPrendaResponse);
            showPrendaResonse(prendaResponse);
            LOGGER.info(">>>>> prendaResponse >>>> " + prendaResponse.toString());
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(prendaResponse, HttpStatus.OK);
    }

    private void showPrendaResonse(Page<PrendaResponse> prendaResponse) {
        System.out.println(prendaResponse.getContent().toString());
    }

    private void showPrendas(Page<Prenda> prendas) {
        System.out.println(prendas.getContent().toString());
    }

    /**
     * Buscar prenda por id
     *
     * @param id identificador del prenda
     * @return retorna el prenda
     */
    @GetMapping(path = "/prendas/{id}")
    public ResponseEntity<Object> getPrenda(@PathVariable Long id) {
        LOGGER.info("lista al prenda solicitado");
        PrendaResponse prendaResponse = null;
        Prenda prenda = null;
        try {
            prenda = prendaService.findById(id);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        try {
//prendaResponse = mapper.map(prenda, PrendaResponse.class);
            //TODO uncomment next line and fix it to make it work

//            prendaResponse = prendaMapper.mapToPrendaResponse(prenda);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(prendaResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/prendas/liquidacion")
    public ResponseEntity<PrendaLiquidacionResponse> createPrenda(@RequestBody PrendaInsertLiquidacionRequest datosPrenda) {
        PrendaLiquidacionResponse prendaLiquidacionResponse = null;
        PrendaLiquidacion prendaLiquidacion = prendaMapper.mapToPrendaLiquidacion(datosPrenda);
        return grabarPrenda(prendaLiquidacion, prendaLiquidacionResponse);
    }

    private ResponseEntity<PrendaLiquidacionResponse> grabarPrenda(PrendaLiquidacion prendaLiquidacion, PrendaLiquidacionResponse prendaLiquidacionResponse) {
        try {
            prendaLiquidacion = prendaService.save(prendaLiquidacion);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        try {
            prendaLiquidacionResponse = prendaMapper.mapToPrendaLiquidacionResponse(prendaLiquidacion);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(prendaLiquidacionResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = "/prendas/nueva")
    public ResponseEntity<PrendaNuevaResponse> createPrenda(@RequestBody PrendaInsertNuevaRequest datosPrenda) {

        PrendaNuevaResponse prendaNuevaResponse = null;
        PrendaNueva prendaNueva = prendaMapper.mapToPrendaNueva(datosPrenda);
        return grabarPrenda(prendaNueva, prendaNuevaResponse);
    }

    private ResponseEntity<PrendaNuevaResponse> grabarPrenda(PrendaNueva prendaNueva, PrendaNuevaResponse prendaNuevaResponse) {
        try {
            prendaNueva = prendaService.save(prendaNueva);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        try {
            prendaNuevaResponse = prendaMapper.mapToPrendaNuevaResponse(prendaNueva);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(prendaNuevaResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = "/prendas/promocion")
    public ResponseEntity<PrendaPromocionResponse> createPrenda(@RequestBody PrendaInsertPromocionRequest datosPrenda) {

        PrendaPromocionResponse prendaPromocionResponse = null;
        PrendaPromocion prendaPromocion = prendaMapper.mapToPrendaPromocion(datosPrenda);
        return grabarPrenda(prendaPromocion, prendaPromocionResponse);
    }

    private ResponseEntity<PrendaPromocionResponse> grabarPrenda(PrendaPromocion prendaPromocion, PrendaPromocionResponse prendaPromocionResponse) {
        try {
            prendaPromocion = prendaService.save(prendaPromocion);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        try {
            prendaPromocionResponse = prendaMapper.mapToPrendaPromocionResponse(prendaPromocion);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(prendaPromocionResponse, HttpStatus.CREATED);
    }

//    @PostMapping(path = "/prendas/new")
//    public ResponseEntity<PrendaResponse> createPrenda(@RequestBody PrendaInsertRequest
//                                                               datosPrenda) {
//
//        Prenda prenda = null;
//        PrendaResponse prendaResponse = null;
//// Convertir PrendaInsertRequest en Prenda
//        try {
////prenda = mapper.map(datosPrenda, Prenda.class);
//            prenda = prendaMapper.mapToPrenda(datosPrenda);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//// Grabar el nuevo Prenda
//        try {
//            prenda = prendaService.save(prenda);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//        }
//// Convertir Prenda en PrendaResponse
//        try {
////prendaResponse = mapper.map(prenda, PrendaResponse.class);
//            prendaResponse = prendaMapper.mapToPrendaResponse(prenda);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(prendaResponse, HttpStatus.CREATED);
//    }

    /**
     * Modificar los datos de un prenda
     *
     * @param id identificador de una prenda
     * @param datosPrenda datos a modificar de la prenda
     * @return los datos de una prenda modificada
     */
//    @PutMapping("/prendas/{id}")
//    public ResponseEntity<Object> updatePrenda(@PathVariable("id") long id,
//
//                                               @RequestBody PrendaUpdateRequest datosPrenda) {
//        Prenda prendaModifar = null;
//        Prenda prendaNuevo = null;
//        PrendaResponse prendaResponse = null;
//// Convertir PrendaInsertRequest en Prenda
//        try {
//            //prendaNuevo = mapper.map(datosPrenda, Prenda.class);
//            prendaNuevo = prendaMapper.mapToPrenda(datosPrenda);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//        try {
//            prendaModifar = prendaService.findById(id);
//        } catch (BusinessException e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//        if (Objects.nonNull(prendaModifar)) {
//            prendaModifar.setDescripcion(prendaNuevo.getDescripcion());
//            prendaModifar.setTipo(prendaNuevo.getTipo());
//            prendaModifar.setPrecioBase(prendaNuevo.getPrecioBase());
//// Grabar el Prenda Nuevo en Prenda a Modificar
//            try {
//                prendaModifar = prendaService.update(prendaModifar);
//            } catch (BusinessException e) {
//                LOGGER.error(e.getMessage());
//                e.printStackTrace();
//                return new ResponseEntity<>(e.getMessage(),
//
//                        HttpStatus.EXPECTATION_FAILED);
//
//            } catch (Exception e) {
//                LOGGER.error(e.getMessage());
//                e.printStackTrace();
//                return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//            }
//        } else {
//            LOGGER.error("Prenda a modificar es null");
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//// Convertir Prenda en PrendaResponse
//        try {
////prendaResponse = mapper.map(prendaModifar, PrendaResponse.class);
//            prendaResponse = prendaMapper.mapToPrendaResponse(prendaModifar);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(prendaResponse, HttpStatus.CREATED);
//    }

    /**
     * Borrado de la prenda
     *
     * @param id identificador de una prenda
     * @return
     */
    @DeleteMapping("/prendas/{id}")
    public ResponseEntity<HttpStatus> deletePrenda(@PathVariable("id") Long id) {
        try {
            prendaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
