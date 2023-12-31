package ar.edu.davinci.dvds20231cg4.service;

import ar.edu.davinci.dvds20231cg4.domain.*;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.repository.PrendaLiquidacionRepository;
import ar.edu.davinci.dvds20231cg4.repository.PrendaNuevaRepository;
import ar.edu.davinci.dvds20231cg4.repository.PrendaPromocionRepository;
import ar.edu.davinci.dvds20231cg4.repository.PrendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaServiceImplementation implements PrendaService {
    private final Logger LOGGER = LoggerFactory.getLogger(PrendaServiceImplementation.class);
    private final PrendaRepository prendaRepository;
    private final PrendaNuevaRepository prendaNuevaRepository;

    private final PrendaLiquidacionRepository prendaLiquidacionRepository;

    private final PrendaPromocionRepository prendaPromocionRepository;

    @Autowired
    public PrendaServiceImplementation(final PrendaRepository prendaRepository,
                                       final PrendaNuevaRepository prendaNuevaRepository,
                                       final PrendaPromocionRepository prendaPromocionRepository,
                                       final PrendaLiquidacionRepository prendaLiquidacionRepository) {
        this.prendaRepository = prendaRepository;
        this.prendaNuevaRepository = prendaNuevaRepository;
        this.prendaLiquidacionRepository = prendaLiquidacionRepository;
        this.prendaPromocionRepository = prendaPromocionRepository;
    }

    /**
     * @param prendaNueva
     * @return
     * @throws BusinessException
     */
    @Override
    public PrendaNueva save(PrendaNueva prendaNueva) throws BusinessException {
        LOGGER.debug("guardando la prenda: " + prendaNueva.toString());
        if (prendaNueva.getId() == null) {
            prendaNueva = PrendaNueva.builder()
                    .descripcion(prendaNueva.getDescripcion())
                    .tipo(prendaNueva.getTipo())
                    .precioBase(prendaNueva.getPrecioBase())
                    .build();
            return prendaNuevaRepository.save(prendaNueva);
        }
        throw new BusinessException("no se puede guardar una prenda con un ID específico");

    }

    /**
     * @param prendaPromocion
     * @return
     * @throws BusinessException
     */
    @Override
    public PrendaPromocion save(PrendaPromocion prendaPromocion) throws BusinessException {
        LOGGER.debug("guardando la prenda: " + prendaPromocion.toString());
        if (prendaPromocion.getId() == null) {
            prendaPromocion = PrendaPromocion.builder()
                    .descripcion(prendaPromocion.getDescripcion())
                    .tipo(prendaPromocion.getTipo())
                    .precioBase(prendaPromocion.getPrecioBase())
                    .valorDescuento(prendaPromocion.getValorDescuento())
                    .build();
            return prendaPromocionRepository.save(prendaPromocion);
        }
        throw new BusinessException("no se puede guardar una prenda con un ID específico");
    }

    /**
     * @param prendaLiquidacion
     * @return
     * @throws BusinessException
     */
    @Override
    public PrendaLiquidacion save(PrendaLiquidacion prendaLiquidacion) throws BusinessException {
        LOGGER.debug("guardando la prenda: " + prendaLiquidacion.toString());
        if (prendaLiquidacion.getId() == null) {
            prendaLiquidacion = PrendaLiquidacion.builder()
                    .descripcion(prendaLiquidacion.getDescripcion())
                    .tipo(prendaLiquidacion.getTipo())
                    .precioBase(prendaLiquidacion.getPrecioBase())
                    .porcentajeDescuento(prendaLiquidacion.getPorcentajeDescuento())
                    .build();
            return prendaLiquidacionRepository.save(prendaLiquidacion);
        }
        throw new BusinessException("no se puede guardar una prenda con un ID específico");
    }


    public Prenda update(final Prenda prenda) throws BusinessException {
        LOGGER.debug("Modificamos la prenda: " + prenda.toString());
        if (prenda.getId() != null) {
            return prendaRepository.save(prenda);
        }
        throw new BusinessException("No se puede modificar una prenda que aún no fue creada.");
    }


    @Override
    public void delete(final Long id) {
        Optional<Prenda> prenda = prendaRepository.findById(id);

        if (prenda.isPresent()) {
            Prenda pren = prenda.get();
            LOGGER.debug("Borramos la prenda: " + pren.toString());
            prendaRepository.delete(pren);
            }
    }

    @Override
    public Prenda findById(final Long id) throws BusinessException {
        LOGGER.debug("Buscamos a la prenda por id: " + id);
        Optional<Prenda> prendaOptional = prendaRepository.findById(id);
        if (prendaOptional.isPresent()) {
            return prendaOptional.get();
        }
        throw new BusinessException("No se encontró la prenda con el id: " + id);
    }

    @Override
    public List<Prenda> list() {
        LOGGER.debug("Listado de todas las prendas");
        return prendaRepository.findAll();
    }

    @Override
    public Page<Prenda> list(Pageable pageable) {
        LOGGER.debug("Listado de todas las prendas por páginas");
        LOGGER.debug("Pageable: offset: " + pageable.getOffset() + ", pageSize: " +

                pageable.getPageSize() + " and pageNumber: " + pageable.getPageNumber());

        return prendaRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return prendaRepository.count();
    }

    @Override
    public List<TipoPrenda> getTipoPrendas() {
        return TipoPrenda.getTipoPrendas();
    }

    @Override
    public List<EstadoPrenda> getEstadoPrendas() {
        return EstadoPrenda.getEstadoPrendas();
    }

}
