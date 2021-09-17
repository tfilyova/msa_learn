package msa.lesson7.service;

import msa.lesson7.entity.Contract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-junit.properties")
@SpringBootTest
public class ContractServiceImplTest {
    @Autowired
    private ContractService contractService;

    @Test
    public void getAllTest() {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_01");
        contract1.setClient("Client_1");
        contractService.insert(contract1);

        Contract contract2 = new Contract();
        contract2.setNumber("Contract_02");
        contract2.setClient("Client_2");
        contractService.insert(contract2);

        Contract contract3 = new Contract();
        contract3.setNumber("Contract_03");
        contract3.setClient("Client_3");
        contractService.insert(contract3);

        int countContracts = contractService.getAll().size();
        assertTrue(countContracts >=3L);
    }


    @Test
    public void getByIdTest() {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_01");
        contract1.setClient("Client_1");
        contractService.insert(contract1);

        Optional<Contract> contractFind = Optional.ofNullable(contractService.getById(contract1.getId()));
        Assert.assertEquals(contractFind.get().getId().longValue(), contract1.getId().longValue());
    }

    @Test
    public void insertTest() {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_01");
        contract1.setClient("Client_1");
        contractService.insert(contract1);

        Contract contractFind = contractService.getById(contract1.getId());
        assertEquals(contractFind,contract1);
    }

    @Test
    public void deleteTest() {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_01");
        contract1.setClient("Client_1");
        contractService.insert(contract1);

        Contract contractFind = contractService.getById(contract1.getId());
        contractService.delete(contractFind.getId());

        long id =contract1.getId();

        Optional<Contract> contractFind2 = Optional.ofNullable(contractService.getById(id));
        assertFalse(contractFind2.isPresent() );
    }

    @Test
    public void updateTest() {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_01");
        contract1.setClient("Client_1");
        contractService.insert(contract1);

        Contract contract2 =  new Contract();
        contract2.setNumber("Contract_02");
        contract2.setClient("Client_2");

        Contract contract3 = contractService.update(contract1.getId(),contract2);

        assertEquals(contract3.getNumber(), "Contract_02");
    }
}