package ar.edu.davinci.dvds20231cg4.service;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ar.edu.davinci.dvds20231cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20231cg4.domain.Venta;
import ar.edu.davinci.dvds20231cg4.repository.VentaTarjetaRepository;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VentaTarjetaServiceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(VentaTarjetaServiceTest.class);
    @Autowired
    private VentaService tarjetaService;
    @Test
    void testList() {
        assertNotNull(tarjetaService, "El servico es nulo.");
        List<Venta> ventas = tarjetaService.list();
        LOGGER.info("Ventas con tarjeta encontradas: " + ventas.size());
        assertNotNull(ventas, "ventas con tarjeta es nulo");
        assertTrue(ventas.size() > 0, "No existen ventas con tarjeta.");
    }

}
