package com.polytech.sportbook.controller;

import com.polytech.sportbook.domain.Category;
import com.polytech.sportbook.domain.SportObject;
import com.polytech.sportbook.service.SportObjectService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class SportObjectController {
    private final SportObjectService sportObjectService;

    @GetMapping("/objects")
    public ResponseEntity<List<SportObject>> getObjects() {
        return ResponseEntity.ok().body(sportObjectService.objects());
    }

    @GetMapping("/objects/names/{name}")
    public ResponseEntity<SportObject> getObjectByName(@PathVariable String name) {
        return ResponseEntity.ok().body(sportObjectService.getObject(name));
    }

    @GetMapping("/objects/{id}")
    public ResponseEntity<SportObject> getObjectById(@PathVariable Long id) {
        return ResponseEntity.ok().body(sportObjectService.getObjectById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(sportObjectService.categories());
    }



    @PostMapping("/objects/owners/{id}")
    public ResponseEntity<SportObject> saveObject(@PathVariable Long id, @RequestBody SportObject object) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/objects").toUriString());
        return ResponseEntity.created(uri).body(sportObjectService.saveObject(id, object));
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/categories").toUriString());
        return ResponseEntity.created(uri).body(sportObjectService.saveCategory(category));

    }

    @PutMapping("/objects/categories")
    public ResponseEntity<?> addCategoryToObject(@RequestBody CategoryToObjectForm form) {
        sportObjectService.addCategoryToObject(form.getCategoryName(), form.getObjectName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/objects/{id}")
    public ResponseEntity<SportObject> updateObject(@PathVariable Long id, @RequestBody SportObject object) {
        return ResponseEntity.ok().body(sportObjectService.updateObject(id, object));
    }

    @DeleteMapping("/objects/{id}")
    public ResponseEntity<?> deleteObject(@PathVariable Long id) {
        sportObjectService.deleteObject(id);
        return ResponseEntity.ok().build();
    }
}

@Data
class CategoryToObjectForm {
    private String categoryName;
    private String objectName;
}

