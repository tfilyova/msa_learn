package msa.lesson5.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String client;


 /*   public Contract(String number, String client) {
        this.number = number;
        this.client = client;
    }*/

    @Override
    public String toString() {
        return String.format(
                "Contract[id=%d, number='%s', client='%s']",
                id, number, client);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}