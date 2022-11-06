package one.digitalinnovation.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.model.Address;
import one.digitalinnovation.model.AddressRepository;
import one.digitalinnovation.model.Clients;
import one.digitalinnovation.model.ClientsRepository;
import one.digitalinnovation.service.ClientsService;
import one.digitalinnovation.service.ViaCepService;

@Service
public class ClientServiceImpl implements ClientsService {

  @Autowired
  private ClientsRepository _clientsRepository;
  @Autowired
  private AddressRepository _addressRepository;
  @Autowired
  private ViaCepService _viaCepService;

  @Override
  public Iterable<Clients> findAll() {

    return _clientsRepository.findAll();
  }

  @Override
  public Clients findById(Long Id) {

    Optional<Clients> client = _clientsRepository.findById(Id);
    return client.get();
  }

  @Override
  public void insert(Clients client) {
    savewithCep(client);
  }

  @Override
  public void update(Long Id, Clients client) {
    Optional<Clients> clientId = _clientsRepository.findById(Id);
    if (clientId.isPresent()) {
      savewithCep(client);
    }

  }

  @Override
  public void delete(Long Id) {
    _clientsRepository.deleteById(Id);

  }

  private void savewithCep(Clients client) {
    String cep = client.getAddress().getCep();
    Address address = _addressRepository.findById(cep).orElseGet(() -> {
      Address newAddress = _viaCepService.consultarCep(cep);
      _addressRepository.save(newAddress);
      return newAddress;
    });
    client.setAddress(address);
    _clientsRepository.save(client);

  }

}
