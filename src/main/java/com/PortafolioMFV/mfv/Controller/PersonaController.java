package com.PortafolioMFV.mfv.Controller;

import com.PortafolioMFV.mfv.Dto.dtoPersona;
import com.PortafolioMFV.mfv.Entity.Persona;
import com.PortafolioMFV.mfv.Security.Controller.Mensaje;
import com.PortafolioMFV.mfv.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")

public class PersonaController {

    @Autowired

    ImpPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
       
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoper) {
        //vALIDAMOS SI EXISTE EL ID
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        //COMPARA NOMBRE PERSONAS
        if (personaService.existsByNombre(dtoper.getNombre()) && personaService.getByNombre(dtoper.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        //EL CAMPO NO PUEDE ESTAR VACIO
        if (StringUtils.isBlank(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        
        Persona persona = personaService.getOne(id).get();
        
        persona.setNombre(dtoper.getNombre());
        persona.setApellido(dtoper.getApellido());
        persona.setDescripcion(dtoper.getDescripcion());
        persona.setImg(dtoper.getImg());
        
        
        personaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }


    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Estudio eliminado"), HttpStatus.OK);
    }*/
 /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtoper) {
        if (StringUtils.isBlank(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (personaService.existsByNombre(dtoper.getNombreE())) {
            return new ResponseEntity(new Mensaje("Ese estudio existe"), HttpStatus.BAD_REQUEST);
        }
        Estudios estudios = new Estudios(dtoest.getNombreE(), dtoest.getDescripcionE());
        sEstudios.save(estudios);
        return new ResponseEntity(new Mensaje("Estudio agregado"), HttpStatus.OK);
    }*/
}
