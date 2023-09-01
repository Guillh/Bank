package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.services.AssociatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
