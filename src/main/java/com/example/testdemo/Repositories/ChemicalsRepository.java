package com.example.testdemo.Repositories;

import com.example.testdemo.model.Chemicals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalsRepository extends PagingAndSortingRepository<Chemicals, Long>, JpaRepository<Chemicals, Long> {
}
