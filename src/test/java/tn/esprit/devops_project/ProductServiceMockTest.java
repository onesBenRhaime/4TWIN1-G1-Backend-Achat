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
    void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);
        product.setTitle("Test Product");
        product.setPrice(10.0f);
        product.setQuantity(100);
        product.setCategory(ProductCategory.ELECTRONICS);

        // Définir le comportement du repository mock pour la méthode findById
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Appeler la méthode du service pour récupérer le produit
        Product retrievedProduct = productService.retrieveProduct(productId);

        // Vérifier si le produit récupéré est correct
        assertEquals(product, retrievedProduct);

        // Vérifier si la méthode findById du repository a été appelée une fois avec l'ID correct
        verify(productRepository, times(1)).findById(productId);
    }
}
