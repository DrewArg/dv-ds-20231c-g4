package ar.edu.davinci.dvds20231cg4.service;
import java.util.List;

import ar.edu.davinci.dvds20231cg4.domain.PedidoVentaItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ar.edu.davinci.dvds20231cg4.exceptions.BusinessException;
public interface ItemService {
    PedidoVentaItem save(PedidoVentaItem pedidoVentaItem) throws BusinessException;
    PedidoVentaItem update(PedidoVentaItem pedidoVentaItem) throws BusinessException;
    public void delete(PedidoVentaItem pedidoVentaItem);
    public void delete(Long id);
    public PedidoVentaItem findById(Long id) throws BusinessException;
    public List<PedidoVentaItem> listAll();
    public Page<PedidoVentaItem> list(Pageable pageable);
    public long count();
}