import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        GeneticAlgorithm g = new GeneticAlgorithm();

        ArrayList<Chromosome> population =  g.populationGeneration(10);

        System.out.println(population.get(0).getGenes());
        System.out.println(population.get(1).getGenes());
        System.out.println("------------------------");

        ArrayList<Chromosome> result =  g.onePointCrossOver(new ArrayList<>(List.of(population.get(0), population.get(1))), 2);

        System.out.println(result.get(0).getGenes());
        System.out.println(result.get(1).getGenes());
        System.out.println("------------------------");
        System.out.println(population.get(0).getGenes());
        System.out.println(population.get(1).getGenes());
    }
}
