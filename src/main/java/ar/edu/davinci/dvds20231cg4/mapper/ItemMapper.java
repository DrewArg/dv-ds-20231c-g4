package ar.edu.davinci.dvds20231cg4.mapper;
import java.util.Collection;
import java.util.List;

import ar.edu.davinci.dvds20231cg4.domain.PedidoVentaItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ar.edu.davinci.dvds20231cg4.controller.request.ItemInsertRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.ItemUpdateRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.ItemResponse;
import ar.edu.davinci.dvds20231cg4.controller.web.request.VentaItemCreateRequest;

@Mapper
public interface ItemMapper {
    ItemMapper instance = Mappers.getMapper(ItemMapper.class);
    @Mapping(target = "importe", expression = "java(new java.math.BigDecimal(pedidoVentaItem.importe().doubleValue()))")
    @Mapping(target = "prenda", source = "prenda")
    ItemResponse mapToItemResponse(PedidoVentaItem pedidoVentaItem);
    @Mapping(target = "prenda.id", source = "prendaId")
    PedidoVentaItem matToItem(ItemInsertRequest itemDto);

    PedidoVentaItem matToItem(ItemUpdateRequest itemDto);

    @Mapping(target = "importe", expression = "java(new java.math.BigDecimal(item.importe().doubleValue()))")
    @Mapping(target = "prenda", source = "prenda")
    List<ItemResponse> mapToItemRespons(Collection<PedidoVentaItem> itmens);

    @Mapping(target = "prenda.id", source = "prendaId")
    PedidoVentaItem matToItem(VentaItemCreateRequest ventaItemCreateRequest);
}
