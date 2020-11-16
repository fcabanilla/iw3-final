package iua.edu.ar.model.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iua.edu.ar.model.Orden;
import iua.edu.ar.model.OrdenDetalle;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, Long> {

	
//	public List<Venta> findByProductoListId(Long id);
	public List<OrdenDetalle> findByOrden(Orden orden);
	
	public List<OrdenDetalle> findAllOrdenDetalleByOrden(Orden orden);

	public List<OrdenDetalle> findByOrdenIdOrderByFecha(Long id);
}
