package iua.edu.ar.model.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iua.edu.ar.model.Orden;
import iua.edu.ar.model.PromedioDatoCarga;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

	
	@Query(nativeQuery = true)
	public PromedioDatoCarga findPromedios(@Param("id") long idOrden);
}
