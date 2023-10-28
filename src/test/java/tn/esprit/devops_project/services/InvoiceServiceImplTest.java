package tn.esprit.devops_project.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.Supplier;

import javax.persistence.SecondaryTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class InvoiceServiceImplTest {
@Autowired
InvoiceServiceImpl invoiceService;
@Autowired
SupplierServiceImpl supplierService;
@Autowired
OperatorServiceImpl operatorService;
    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    void retrieveAllInvoices() {
        final List<Invoice> allinvooice = this.invoiceService.retrieveAllInvoices();
        assertEquals(allinvooice.size(),1);
    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    void cancelInvoice() {
        final Invoice invoice = this.invoiceService.retrieveInvoice(1L);
        this.invoiceService.cancelInvoice(1L);
        assertEquals(this.invoiceService.retrieveAllInvoices().size(),1);
        assertEquals(invoice.getArchived(), Boolean.TRUE);

    }
    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    void cancelInvoice_Null() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            this.invoiceService.cancelInvoice(100L);
        });
    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    void retrieveInvoice() {
        final Invoice invoice = this.invoiceService.retrieveInvoice(1L);
        assertEquals(2.0, invoice.getAmountInvoice());
        assertEquals(5.0, invoice.getAmountDiscount());
    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    @DatabaseSetup("/data-set/supplier-data.xml")
    void getInvoicesBySupplier() {
        Supplier supplier = this.supplierService.retrieveSupplier(1L);
        List<Invoice> invoices = this.invoiceService.getInvoicesBySupplier(1L);
        assertEquals(invoices.size(), 0);
    }

    @Test
    @DatabaseSetup("/data-set/supplier-data.xml")
    @DatabaseSetup("/data-set/invoice-data.xml")
    void getInvoicesBySupplier_nullId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Supplier supplier = this.supplierService.retrieveSupplier(80L);
        });
        Exception exception1 = assertThrows(NullPointerException.class, () -> {
            List<Invoice> invoices = this.invoiceService.getInvoicesBySupplier(80L);
        });
    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    @DatabaseSetup("/data-set/operator-data.xml")
    void assignOperatorToInvoice() {
       final Operator operator = this.operatorService.retrieveOperator(1L);
       final Invoice invoice = this.invoiceService.retrieveInvoice(1L);
        this.invoiceService.assignOperatorToInvoice(1L, 1L);
       assertEquals(1, operator.getInvoices().size());

    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    @DatabaseSetup("/data-set/operator-data.xml")
    void assignOperatorToInvoice_Null() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            this.invoiceService.assignOperatorToInvoice(100L, 100L);
        });
    }
    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    @DatabaseSetup("/data-set/operator-data.xml")
    void assignOperatorToInvoice_NullOp() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            final Operator operator = this.operatorService.retrieveOperator(1L);
            this.invoiceService.assignOperatorToInvoice(50L, 1L);
        });
    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    void getTotalAmountInvoiceBetweenDates() {
       //  float amount = this.invoiceService.getTotalAmountInvoiceBetweenDates(("2023-01-01"),("2023-10-24"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     //   Date startDate = dateFormat.parse("2023-12-31");
      //  Date endDate = dateFormat.parse("2023-12-31");

    }

    @Test
    @DatabaseSetup("/data-set/invoice-data.xml")
    void retrieveInvoice_nullId() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            final Invoice invoice = this.invoiceService.retrieveInvoice(20L);
        });
    }
}