public class Q5 {
    static int count = 0;
    static int i = 0;
    public static int countX(String string){
        if(i < string.length()){
            if(string.charAt(i) == 'x'){
                count++;
            };
            i++;
            return countX(string);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countX("xxhixx"));
        i = 0;
        count = 0;
        System.out.println(countX("xhixhix"));
        i = 0;
        count = 0;
        System.out.println(countX("hi"));
    }
}
