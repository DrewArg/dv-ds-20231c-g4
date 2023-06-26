package ar.edu.davinci.dvds20231cg4.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ar.edu.davinci.dvds20231cg4.controller.request.VentaEfectivoRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.VentaTarjetaRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.VentaEfectivoResponse;
import ar.edu.davinci.dvds20231cg4.controller.response.VentaTarjetaResponse;
import ar.edu.davinci.dvds20231cg4.controller.web.request.VentaEfectivoCreateRequest;
import ar.edu.davinci.dvds20231cg4.controller.web.request.VentaTarjetaCreateRequest;
import ar.edu.davinci.dvds20231cg4.domain.VentaEfectivo;
import ar.edu.davinci.dvds20231cg4.domain.VentaTarjeta;
@Mapper(uses=DateMapper.class)
public interface VentaMapper {
    VentaMapper instance = Mappers.getMapper(VentaMapper.class);

 //VENTA EFECTIVO
@Mapping(target = "cliente", source = "cliente")
@Mapping(target = "items", source = "pedidoVentaItems")
@Mapping(target = "importeFinal", expression = "java(new java.math.BigDecimal(ventaEfectivo.importeFinal().doubleValue()))")
    VentaEfectivoResponse mapToVentaEfectivoResponse(VentaEfectivo ventaEfectivo);
    @Mapping(target = "cliente.id", source = "clienteId")
    VentaEfectivo mapToVentaEfectivo(VentaEfectivoRequest ventaEfectivoRequest);
    @Mapping(target = "cliente.id", source = "clienteId")
    VentaEfectivo mapToVentaEfectivo(VentaEfectivoCreateRequest
                                             ventaEfectivoCreateRequest);

// VENTA TARJETA
@Mapping(target = "cliente", source = "cliente")
@Mapping(target = "items", source = "pedidoVentaItems")
//@Mapping(target = "importeFinal", expression = "java(new java.math.BigDecimal(ventaTarjeta.importeFinal().doubleValue()))")
    VentaTarjetaResponse mapToVentaTarjetaResponse(VentaTarjeta ventaTarjeta);
    @Mapping(target = "cliente.id", source = "clienteId")
    VentaTarjeta mapToVentaTarjeta(VentaTarjetaRequest ventaTarjetaRequest);
    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "fecha", source = "fecha")
    VentaTarjeta mapToVentaTarjeta(VentaTarjetaCreateRequest ventaTarjetaCreateRequest);
}
