package one.digitalinnovation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.model.Clients;
import one.digitalinnovation.service.ClientsService;

@RestController
@RequestMapping("clientes")
public class ClientsRestController {
  @Autowired
  private ClientsService _cService;

  @GetMapping
  public ResponseEntity<Iterable<Clients>> buscarTodos() {
    return ResponseEntity.ok(_cService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Clients> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(_cService.findById(id));
  }

  @GetMapping
  public ResponseEntity<Clients> Inserir(@RequestBody Clients client) {
    _cService.insert(client);
    return ResponseEntity.ok(client);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Clients> Atualizar(@PathVariable Long id, @RequestBody Clients client) {
    _cService.update(id, client);
    return ResponseEntity.ok(client);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> Excluir(@PathVariable Long id) {
    _cService.delete(id);
    return ResponseEntity.ok().build();
  }
}
