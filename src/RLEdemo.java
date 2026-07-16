import java.util.Scanner;

public class RLEdemo{
    public static void main(String[] args) {
        System.out.println(compress(getInput()));
    }

    private static String compress(String text){
        String compressed = "";
        for(int i=0; i<text.length(); i++){
            int count = 1;
            while(i+1<text.length() && text.charAt(i) == text.charAt(i+1)){
                count++;
                i++;
            }
            compressed += String.valueOf(count) + text.charAt(i);
        }
        return compressed.toString();
    }

    private static String getInput(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the input. ");
        return s.nextLine();
    }
}