package com.PortafolioMFV.mfv.Security.Repository;

import com.PortafolioMFV.mfv.Security.Entity.Rol;
import com.PortafolioMFV.mfv.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre (RolNombre rolNombre);
}