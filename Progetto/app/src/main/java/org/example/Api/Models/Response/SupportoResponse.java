package org.example.Api.Models.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.SupportoState;

@Getter
@Setter
@NoArgsConstructor
public class SupportoResponse {
    private SupportoState state;
    private String team;
    private String Hackthon;
}
