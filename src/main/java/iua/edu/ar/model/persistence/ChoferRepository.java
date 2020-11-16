package iua.edu.ar.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.edu.ar.model.Chofer;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Long> {

}
