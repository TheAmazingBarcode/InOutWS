package org.mathew.InOutREST.services.accounts;

import lombok.*;

import javax.persistence.*;

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
}
