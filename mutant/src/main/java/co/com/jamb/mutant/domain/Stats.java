package co.com.jamb.mutant.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stats {

    @JsonProperty("“count_mutant_dna")
    private long countMutantAdn;
    @JsonProperty("“count_human_dna")
    private long countHumanoAdn;
    private Double ratio;
}
