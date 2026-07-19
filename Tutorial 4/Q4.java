public class Q4 {

    public static void nTwice(String string, int n){
        System.out.println(string.substring(0, n) + string.substring(string.length()- n, string.length()));
    }

    public static void main(String[] args) {
        nTwice("Hello", 2);
        nTwice("Chocolate", 3);
        nTwice("Chocolate", 1);
    }
    
}
