package nl.moukafih.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private Date birthday = null;

    public Author(){}
    public Author(String firstName, String lastName){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
