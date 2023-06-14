package ar.edu.davinci.dvds20231cg4.controller.web.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaTarjetaCreateRequest {
    private Long clienteId;
    private String fecha;
    private Integer cantidadCuotas;
}
