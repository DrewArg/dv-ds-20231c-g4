package ar.edu.davinci.dvds20231cg4.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaInsertRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.PrendaUpdateRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.PrendaResponse;
import ar.edu.davinci.dvds20231cg4.domain.Prenda;
@Mapper
public interface PrendaMapper {
    PrendaMapper instance = Mappers.getMapper(PrendaMapper.class);
    PrendaResponse mapToPrendaResponse(Prenda prenda);
    Prenda matToPrenda(PrendaInsertRequest prendaDto);
    Prenda matToPrenda(PrendaUpdateRequest prendaDto);
}
