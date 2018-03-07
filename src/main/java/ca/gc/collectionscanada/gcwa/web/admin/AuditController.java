package ca.gc.collectionscanada.gcwa.web.admin;

import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.Seed;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/audit")
public class AuditController {

    @Autowired
    EntityManager entityManager;
    
    @RequestMapping
    public String list(Model model, Locale locale) {
        
        AuditReader reader = AuditReaderFactory.get(entityManager);
        
        /**
         * Info: forRevisionsOfEntity.getResultList() returns a list of objects
         * Object (seed, collection, etc)
         * Revision Info (timestamp, revision id, etc)
         * Revision Type (add,mod,del)
         */
        
        @SuppressWarnings("unchecked")
        List<Object[]> seedHistory = reader.createQuery()
                .forRevisionsOfEntity(Seed.class, false, true)
                .setMaxResults(50)
                .addOrder(AuditEntity.revisionNumber().desc())
                .getResultList();
        model.addAttribute("seedHistory", seedHistory);
        
        @SuppressWarnings("unchecked")
        List<Object[]> collectionHistory = reader.createQuery()
                .forRevisionsOfEntity(Collection.class, false, true)
                .setMaxResults(50)
                .addOrder(AuditEntity.revisionNumber().desc())
                .getResultList();
        model.addAttribute("collectionHistory", collectionHistory);
        
        return "admin/audit/list";
    }
}
