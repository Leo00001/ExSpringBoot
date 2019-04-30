package me.spring.boot.data.repo;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CustomRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements ICustomRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }


    @Override
    public Query executeFunc(String sql) {
        return entityManager.createNativeQuery(sql);
    }
}
