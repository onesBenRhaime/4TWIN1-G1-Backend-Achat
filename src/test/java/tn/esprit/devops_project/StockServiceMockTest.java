package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceMockTest {



    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    StockRepository universiteRepository;

    @InjectMocks
    StockServiceImpl iUniversiteService;

    @BeforeEach
    public void setup() {

    }

    @Test
    void testGetUniveristesList() {
        Stock universite1 = new Stock(9, "ben" ,new HashSet<>());
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1));
        List<Stock> universiteList = iUniversiteService.retrieveAllStock();
        assertEquals(2, universiteList.size());
        assertEquals("ben", universiteList.get(0).getTitle());

    }


    @Test
    void testGetUniveristeById() {
        Stock universite = new Stock(10, "george",new HashSet<>());
        when(universiteRepository.findById(10L)).thenReturn(Optional.of(universite));
        Stock universiteById = iUniversiteService.retrieveStock(10L);
        assertNotEquals(null, universiteById);
        assertEquals("george", universiteById.getIdStock());
    }

    @Test
    void testGetInvalidUniversiteById() {
        when(universiteRepository.findById(17L)).thenThrow(new RuntimeException("Stock Not Found with ID"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            iUniversiteService.retrieveStock(17L);
        });

        assertTrue(exception.getMessage().contains("Stock Not Found with ID"));
    }


    @Test
    void testCreateUniversite() {
        Stock universite = new Stock(12, "john",new HashSet<>());
        iUniversiteService.addStock(universite);
        verify(universiteRepository, times(1)).save(universite);
        ArgumentCaptor<Stock> universiteArgumentCaptor = ArgumentCaptor.forClass(Stock.class);
        verify(universiteRepository).save(universiteArgumentCaptor.capture());
        Stock universiteCreated = universiteArgumentCaptor.getValue();
        assertNotNull(universiteCreated.getIdStock());
        assertEquals("john", universiteCreated.getTitle());
    }




}
