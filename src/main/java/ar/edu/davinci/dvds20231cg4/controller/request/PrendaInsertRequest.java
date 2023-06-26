package ar.edu.davinci.dvds20231cg4.controller.request;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrendaInsertRequest {
    private String descripcion;
    private String tipo;
    private BigDecimal precioBase;
    private String estado;
}
