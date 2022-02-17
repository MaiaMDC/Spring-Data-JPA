package br.com.alura.spring.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.spring.data.execption.ResourceNotFoundException;
import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@RestController
@RequestMapping("/marcio/douglas")
public class CargosController {
	
	@Autowired
    private CargoRepository cargoRepository;

    @GetMapping("/cargos")
    public List < Cargo > getAllcargos() {
    	System.out.println("asfndiuashfduyasinf");
        return cargoRepository.findAll();
    }
    
    @GetMapping("/cargos/{id}")
    public ResponseEntity < Cargo > getCargoById(@PathVariable(value = "id") Integer cargoId)
    throws ResourceNotFoundException {
        Cargo Cargo = cargoRepository.findById(cargoId)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo not found for this id :: " + cargoId));
        return ResponseEntity.ok().body(Cargo);
    }

    @PostMapping("/cargos")
    public Cargo createCargo(@RequestBody Cargo Cargo) {
        return cargoRepository.save(Cargo);
    }

    @PutMapping("/cargos/{id}")
    public ResponseEntity < Cargo > updateCargo(@PathVariable(value = "id") Integer CargoId,
       @RequestBody Cargo CargoDetails) throws ResourceNotFoundException {
        Cargo Cargo = cargoRepository.findById(CargoId)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo not found for this id :: " + CargoId));

        Cargo.setDescricao(CargoDetails.getDescricao());
        final Cargo updatedCargo = cargoRepository.save(Cargo);
        return ResponseEntity.ok(updatedCargo);
    }

    @DeleteMapping("/cargos/{id}")
    public Map < String, Boolean > deleteCargo(@PathVariable(value = "id") Integer CargoId)
    throws ResourceNotFoundException {
        Cargo Cargo = cargoRepository.findById(CargoId)
            .orElseThrow(() -> new ResourceNotFoundException("Cargo not found for this id :: " + CargoId));

        cargoRepository.delete(Cargo);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
