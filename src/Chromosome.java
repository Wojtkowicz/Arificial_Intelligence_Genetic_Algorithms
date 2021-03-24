import java.util.ArrayList;
public class Chromosome {

    private ArrayList<Character> genes;

    private int fitness;

    public ArrayList<Character> getGenes() {
        return genes;
    }

    public Chromosome(ArrayList<Character> genes){
        this.genes = genes;
    }

    public void setFitness(int fitness) { this.fitness = fitness; }

    public int getFitness(){ return this.fitness; }
}
