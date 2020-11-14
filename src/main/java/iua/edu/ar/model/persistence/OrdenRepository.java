package iua.edu.ar.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import iua.edu.ar.model.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

	public Optional<Orden> findFirstByCodigoExterno(String codigoExterno);
}
