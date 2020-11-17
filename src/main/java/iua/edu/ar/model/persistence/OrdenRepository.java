package iua.edu.ar.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.edu.ar.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

//	public Optional<Orden> findFirstByCodigoExterno(String codigoExterno);
}
