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

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class ClientController {

    @Autowired
    ClientsService clientsService;

    @GetMapping("/clients")
    public List<Clients> getAllClients() {
        return clientsService.getAllClients();
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> addClient(@Valid @RequestBody Clients clients
            ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        clientsService.addClient(clients);
        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable long id) {
        Clients client = clientsService.getClientById(id);

        if (client == null) {
            return new ResponseEntity<>("Client with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id) {
        Clients client = clientsService.getClientById(id);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Client with id: %d not found", id));
        }
        clientsService.deleteClientById(id);
        String message = String.format("Client with id: %d was deleted", id);
        return ResponseEntity.ok(message);
    }


}
