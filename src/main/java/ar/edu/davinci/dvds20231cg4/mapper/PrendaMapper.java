package ar.edu.davinci.dvds20231cg4.mapper;

import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertNuevaRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaNuevaResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaResponse;
import ar.edu.davinci.dvds20231cg4.controller.web.request.PrendaNuevaCreateRequest;
import ar.edu.davinci.dvds20231cg4.domain.PrendaNueva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrendaMapper {
    PrendaMapper instance = Mappers.getMapper(PrendaMapper.class);

    //    PrendaResponse mapToPrendaResponse(Prenda prenda);
//    Prenda mapToPrenda(PrendaInsertRequest prendaDto);
//    Prenda mapToPrenda(PrendaUpdateRequest prendaDto);
//    @Mapping(target = "id", source = "id")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "precioBase", source = "precioBase")
//    @Mapping(target = "estado", source = "estadoPrenda")
    PrendaResponse mapToPrendaNuevaResponse(PrendaNueva prendaNueva);

//    @Mapping(target = "estadoPrenda", source = "estado")
    PrendaNueva mapToPrendaNueva(PrendaInsertNuevaRequest prendaInsertNuevaRequest);

//    @Mapping(target = "estadoPrenda", source = "estado")
    PrendaNueva mapToPrendaNueva(PrendaNuevaCreateRequest prendaNuevaCreateRequest);
}
