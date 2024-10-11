package com.market_place.Service;

//import com.market_place.Dto.ClientDto;
import com.market_place.Dto.ProductDto;
import com.market_place.Entity.Clients;
import com.market_place.Entity.Product;
import com.market_place.Repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    @Transactional
    public List<Clients> getAllClients() {
        return clientsRepository.getAllClients();
    }

    @Override
    @Transactional
    public void addClient(Clients client) {
        clientsRepository.addClient(client);
    }

    @Override
    @Transactional
    public Clients getClientById(long id) {
        return clientsRepository.getClientById(id);
    }

    @Override
    @Transactional
    public void deleteClientById(long id) {
        clientsRepository.deleteClientById(id);
    }

    public Product convertProductToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public ProductDto convertProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
