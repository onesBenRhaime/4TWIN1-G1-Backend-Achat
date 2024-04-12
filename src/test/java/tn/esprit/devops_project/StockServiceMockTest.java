package tn.esprit.devops_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceMockTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testAddStock() {
        Stock stockToAdd = new Stock();
        stockToAdd.setIdStock(1L);
        stockToAdd.setTitle("Stock 1");

        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        Stock savedStock = stockService.addStock(stockToAdd);

        assertEquals(stockToAdd, savedStock);
        verify(stockRepository, times(1)).save(stockToAdd);
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Stock 1");

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(stockId);

        assertEquals(stock, retrievedStock);
        verify(stockRepository, times(1)).findById(stockId);
    }

    /*@Test
    void testUpdateStock() {
        Stock stockToUpdate = new Stock();
        stockToUpdate.setIdStock(1L);
        stockToUpdate.setTitle("Stock 1");

        when(stockRepository.save(stockToUpdate)).thenReturn(stockToUpdate);

        Stock updatedStock = stockService.updateStock(stockToUpdate);

        assertEquals(stockToUpdate, updatedStock);
        verify(stockRepository, times(1)).save(stockToUpdate);
    }*/

    /*@Test
    void testDeleteStock() {
        Long stockId = 1L;

        stockService.deleteStock(stockId);

        verify(stockRepository, times(1)).deleteById(stockId);
    }*/
}
