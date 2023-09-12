package com.bankapp.bankapp.services;

import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.models.BankAgency;
import com.bankapp.bankapp.respositories.AssociatesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class AssociatesService {
    private final AssociatesRepository associatesRepository;

    public AssociatesService(AssociatesRepository associatesRepository) {
        this.associatesRepository = associatesRepository;
    }

    public Object createAssociate(Associates associates) {
        if(this.associatesRepository.countAssociatesByDocumentNumber(associates.getDocumentNumber())){
            throw new RuntimeException("Este documento já está sendo ultilizado!");
        }

//        BigDecimal bigDecimal = new BigDecimal();
       // Associates associate = new Associates();
        associates.setName(associates.getName());
        associates.setPhoneNumber(associates.getPhoneNumber());
        associates.setDocumentNumber(associates.getDocumentNumber());
        associates.setSalary((associates.getSalary()));
        return ResponseEntity.ok(this.associatesRepository.save(associates));
    }

    public void deleteAssociate(Integer id) {
        this.associatesRepository.deleteById(id);
    }

    public List<Associates> getAllAssociates() {
        return this.associatesRepository.findAll();
    }
    public Associates getAssociateById(Integer id) {
        return this.associatesRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Associado não encontrado!");
        });
    }

    public ResponseEntity<Object> replaceAssociate(Integer id, Associates associates) {
        if (this.associatesRepository.countAssociatesByDocumentNumber(associates.getDocumentNumber())) {
            throw new RuntimeException("Documento já cadastrado!");
        }
        Associates a = this.associatesRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Associado não encontrado!");
        });

        a.setSalary(associates.getSalary());
        a.setName(associates.getName());
//        a.setDocumentNumber(associates.getDocumentNumber());
        a.setPhoneNumber(associates.getPhoneNumber());
        return ResponseEntity.ok(this.associatesRepository.save(a));
    }
}
