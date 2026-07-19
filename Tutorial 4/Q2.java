public class Q2 {

    public static void firstTwo(String string){
        if(string.length() >=2 ){
            for(int i = 0; i < 2; i++){
                System.out.print(string.charAt(i));
            }
            System.out.println();
        } else {
            System.out.println(string);
        }
        
    }

    public static void main(String[] args){
        String hello = "Hello";
        String meep = "meep";
        String x = "X";
        String empty = "";
        firstTwo(hello);
        firstTwo(meep);
        firstTwo(x);
        firstTwo(empty);
        firstTwo("ab");

    }
}
