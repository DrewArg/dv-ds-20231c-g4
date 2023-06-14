package ar.edu.davinci.dvds20231cg4.domain;
import java.io.Serializable;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable{
    private static final long serialVersionUID = 6363777413501451503L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "cli_id")
    private Long id;
    @Column(name = "cli_nombre")
    private String nombre;
    @Column(name = "cli_apellido")
    private String apellido;
    public String getRazonSocial() {
        return nombre + " " + apellido;
    }
}
