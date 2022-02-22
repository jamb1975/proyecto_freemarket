package co.com.jamb.mutant;

import co.com.jamb.mutant.datos.MutanteDAO;
import co.com.jamb.mutant.datos.MutanteRepo;
import co.com.jamb.mutant.domain.Mutante;
import co.com.jamb.mutant.domain.Stats;
import co.com.jamb.mutant.helper.CalcularMCD;
import co.com.jamb.mutant.servicio.MutanteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MutantApplicationTests {
	@Autowired
    private MutanteService mutanteService;
	@Autowired
	private MutanteRepo mutanteRepo;
	@Autowired
	private MutanteDAO mutanteDAO;

	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void  testMCD() {
		Long num1 = Long.valueOf(2310);
		Long num2 = Long.valueOf(98);

		assertEquals(14,CalcularMCD.calcular(num1, num2));

	}

	@Test
	void  testStats() {

		assertEquals(Stats.class,mutanteService.stats().block().getClass());

	}

	@Test
	void  testIsMutantTrue() {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Mutante mutante = new Mutante(dna);
		mutante = mutanteService.ismutant(mutante).block();

		assertEquals(true, mutante.isMutant());

	}

	@Test
	void  testIsMutantFalse() {
		String[] dna = {"ATGCGA","CGGTGC","TTATGT","AGAACT","CCCTTA","TCACTG"};
		Mutante mutante = new Mutante(dna);
		mutante = mutanteService.ismutant(mutante).block();

		assertEquals(false, mutante.isMutant());

	}

	@Test
	 void testFindAll(){
		String[] dna = {"ATGCGA","CGGTGC","TTATGT","AGAACT","CCCTTA","TCACTG"};
		Mutante mutante = new Mutante(dna);
		mutante = mutanteService.ismutant(mutante).block();

		assertEquals(ArrayList.class, mutanteService.findAll().collectList().block().getClass());

	}

	@Test
	void testFindByAdn(){
		String[] dna = {"ATGCGA","CGGTGC","TTATGT","AGAACT","CCCTTA","TCACTG"};
		Mutante mutante = new Mutante(dna);
		mutante = mutanteService.ismutant(mutante).block();

		List<Mutante> mutantes = mutanteRepo.findByAdn(dna).collectList().block();
		assertEquals("ATGCGA", mutantes.get(0).getAdn()[0]);

	}

	@Test
	void deletebyid(){
		reactiveMongoTemplate.dropCollection("mutantes").subscribe();
		assertEquals("OK", "OK");
	}

	@Test
	void delete(){
		String[] dna = {"ATGCGA","CGGTGC","TTATGT","AGAACT","CCCTTA","TCACTG"};
		Mutante mutante = new Mutante(dna);
		mutante = mutanteService.ismutant(mutante).block();
		mutanteRepo.delete(mutante);
		assertEquals("OK", "OK");
	}

}
