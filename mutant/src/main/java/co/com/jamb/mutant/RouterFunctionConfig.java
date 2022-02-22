package co.com.jamb.mutant;

import co.com.jamb.mutant.handler.MutanteHandler;
import co.com.jamb.mutant.servicio.MutanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {
    @Autowired
    private MutanteService mutantService;

    @Bean
    public RouterFunction<ServerResponse> routes(MutanteHandler handler){
        return route(GET("/api/mutantes"),handler::listar)
                .andRoute(GET("/api/stats"),handler::stats)
                .andRoute(POST("/api/mutant"),handler::ismutant);
    }
}
