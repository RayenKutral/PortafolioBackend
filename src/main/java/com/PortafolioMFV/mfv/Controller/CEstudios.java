package com.PortafolioMFV.mfv.Controller;

import com.PortafolioMFV.mfv.Dto.dtoEstudios;
import com.PortafolioMFV.mfv.Entity.Estudios;
import com.PortafolioMFV.mfv.Security.Controller.Mensaje;
import com.PortafolioMFV.mfv.Service.SEstudios;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estudios")
@CrossOrigin("https://backendmfv.onrender.com/")
public class CEstudios {

    @Autowired
    SEstudios sEstudios;

    @GetMapping("/lista")
    public ResponseEntity<List<Estudios>> list() {
        List<Estudios> list = sEstudios.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEstudios dtoest) {
        if (StringUtils.isBlank(dtoest.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEstudios.existsByNombreE(dtoest.getNombreE())) {
            return new ResponseEntity(new Mensaje("Ese estudio existe"), HttpStatus.BAD_REQUEST);
        }
        Estudios estudios = new Estudios(dtoest.getNombreE(), dtoest.getDescripcionE());
        sEstudios.save(estudios);
        return new ResponseEntity(new Mensaje("Estudio agregado"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Estudios> getById(@PathVariable("id") int id) {
        if (!sEstudios.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Estudios estudios = sEstudios.getOne(id).get();
        return new ResponseEntity(estudios, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEstudios dtoest) {
        //vALIDAMOS SI EXISTE EL ID
        if (!sEstudios.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        //COMPARA NOMBRE EXPERIENCIAS
        if (sEstudios.existsByNombreE(dtoest.getNombreE()) && sEstudios.getByNombreE(dtoest.getNombreE()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa estudio ya existe"), HttpStatus.BAD_REQUEST);
        }
        //EL CAMPO NO PUEDE ESTAR VACIO
        if (StringUtils.isBlank(dtoest.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Estudios estudios = sEstudios.getOne(id).get();
        estudios.setNombreE(dtoest.getNombreE());
        estudios.setDescripcionE((dtoest.getDescripcionE()));

        sEstudios.save(estudios);

        return new ResponseEntity(new Mensaje("Estudio actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEstudios.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        sEstudios.delete(id);
        return new ResponseEntity(new Mensaje("Estudio eliminado"), HttpStatus.OK);
    }

}
