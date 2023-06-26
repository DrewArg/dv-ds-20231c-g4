package ar.edu.davinci.dvds20231cg4.domain;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
public class VentaTarjetaTest {
    @Test
    void testBuilder() {
// Given
        Long id = 1L;
        Integer cantidadCuotas = 3;
        BigDecimal coeficienteTarjeta= new BigDecimal(0.5);
// When
        VentaTarjeta venta = VentaTarjeta.builder()

                .id(id)
                .cantidadCuotas(cantidadCuotas)
                .coeficienteTarjeta(coeficienteTarjeta)
                .build();

// Then
        assertNotNull(id);
        assertEquals(id, venta.getId());
        assertEquals(cantidadCuotas, venta.getCantidadCuotas());
        assertEquals(coeficienteTarjeta, venta.getCoeficienteTarjeta());

    }
}
