package org.mathew.InOutREST.services.accounts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.mathew.InOutREST.services.rating.Rating;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "korisnici")
public class Accounts {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username",nullable = false)
    String username;

    @Column(name = "email",nullable = false)
    String email;

    @Column(name = "password",nullable = false)
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "korisnik")
    Set<Rating> ocene;
}
