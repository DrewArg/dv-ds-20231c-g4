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
import ar.edu.davinci.dvds20231cg4.domain.Prenda;
import ar.edu.davinci.dvds20231cg4.domain.TipoPrenda;
import ar.edu.davinci.dvds20231cg4.repository.PrendaRepository;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PrendaServiceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(PrendaServiceTest.class);
    @Autowired
    private PrendaService prendaService;
    @Test
    void testList() {
        assertNotNull(prendaService, "El servico es nulo.");
        List<Prenda> prendas = prendaService.list();
        LOGGER.info("Prendas encontradas: " + prendas.size());
        assertNotNull(prendas, "prendas es nulo");
        assertTrue(prendas.size() > 0, "No existen prendas.");
    }
    @Test
    void testTipoPrenda() {
        List<TipoPrenda> list = prendaService.getTipoPrendas();
        assertNotNull(list, "tipo prendas es nulo");
        assertTrue(list.size() == 8, "La cantidad de tipos es distinta");

    }
}
