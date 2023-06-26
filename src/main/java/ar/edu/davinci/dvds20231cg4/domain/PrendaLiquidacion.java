package ar.edu.davinci.dvds20231cg4.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

// Configuración de Lombok
@Entity
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("LIQUIDACION")
@Table(name = "prendas_liquidacion")
@Data
@NoArgsConstructor
@SuperBuilder
public class PrendaLiquidacion extends Prenda implements EstadoPrendaStrategy, Serializable {
    @Column(name = "porcentahe_descuento")
    private int porcentajeDescuento;


    @Override
    public Double calcularPrecioVenta() {
        return null;
    }
}
