package ar.edu.davinci.dvds20231cg4.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.davinci.dvds20231cg4.domain.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
