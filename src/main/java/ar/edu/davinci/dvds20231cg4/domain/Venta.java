package ar.edu.davinci.dvds20231cg4.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
//Configuración inicial de JPA de la entidad prendas
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_venta")
@Table(name="ventas")
//Configuración de lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public abstract class Venta implements Serializable{
    private static final long serialVersionUID = 4377003933780707501L;
// Configuración por JPA de la PK de la tabla
    @Id
// Configuración por JPA de la manera en que se generan los IDs autogenerados en MySQL
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
// Configuración por JPA del nombre de la columna
    @Column(name = "vta_id")
    private Long id;
    @ManyToOne(targetEntity = Cliente.class, cascade = CascadeType.PERSIST, fetch =
            FetchType.EAGER)
    @JoinColumn(name="vta_cli_id", referencedColumnName="cli_id", nullable = false)
    private Cliente cliente;
    @Column(name = "vta_fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(mappedBy="venta", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER,
            orphanRemoval = true)
    @JsonManagedReference
    private List<PedidoVentaItem> pedidoVentaItems;
    
    // Defnición del método del Template Method
    public abstract Double conRecargo(Double importeBase);
    
    public String getRazonSocial() {
        return cliente.getRazonSocial();
    }
    
    public BigDecimal importeBruto() {
        Double suma = pedidoVentaItems.stream()
                .collect(Collectors.summingDouble(item -> item.importe().doubleValue()));

        return new BigDecimal(suma).setScale(2, RoundingMode.UP);
    }
    
    // Implementación del Template Method
    public BigDecimal importeFinal() {
        Double suma = pedidoVentaItems.stream()
                .collect(Collectors.summingDouble(item -> conRecargo(item.importe().doubleValue())));
        return new BigDecimal(suma).setScale(2, RoundingMode.UP);
    }
    
    public String getImporteFinalStr() {
        return importeFinal().toString();
    }
    
    public boolean esDeFecha(Date fecha) {
        return (this.fecha.compareTo(fecha) == 0) ? true : false;
    }
    
    public void addItem(PedidoVentaItem pedidoVentaItem) {
        if (this.pedidoVentaItems == null) {
            this.pedidoVentaItems = new ArrayList<PedidoVentaItem>();
        }    
    this.pedidoVentaItems.add(pedidoVentaItem);
    }
    
}
