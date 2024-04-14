import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.devops_project.controllers.InvoiceController;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    @Mock
    private IInvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @Test
    void testAddInvoice() {
        // Create a mock invoice
        Invoice mockInvoice = new Invoice();
        mockInvoice.setId(1L);
        mockInvoice.setDateCreationInvoice(new Date());
        mockInvoice.setDateLastModificationInvoice(new Date());

        // Mock the behavior of the invoiceService
        when(invoiceService.addInvoice(any(Invoice.class))).thenReturn(mockInvoice);

        // Call the addInvoice method in the controller
        ResponseEntity<Invoice> responseEntity = invoiceController.addInvoice(mockInvoice);

        // Verify that the response status is OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the returned invoice matches the mock invoice
        assertEquals(mockInvoice, responseEntity.getBody());

        // Verify that the addInvoice method in invoiceService was called exactly once
        verify(invoiceService, times(1)).addInvoice(any(Invoice.class));
    }

    @Test
    void testGetInvoices() {
        // Create a list of mock invoices
        List<Invoice> mockInvoices = new ArrayList<>();
        Invoice mockInvoice1 = new Invoice();
        mockInvoice1.setId(1L);
        mockInvoice1.setDateCreationInvoice(new Date());
        mockInvoice1.setDateLastModificationInvoice(new Date());
        mockInvoices.add(mockInvoice1);

        Invoice mockInvoice2 = new Invoice();
        mockInvoice2.setId(2L);
        mockInvoice2.setDateCreationInvoice(new Date());
        mockInvoice2.setDateLastModificationInvoice(new Date());
        mockInvoices.add(mockInvoice2);

        // Mock the behavior of the invoiceService
        when(invoiceService.retrieveAllInvoices()).thenReturn(mockInvoices);

        // Call the getInvoices method in the controller
        List<Invoice> retrievedInvoices = invoiceController.getInvoices();

        // Verify that the returned list of invoices matches the mock list of invoices
        assertEquals(mockInvoices, retrievedInvoices);

        // Verify that the retrieveAllInvoices method in invoiceService was called exactly once
        verify(invoiceService, times(1)).retrieveAllInvoices();
    }
}
