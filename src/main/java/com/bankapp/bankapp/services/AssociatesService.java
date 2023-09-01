package com.bankapp.bankapp.services;

import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.respositories.AssociatesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class AssociatesService {
    private final AssociatesRepository associatesRepository;

    public AssociatesService(AssociatesRepository associatesRepository) {
        this.associatesRepository = associatesRepository;
    }

    public Object createAssociate(Associates associates) {
        if(!this.associatesRepository.countAssociatesByDocumentNumber(associates.getDocumentNumber())){
            throw new RuntimeException("Este documento já está sendo ultilizado!");
        }

        BigDecimal bigDecimal = new BigDecimal();
        Associates associate = new Associates();
        associate.setName(associate.getName());
        associate.setPhoneNumber(associate.getPhoneNumber());
        associate.setDocumentNumber(associate.getDocumentNumber());
        associate.setSalary((associate.getSalary()));
        return ResponseEntity.ok(this.associatesRepository.save(associate));
    }
}
