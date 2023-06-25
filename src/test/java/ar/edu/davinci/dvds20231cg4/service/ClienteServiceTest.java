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
import ar.edu.davinci.dvds20231cg4.domain.Cliente;
import ar.edu.davinci.dvds20231cg4.repository.ClienteRepository;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceTest.class);
    @Autowired
    private ClienteService clienteService;
    @Test
    void testList() {
        assertNotNull(clienteService, "El servico es nulo.");
        List<Cliente> clientes = clienteService.list();
        LOGGER.info("Clientes encontrados: " + clientes.size());
        assertNotNull(clientes, "clientes es nulo");
        assertTrue(clientes.size() > 0, "No existen clientes.");
    }
    
}
