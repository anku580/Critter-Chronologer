package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setPetType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        return convertPetToPetDTO(petService.savePet(pet, petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        Pet pet = petService.getPetById(petId).get();
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        petDTO.setType(pet.getPetType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();
        List<PetDTO> petDTOS = new ArrayList<PetDTO>();

        for(int i=0; i<pets.size(); i++) {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pets.get(i).getId());
            petDTO.setName(pets.get(i).getName());
            petDTO.setBirthDate(pets.get(i).getBirthDate());
            petDTO.setNotes(pets.get(i).getNotes());
            petDTO.setType(pets.get(i).getPetType());
            petDTO.setOwnerId(pets.get(i).getCustomer().getId());
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        List<Pet> pets = petService.findPetsByOwnerId(ownerId);
        List<PetDTO> petDTOS = new ArrayList<PetDTO>();

        for(int i=0; i<pets.size(); i++) {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pets.get(i).getId());
            petDTO.setName(pets.get(i).getName());
            petDTO.setBirthDate(pets.get(i).getBirthDate());
            petDTO.setNotes(pets.get(i).getNotes());
            petDTO.setType(pets.get(i).getPetType());
            petDTO.setOwnerId(pets.get(i).getCustomer().getId());
            petDTOS.add(petDTO);
        }

        return petDTOS;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setType(pet.getPetType());
        petDTO.setNotes(pet.getNotes());
        petDTO.setName(pet.getName());
        return petDTO;
    }
}
