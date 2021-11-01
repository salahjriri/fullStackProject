package com.benbal.springbootsecurityjwt.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_financials")
public class UserFinancialDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate statDate;

    private Long sumOfPayments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "id"
    )
    private UtilisateurDao userDAO;

}
