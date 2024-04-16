package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.repository.custom.DonDatCustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class DonDatCustomRepositoryImpl implements DonDatCustomRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;


}
