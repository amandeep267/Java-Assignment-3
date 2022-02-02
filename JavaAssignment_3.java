import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class JavaAssignment_3 {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of steps");
        int numberOfStep=sc.nextInt();
        System.out.println("Enter host");
        sc.nextLine();

        String hostToPing=sc.nextLine();
        try
        {
          hostThePing(numberOfStep,hostToPing);
        }
        catch(IOException e)
        {
            System.out.println("Unable to ping");
        }

    }
    static void hostThePing (int numberOfSteps,String host) throws IOException {

        Process process = Runtime.getRuntime().exec("ping "+host);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String outputLine =  input.readLine();
        ArrayList<Double> times = new ArrayList<>();

        while (outputLine!=null && numberOfSteps>=0)
        {
            System.out.println(outputLine);
            if(outputLine.contains("time"))
            {
                String getTime = outputLine.substring(outputLine.indexOf("time")+5,outputLine.indexOf("ms"));
                double time = Double.parseDouble(getTime);
                times.add(time);
            }
            outputLine = input.readLine();
            numberOfSteps--;
        }

        calculateMedianOfTime(times);

    }
    static void calculateMedianOfTime(ArrayList<Double>time)
    {
        Collections.sort(time);
        if(time.size()%2!=0)
            System.out.println("Median is : "+time.get((time.size()-1)/2));
        else
            System.out.println("Median is : "+((time.get((time.size()-2)/2)+time.get(time.size()/2)))/2);

    }
}
