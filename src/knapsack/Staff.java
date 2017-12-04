package knapsack;

public class Staff {

    private int weigh;
    private int value;

    public Staff(int weigh, int value) {
        this.weigh = weigh;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

   public int getWeigh() {
        return weigh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (weigh != staff.weigh) return false;
        return value == staff.value;

    }

    @Override
    public int hashCode() {
        int result = weigh;
        result = 31 * result + value;
        return result;
    }
}
