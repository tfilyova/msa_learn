package msa.lesson7.controller;

import msa.lesson7.entity.Contract;
import msa.lesson7.service.ContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-junit.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class ContractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContractService contractService;

    @Test
    public void listTest() throws Exception {
        mockMvc.perform(get("/contract/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTest() throws Exception {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_1");
        contract1.setClient("Client_1");

        Contract newContract = contractService.insert(contract1);

        mockMvc.perform(get("/contract/" + newContract.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is("Contract_1")));
    }

    @Test
    public void createTest() throws Exception {
        mockMvc.perform(post("/contract")
                .content("{\"number\" : \"contract_1\", \"client\" : \"client_1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_1");
        contract1.setClient("Client_1");
        Contract newContract = contractService.insert(contract1);

        mockMvc.perform(put("/contract/1").content("{\"number\" : \"Contract_2\", \"client\" : \"Client_2\"}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        Contract contract1 = new Contract();
        contract1.setNumber("Contract_1");
        contract1.setClient("Client_1");
        Contract newContract = contractService.insert(contract1);

        mockMvc.perform(delete("/contract/" + newContract.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}