package co.com.jamb.mutant.domain;

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
    private String adn;
    private boolean mutant;
    @Transient
    private int totalMutant=0;
    @Transient
    private int totalHumanos=0;
    public Mutante(String adn) {
        this.adn = adn;
    }

}
