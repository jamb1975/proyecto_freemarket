package co.com.jamb.mutant.datos;

import co.com.jamb.mutant.domain.Mutante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Repository
public class MutanteRepoImpl implements MutanteRepo{

    @Autowired
    private MutanteDAO mutanteDAO;

    @Override
    public Flux<Mutante> findAll() {
        return mutanteDAO.findAll();
    }

    @Override
    public Mono<Tuple3<Long, Long, Double>> stats() {
      return Mono.zip(mutanteDAO.findAll().filter(m-> m.isMutant()).count(),
                      mutanteDAO.findAll().filter(m-> !m.isMutant()).count(),
                      mutanteDAO.findAll().filter(m-> !m.isMutant()).count().map(m->{
                        return  mutanteDAO.findAll().filter(h-> h.isMutant()).count().map(h-> Double.valueOf(m/h)).block();
                      }));


    }

    @Override
    public Mono<Mutante> findById(String id) {
        return null;
    }

    @Override
    public Mono<Mutante> save(Mutante mutante) {
        return mutanteDAO.save(mutante);
    }

    @Override
    public Mono<Void> delete(Mutante mutante) {
        return mutanteDAO.delete(mutante);
    }
}
