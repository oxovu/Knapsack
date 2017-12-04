package knapsack;

import junit.framework.TestCase;
import org.junit.Assert;


public class KnapsackTest1 extends TestCase {

    Knapsack knapsack = new Knapsack();

    public static final int MAX_WEIGHT = 50;
    public static final int POPULATION_NUMBER = 10;
    public static final int CROSSING_NUMBER = 5;

    public static Staff allStaff[] = new Staff[]{
            new Staff(10, 44), new Staff(70, 80), new Staff(9, 90), new Staff(16, 22), new Staff(99, 9), new Staff(4, 8)
    };

    boolean chromosome[] = new boolean[]{true, false, true, true, false, true};
    Gene answer = new Gene();


    public void testFindSolution() {
        answer.chromosome = chromosome;
        Assert.assertEquals(answer, knapsack.findSolution());
    }

}