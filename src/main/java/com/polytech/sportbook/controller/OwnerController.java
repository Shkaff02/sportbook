package com.polytech.sportbook.controller;

import com.polytech.sportbook.domain.Owner;
import com.polytech.sportbook.service.OwnerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        return ResponseEntity.ok().body(ownerService.owners());
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ownerService.getOwnerById(id));
    }

    @PostMapping("/owners")
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/owners").toUriString());
        return ResponseEntity.created(uri).body(ownerService.saveOwner(owner));
    }

    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.updateOwner(id, owner));
    }

    @DeleteMapping("/owners/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok().build();
    }

}


