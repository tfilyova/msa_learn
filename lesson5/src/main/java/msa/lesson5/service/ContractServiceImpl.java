package msa.lesson5.service;


import msa.lesson5.entity.Contract;
import msa.lesson5.exception.NotFoundException;
import msa.lesson5.repository.ContractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> getAll() {
        return (List<Contract>) contractRepository.findAll();
    }

    @Override
    public Contract getById(Long id) {
        return contractRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    @Override
    public Contract insert(Contract contract) {
        contractRepository.save(contract);
        return contract;
    }
    @Override
    public void delete(Long id) {
        contractRepository.deleteById(id);
    }
    @Override
    public Contract update(Long id, Contract contract) {
        Contract updContract = contractRepository.findById(id).orElseThrow(NotFoundException::new);
        updContract.setNumber(contract.getNumber());
        updContract.setClient(contract.getClient());
        return contract;
    }
}
