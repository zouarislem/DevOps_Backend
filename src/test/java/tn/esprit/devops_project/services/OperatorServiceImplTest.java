package tn.esprit.devops_project.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.OperationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Stock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class OperatorServiceImplTest {
@Autowired
OperatorServiceImpl operatorService;
    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void retrieveAllOperators() {
        final List<Operator> allOperator = this.operatorService.retrieveAllOperators();
        assertEquals(allOperator.size(), 1);
    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void addOperator() {
        final Operator operator= new Operator();
        operator.setFname("fname2");
        operator.setLname("lname2");
        operator.setPassword("pwd2");
        this.operatorService.addOperator(operator);
        assertEquals(this.operatorService.retrieveAllOperators().size(),2);
        assertEquals(this.operatorService.retrieveOperator(2L).getPassword(),"pwd2");
    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void deleteOperator() {
        final Operator operator = this.operatorService.retrieveOperator(1L);
        this.operatorService.deleteOperator(1L);
        assertEquals(this.operatorService.retrieveAllOperators().size(),0);
    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void updateOperator() {
        final Operator operator = this.operatorService.retrieveOperator(1L);
        operator.setLname("next");
        operator.setFname("f");
        operator.setPassword("ww");
        this.operatorService.updateOperator(operator);
        assertEquals(this.operatorService.retrieveOperator(1L).getLname(),"next");
    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void retrieveOperator() {
        final Operator operator = this.operatorService.retrieveOperator(1L);
        assertEquals("lname 1", operator.getLname());
        assertEquals("fname 1", operator.getFname());
        assertEquals("pwd1", operator.getPassword());
    }
    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void retrieveOperator_nullId() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            final Operator operator = this.operatorService.retrieveOperator(30L);
        });
    }
}