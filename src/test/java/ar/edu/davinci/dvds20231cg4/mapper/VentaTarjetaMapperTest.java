package ar.edu.davinci.dvds20231cg4.mapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.edu.davinci.dvds20231cg4.controller.request.VentaTarjetaRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.VentaTarjetaResponse;
import ar.edu.davinci.dvds20231cg4.controller.web.request.VentaTarjetaCreateRequest;
import ar.edu.davinci.dvds20231cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20231cg4.domain.Venta;

public class VentaTarjetaMapperTest {
    private final Logger LOGGER = LoggerFactory.getLogger(VentaTarjetaMapperTest.class);
    private final VentaMapper mapper = VentaMapper.instance;
    @Test
    void testMapToVentaTarjetaResponse() {
    	VentaTarjeta venta = VentaTarjeta.builder()

                .id(10L)
                .cantidadCuotas(3)
                .coeficienteTarjeta(new BigDecimal(0.5)) //se supone que debo sacarlo tambien ?  
                .build();

        LOGGER.info("Venta por tarjeta: " + venta.toString());
        VentaTarjetaResponse ventaTarjetaResponse = mapper.mapToVentaTarjetaResponse(venta);
        LOGGER.info("prendaResponse: " + ventaTarjetaResponse.toString());
    }
    @Test
    void testVentaTarjetaInsertRequestMatToVenta() {
        VentaTarjetaCreateRequest ventaInsertRequest = VentaTarjetaCreateRequest.builder()

        		.fecha("20-06-2023") // aca no puse coeficiente de tarjeta porque no esta en ventatarjetacreaterequest
        		.cantidadCuotas(3)
                .build();
        
        LOGGER.info("VentaTarjetaInsertRequest: " + ventaInsertRequest.toString());
        VentaTarjeta venta = mapper.mapToVentaTarjeta(ventaInsertRequest);
        LOGGER.info("Prenda: " + venta.toString());
    }

}


