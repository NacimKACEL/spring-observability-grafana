package com.smartsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartsys.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    long countByCompleted(boolean completed);
}
