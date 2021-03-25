import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Item i1 = new Item(78, 18);
    public static Item i2 = new Item(35,9);
    public static Item i3 = new Item(89,23);
    public static Item i4 = new Item(36,20);
    public static Item i5 = new Item(94,59);
    public static Item i6 = new Item(75,61);
    public static Item i7 = new Item(74,70);
    public static Item i8 = new Item(79,75);
    public static Item i9 = new Item(80,76);
    public static Item i10 = new Item(16,30);

    public static ArrayList<Item> allItems = new ArrayList<>(List.of(i1,i2,i3,i4,i5,i6,i7,i8,i9,i10));

    public static void main(String[] args) {

        //A
        //(i)
        //GeneticAlgorithmImplementationA questionAOne = new GeneticAlgorithmImplementationA();
        //questionAOne.algorithm(20, 500, 0.001f);

        //(ii)
        //GeneticAlgorithmImplementationB questionATwo = new GeneticAlgorithmImplementationB();
        //questionATwo.algorithm(20, 500, 0.001f);

        //GeneticAlgorithmImplementationC questionAThree = new GeneticAlgorithmImplementationC();
        //questionAThree.algorithm(20, 500, 0.001f, 20);


        GeneticAlgorithmImplementationD questionBOne = new GeneticAlgorithmImplementationD(103);
        questionBOne.algorithm(1000, 500, 0.01f, 10);

        GeneticAlgorithmImplementationD questionBTwo = new GeneticAlgorithmImplementationD(156);
        questionBTwo.algorithm(1000, 500, 0.01f, 10);


    }
}
