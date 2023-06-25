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
import ar.edu.davinci.dvds20231cg4.domain.Cliente;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClienteRepositoryTest {
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteRepositoryTest.class);
    @Autowired
    private ClienteRepository clienteRepository;
    @Test
    void testFindAll() {
        assertNotNull(clienteRepository, "El repositorio es nulo.");
        List<Cliente> clientes = clienteRepository.findAll();
        LOGGER.info("Clientes encontrados: " + clientes.size());
        assertNotNull(clientes, "clientes es nulo");
        assertTrue(clientes.size() > 0, "No existen clientes.");
    }
    @Test
    void testFindById() {
        Long id = 4L;
        Cliente cliente = null;
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            cliente = clienteOptional.get();
            LOGGER.info("Cliente encontrado para el id: " + cliente.getId());
           

        } else {
            LOGGER.info("Cliente no encontrado para el id: " + id);
            cliente = clienteOptional.get();
            assertNull(cliente);
        }

    }

}
