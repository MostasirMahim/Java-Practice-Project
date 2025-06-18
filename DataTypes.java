import java.io.*;
import java.util.*;

public class DataTypes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long y = sc.nextLong();
        
        for (int i = 1;i<=y;i++){
        long x;
        String inputStr;
            
    if(sc.hasNextLong()){
     x = sc.nextLong();
        if ( Long.MIN_VALUE <= x && x <= Long.MAX_VALUE) {

            System.out.println(x + " can be fitted in:");
            if (Integer.MIN_VALUE <= x && x <= Integer.MAX_VALUE) {
                if (Short.MIN_VALUE <= x && x <=Short.MAX_VALUE) {
                    if (Byte.MIN_VALUE <= x && x <=Byte.MAX_VALUE) {
           
                        System.out.println("* byte");
                    }

                    System.out.println("* short ");
                }
                System.out.println("* int");
            }
            System.out.println("* long");
            
        } 
    }
    else {
        inputStr = sc.next();
            System.out.println(inputStr+ " can't be fitted anywhere.");
        }
        

        } 
    }
}
