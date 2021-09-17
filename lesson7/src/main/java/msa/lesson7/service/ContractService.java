package msa.lesson7.service;


import msa.lesson7.entity.Contract;

import java.util.List;

public interface ContractService {

   List<Contract> getAll() ;

   Contract getById(Long id) ;

   Contract insert(Contract contract);

    void delete(Long id) ;

    Contract update(Long id, Contract contract);

}
