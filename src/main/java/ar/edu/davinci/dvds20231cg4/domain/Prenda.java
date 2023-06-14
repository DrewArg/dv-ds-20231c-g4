package ar.edu.davinci.dvds20231cg4.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Configuración inical de JPA de una entidad
@Entity
@Table(name="prendas")
// Configuración de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Prenda implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9201477148327989795L;
    // Configurar por JPA cual el PK de la tabla prendas
    @Id
// Configurar la estragia de generación de los ids por JPA
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
// Configuramos por JPA el nombre de la columna
    @Column(name = "prd_id")
    private Long id;
    @Column(name = "prd_descripcion", nullable = false)
    private String descripcion;
   @Column(name = "prd_tipo_prenda")
   @Enumerated(EnumType.STRING)
    private TipoPrenda tipo;
  @Column(name = "prd_precio_base")
  private BigDecimal precioBase;
   public BigDecimal getPrecioFinal() {
       return precioBase;
   }
}
