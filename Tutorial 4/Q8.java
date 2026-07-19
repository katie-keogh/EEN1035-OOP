import java.util.Arrays;

public class Q8 {
    private double[] anArray;
    private int arrayLen;
    
    public Q8(double[] anArray) {
        this.anArray = anArray;
        this.arrayLen = anArray.length;
    }

    public double max(){
        double max = 0;
        for(int i = 0; i < arrayLen; i++){
            if(anArray[i] > max){
                max = anArray[i];
            }
        }
        return max;
    }

    public double min(){
        double min = anArray[0];
        for(int i = 0; i < arrayLen; i++){
            if(anArray[i] < min){
                min = anArray[i];
            }
        }
        return min;
    }

    public double average(){
        double sum = 0;
        for(int i = 0; i < arrayLen; i++){
            sum = sum + anArray[i];
        }

        if(sum > 0){
            return sum / arrayLen;
        }
        return sum;

    }

    public void display() {
        System.out.print("Array [");
        for(int i = 0; i < arrayLen; i++){
            System.out.print(anArray[i] + " ");
        }
        System.out.println("]");
    }

    public void sort(){
        Arrays.sort(anArray);
    }

    public double median(){
        Arrays.sort(anArray);

        if(arrayLen % 2 == 1) {
            return anArray[arrayLen / 2];
        }

        double a = anArray[(arrayLen+1) / 2];
        double b = anArray[(arrayLen) / 2];

        return (a + b)/2;
        
    }

    public static void main(String[] args) {
        double[] anArray = {1, 2, 8, 9, 1, 4, 15, 8, 9, 10, 2, 2, 12, 14, 19, 20};
        Q8 s = new Q8(anArray);
        s.display();
        System.out.println("The average is: " + s.average()); 
        System.out.println("The max is :" + s.max()); 
        System.out.println("The min is :" + s.min());  
        s.sort();
        System.out.print("The sorted array: ");          
        s.display();
        System.out.println("The median is: " + s.median());


    }
    
}
