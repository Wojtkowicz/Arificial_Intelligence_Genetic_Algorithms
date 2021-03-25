import java.util.ArrayList;

import static java.lang.Math.round;

public class GeneticAlgorithmImplementationB extends GeneticAlgorithm{
    private ArrayList<Character> genes;

    @Override
    int calculateFitnessOfChromosome(Chromosome chromosome) {
        int fitness = 0;
        for(int i = 0; i < 20; i++){
            if(chromosome.getGenes().get(i).equals(genes.get(i))){
                fitness++;
            }
        }
        chromosome.setFitness(fitness);
        return fitness;
    }

    public  GeneticAlgorithmImplementationB(){
        genes = new ArrayList<>();
        createTargetGenes();
    }

    private void createTargetGenes(){
        //Random gene to match
        for (int i = 0; i < 20; i++) {
            genes.add((char)round((Math.random() * 1) + 48));
        }
    }
}
