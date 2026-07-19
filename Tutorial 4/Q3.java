public class Q3 {
    
    public static void hasBad(String string){
        if (string.charAt(0) == 'b') {
            System.out.println(string.charAt(0 )== 'b' && string.charAt(1) == 'a' && string.charAt(2) == 'd');
        } else {
            System.out.println(string.charAt(1)== 'b' && string.charAt(2) == 'a' && string.charAt(3) == 'd');
        }
        
    }

    public static void main(String[] args){
        hasBad("badxx");
        hasBad("xbadxx");
        hasBad("xxbadxx");
    }
}
