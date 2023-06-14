package ar.edu.davinci.dvds20231cg4.service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ar.edu.davinci.dvds20231cg4.domain.Prenda;
import ar.edu.davinci.dvds20231cg4.domain.TipoPrenda;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.repository.PrendaRepository;
@Service
public class PrendaServiceImpl implements PrendaService{
    private final Logger LOGGER = LoggerFactory.getLogger(PrendaServiceImpl.class);
    private final PrendaRepository repository;
    @Autowired
    public PrendaServiceImpl(final PrendaRepository repository) {
        this.repository = repository;
    }
    @Override
    public Prenda save(final Prenda prenda) throws BusinessException {
        LOGGER.debug("Grabamos la prenda: " + prenda.toString());
        if (prenda.getId() == null) {
            return repository.save(prenda);
        }
        throw new BusinessException("No se puede crear la prenda con un id específico.");
    }
    @Override
    public Prenda update(final Prenda prenda) throws BusinessException {
        LOGGER.debug("Modificamos la prenda: " + prenda.toString());
        if (prenda.getId() != null) {
            return repository.save(prenda);
        }
        throw new BusinessException("No se puede modificar una prenda que aún no fue creada.");
    }
    @Override
    public void delete(final Prenda prenda) {
        LOGGER.debug("Borramos la prenda: " + prenda.toString());
        repository.delete(prenda);
    }
    public void delete(final Long id) {
        LOGGER.debug("Borramos la prenda por id: " + id);
        repository.deleteById(id);
    }
    @Override
    public Prenda findById(final Long id) throws BusinessException {
        LOGGER.debug("Buscamos a la prenda por id: " + id);
        Optional<Prenda> prendaOptional = repository.findById(id);
        if (prendaOptional.isPresent()) {
            return prendaOptional.get();
        }
        throw new BusinessException("No se encontró la prenda con el id: " + id);
    }
    @Override
    public List<Prenda> list() {
        LOGGER.debug("Listado de todas las prendas");
        return repository.findAll();
    }
    @Override
    public Page<Prenda> list(Pageable pageable) {
        LOGGER.debug("Listado de todas las prendas por páginas");
        LOGGER.debug("Pageable: offset: " + pageable.getOffset() + ", pageSize: " +

                pageable.getPageSize() + " and pageNumber: " + pageable.getPageNumber());

        return repository.findAll(pageable);
    }
    @Override
    public long count() {
        return repository.count();
    }
    @Override
    public List<TipoPrenda> getTipoPrendas() {
        return TipoPrenda.getTipoPrendas();
    }
}
