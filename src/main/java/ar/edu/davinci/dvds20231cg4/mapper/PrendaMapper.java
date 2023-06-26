package ar.edu.davinci.dvds20231cg4.mapper;

import ar.edu.davinci.dvds20231cg4.controller.request.*;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaLiquidacionResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaNuevaResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaPromocionResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaResponse;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaCreateLiquidacionRequest;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaCreateNuevaRequest;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaCreatePromocionRequest;
import ar.edu.davinci.dvds20231cg4.domain.Prenda;
import ar.edu.davinci.dvds20231cg4.domain.PrendaLiquidacion;
import ar.edu.davinci.dvds20231cg4.domain.PrendaNueva;
import ar.edu.davinci.dvds20231cg4.domain.PrendaPromocion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrendaMapper {
    PrendaMapper instance = Mappers.getMapper(PrendaMapper.class);

    PrendaResponse mapToPrendaResponse(Prenda prenda);

    //    Prenda mapToPrenda(PrendaInsertRequest prendaDto);
//    Prenda mapToPrenda(PrendaUpdateRequest prendaDto);
    PrendaNuevaResponse mapToPrendaNuevaResponse(PrendaNueva prendaNueva);

    PrendaNueva mapToPrendaNueva(PrendaInsertNuevaRequest prendaInsertNuevaRequest);

    PrendaNueva mapToPrendaNueva(PrendaCreateNuevaRequest prendaCreateNuevaRequest);

    PrendaNueva mapToPrendaNueva(PrendaNuevaUpdateRequest prendaNuevaUpdateRequest);

    PrendaLiquidacionResponse mapToPrendaLiquidacionResponse(PrendaLiquidacion prendaLiquidacion);

    PrendaLiquidacion mapToPrendaLiquidacion(PrendaInsertLiquidacionRequest prendaInsertLiquidacionRequest);

    PrendaLiquidacion mapToPrendaLiquidacion(PrendaCreateLiquidacionRequest prendaCreateLiquidacionRequest);

    PrendaLiquidacion mapToPrendaLiquidacion(PrendaLiquidacionUpdateRequest prendaLiquidacionUpdateRequest);

    PrendaPromocionResponse mapToPrendaPromocionResponse(PrendaPromocion prendaPromocion);

    PrendaPromocion mapToPrendaPromocion(PrendaInsertPromocionRequest prendaInsertPromocionRequest);

    PrendaPromocion mapToPrendaPromocion(PrendaCreatePromocionRequest prendaCreatePromocionRequest);

    PrendaPromocion mapToPrendaPromocion(PrendaPromocionUpdateRequest prendaPromocionUpdateRequest);


}
