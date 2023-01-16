package com.example.testdemo.Repositories;

import com.example.testdemo.model.Chemicals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalsRepository extends JpaRepository<Chemicals, Long> {
}
