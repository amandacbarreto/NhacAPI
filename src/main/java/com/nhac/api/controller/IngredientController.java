package com.nhac.api.controller;

import com.nhac.api.entity.Ingredient;
import com.nhac.api.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/api/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients(){
        List<Ingredient> list = ingredientService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable String id) {
        Ingredient obj = ingredientService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Ingredient> insert(@RequestBody Ingredient dto){
        Ingredient ingredient = ingredientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ingredient.getId()).toUri();
        return ResponseEntity.created(uri).body(ingredient);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable String id){
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable String id, @RequestBody Ingredient obj) {
        Ingredient ingredient = ingredientService.update(id, obj);
        return ResponseEntity.ok().body(ingredient);
    }

}
