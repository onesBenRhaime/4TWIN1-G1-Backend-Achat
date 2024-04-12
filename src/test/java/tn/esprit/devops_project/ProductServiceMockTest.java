package tn.esprit.devops_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testAddProduct() {
        Product productToAdd = new Product();
        productToAdd.setIdProduct(1L);
        productToAdd.setTitle("Test Product");
        productToAdd.setPrice(10.0f);
        productToAdd.setQuantity(100);
        productToAdd.setCategory(ProductCategory.ELECTRONICS);

        // Définir le comportement du repository mock pour la méthode save
        when(productRepository.save(productToAdd)).thenReturn(productToAdd);

        // Appeler la méthode du service pour ajouter un produit
        Product savedProduct = productService.addProduct(productToAdd, 1L);

        // Vérifier si le produit ajouté est renvoyé par le service
        assertEquals(productToAdd, savedProduct);

        // Vérifier si la méthode save du repository a été appelée une fois
        verify(productRepository, times(1)).save(productToAdd);
    }

    @Test
    void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);
        product.setTitle("Test Product");
        product.setPrice(10.0f);
        product.setQuantity(100);
        product.setCategory(ProductCategory.ELECTRONICS);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(productId);

        assertEquals(product, retrievedProduct);
        verify(productRepository, times(1)).findById(productId);
    }
}
