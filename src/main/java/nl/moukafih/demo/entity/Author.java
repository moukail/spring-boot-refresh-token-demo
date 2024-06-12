package nl.moukafih.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    public Author(){}
    public Author(String name){
        super();
        this.name = name;
    }
}
