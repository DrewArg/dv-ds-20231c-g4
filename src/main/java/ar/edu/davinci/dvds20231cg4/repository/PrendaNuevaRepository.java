package ar.edu.davinci.dvds20231cg4.repository;

import ar.edu.davinci.dvds20231cg4.domain.PrendaNueva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaNuevaRepository extends JpaRepository<PrendaNueva, Long> {
}
