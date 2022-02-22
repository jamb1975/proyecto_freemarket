package co.com.jamb.mutant.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;

@Document(collection = "mutantes")
@Data
public class Mutante {
    @Id
    private String id;
    @Pattern(regexp = "[ATCG]")
    @JsonProperty("adn")
    private String[] adn;
    @JsonProperty("mutant")
    private boolean mutant;
    public Mutante(String[] adn) {
        this.adn = adn;
    }
    public Mutante() {

    }
}
