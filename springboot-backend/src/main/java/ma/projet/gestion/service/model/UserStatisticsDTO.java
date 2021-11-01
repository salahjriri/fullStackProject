package ma.projet.gestion.service.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsDTO {

    LocalDate statDate;

    Long numberOfLogins;

}
