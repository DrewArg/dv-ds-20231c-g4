package ar.edu.davinci.dvds20231cg4.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrendaPromocionUpdateRequest {

    private String descripcion;
    private String tipo;
    private BigDecimal precioBase;
    private String estado;
    private int valorDescuento;
}
