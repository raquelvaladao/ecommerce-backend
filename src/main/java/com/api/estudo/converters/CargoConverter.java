package com.api.estudo.converters;

import com.api.estudo.enums.CargoNome;
import com.api.estudo.exceptions.InputInvalidoException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CargoConverter implements Converter<String, CargoNome> {

    @Override
    public CargoNome convert(String fonte) {
        try {
            return CargoNome.valueOf(fonte.toUpperCase());
        } catch (Exception e) {
           throw new InputInvalidoException("Esse caminho não existe");
        }
    }


}

