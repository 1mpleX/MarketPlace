package com.market_place.Controller;

import com.market_place.Dto.ProductDto;
import com.market_place.Entity.Clients;
import com.market_place.Entity.Product;
import com.market_place.Service.ClientsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ClientsService clientsService;

    @GetMapping("/clients/{clientId}/products")
    public ResponseEntity<Object> getAllProductsByClient(@PathVariable long clientId) {
        Clients client = clientsService.getClientById(clientId);

        if (client == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Client with id: "+clientId+" not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        List<ProductDto> productDtos = client.getProducts()
                .stream()
                .map(clientsService::convertProductToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }


    @GetMapping("/clients/{clientId}/products/{productId}")
    public ResponseEntity<Object> getProductByClientAndId(@PathVariable long clientId, @PathVariable long productId) {
        Clients client = clientsService.getClientById(clientId);
        if (client == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("ClientError", "Client with id: "+clientId +" not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        Product product = client.getProducts().stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);

        if (product == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("ProductError", "Product with id: "+productId+" not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(clientsService.convertProductToDto(product));
    }


    @PostMapping("/clients/{clientId}/products")
    public ResponseEntity<Object> addProductToClient(@PathVariable long clientId, @RequestBody ProductDto productDto, BindingResult bindingResult) {
        Clients client = clientsService.getClientById(clientId);
        if (client == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Client with id: "+clientId+" not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }


        Product product = clientsService.convertProductToEntity(productDto);
        product.setClient(client);
        client.getProducts().add(product);
        clientsService.addClient(client);
        return ResponseEntity.ok(clientsService.convertProductToDto(product));
    }


    @DeleteMapping("/clients/{clientId}/products/{productId}")
    public ResponseEntity<Object> deleteProductFromClient(@PathVariable long clientId, @PathVariable long productId) {
        Clients client = clientsService.getClientById(clientId);
        if (client == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("ClientError", "Client with id: "+clientId +" not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        Product product = client
                .getProducts()
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);

        if (product == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("ProductError", "Product with id: "+productId+" not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        client.getProducts().remove(product);
        clientsService.addClient(client);
        return ResponseEntity.ok("Product with id " + productId + " was deleted");
    }
}
