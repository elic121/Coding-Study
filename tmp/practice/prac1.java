package practice;
import java.util.*;
import java.io.*;

public class prac1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] targetArray = new int[10];
        for (int i=0; i<10; i++){
            targetArray[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(targetArray));
        br.close();   
    }
}
