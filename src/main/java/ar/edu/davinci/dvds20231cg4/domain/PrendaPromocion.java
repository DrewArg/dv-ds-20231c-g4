package ar.edu.davinci.dvds20231cg4.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("PROMOCION")
@Table(name = "prendas_promocion")
@Data
@NoArgsConstructor
@SuperBuilder
public class PrendaPromocion extends Prenda implements EstadoPrendaStrategy, Serializable {
	@Column(name = "valor_descuento")
	private int valorDescuento;


	@Override
	public Double calcularPrecioVenta() {
		return null;
	}
}
