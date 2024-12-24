package com.appliances.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Client extends User{
    @NotBlank(message = "Card is required")
    private String card;

    public Client(Long id, String name, String email, String password, String card) {
        super(id, name, email, password);
        this.card = card;
    }
}
