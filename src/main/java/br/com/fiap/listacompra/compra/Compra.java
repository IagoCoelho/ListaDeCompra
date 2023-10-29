package br.com.fiap.listacompra.compra;

import br.com.fiap.listacompra.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Compra {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank
    String title;

    @Size(min = 5)
    String description;

    @Min(1) @Max(10)
    Integer score;

    @Min(1) @Max(10)
    Integer status;
}
