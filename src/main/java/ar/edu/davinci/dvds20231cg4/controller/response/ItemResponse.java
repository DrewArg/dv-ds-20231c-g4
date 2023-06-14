package ar.edu.davinci.dvds20231cg4.controller.response;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {
    private Long id;
    private Integer cantidad;
    private PrendaResponse prenda;
    private BigDecimal importe;
}
