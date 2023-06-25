package ar.edu.davinci.dvds20231cg4.domain;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="prendas")
// Configuración de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PrendaNueva extends Prenda implements EstadoPrendaStrategy {


    @Override
    public Double calcularPrecioVenta() {
        return null;
    }
}
