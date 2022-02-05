package com.aldeamo.app.project.repository;

import com.aldeamo.app.project.domain.Array;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrayRepository extends JpaRepository<Array, Long>{

}
