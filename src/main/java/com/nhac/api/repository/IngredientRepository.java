package com.nhac.api.repository;

import com.nhac.api.entity.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Ingredient findByName(String name);
}
