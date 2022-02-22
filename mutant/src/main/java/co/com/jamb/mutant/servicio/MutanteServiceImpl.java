package co.com.jamb.mutant.servicio;

import co.com.jamb.mutant.datos.MutanteRepo;
import co.com.jamb.mutant.domain.Mutante;
import co.com.jamb.mutant.domain.Stats;
import co.com.jamb.mutant.helper.CalcularMCD;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Locale;

@Service
@Log4j2
public class MutanteServiceImpl implements MutanteService {

    @Autowired
    private MutanteRepo mutanteRepo;
    @Override
    public Flux<Mutante> findAll() {
        return mutanteRepo.findAll();
    }

    @Override
    public Mono<Stats> stats() {
         Mono<Tuple2<Long, Long>> countAllMutantHuman = Mono.zip(mutanteRepo.findAllMutant(true), mutanteRepo.findAllMutant(false));
       return  countAllMutantHuman.flatMap(t2-> Mono.just(new Stats(t2.getT1(), t2.getT2(), Double.valueOf(t2.getT1().doubleValue()/ CalcularMCD.calcular(t2.getT1(), t2.getT2()).doubleValue()).doubleValue())));
    }

    public Mono<Mutante> ismutant(Mutante mutante){
        return Mono.just(mutante).flatMap(m->{

            //Definimos Array de Strings posxy de cda lettra(POS 0 = A, POS 1=T POS 2 =C POS 3 =G
            String [] letrasAdn= {"A", "T", "C", "G"};
            int acumMismaLetra = 0;
            //Cragamos la posiciones xy de la matrix ne cada letra del Array letraAdn
            for(int l =0; l < letrasAdn.length; l++){
                for(int f = 0; f < m.getAdn().length; f++){
                    for(int c = 0; c < m.getAdn()[f].length(); c++) {
                        //Posiciones iniciales matrix

                        if (m.getAdn()[f].charAt(c) == letrasAdn[l].charAt(0)) {
                            int posY = f;
                            int posX = c;
                            //Calculamos posiciones consecutivas oblicuas hacia la derecha para encontrar min 4 letras =
                            acumMismaLetra = 0;
                                for (int  i = 0; i < 4; i++) {

                                        if(posY < m.getAdn().length  && posX < m.getAdn()[posY].length() ) {
                                            if(m.getAdn()[posY].charAt(posX) == letrasAdn[l].charAt(0)) {log.info("acum->" +acumMismaLetra); acumMismaLetra++;}
                                            else {acumMismaLetra = 0;}

                                            if(acumMismaLetra == 4) { return Mono.just(Boolean.TRUE); }

                                        }
                                       posX++;
                                       posY++;
                                    }

                            //Calculamos posiciones consecutivas oblicuas hacia la izquierda para encontrar min 4 letras =
                            acumMismaLetra = 0;
                            posY = f;
                            posX = c;
                            for (int  i = 0; i < 4; i++) {

                                if(posY < m.getAdn().length  && posX >=0 ) {
                                    if (m.getAdn()[posY].charAt(posX) == letrasAdn[l].charAt(0)) { acumMismaLetra++; }
                                    else {acumMismaLetra = 0;}

                                    if (acumMismaLetra == 4) {
                                        return Mono.just(Boolean.TRUE);
                                    }
                                }
                                    posX--;
                                    posY++;

                            }


                                //Calculamos posciones consecutivas horizontales para encontrar min 4 letras =
                                acumMismaLetra = 0;
                                posY = f;
                                posX = c;
                                for (int ob = posX; ob < 4; ob++) {
                                   if(ob < m.getAdn()[posY].length()-1) {
                                       if (m.getAdn()[posY].charAt(ob) == letrasAdn[l].charAt(0)) {acumMismaLetra++; }

                                       if (acumMismaLetra == 4) {
                                           return Mono.just(Boolean.TRUE);
                                       }
                                   }
                                }
                                //Calculamos posciones consecutivas verticales para encontrar min 4 letras =
                                acumMismaLetra = 0;
                                posY = f;
                                posX = c;
                                for (int ob = posY; ob < 4; ob++) {
                                 if(ob < m.getAdn().length -1) {
                                     if (m.getAdn()[ob].charAt(posX) == letrasAdn[l].charAt(0)) { acumMismaLetra++; }

                                     if (acumMismaLetra == 4) { return Mono.just(Boolean.TRUE); }
                                 }
                                }
                            }
                    }
                }
            }
            return Mono.just(Boolean.FALSE);
        }).map(b-> {
            Mutante modificadoMutanta =new Mutante();
            modificadoMutanta.setMutant(b.booleanValue());
            modificadoMutanta.setAdn(mutante.getAdn());
            return modificadoMutanta;
        }).flatMap(mutanteRepo::save);
    }
}
