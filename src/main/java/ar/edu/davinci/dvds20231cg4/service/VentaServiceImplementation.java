package ar.edu.davinci.dvds20231cg4.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import ar.edu.davinci.dvds20231cg4.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ar.edu.davinci.dvds20231cg4.domain.PedidoVentaItem;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
import ar.edu.davinci.dvds20231cg4.repository.VentaEfectivoRepository;
import ar.edu.davinci.dvds20231cg4.repository.VentaRepository;
import ar.edu.davinci.dvds20231cg4.repository.VentaTarjetaRepository;
@Service
public class VentaServiceImplementation implements VentaService{
    private final Logger LOGGER = LoggerFactory.getLogger(VentaServiceImplementation.class);
    private final VentaRepository ventaRepository;
    private final VentaEfectivoRepository ventaEfectivoRepository;
    private final VentaTarjetaRepository ventaTarjetaRepository;
    private final ClienteService clienteService;
    private final PrendaService prendaService;
    private final ItemService itemService;
    @Autowired
    public VentaServiceImplementation(final VentaRepository ventaRepository,
                            final VentaEfectivoRepository ventaEfectivoRepository,
                            final VentaTarjetaRepository ventaTarjetaRepository,
                            final ClienteService clienteService,
                            final PrendaService prendaService,
                            final ItemService itemService) {
        this.ventaRepository = ventaRepository;
        this.ventaEfectivoRepository = ventaEfectivoRepository;
        this.ventaTarjetaRepository = ventaTarjetaRepository;
        this.clienteService = clienteService;
        this.prendaService = prendaService;
        this.itemService = itemService;
    }
    @Override
    public VentaEfectivo save(VentaEfectivo venta) throws BusinessException {
        Cliente cliente = null;
        if (venta.getCliente().getId() != null) {
            cliente = getCliente(venta.getCliente().getId());
        } else {
            throw new BusinessException("El cliente es obligatorio");
        }
        List<PedidoVentaItem> pedidoVentaItems = new ArrayList<PedidoVentaItem>();
        if (venta.getPedidoVentaItems() != null) {
            pedidoVentaItems = getItems(venta.getPedidoVentaItems());
        }
        venta = VentaEfectivo.builder()
                .cliente(cliente)
                .fecha(Calendar.getInstance().getTime())
                .pedidoVentaItems(pedidoVentaItems)
                .build();

        return ventaEfectivoRepository.save(venta);
    }
    @Override
    public VentaEfectivo save(VentaEfectivo ventaEfectivo, PedidoVentaItem pedidoVentaItem) throws
            BusinessException {

        ventaEfectivo.addItem(pedidoVentaItem);
        return ventaEfectivoRepository.save(ventaEfectivo);
    }
    @Override
    public VentaTarjeta save(VentaTarjeta venta) throws BusinessException {
        Cliente cliente = null;
        if (venta.getCliente().getId() != null) {
            cliente = getCliente(venta.getCliente().getId());
        } else {
            throw new BusinessException("El cliente es obligatorio");
        }
        List<PedidoVentaItem> pedidoVentaItems = new ArrayList<PedidoVentaItem>();
        if (venta.getPedidoVentaItems() != null) {
            pedidoVentaItems = getItems(venta.getPedidoVentaItems());
        }
        venta = VentaTarjeta.builder()
                .cliente(cliente)
                .fecha(Calendar.getInstance().getTime())
                .pedidoVentaItems(pedidoVentaItems)
                .cantidadCuotas(venta.getCantidadCuotas())
                .coeficienteTarjeta(new BigDecimal(0.01D))
                .build();

        return ventaTarjetaRepository.save(venta);
    }
    @Override
    public VentaTarjeta save(VentaTarjeta ventaTarjeta, PedidoVentaItem pedidoVentaItem) throws
            BusinessException {

        ventaTarjeta.addItem(pedidoVentaItem);
        return ventaTarjetaRepository.save(ventaTarjeta);
    }
    @Override
    public void delete(Venta venta) {
        ventaRepository.delete(venta);
    }
    @Override
    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }
    @Override
    public Venta findById(Long id) throws BusinessException {
        LOGGER.debug("Busqueda de una venta por ID");
        Optional<Venta> itemOptional = ventaRepository.findById(id);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        }
        throw new BusinessException("No se encotró la venta por el id: " + id);
    }
    @Override
    public List<Venta> list() {
        LOGGER.debug("Listado de todas las ventas");
        return ventaRepository.findAll();
    }
    @Override
    public Page<Venta> list(Pageable pageable) {
        LOGGER.debug("Listado de todas las ventas por páginas");
        LOGGER.debug("Pageable: offset: " + pageable.getOffset() + ", pageSize: " +

                pageable.getPageSize() + " and pageNumber: " + pageable.getPageNumber());

        return ventaRepository.findAll(pageable);
    }
    @Override
    public long count() {
        return ventaRepository.count();
    }
    @Override
    public Venta addItem(Long ventaId, PedidoVentaItem pedidoVentaItem) throws BusinessException {
        Venta venta = getVenta(ventaId);
        Prenda prenda = getPrenda(pedidoVentaItem);
        PedidoVentaItem newPedidoVentaItem = PedidoVentaItem.builder()

                .cantidad(pedidoVentaItem.getCantidad())
                .prenda(prenda)
                .venta(venta)
                .build();

        newPedidoVentaItem = itemService.save(newPedidoVentaItem);
        venta.addItem(newPedidoVentaItem);
        return venta;
    }
    @Override
    public Venta updateItem(Long ventaId, Long itemId, PedidoVentaItem pedidoVentaItem) throws
            BusinessException {

        Venta venta = getVenta(ventaId);
        PedidoVentaItem actualPedidoVentaItem = getItem(itemId);
        actualPedidoVentaItem.setCantidad(pedidoVentaItem.getCantidad());
        actualPedidoVentaItem = itemService.update(actualPedidoVentaItem);
        return venta;
    }
    @Override
    public Venta deleteItem(Long ventaId, Long itemId) throws BusinessException {
        Venta venta = getVenta(ventaId);
        PedidoVentaItem actualPedidoVentaItem = getItem(itemId);
        itemService.delete(itemId);
        venta.getPedidoVentaItems().remove(actualPedidoVentaItem);
        ventaRepository.save(venta);
        return venta;
    }
    private Venta getVenta(Long ventaId) throws BusinessException {
        Optional<Venta> ventaOptional = ventaRepository.findById(ventaId);
        if (ventaOptional.isPresent()) {
            return ventaOptional.get();
        } else {
            throw new BusinessException("Venta no encotrado para el id: " + ventaId);
        }
    }
    private List<PedidoVentaItem> getItems(List<PedidoVentaItem> requestPedidoVentaItems) throws BusinessException {
        List<PedidoVentaItem> pedidoVentaItems = new ArrayList<PedidoVentaItem>();
        for (PedidoVentaItem requestPedidoVentaItem : requestPedidoVentaItems) {
            Prenda prenda = getPrenda(requestPedidoVentaItem);
            PedidoVentaItem pedidoVentaItem = PedidoVentaItem.builder()

                    .cantidad(requestPedidoVentaItem.getCantidad())
                    .prenda(prenda)
                    .build();
            pedidoVentaItems.add(pedidoVentaItem);
        }
        return pedidoVentaItems;
    }
    private Prenda getPrenda(PedidoVentaItem requestPedidoVentaItem) throws BusinessException {
        if (requestPedidoVentaItem.getPrenda().getId() != null) {
            return prendaService.findById(requestPedidoVentaItem.getPrenda().getId());
        } else {
            throw new BusinessException("La Prenda es obligatoria");
        }
    }
    private PedidoVentaItem getItem(Long id) throws BusinessException {
        return itemService.findById(id);
    }
    private Cliente getCliente(Long id) throws BusinessException {
        return clienteService.findById(id);
    }
}
