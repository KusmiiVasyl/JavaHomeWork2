package task1;

public class RandomArray extends Thread {
    private int[] arrayNumbers;

    public RandomArray(int arraySize) {
        this.arrayNumbers = new int[arraySize];
    }

    public int[] getArrayNumbers() { return arrayNumbers; }

    @Override
    public void run() {
        for (int i = 0; i < arrayNumbers.length; i++) {
            arrayNumbers[i] = (int) (Math.random() * 100);
        }
    }
}
