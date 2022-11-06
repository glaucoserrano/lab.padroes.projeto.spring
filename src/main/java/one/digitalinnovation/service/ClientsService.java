package one.digitalinnovation.service;

import one.digitalinnovation.model.Clients;

public interface ClientsService {
  Iterable<Clients> findAll();

  Clients findById(Long Id);

  void insert(Clients client);

  void update(Long Id, Clients client);

  void delete(Long Id);
}
