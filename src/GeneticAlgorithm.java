import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.round;

public class GeneticAlgorithm {

    public Chromosome chromosomeGeneration() {
        ArrayList<Character> genes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            genes.add((char)round((Math.random() * 1) + 48));
        }
        return new Chromosome(genes);
    }

    public ArrayList<Chromosome> populationGeneration(int size){
        ArrayList<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            chromosomes.add(chromosomeGeneration());
        }
        return chromosomes;
    }

    private int calculateFitnessOfChromosome(Chromosome chromosome){
        return Collections.frequency(chromosome.getGenes(), 1);
    }

    public int averageFitnessOfPopulation(ArrayList<Chromosome> chromosomes){
        int total = 0;
        for (Chromosome c : chromosomes) {
            total += calculateFitnessOfChromosome(c);
        }
        return total/chromosomes.size();
    }

    public ArrayList<Chromosome> onePointCrossOver(ArrayList<Chromosome> chromosomes, int crossOverPoint){
        ArrayList<Character> parent1 = new ArrayList<>(chromosomes.get(0).getGenes());
        ArrayList<Character> parent2 = new ArrayList<>(chromosomes.get(1).getGenes());
        ArrayList<Character> parent1Head = new ArrayList<>();
        ArrayList<Character> parent2Head = new ArrayList<>();
        ArrayList<Character> parent1Tail = new ArrayList<>();
        ArrayList<Character> parent2Tail = new ArrayList<>();

        for(int i = 0; i < parent1.size(); i++){
            if(crossOverPoint > i){
                parent1Head.add(parent1.get(i));
                parent2Head.add(parent2.get(i));
            }
            else{
                parent1Tail.add(parent1.get(i));
                parent2Tail.add(parent2.get(i));
            }
        }

        parent1Head.addAll(parent2Tail);
        parent2Head.addAll(parent1Tail);

        return new ArrayList<>(List.of(new Chromosome(parent1Head), new Chromosome(parent2Head)));
    }
}
