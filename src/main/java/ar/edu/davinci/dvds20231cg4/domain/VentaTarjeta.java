package ar.edu.davinci.dvds20231cg4.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
/**
 * Calcular Venta en Tarjeta
 *
 * @author frmontero
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "vta_id")
@DiscriminatorValue("TARJETA")
@Table(name="ventas_tarjeta")
@Data
@NoArgsConstructor
@SuperBuilder
public class VentaTarjeta extends Venta implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 7549753306871297143L;
    @Column(name = "vtt_cantidad_cuotas")
    private Integer cantidadCuotas;
    @Column(name = "vtt_coeficiente")
    private BigDecimal coeficienteTarjeta;
    public BigDecimal importeRecargado() {
        return super.importeBruto()

                .add(super.importeBruto().multiply(new BigDecimal(0.01)))

                .add(coeficienteTarjeta

                        .multiply(new BigDecimal(cantidadCuotas)))
                .multiply(new

                        BigDecimal(this.getItems().size()));
    }
    @Override
    public Double conRecargo(Double importeBase) {
        return importeBase + (importeBase * 0.01 + coeficienteTarjeta.doubleValue() *

                cantidadCuotas.doubleValue());
    }
}
