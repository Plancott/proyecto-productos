package com.nttdata.dockerized.postgresql;

import com.nttdata.dockerized.postgresql.exception.ProductoNoFoundException;
import com.nttdata.dockerized.postgresql.exception.RangeInvalidException;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import com.nttdata.dockerized.postgresql.service.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Electrónica");

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(1500.0);
        producto.setCategoria(categoria);
    }

    @Test
    void testListAll() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));

        List<Producto> result = productoService.listAll();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccessfully() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoService.findById(1L);

        assertNotNull(result);
        assertEquals("Laptop", result.getNombre());
    }

    @Test
    void testSaveSuccessfully() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoService.save(producto, 1L);

        assertNotNull(result);
        assertEquals("Electrónica", result.getCategoria().getNombre());
        verify(productoRepository).save(producto);
    }

    @Test
    void testUpdateSuccessfully() {
        Producto actualizado = new Producto();
        actualizado.setNombre("Tablet");
        actualizado.setPrecio(800.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto result = productoService.update(1L, actualizado, 1L);

        assertNotNull(result);
        assertEquals("Tablet", result.getNombre());
        assertEquals(800.0, result.getPrecio());
    }

    @Test
    void testDeleteSuccessfully() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1L);

        productoService.delete(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByCategoriaSuccessfully() {
        when(categoriaRepository.existsById(1L)).thenReturn(true);
        when(productoRepository.findByCategoriaId(1L)).thenReturn(Arrays.asList(producto));

        List<Producto> result = productoService.findByCategoria(1L);

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombre());
    }

    @Test
    void testFindByPrecioBetweenSuccessfully() {
        when(productoRepository.findByPrecioBetween(1000.0, 2000.0))
                .thenReturn(Arrays.asList(producto));

        List<Producto> result = productoService.findByPrecioBetween(1000.0, 2000.0);

        assertEquals(1, result.size());
        assertEquals(1500.0, result.get(0).getPrecio());
    }

    @Test
    void testFindByIdThrowsProductoNoFoundException() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductoNoFoundException.class, () -> productoService.findById(99L));
    }

    @Test
    void testFindByPrecioBetweenThrowsRangeInvalidException() {
        assertThrows(RangeInvalidException.class, () ->
                productoService.findByPrecioBetween(5000.0, 1000.0));
    }
}
