package com.example.demo.database;

import java.util.List;

import com.example.demo.model.Culture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {

    // https://github.com/spring-projects/spring-data-jpa/issues/2472
    public List<Culture> findByNameContaining(@Param("name") String name);
}
