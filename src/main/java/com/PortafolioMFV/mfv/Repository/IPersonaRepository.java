
package com.PortafolioMFV.mfv.Repository;

import com.PortafolioMFV.mfv.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface IPersonaRepository extends JpaRepository<Persona,Integer> {
   public Optional<Persona> findByNombre(String Nombre);
    public boolean existsByNombre(String Nombre);  
}
