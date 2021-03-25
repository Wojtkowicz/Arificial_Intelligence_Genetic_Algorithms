import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.round;

public class GeneticAlgorithmImplementationD extends GeneticAlgorithm{

    private final int maxSize;

    public GeneticAlgorithmImplementationD(int maxSize) {
        this.maxSize = maxSize;
    }

    public Chromosome chromosomeGeneration(int num) {
        ArrayList<Character> genes = new ArrayList<>();
        do{
            genes = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                genes.add((char)round((Math.random() * 1) + 48));
            }
        }while (chromosomeKnapsack(genes).getTotalWeight() > maxSize);

        return new Chromosome(genes);
    }

    @Override
    int calculateFitnessOfChromosome(Chromosome chromosome) {
        return 0;
    }

    public Knapsack chromosomeKnapsack(ArrayList<Character> genes){
        Knapsack k = new Knapsack(maxSize);
        for(int i = 0; i < genes.size(); i++){
            if(genes.get(i).equals('1')){
                k.addItem(Main.allItems.get(i));
            }
        }
            return k;
        }

    public ArrayList<Chromosome> populationGeneration(int size, int geneNum){
        ArrayList<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            chromosomes.add(chromosomeGeneration(geneNum));
        }
        return chromosomes;
    }

    public int averageFitnessOfPopulation(ArrayList<Chromosome> chromosomes){ return ((totalFitness(chromosomes))/chromosomes.size()); }

    public int totalFitness(ArrayList<Chromosome> chromosomes){
        int total = 0;
        for (Chromosome c : chromosomes) {
                Knapsack k = chromosomeKnapsack(c.getGenes());
                total += k.getFitness();
            }

        return total;
    }

    public void algorithm(int generationSize, int numGenerations, float mutationChancePercentage, int geneNum){
        ArrayList<Chromosome> generationN = populationGeneration(generationSize, geneNum);
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
