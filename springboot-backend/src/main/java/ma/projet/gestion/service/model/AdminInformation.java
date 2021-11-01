package ma.projet.gestion.service.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminInformation {

    Long numberOfUsers;

    List<UserStatisticsDTO> userStatisticsList = new ArrayList<>();

}
