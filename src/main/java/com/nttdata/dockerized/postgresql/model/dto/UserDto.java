package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private Date fechaRegistro;

    private String status;
}
