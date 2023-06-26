package ar.edu.davinci.dvds20231cg4.repository;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ar.edu.davinci.dvds20231cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20231cg4.domain.Venta;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VentaTarjetaRepositoryTest {
    private final Logger LOGGER = LoggerFactory.getLogger(VentaTarjetaRepositoryTest.class);
    @Autowired
    private VentaTarjetaRepository ventaTarjetaRepository;
    @Test
    void testFindAll() {
        assertNotNull(ventaTarjetaRepository, "El repositorio es nulo.");
        List<VentaTarjeta> ventas = ventaTarjetaRepository.findAll();
        LOGGER.info("Ventas con tarjeta encontradas: " + ventas.size());
        assertNotNull(ventas, "ventas con tarjeta es nulo");
        assertTrue(ventas.size() > 0, "No existen ventas con tarjeta.");
    }
    @Test
    void testFindById() {
        Long id = 4L;
        VentaTarjeta venta = null;
        Optional<VentaTarjeta> ventaOptional = ventaTarjetaRepository.findById(id);
        if (ventaOptional.isPresent()) {
            venta = ventaOptional.get();
            LOGGER.info("Venta encontrada para el id: " + venta.getId());
            LOGGER.info("Cantidad de cuotas: " + venta.getCantidadCuotas());
            LOGGER.info("Fecha: " + venta.getFecha());

            } else {
            LOGGER.info("Venta con tarjeta no encontrada para el id: " + id);
            venta = ventaOptional.get();
            assertNull(venta);
        }

    }

}
