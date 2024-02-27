package com.zhurawell.base.integration.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserMessage {

    private BigInteger id;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private Date registrationDate;

}
