package ca.gc.collectionscanada.gcwa.web.admin;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.Seed;

@Controller
@RequestMapping("/admin/audit")
public class AuditController {

    @Autowired
    EntityManager entityManager;
    
    @RequestMapping
    public String list(Model model, Locale locale) {
        
        AuditReader reader = AuditReaderFactory.get(entityManager);
        
        @SuppressWarnings("unchecked")
        List<Object[]> seedHistory = reader.createQuery()
                .forRevisionsOfEntity(Seed.class, false, true)
                .setMaxResults(50)
                .addOrder(AuditEntity.revisionNumber().desc())
                //.addProjection(AuditEntity.revisionProperty("timestamp"))
                //.addProjection(AuditEntity.property("url"))
                .getResultList();
        model.addAttribute("seedHistory", seedHistory);
        
        @SuppressWarnings("unchecked")
        List<Object[]> collectionHistory = reader.createQuery()
                .forRevisionsOfEntity(Collection.class, false, true)
                .setMaxResults(50)
                .addOrder(AuditEntity.revisionNumber().desc())
                //.addProjection(AuditEntity.revisionProperty("timestamp"))
                //.addProjection(AuditEntity.property("url"))
                .getResultList();
        model.addAttribute("collectionHistory", collectionHistory);
        
        return "admin/audit/list";
    }
}
