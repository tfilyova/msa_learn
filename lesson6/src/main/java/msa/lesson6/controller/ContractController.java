package msa.lesson6.controller;



import msa.lesson6.entity.Contract;
import msa.lesson6.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    ContractService contractService;

    @GetMapping
    public Iterable<Contract> list() {
        return contractService.findAll();
    }

    @GetMapping("{id}")
    public Contract get(@PathVariable Long id) {
        return contractService.getById(id);
    }

    /*fetch('/contract', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({number:'contr1',client:'cl1'})}).then(result => console.log(result))*/
    @PostMapping
    @Transactional
    public void create(@RequestBody Contract contract) {
        contractService.insert(contract);
    }

   /* fetch('/contract/1', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({number:'contr1',client:'cl2'})}).then(result => console.log(result))*/
    @PutMapping("{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody Contract contract) {
        contractService.update(id,contract);
    }
   /* fetch('/contract/1', { method: 'DELETE', headers: {'Content-Type': 'application/json'}}).then(result => console.log(result))*/
    @DeleteMapping("{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        contractService.delete(id);
    }
}
