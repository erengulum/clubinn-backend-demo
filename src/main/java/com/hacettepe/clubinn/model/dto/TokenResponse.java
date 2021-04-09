package com.hacettepe.clubinn.model.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Front-ende verilecek olan Token objesi: Daha fazla bilgi de verilebilir sonraki asamada (isim soy isim gibi)


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String username;
    private String firstname;
    private String surname;
    private String role;
    private String token;
}
