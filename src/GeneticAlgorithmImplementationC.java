import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    @Override
    public void algorithm(int generationSize, int numGenerations, float mutationChancePercentage, int geneNum){
        ArrayList<Chromosome> generationN = populationGeneration(generationSize, geneNum);
        generationN.remove(0);
        generationN.add(new Chromosome(new ArrayList<>(List.of('0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'))));
        ArrayList<Chromosome> generationN1 = new ArrayList<>();
        for(int i =0; i < numGenerations; i++) {
            int averageFitness = averageFitnessOfPopulation(generationN);
            int totalFitness = totalFitness(generationN);
            System.out.println("Generation "+i+ " has average fitness of: " + averageFitness);
            while (generationN1.size() != generationN.size()) {
                //Do selection to find two chromosomes
                Chromosome parent1 = rouletteWheelSelectOne(generationN, totalFitness);
                Chromosome parent2 = rouletteWheelSelectOne(generationN, totalFitness);
                //One point crossover to gen new children
                ArrayList<Chromosome> children = onePointCrossOver(new ArrayList<>(List.of(parent1, parent2)), ThreadLocalRandom.current().nextInt(0, parent1.getGenes().size()));
                //Try mutation on each child
                children.set(0, mutation(children.get(0), mutationChancePercentage));
                children.set(1,mutation(children.get(1), mutationChancePercentage));
                //add children to next generation
                generationN1.addAll(children);
            }
            generationN = new ArrayList<>(generationN1);
            generationN1.clear();
        }
    }
}
