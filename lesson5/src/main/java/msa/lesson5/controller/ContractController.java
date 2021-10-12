package msa.lesson5.controller;



import msa.lesson5.entity.Contract;
import msa.lesson5.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("{id}")
    public Contract get(@PathVariable Long id) {
        return contractService.getById(id);
    }


    /*fetch('/contract', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({number:'contr1',client:'cl1'})}).then(result => console.log(result))*/
    @PostMapping
    public void create(@RequestBody Contract contract) {
        contractService.insert(contract);
    }

   /* fetch('/contract/1', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({number:'contr1',client:'cl2'})}).then(result => console.log(result))*/
    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody Contract contract) {
        contractService.update(id,contract);
    }
   /* fetch('/contract/1', { method: 'DELETE', headers: {'Content-Type': 'application/json'}}).then(result => console.log(result))*/
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        contractService.delete(id);
    }
}
