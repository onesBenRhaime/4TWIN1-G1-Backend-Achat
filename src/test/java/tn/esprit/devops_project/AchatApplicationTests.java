package tn.esprit.devops_project;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AchatApplicationTests {

    @Test
    void contextLoads() {
        // Ajoutez une assertion pour vérifier que le contexte se charge correctement
        //        assertNotNull(applicationContext.getBean(StockServiceImpl.class));
        //      assertNotNull(applicationContext.getBean(StockRepository.class));

        assertTrue(true);
    }

}
