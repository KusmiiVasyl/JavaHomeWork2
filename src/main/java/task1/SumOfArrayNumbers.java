package task1;


public class SumOfArrayNumbers extends Thread {
    private int sum;
    private int[] arrayNumbers;

    public SumOfArrayNumbers(int[] arr) {
        this.arrayNumbers = arr;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        for (int arrayNumber : arrayNumbers) {
            sum += arrayNumber;
        }
    }
}
