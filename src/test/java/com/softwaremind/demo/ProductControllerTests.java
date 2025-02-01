package com.softwaremind.demo;

import com.softwaremind.demo.controller.ProductController;
import com.softwaremind.demo.dto.product.ProductRequest;
import com.softwaremind.demo.dto.product.ProductResponse;
import com.softwaremind.demo.model.product.Product;
import com.softwaremind.demo.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTests {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void createProduct_shouldCallProductService() {
        ProductRequest productRequest = new ProductRequest();

        productController.createProduct(productRequest);

        verify(productService, times(1)).createProduct(productRequest);
    }

    @Test
    public void getProduct_shouldCallProductService() {
        UUID productId = UUID.randomUUID();
        ProductResponse productResponse = new ProductResponse();
        when(productService.getProduct(productId)).thenReturn(productResponse);

        ProductResponse result = productController.getProduct(productId);

        assertEquals(productResponse, result);
        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    public void getAllProducts_shouldCallProductService() {
        List<ProductResponse> productResponses = List.of(new ProductResponse(), new ProductResponse());
        when(productService.getAllProducts()).thenReturn(productResponses);

        List<ProductResponse> result = productController.getAllProducts();

        assertEquals(productResponses, result);
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void getProductsWithCategory_shouldCallProductService() {
        String category = "category";
        List<ProductResponse> productResponses = List.of(new ProductResponse(), new ProductResponse());
        when(productService.getProductsWithCategory(category)).thenReturn(productResponses);

        List<ProductResponse> result = productController.getProductsWithCategory(category);

        assertEquals(productResponses, result);
        verify(productService, times(1)).getProductsWithCategory(category);
    }

    @Test
    public void updateProduct_shouldCallProductService() {
        UUID productId = UUID.randomUUID();
        Product productRequest = new Product();
        ProductResponse productResponse = new ProductResponse();
        when(productService.updateProduct(productId, productRequest)).thenReturn(ResponseEntity.ok(productResponse));

        ResponseEntity<ProductResponse> result = productController.updateProduct(productId, productRequest);

        assertEquals(ResponseEntity.ok(productResponse), result);
        verify(productService, times(1)).updateProduct(productId, productRequest);
    }

    @Test
    public void deleteProduct_shouldCallProductService() {
        UUID productId = UUID.randomUUID();
        when(productService.deleteProduct(productId)).thenReturn(ResponseEntity.ok("Product deleted"));

        ResponseEntity<String> result = productController.deleteProduct(productId);

        assertEquals(ResponseEntity.ok("Product deleted"), result);
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    public void createProduct_shouldHavePreAuthorizeAnnotation() throws NoSuchMethodException {
        PreAuthorize preAuthorize = productController.getClass().getDeclaredMethod("createProduct", ProductRequest.class).getAnnotation(PreAuthorize.class);
        assertNotNull(preAuthorize);
        assertEquals("hasRole('ADMIN')", preAuthorize.value());
    }

    @Test
    public void getProduct_shouldHavePreAuthorizeAnnotation() throws NoSuchMethodException {
        PreAuthorize preAuthorize = productController.getClass().getDeclaredMethod("getProduct", UUID.class).getAnnotation(PreAuthorize.class);
        assertNotNull(preAuthorize);
        assertEquals("hasRole('USER') or hasRole('ADMIN')", preAuthorize.value());
    }

    @Test
    public void getAllProducts_shouldHavePreAuthorizeAnnotation() throws NoSuchMethodException {
        PreAuthorize preAuthorize = productController.getClass().getDeclaredMethod("getAllProducts").getAnnotation(PreAuthorize.class);
        assertNotNull(preAuthorize);
        assertEquals("hasRole('USER') or hasRole('ADMIN')", preAuthorize.value());
    }

    @Test
    public void getProductsWithCategory_shouldHavePreAuthorizeAnnotation() throws NoSuchMethodException {
        PreAuthorize preAuthorize = productController.getClass().getDeclaredMethod("getProductsWithCategory", String.class).getAnnotation(PreAuthorize.class);
        assertNotNull(preAuthorize);
        assertEquals("hasRole('USER') or hasRole('ADMIN')", preAuthorize.value());
    }

    @Test
    public void updateProduct_shouldHavePreAuthorizeAnnotation() throws NoSuchMethodException {
        PreAuthorize preAuthorize = productController.getClass().getDeclaredMethod("updateProduct", UUID.class, Product.class).getAnnotation(PreAuthorize.class);
        assertNotNull(preAuthorize);
        assertEquals("hasRole('ADMIN')", preAuthorize.value());
    }

    @Test
    public void deleteProduct_shouldHavePreAuthorizeAnnotation() throws NoSuchMethodException {
        PreAuthorize preAuthorize = productController.getClass().getDeclaredMethod("deleteProduct", UUID.class).getAnnotation(PreAuthorize.class);
        assertNotNull(preAuthorize);
        assertEquals("hasRole('ADMIN')", preAuthorize.value());
    }
}