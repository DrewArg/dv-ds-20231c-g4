package ar.edu.davinci.dvds20231cg4.domain;
import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="venta_items")
//Configuración de lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class PedidoVentaItem implements Serializable{
    private static final long serialVersionUID = 5324396181568770929L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "itm_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="itm_vta_id", referencedColumnName="vta_id", nullable = false)
    @JsonBackReference
    private Venta venta;
    @Column(name = "itm_cantidad")
    private Integer cantidad;
    @ManyToOne(targetEntity = Prenda.class, cascade = CascadeType.PERSIST, fetch =
            FetchType.EAGER)
    @JoinColumn(name="itm_prd_id", referencedColumnName="prd_id", nullable = false)
    private Prenda prenda;
    public BigDecimal importe() {
        return prenda.getPrecioFinal().multiply(new BigDecimal(cantidad));
    }
}
