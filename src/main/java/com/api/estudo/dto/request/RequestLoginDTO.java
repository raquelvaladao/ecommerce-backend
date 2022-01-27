package com.api.estudo.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestLoginDTO {

        @NotEmpty
        @Size(max = 20)
        private String login;

        @NotEmpty
        @Size(min = 6, max = 40)
        private String senha;
}
