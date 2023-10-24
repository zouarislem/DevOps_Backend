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
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.Supplier;

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
class ProductServiceImplTest {
@Autowired
private ProductServiceImpl productService;

@Autowired
StockServiceImpl stockService;
    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    @DatabaseSetup("/data-set/stock-data.xml")
    void addProduct() {
       Stock stock = this.stockService.retrieveStock(1L);
       Product product = new Product();
       product.setStock(stock);
       product.setQuantity(50);
       product.setTitle("titre20");
       product.setCategory(ProductCategory.BOOKS);

       this.productService.addProduct(product, stock.getIdStock());
       assertEquals(50, product.getQuantity());
    }

    @Test
    @DatabaseSetup("/data-set/stock-data.xml")
    @DatabaseSetup("/data-set/product-data.xml")
    void addProduct_nullId() {

        Exception exception1 = assertThrows(NullPointerException.class, () -> {;
            this.productService.addProduct(product, stock.getIdStock());
        });
    }
    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retrieveProduct() {
        final Product product = this.productService.retrieveProduct(1L);
        assertEquals("titre1", product.getTitle());
      //  assertEquals("10.0", product.getPrice());
        assertEquals(30, product.getQuantity());
    }


    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retrieveProductByCategory() {
        final List<Product> allproduct= this.productService.retrieveProductByCategory(ProductCategory.BOOKS);
        for (Product product : allproduct){
            assertEquals(ProductCategory.BOOKS, product.getCategory());
        }
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void deleteProduct() {
        final Product product = this.productService.retrieveProduct(1L);
        this.productService.deleteProduct(1L);
        assertEquals(this.productService.retreiveAllProduct().size(),0);
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    @DatabaseSetup("/data-set/stock-data.xml")
    void retreiveProductStock() {
        Stock stock = this.stockService.retrieveStock(1L);
        final List<Product> allproduct = this.productService.retreiveProductStock(1L);
        for (Product product : allproduct){
            assertEquals(product.getStock(),stock);
        }
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retrieveStock_nullId() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            final Product product = this.productService.retrieveProduct(50L);
        });
    }
}