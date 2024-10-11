package com.market_place.Repository;

//import org.springframework.data.jpa.repository.JpaRepository; -> For different query

import com.market_place.Entity.Clients;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientsRepImpl implements ClientsRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Clients> getAllClients() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from Clients");
        List<Clients> list = query.getResultList();
        return list;
    }

    @Override
    public void addClient(Clients client) {
        Session session = em.unwrap(Session.class);
        session.save(client);
    }

    @Override
    public Clients getClientById(long id) {
        Session session = em.unwrap(Session.class);
        return session.get(Clients.class, id);
    }

    @Override
    public void deleteClientById(long id) {
        Session session = em.unwrap(Session.class);
        Clients clients = session.get(Clients.class, id);
        session.delete(clients);
    }
}
