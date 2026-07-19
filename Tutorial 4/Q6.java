import java.lang.Math;

public class Q6 {

    public static int[] lotteryNumbers(){
        int numbers[] = new int[7];

        for(int i = 0; i < 5; i++){
            numbers[i] = (int)(50 * Math.random());
        }

        for(int i = 5; i < 7; i++){
            numbers[i] = (int)(11 * Math.random());
        }

        return numbers;
    }


    public static void main(String[] args) {
        int lottoNumbers[] = lotteryNumbers();
        for(int i = 0; i < lottoNumbers.length; i++ ){
            System.out.print(lottoNumbers[i] + " ");
        }
        System.out.println();
    }
    
}
