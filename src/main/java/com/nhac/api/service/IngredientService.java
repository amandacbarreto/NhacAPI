package com.nhac.api.service;

import com.nhac.api.entity.Ingredient;
import com.nhac.api.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public Ingredient findById(String id){
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient Id:" + id));
    }

    public Ingredient findByName(String name){
        return ingredientRepository.findById(name)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient name:" + name));
    }

    public void delete(String id){
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient Id:" + id));
        ingredientRepository.delete(ingredient);
    }

    public Ingredient insert (Ingredient ingredient) {
        Ingredient newIngredient = new Ingredient(ingredient.getName());
        return ingredientRepository.insert(newIngredient);
    }

    public Ingredient update (String id, Ingredient ingredientDTO){
        Ingredient ingredient = this.findById(id);
        ingredient.setName(ingredientDTO.getName());
        return ingredientRepository.save(ingredient);
    }
}
