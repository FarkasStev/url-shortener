package com.myorg.urlshortener.respository;

import com.myorg.urlshortener.model.URLMapping;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class URLMappingRepositoryImpl implements URLMappingRepository {

  @PersistenceContext
  private EntityManager em;

  public URLMapping save(URLMapping url) {
      em.persist(url);
      return url;
  }

  public URLMapping findByShortCode(String code) {
      return em.createQuery(
        "SELECT u FROM URLMapping u WHERE u.shortCode = :code",
        URLMapping.class)
        .setParameter("code", code)
        .getResultStream()
        .findFirst()
        .orElse(null);
  }
}
