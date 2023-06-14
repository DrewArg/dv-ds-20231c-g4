package ar.edu.davinci.dvds20231cg4.service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ar.edu.davinci.dvds20231cg4.domain.Cliente;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.repository.ClienteRepository;
@Service
public class ClienteServiceImplementation implements ClienteService{
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImplementation.class);
    private final ClienteRepository repository;
    @Autowired
    public ClienteServiceImplementation(final ClienteRepository repository) {
        this.repository = repository;
    }
    @Override
    public Cliente save(final Cliente cliente) throws BusinessException {
        LOGGER.debug("Grabamos la cliente: " + cliente.toString());
        if (cliente.getId() == null) {
            return repository.save(cliente);
        }
        throw new BusinessException("No se puede crear la cliente con un id específico.");
    }
    @Override
    public Cliente update(final Cliente cliente) throws BusinessException {
        LOGGER.debug("Modificamos la cliente: " + cliente.toString());
        if (cliente.getId() != null) {
            return repository.save(cliente);
        }
        throw new BusinessException("No se puede modificar una cliente que aún no fue creada.");
    }
    @Override
    public void delete(final Cliente cliente) {
        LOGGER.debug("Borramos la cliente: " + cliente.toString());
        repository.delete(cliente);
    }
    public void delete(final Long id) {
        LOGGER.debug("Borramos la cliente por id: " + id);
        repository.deleteById(id);
    }
    @Override
    public Cliente findById(final Long id) throws BusinessException {
        LOGGER.debug("Buscamos a la cliente por id: " + id);
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        }
        throw new BusinessException("No se encontró la cliente con el id: " + id);
    }
    @Override
    public List<Cliente> list() {
        LOGGER.debug("Listado de todas las clientes");
        return repository.findAll();
    }
    @Override
    public Page<Cliente> list(Pageable pageable) {
        LOGGER.debug("Listado de todas las clientes por páginas");
        LOGGER.debug("Pageable: offset: " + pageable.getOffset() + ", pageSize: " +

                pageable.getPageSize() + " and pageNumber: " + pageable.getPageNumber());
        return repository.findAll(pageable);
    }
    @Override
    public long count() {
        return repository.count();
    }
}
