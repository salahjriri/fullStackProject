package com.benbal.springbootsecurityjwt.service.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsDTO {

    LocalDate statDate;

    Long numberOfLogins;

}
