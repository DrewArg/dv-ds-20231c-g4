package ar.edu.davinci.dvds20231cg4.controller.response;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponse {
    private Long id;
    private ClienteResponse cliente;
    private String fecha;
    private List<ItemResponse> items;
    private BigDecimal importeFinal;
}
