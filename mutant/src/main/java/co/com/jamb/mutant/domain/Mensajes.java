package co.com.jamb.mutant.domain;

import lombok.Data;

@Data
public class Mensajes {
    String mensage;

    public Mensajes(String mensage) {
        this.mensage = mensage;
    }
}
