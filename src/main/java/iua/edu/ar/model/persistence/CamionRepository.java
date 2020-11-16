package iua.edu.ar.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.edu.ar.model.Camion;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

}
