package ar.edu.davinci.dvds20231cg4.domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class ClienteTest {
    @Test
    void testBuilder() {
// Given
        Long id = 1L;
        String nombre = "Lu";
        String apellido = "Bel";
// When
        Cliente cliente = Cliente.builder()

                .id(id)
                .nombre(nombre)
                .apellido(apellido)
                .build();

// Then
        assertNotNull(nombre);
        assertEquals(id, cliente.getId());
        assertEquals(nombre, cliente.getNombre());
        assertEquals(apellido, cliente.getApellido());

    }
}
