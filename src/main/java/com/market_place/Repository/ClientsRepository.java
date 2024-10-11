package com.market_place.Repository;

import com.market_place.Entity.Clients;

import java.util.List;

public interface ClientsRepository {
    List<Clients> getAllClients();
    void addClient(Clients client);
    Clients getClientById(long id);
    void deleteClientById(long id);
}
