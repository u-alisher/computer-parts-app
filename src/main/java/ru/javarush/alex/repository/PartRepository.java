package ru.javarush.alex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javarush.alex.domain.Part;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Long> {
    Page<Part> findAll(Pageable pageable);
    Page<Part> findByName(String name, Pageable pageable);
    Page<Part> findByNeeded(Boolean needed, Pageable pageable);
    List<Part> findByNeeded(Boolean needed);
}
