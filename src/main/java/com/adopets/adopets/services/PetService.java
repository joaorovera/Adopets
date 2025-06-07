package com.adopets.adopets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopets.adopets.model.Pet;
import com.adopets.adopets.repositories.PetRepository;

import jakarta.transaction.Transactional;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;

    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }

    @Transactional
    public Pet create(Pet pet) {
        pet.setId(null); // Ensure the ID is null for a new entity
        pet = this.petRepository.save(pet);
        return pet;
    }

    @Transactional
    public Pet update(Pet pet) {
        Pet newPet = findById(pet.getId());
        newPet.setAge(pet.getAge());
        return petRepository.save(newPet);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.petRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting pet with id: " + id, e);
        }
    }
}
