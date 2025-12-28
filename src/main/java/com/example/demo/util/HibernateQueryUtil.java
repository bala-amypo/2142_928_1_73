package com.example.demo.util;

import com.example.demo.model.ApprovalAction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class HibernateQueryUtil {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ApprovalAction> findActionsByApproverUsingCriteria(Long approverId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ApprovalAction> criteriaQuery = criteriaBuilder.createQuery(ApprovalAction.class);
        Root<ApprovalAction> root = criteriaQuery.from(ApprovalAction.class);
        
        criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("approverId"), approverId));
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
