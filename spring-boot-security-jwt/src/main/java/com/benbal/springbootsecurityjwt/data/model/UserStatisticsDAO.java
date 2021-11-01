package com.benbal.springbootsecurityjwt.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_statistics")
public class UserStatisticsDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate statDate;

    private Integer numberOfLoginsPerDay;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "id"
    )
    private UtilisateurDao userDAO;

    public UserStatisticsDAO(LocalDate statDate, Integer numberOfLoginsPerDay, UtilisateurDao userDAO) {
        this.statDate = statDate;
        this.numberOfLoginsPerDay = numberOfLoginsPerDay;
        this.userDAO = userDAO;
    }

}
