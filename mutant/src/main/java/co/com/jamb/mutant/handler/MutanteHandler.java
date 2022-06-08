package co.com.jamb.mutant.handler;

import co.com.jamb.mutant.datos.MutanteRepo;
import co.com.jamb.mutant.domain.Mensajes;
import co.com.jamb.mutant.domain.Mutante;
import co.com.jamb.mutant.helper.ValidarAdn;
import co.com.jamb.mutant.servicio.MutanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class
MutanteHandler {

    @Autowired
    private MutanteService mutanteService;

    @Autowired
    private Validator validator; nm 
    @Autowired
    private MutanteRepo mutanteRepo;
    public Mono<ServerResponse> listar(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mutanteService.findAll(), Mutante.class);
    }

    public Mono<ServerResponse> stats(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mutanteService.stats(), Mutante.class);
    }

    public Mono<ServerResponse> ismutant(ServerRequest request){
        Mono<Mutante> mutante = request.bodyToMono(Mutante.class);

        return mutante.flatMap(m -> {

            Errors errors = new BeanPropertyBindingResult(m, Mutante.class.getName());
            validator.validate(m, errors);

            if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromValue(list)));
            }
           if(ValidarAdn.validarAdn(m.getAdn())){
                return mutanteRepo.findByAdn(m.getAdn()).collectList().doOnError(throwable -> throwable.printStackTrace()).filter(mf -> mf.size() == 0).flatMap(mf -> mutanteService.ismutant(m)).flatMap(m2 -> {

                 if (m2.isMutant()) {
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(m2));
                }
                 else {
                    return ServerResponse.status(HttpStatus.FORBIDDEN)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(m2));
                }
            }).switchIfEmpty(ServerResponse.status(HttpStatus.FORBIDDEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(new Mensajes("Adn ya Existe"))));
        }
         else{
             return ServerResponse.status(HttpStatus.FORBIDDEN)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(BodyInserters.fromValue(new Mensajes("Letra en Mapa Adn no Válida")));
         }
        });

    }


}
