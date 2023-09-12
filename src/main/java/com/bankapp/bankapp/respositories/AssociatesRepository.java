package com.bankapp.bankapp.respositories;

import com.bankapp.bankapp.models.Associates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatesRepository extends JpaRepository<Associates, Integer> {

    @Query(nativeQuery = true,
            value = " SELECT COUNT(*) > 0 " +
                    " FROM associates a " +
                    " WHERE a.document_number = :documentNumber ")
    boolean countAssociatesByDocumentNumber(@Param("documentNumber") Integer documentNumber);


}

