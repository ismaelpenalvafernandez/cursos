package com.softtek.cursos.excepciones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcepcionRespuesta {
    @JsonProperty
    private LocalDateTime fecha;
    @JsonProperty
    private String mensaje;
    @JsonProperty
    private String detalle;
}
