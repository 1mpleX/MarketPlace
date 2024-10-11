package com.market_place.Service;

//import com.market_place.Dto.ClientDto;
import com.market_place.Dto.ProductDto;
import com.market_place.Entity.Clients;
import com.market_place.Entity.Product;

import java.util.List;

public interface ClientsService {
    List<Clients> getAllClients();
    void addClient(Clients client);
    Clients getClientById(long id);
    void deleteClientById(long id);

    ProductDto convertProductToDto(Product product);
    Product convertProductToEntity(ProductDto productDto);
}
