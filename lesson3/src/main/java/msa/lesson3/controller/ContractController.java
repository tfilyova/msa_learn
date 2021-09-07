package msa.lesson3.controller;

import msa.lesson3.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("contract")
public class ContractController {
    private int counter = 4;
    private List<Map<String, String>> contracts = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("number", "Contarct_01");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("number", "Contarct_02");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("number", "Contarct_03");
        }});
    }};

    @GetMapping
    public  List<Map<String, String>> list(){
        return contracts;
    }
    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getContract(id);
    }

    public Map<String, String> getContract(String id) {
        return contracts.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {

       // System.out.println(message);
        message.put("id", String.valueOf(counter++));
       // System.out.println(message);
        contracts.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFrom = getContract(id);
        messageFrom.putAll(message);
        messageFrom.put("id", id);
        return messageFrom;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getContract(id);
        contracts.remove(message);
    }
}
