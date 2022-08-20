package com.testspring.demorest.service;

import com.testspring.demorest.model.Client;

import java.util.List;

public interface IClientService {
    void create(Client client);
    List<Client> readAll();
    Client read(int id);
    boolean update(Client client, int id);
    boolean delete(int id);
}
