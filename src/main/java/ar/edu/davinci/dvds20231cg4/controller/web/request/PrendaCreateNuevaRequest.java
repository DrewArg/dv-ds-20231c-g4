package ar.edu.davinci.dvds20231cg4.controller.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrendaCreateNuevaRequest {
    private String descripcion;
    private String tipo;
    private BigDecimal precioBase;
    private String estado;
}
