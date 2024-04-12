package tn.esprit.devops_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceMockTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    void testAddSupplier() {
        Supplier supplierToAdd = new Supplier();
        supplierToAdd.setIdSupplier(1L);
        supplierToAdd.setCode("SUP001");
        supplierToAdd.setLabel("Supplier 1");

        when(supplierRepository.save(supplierToAdd)).thenReturn(supplierToAdd);

        Supplier savedSupplier = supplierService.addSupplier(supplierToAdd);

        assertEquals(supplierToAdd, savedSupplier);
        verify(supplierRepository, times(1)).save(supplierToAdd);
    }

    @Test
    void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);
        supplier.setCode("SUP001");
        supplier.setLabel("Supplier 1");

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals(supplier, retrievedSupplier);
        verify(supplierRepository, times(1)).findById(supplierId);
    }

    @Test
    void testUpdateSupplier() {
        Supplier supplierToUpdate = new Supplier();
        supplierToUpdate.setIdSupplier(1L);
        supplierToUpdate.setCode("SUP001");
        supplierToUpdate.setLabel("Supplier 1");

        when(supplierRepository.save(supplierToUpdate)).thenReturn(supplierToUpdate);

        Supplier updatedSupplier = supplierService.updateSupplier(supplierToUpdate);

        assertEquals(supplierToUpdate, updatedSupplier);
        verify(supplierRepository, times(1)).save(supplierToUpdate);
    }

    @Test
    void testDeleteSupplier() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        verify(supplierRepository, times(1)).deleteById(supplierId);
    }
}
