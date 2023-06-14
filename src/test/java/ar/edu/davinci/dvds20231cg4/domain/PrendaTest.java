package ar.edu.davinci.dvds20231cg4.domain;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
public class PrendaTest {
    @Test
    void testBuilder() {
// Given
        Long id = 1L;
        String descripcion = "Pantalon";
        TipoPrenda tipoPrenda = TipoPrenda.PANTALON;
        BigDecimal precio = new BigDecimal(100);
// When
        Prenda prenda = Prenda.builder()

                .id(id)
                .descripcion(descripcion)
                .tipo(tipoPrenda)
                .precioBase(precio )
                .build();

// Then
        assertNotNull(prenda);
        assertEquals(id, prenda.getId());
        assertEquals(descripcion, prenda.getDescripcion());
        assertEquals(tipoPrenda, prenda.getTipo());
        assertEquals(precio, prenda.getPrecioBase());

    }
}
