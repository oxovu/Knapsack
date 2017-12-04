package knapsack;

import java.util.Arrays;
import java.util.Random;

import static knapsack.KnapsackTest1.MAX_WEIGHT;
import static knapsack.KnapsackTest1.allStaff;

public class Gene {

    boolean chromosome[] = new boolean[allStaff.length];

    Random random = new Random();

    public void createGene() {
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = random.nextBoolean();
        }
    }

    public int weighSum(){
        int weighSum = 0;
        for (int i = 0; i < chromosome.length; i++) {
            if (this.chromosome[i]) weighSum += allStaff[i].getWeigh();
        }
        return weighSum;
    }

    public boolean checkFullness() {
        if (weighSum() > MAX_WEIGHT) return false;
        return true;
    }

    public int profitFunction() {
        int profit = 0;
        for (int i = 0; i < chromosome.length; i++) {
            if (this.chromosome[i]) profit += allStaff[i].getValue();
        }
        return profit;
    }

    public double chance() {
        double maxProfit = 0;
        for (int i = 0; i < chromosome.length; i++) {
            maxProfit += allStaff[i].getValue();
        }
        double chance = profitFunction() / maxProfit;
        return chance;
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gene gene = (Gene) o;

        if (!Arrays.equals(chromosome, gene.chromosome)) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(chromosome);
        result = 31 * result + (random != null ? random.hashCode() : 0);
        return result;
    }
}
