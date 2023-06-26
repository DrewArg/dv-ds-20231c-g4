package ar.edu.davinci.dvds20231cg4.service;

import ar.edu.davinci.dvds20231cg4.domain.*;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PrendaService {

    PrendaNueva save(PrendaNueva prendaNueva) throws BusinessException;

    PrendaPromocion save(PrendaPromocion prendaPromocion) throws BusinessException;

    PrendaLiquidacion save(PrendaLiquidacion prendaLiquidacion) throws BusinessException;

    Prenda update(final Prenda prenda) throws BusinessException;

    void delete(final Long id);

    Prenda findById(Long id) throws BusinessException;

    List<Prenda> list();

    Page<Prenda> list(Pageable pageable);

    long count();

    List<TipoPrenda> getTipoPrendas();

    List<EstadoPrenda> getEstadoPrendas();
}
