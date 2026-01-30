package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.models.Team;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    private Double amount;
    private Team team;
}
