package ru.pis.lab9.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pis.lab9.model.Automobile;

import java.util.List;

@Repository
public interface AutomobileRepo extends JpaRepository<Automobile, Long> {
    Automobile getById(Long id);

    @Query("SELECT auto FROM Automobile auto")
    List<Automobile> getAll();
}
