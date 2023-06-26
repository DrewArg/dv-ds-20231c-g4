package ar.edu.davinci.dvds20231cg4.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrendaLiquidacionResponse extends PrendaResponse {
    private int porcentajeDescuento;
}
