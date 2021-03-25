import java.util.Collections;

public class GeneticAlgorithmImplementationC extends GeneticAlgorithm {

    @Override
    int calculateFitnessOfChromosome(Chromosome chromosome) {
        int fitness = Collections.frequency(chromosome.getGenes(), '1');
        chromosome.setFitness(fitness);
        if(fitness ==  0){
            int deceptiveFitness = 2*chromosome.getGenes().size();
            chromosome.setFitness(deceptiveFitness);
            return deceptiveFitness;
        }
        return fitness;
    }

}
