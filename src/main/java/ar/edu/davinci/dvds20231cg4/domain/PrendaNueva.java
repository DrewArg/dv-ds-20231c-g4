package ar.edu.davinci.dvds20231cg4.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("NUEVA")
@Table(name = "prendas_nueva")
@NoArgsConstructor
@SuperBuilder
public class PrendaNueva extends Prenda implements EstadoPrendaStrategy, Serializable {


    @Override
    public Double calcularPrecioVenta() {
        return null;
    }
}
