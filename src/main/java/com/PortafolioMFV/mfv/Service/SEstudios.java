/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PortafolioMFV.mfv.Service;

import com.PortafolioMFV.mfv.Entity.Estudios;
import com.PortafolioMFV.mfv.Repository.REstudios;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SEstudios {
    
    @Autowired
    REstudios rEstudios;
    
    public List<Estudios> list(){
        return rEstudios.findAll();
}
    public Optional<Estudios> getOne(int id){
        return rEstudios.findById(id);
    }
    
    public Optional<Estudios> getByNombreE(String nombreE){
        return rEstudios.findByNombreE(nombreE);
    }
    
    public void save(Estudios est){
        rEstudios.save(est);
    }
    public void delete(int id){
        rEstudios.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rEstudios.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rEstudios.existsByNombreE(nombreE);
    }
            }