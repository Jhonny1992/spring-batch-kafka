package pe.com.susaya.batch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;
    @Column( name = "firstName")
    private String firstName;
    @Column( name = "lastName")
    private String lastName;
    @Column( name = "city")
    private String city;
    @Column( name = "country")
    private String country;
    @Column( name = "firstName2")
    private String firstName2;
    @Column( name = "lastName2")
    private String lastName2;
    @Column( name = "email")
    private String email;
    @Column( name = "random")
    private Integer random;
    @Column( name = "randomFloat")
    private Double randomFloat;
    @Column( name = "bool")
    private String bool;
    @Column( name = "dates")
    private String dates;
    @Column( name = "regEx")
    private String regEx;
    @Column( name = "enume")
    private String enume;
    @Column( name = "last_update")
    private String last_update;
}
