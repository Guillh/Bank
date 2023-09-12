package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.Dto.BankAgencyDto;
import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.services.AssociatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associate")
public class AssociatesController {
    private final AssociatesService associatesService;

    public AssociatesController(AssociatesService associatesService) {
        this.associatesService = associatesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAssociate(@RequestBody Associates associates){
        return ResponseEntity.ok(this.associatesService.createAssociate(associates));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAssociate(@PathVariable Integer id) {
        this.associatesService.deleteAssociate(id);
    }

    @GetMapping("/get-all")
    public List<Associates> getAllAssociates() {
        return this.associatesService.getAllAssociates();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAssociateById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.associatesService.getAssociateById(id));
    }

    @PutMapping("/replace/{id}")
    public ResponseEntity<Object> replaceAssociate(@PathVariable Integer id, @RequestBody Associates associates) {
        return this.associatesService.replaceAssociate(id,associates);
    }
}
