package ar.edu.davinci.dvds20231cg4.mapper;

import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertLiquidacionRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertNuevaRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertPromocionRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaLiquidacionResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaNuevaResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaPromocionResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaResponse;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaCreateLiquidacionRequest;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaCreateNuevaRequest;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaCreatePromocionRequest;
import ar.edu.davinci.dvds20231cg4.domain.PrendaLiquidacion;
import ar.edu.davinci.dvds20231cg4.domain.PrendaNueva;
import ar.edu.davinci.dvds20231cg4.domain.PrendaPromocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrendaMapper {
    PrendaMapper instance = Mappers.getMapper(PrendaMapper.class);

    PrendaNuevaResponse mapToPrendaNuevaResponse(PrendaNueva prendaNueva);

    PrendaNueva mapToPrendaNueva(PrendaInsertNuevaRequest prendaInsertNuevaRequest);

    PrendaNueva mapToPrendaNueva(PrendaCreateNuevaRequest prendaCreateNuevaRequest);

    PrendaLiquidacionResponse mapToPrendaLiquidacionResponse(PrendaLiquidacion prendaLiquidacion);

    PrendaLiquidacion mapToPrendaLiquidacion(PrendaInsertLiquidacionRequest prendaInsertLiquidacionRequest);

    PrendaLiquidacion mapToPrendaLiquidacion(PrendaCreateLiquidacionRequest prendaCreateLiquidacionRequest);

    PrendaPromocionResponse mapToPrendaPromocionResponse(PrendaPromocion prendaPromocion);

    PrendaPromocion mapToPrendaPromocion(PrendaInsertPromocionRequest prendaInsertPromocionRequest);

    PrendaPromocion mapToPrendaPromocion(PrendaCreatePromocionRequest prendaCreatePromocionRequest);


}
