package ru.pis.lab9.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pis.lab9.model.Automobile;
import ru.pis.lab9.repo.AutomobileRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Repository
@Transactional
@RequiredArgsConstructor
public class AutomobileDao {
    private final AutomobileRepo automobileRepo;
    @PersistenceContext
    private final EntityManager entityManager;

    public List<Automobile> getAllAutomobiles() {
        return automobileRepo.getAll();
    }

    public Automobile getAutomobileById(Long id) {
        if (isNull(id)) {
            throw new IllegalArgumentException("Empty id");
        }

        return automobileRepo.getById(id);
    }

    public void updateAutomobile(Automobile automobile) {
        if (isNull(automobile)) {
            throw new IllegalArgumentException("Empty auto");
        }

        entityManager.persist(automobile);
    }
}
