import java.util.ArrayList;

public class Knapsack {

    private final int maxWeight;

    private ArrayList<Item> items;

    public Knapsack(int weight) {
        items = new ArrayList<>();
        maxWeight = weight;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getTotalValue() {
        int total = 0;

        for (Item i : items) {
            total += i.getValue();
        }
        return total;
    }

    public int getTotalWeight() {
        int total = 0;

        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

    public void addItem(Item i){
        items.add(i);
    }

    public int getFitness(){
        int total = 0;
        for (Item c : items) {
            total += c.getFitness();
        }
        return total;
    }

}
