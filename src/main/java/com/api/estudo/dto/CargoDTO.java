package com.api.estudo.dto;

import com.api.estudo.enums.CargoNome;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO {

    private CargoNome nome;
}
