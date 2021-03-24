import java.util.Collections;

public class GeneticAlgorithmImplementationA extends GeneticAlgorithm{
    @Override
    int calculateFitnessOfChromosome(Chromosome chromosome) {
        int fitness = Collections.frequency(chromosome.getGenes(), '1');
        chromosome.setFitness(fitness);
        return fitness;
    }
}
