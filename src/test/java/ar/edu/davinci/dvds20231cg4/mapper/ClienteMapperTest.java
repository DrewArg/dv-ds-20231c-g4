package ar.edu.davinci.dvds20231cg4.mapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.edu.davinci.dvds20231cg4.controller.request.ClienteInsertRequest;
import ar.edu.davinci.dvds20231cg4.controller.request.ClienteUpdateRequest;
import ar.edu.davinci.dvds20231cg4.controller.response.ClienteResponse;
import ar.edu.davinci.dvds20231cg4.domain.Cliente;


public class ClienteMapperTest {
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteMapperTest.class);
    private final ClienteMapper mapper = ClienteMapper.instance;
    @Test
    void testMapToClienteResponse() {
    	Cliente cliente = Cliente.builder()

                .id(10L)
                .nombre("Gasti")
                .apellido("Sampa")
                .build();

        LOGGER.info("Cliente: " + cliente.toString());
        ClienteResponse clienteResponse = mapper.mapToClienteResponse(cliente);
        LOGGER.info("clienteResponse: " + clienteResponse.toString());
    }
    @Test
    void testClienteInsertRequestMatToCliente() {
        ClienteInsertRequest clienteInsertRequest = ClienteInsertRequest.builder()

                .nombre("Gasti")
                .apellido("Sampa")
                .build();

        LOGGER.info("clienteInsertRequest: " + clienteInsertRequest.toString());
        Cliente cliente = mapper.matToCliente(clienteInsertRequest);
        LOGGER.info("Cliente: " + cliente.toString());
    }
    @Test
    void testClienteUpdateRequestMatToCliente() {
        ClienteUpdateRequest clienteUpdateRequest = ClienteUpdateRequest.builder()

        		.nombre("Gasti")
                .apellido("Sampa")
                .build();

        LOGGER.info("clienteUpdateRequest: " + clienteUpdateRequest.toString());
        Cliente cliente = mapper.matToCliente(clienteUpdateRequest);
        LOGGER.info("Cliente: " + cliente.toString());
    }
}

