package ar.edu.davinci.dvds20231cg4.controller.response;
import java.math.BigDecimal;

import ar.edu.davinci.dvds20231cg4.domain.EstadoPrenda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrendaResponse {
    private Long id;
    private String descripcion;
    private String tipo;
    private BigDecimal precioBase;

}
