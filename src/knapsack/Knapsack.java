package knapsack;

import java.util.Random;

import static knapsack.KnapsackTest1.*;

public class Knapsack {


    Gene population[] = new Gene[POPULATION_NUMBER];

    public void createPopulation() {
        for (int i = 0; i < POPULATION_NUMBER; i++) {
            Gene gene = new Gene();
            gene.createGene();
            int weighSum = 0;
            for (int j = 0; j < gene.chromosome.length; j++) {
                if (gene.chromosome[j]) weighSum += allStaff[j].getWeigh();
            }
            if (weighSum <= MAX_WEIGHT) {
                population[i] = gene;
            } else i--;
        }
    }

    public Gene crossing(Gene gene1, Gene gene2) {
        Random random = new Random();
        Gene newGene = new Gene();
        for (int i = 0; i < gene1.chromosome.length; i++) {
            if (gene1.chromosome[i] == gene2.chromosome[i]) newGene.chromosome[i] = gene1.chromosome[i];
            else newGene.chromosome[i] = random.nextBoolean();
        }
        return newGene;
    }

    public Gene mutation(Gene gene) {
        Random random = new Random();
        gene.chromosome[random.nextInt(allStaff.length)] = !gene.chromosome[random.nextInt(allStaff.length)];
        return gene;
    }

    public Gene findBestGene() {
        double bestChance = 0;
        Gene bestGene = null;
        for (int i = 0; i < POPULATION_NUMBER; i++) {
            if (this.population[i].chance() > bestChance && this.population[i].checkFullness()) {
                bestChance = this.population[i].chance();
                bestGene = this.population[i];
            }
        }
        return bestGene;
    }

    public Gene findSolution() {
        Random random = new Random();
        this.createPopulation();
        Gene descendants[] = new Gene[CROSSING_NUMBER];
        double bestChance = 0;
        int counter = 0;
        while (bestChance < 1 && counter < 10) {
            boolean changed[] = new boolean[POPULATION_NUMBER];
            boolean used[] = new boolean[CROSSING_NUMBER];
            for (int i = 0; i < CROSSING_NUMBER; i++) {
                descendants[i] = this.crossing(this.population[random.nextInt(POPULATION_NUMBER)], this.population[random.nextInt(POPULATION_NUMBER)]);
            }
            Gene mutaGene = this.mutation(this.population[random.nextInt(POPULATION_NUMBER)]);
            for (int i = 0; i < POPULATION_NUMBER; i++) {
                for (int j = 0; j < CROSSING_NUMBER; j++) {
                    if (descendants[j].chance() > this.population[i].chance() && descendants[j].checkFullness() && !changed[i] && !used[j]) {
                        this.population[i] = descendants[j];
                        changed[i] = true;
                        used[j] = true;
                    }
                    if (this.population[i].chance() > bestChance) bestChance = this.population[i].chance();
                }
            }
            int localIndex = random.nextInt(POPULATION_NUMBER);
            if (mutaGene.chance() > this.population[localIndex].chance() && mutaGene.checkFullness() && !changed[localIndex]) {
                this.population[localIndex] = mutaGene;
                changed[localIndex] = true;
                if (this.population[localIndex].chance() > bestChance)
                    bestChance = this.population[localIndex].chance();
            }
            counter++;
        }
        System.out.println(this.findBestGene());
        return this.findBestGene();
    }


    public static void main(String[] args) {
    }
}
