package tn.esprit.devops_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        stockToAdd.setTitle("Sample Stock");

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
        stock.setTitle("Sample Stock");

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(stockId);

        assertEquals(stock, retrievedStock);
        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void testRetrieveAllStock() {
        // Create sample Stock objects
        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        stock1.setTitle("Stock 1");

        Stock stock2 = new Stock();
        stock2.setIdStock(2L);
        stock2.setTitle("Stock 2");

        // Create a list of Stock objects
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock1);
        stocks.add(stock2);

        // Mock the behavior of the StockRepository's findAll method
        when(stockRepository.findAll()).thenReturn(stocks);

        // Call the retrieveAllStock method of the stockService
        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        // Verify that the size of the retrievedStocks list is equal to the size of the stocks list
        assertEquals(stocks.size(), retrievedStocks.size());



        // Verify that the findAll method of the stockRepository is called exactly once
        verify(stockRepository, times(1)).findAll();
    }

    // No need to test other methods as requested

}
