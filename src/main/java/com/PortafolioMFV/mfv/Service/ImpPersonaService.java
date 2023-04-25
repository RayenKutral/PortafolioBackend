
package com.PortafolioMFV.mfv.Service;

import com.PortafolioMFV.mfv.Entity.Persona;
import com.PortafolioMFV.mfv.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PortafolioMFV.mfv.Repository.IPersonaRepository;


@Service

public class ImpPersonaService implements IPersonaService{
    
    @Autowired IPersonaRepository ipersonaRepository;

    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = ipersonaRepository.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
       ipersonaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
     Persona persona = ipersonaRepository.findById(id).orElse(null);      
     return persona;    
    }
    
}
