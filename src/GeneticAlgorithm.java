import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.round;

abstract class GeneticAlgorithm {

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

    abstract int calculateFitnessOfChromosome(Chromosome chromosome);

    public int averageFitnessOfPopulation(ArrayList<Chromosome> chromosomes){ return ((totalFitness(chromosomes))/chromosomes.size()); }

    public int totalFitness(ArrayList<Chromosome> chromosomes){
        int total = 0;
        for (Chromosome c : chromosomes) {
            total += calculateFitnessOfChromosome(c);
        }
        return total;
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

    //Mutation performed after crossover, mutation can occur at each bit position with some probability.
    public Chromosome mutation(Chromosome chromosomeToMutate, float chanceToMutate){
        Random r = new Random();
        //loop through all characters, check if chance lucky, if yes then mutate else don't
        for(int i = 0; i < chromosomeToMutate.getGenes().size(); i++){
            float chance = r.nextFloat();
            if(chance <= chanceToMutate){
                Character charToMutate = chromosomeToMutate.getGenes().get(i);
                if(charToMutate == '0'){
                    charToMutate = '1';
                }else{
                    charToMutate = '0';
                }
                chromosomeToMutate.getGenes().set(i, charToMutate);
            }
        }
        return chromosomeToMutate;
    }

    public Chromosome rouletteWheelSelectOne(ArrayList<Chromosome> chromosomes, int fitnessSum){
        //Generate number between 0 and fitnessSum-1
        int p = ThreadLocalRandom.current().nextInt(0, fitnessSum);
        //Pick random chromosomes from population until P > fitnessSum
        Chromosome chosenChromosome = null;
        while(p < fitnessSum){
            int chosenIndex = ThreadLocalRandom.current().nextInt(0, chromosomes.size());
            chosenChromosome = chromosomes.get(chosenIndex);
            p += chosenChromosome.getFitness();
        }

        return chosenChromosome;
    }

    public void algorithm(int generationSize, int numGenerations, float mutationChancePercentage){
        ArrayList<Chromosome> generationN = populationGeneration(generationSize);
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
