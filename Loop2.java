import java.io.*;
import java.util.*;

public class Loop2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         int q = sc.nextInt(); 
        for (int e =1;e<=q;e++) {          
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();
        
        int count = 1;
        int s = a;
         for (int j= 1;count<= n;j*=2) {
            s = s+(j*b);
            count++;  
            System.out.print(s+" "); 
            
         } 
         System.out.println();
        }
    }
}
