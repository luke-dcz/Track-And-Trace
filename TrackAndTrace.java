import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TrackAndTrace {
    public static Scanner kbd = new Scanner(System.in);
    
    public static boolean overlap(int start1, int end1, int start2, int end2) {
    
        boolean overlap = true;
        
        if (start1 < 7) {
            start1 += 24;
        }
        if (end1 < 7) {
            end1 += 24;
        }
        if (start2 < 7) {
            start2 += 24; 
        }
        if (end2 < 7) {
            end2 += 24;
        }

        if ((end1 < start2 || end1 == start2)||(end1 > start2&&start1>=end2)) {
            return overlap = false;
        } 
        else{
            return overlap;
        }
    }  
    
    public static void getOverlaps(int start, int end) {
        System.out.println("Enter the number of customers: ");
        int custNum = kbd.nextInt();
        
        int totTest = 0;
        
        for (int i = 0; i < custNum; i++) {
            
            System.out.println("Enter the customer's name: ");
            String n = kbd.next();
            
            System.out.println("Enter the arrival time: ");
            int at = kbd.nextInt();
            
            System.out.println("Enter the departure time: ");
            int dt = kbd.nextInt();
           
            if (overlap(start, end, at, dt) == true) {
                System.out.println(n + " needs a test.");
                totTest += 1;
            } else {
                System.out.println( n + " does not need a test.");
            }
        }
        System.out.println("There are " + totTest + " tests needed.");
    }
    public static void getOverlapsWithFile(int start, int end, String in_file, String in_file2) {
        int totTest = 0;
        
        try {
        File f = new File(in_file);
        Scanner reader = new Scanner(f);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] array = line.split(" ", -1);
    
            if (overlap(start, end, Integer.parseInt(array[1]), Integer.parseInt(array[2])) == true) {
                System.out.println(array[0] + " needs a test.");
                totTest += 1;
            } 
        }
        }catch (FileNotFoundException e) {
          System.err.println("WARNING: " + in_file + " not found.");

        }
        
        try{
        File f2 = new File(in_file2);
        Scanner reader2 = new Scanner(f2);
        while (reader2.hasNextLine()) {
            String line2 = reader2.nextLine();
            String[] array2 = line2.split(" ", -1);
    
            if (overlap(start, end, Integer.parseInt(array2[1]), Integer.parseInt(array2[2])) == true) {
                System.out.println(array2[0] + " needs a test.");
                totTest += 1;
            } 
        }
        }catch (FileNotFoundException e) {
        }
        System.out.println("There are " + totTest + " tests needed.");
        
    }
    
    public static void main(String[] args){
    
        System.out.println("Enter the start time: ");
        int start = kbd.nextInt();
        
        System.out.println("Enter the end time: ");
        int end = kbd.nextInt();
       
        String in_file2 = "nothing";
       
        if (args.length == 2) {
            in_file2 = args[1];
        }
        
        if(args.length == 0){
            getOverlaps(start, end);
        }else {
            String in_file = args[0];
            getOverlapsWithFile(start, end, in_file, in_file2);
        }
      
    }
}
