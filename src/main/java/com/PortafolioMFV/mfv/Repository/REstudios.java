
package com.PortafolioMFV.mfv.Repository;

import com.PortafolioMFV.mfv.Entity.Estudios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REstudios extends JpaRepository <Estudios, Integer>{
    public Optional<Estudios> findByNombreE(String NombreE);
    public boolean existsByNombreE(String NombreE);   
}
