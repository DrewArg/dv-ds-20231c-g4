package ar.edu.davinci.dvds20231cg4.service;
import java.util.List;
import java.util.Optional;

import ar.edu.davinci.dvds20231cg4.domain.PedidoVentaItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.repository.ItemRepository;
@Service
public class ItemServiceImplementation implements ItemService {
    private final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImplementation.class);
    private final ItemRepository repository;
    @Autowired
    public ItemServiceImplementation (final ItemRepository repository) {
        this.repository = repository;
    }
    @Override
    public PedidoVentaItem save(PedidoVentaItem pedidoVentaItem) throws BusinessException {
        LOGGER.debug("Grabamos la pedidoVentaItem con el id: " + pedidoVentaItem.getId());
        if (pedidoVentaItem.getId() == null) {
            return repository.save(pedidoVentaItem);
        }
        throw new BusinessException("No se puede crear una pedidoVentaItem con un id específico");
    }
    @Override
    public PedidoVentaItem update(PedidoVentaItem pedidoVentaItem) throws BusinessException {
        LOGGER.debug("Modificamos la pedidoVentaItem con el id: " + pedidoVentaItem.getId());
        if (pedidoVentaItem.getId() != null) {
            return repository.save(pedidoVentaItem);
        }
        throw new BusinessException("No se puede modificar una pedidoVentaItem no creada");
    }
    @Override
    public void delete(PedidoVentaItem pedidoVentaItem) {
        LOGGER.debug("Borrando la pedidoVentaItem con el id: " + pedidoVentaItem.getId());
        repository.delete(pedidoVentaItem);
    }
    @Override
    public void delete(Long id) {
        LOGGER.debug("Borrando la item con el id: " + id);
        repository.deleteById(id);
    }
    @Override
    public PedidoVentaItem findById(Long id) throws BusinessException {
        LOGGER.debug("Busqueda de una item por ID");
        Optional<PedidoVentaItem> itemOptional = repository.findById(id);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        }
        throw new BusinessException("No se encotró la item por el id: " + id);
    }
    @Override
    public List<PedidoVentaItem> listAll() {
        LOGGER.debug("Listado de todas las pedidoVentaItems");
        return repository.findAll();
    }
    @Override
    public Page<PedidoVentaItem> list(Pageable pageable) {
        LOGGER.debug("Listado de todas las pedidoVentaItems paginadas");
        LOGGER.debug("Pageable, offset:" + pageable.getOffset() + ", pageSize: " +

                pageable.getPageSize()+ ", pageNumber: " + pageable.getPageNumber());

        return repository.findAll(pageable);
    }
    @Override
    public long count() {
        return repository.count();
    }
}
