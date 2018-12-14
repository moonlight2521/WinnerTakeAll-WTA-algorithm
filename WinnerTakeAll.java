import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.io.PrintWriter;


public class WinnerTakeAll{
    private static int row = 151;

    private static double[][] neuron = new double[2][2];

    private static String[][] data1 = new String[151][2];
    private static double[][] normalData1 = new double[150][2];

    private static double[] net = new double[2];
    private static double[] w = new double[2];

    private static double distant = 0.0;
    private static double alpha = 0.3;

    private static Random r = new Random();

    private static double neuronX = 0.0;
    private static double neuronY = 0.0;
    private static double neuronX1 = 0.0;
    private static double neuronY1 = 0.0;
    private static double neuronX2 = 0.0;
    private static double neuronY2 = 0.0;

    public static void readFile(){
        try{
            File file = new File("Ex1_data.txt");
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                for(int i=0; i<row ; i++){
                    String [] line = fileReader.nextLine().split(",");
                    for(int j=0; j< line.length; j++){
                        data1[i][j] = line[j];
                    }
                }
            }
            fileReader.close();
        } catch(IOException e){
            System.out.println( e.getMessage());
        }
    }

    public static void normalize( String[][] array){
        double maxX = -10.0;
        double minX = 10.0;
        double middleX = 0.0;
        double maxY = -10.0;
        double minY = 10.0;
        double middleY = 0.0;
        
        for(int i=1; i< row; i++){
            if(Double.parseDouble(array[i][0]) > maxX){
                maxX = Double.parseDouble(array[i][0]);
            }
            if(Double.parseDouble( array[i][0]) < minX){
                minX = Double.parseDouble(array[i][0]);
            }
            if(Double.parseDouble( array[i][1]) > maxY){
                maxY = Double.parseDouble(array[i][1]);
            }
            if(Double.parseDouble( array[i][1]) < minY){
                minY = Double.parseDouble(array[i][1]);
            }
        }
        middleX = maxX - minX;
        middleY = maxY - minY;
        for(int i = 1; i<151; i++){
            normalData1[i - 1][0] = (Double.parseDouble(array[i][0]) - minX)/middleX;
            normalData1[i - 1][1] = (Double.parseDouble(array[i][1]) - minY)/middleY;
        }
        //at random:
        // neuronX = r.nextGaussian()* 0.5 + 0.5;
        // neuronY = r.nextGaussian()* 0.5  + 0.5; 
        // neuronX1 = r.nextGaussian()* 0.5  + 0.5; 
        // neuronY1 = r.nextGaussian()* 0.5  + 0.5; 
        //assigned:
        neuronX = r.nextGaussian()* 0.2 + 0.6;
        neuronY = r.nextGaussian()* 0.2 + 0.6; 
        neuronX1 = r.nextGaussian()* 0.2 + 0.6; 
        neuronY1 = r.nextGaussian()* 0.2 + 0.6; 
        neuronX2 = r.nextGaussian()* 0.2 + 0.6; 
        neuronY2 = r.nextGaussian()* 0.2 + 0.6; 

        neuron[0][0] = neuronX;
        neuron[0][1] = neuronY;
        neuron[1][0] = neuronX1;
        neuron[1][1] = neuronY1;
        // neuron[2][0] = neuronX2;
        // neuron[2][1] = neuronY2;

        System.out.println(neuronX);
        System.out.println(neuronY);
        System.out.println(neuronX1);
        System.out.println(neuronY1);
        try{
            PrintWriter writeInitW = new PrintWriter("initWeight.txt");
            for(int i = 0; i<2; i++)
            {
                writeInitW.print(neuron[i][0] + ", " + neuron[i][1]);
                writeInitW.println();
            }
            writeInitW.close();
        } catch(IOException e){
            e.getMessage();
        }
    }
    public static void wta(){
        for(int j = 0; j< 10; j++){

        for(int i=0; i<normalData1.length; i++){
            net[0] = (normalData1[i][0] * neuronX) + (normalData1[i][1] * neuronY);
            net[1] = (normalData1[i][0] * neuronX1) + (normalData1[i][1] * neuronY1);
    
            if(Math.abs(1 - net[0]) < Math.abs(1 - net[1])){
                w[0] = neuronX + (alpha * normalData1[i][0]);
                w[1] = neuronX + (alpha * normalData1[i][1]);
                distant = Math.abs( Math.sqrt( Math.pow(w[0], 2) + Math.pow(w[1], 2)));
                // normalData1[i][0] = w[0] / distant;
                // normalData1[i][1] = w[1] / distant;
                neuron[0][0] = w[0] / distant;
                neuron[0][1] = w[1] / distant;
            }else{
                w[0] = neuronX1 + (alpha * normalData1[i][0]);
                w[1] = neuronX1 + (alpha * normalData1[i][1]);
                distant = Math.abs( Math.sqrt( Math.pow(w[0], 2) + Math.pow(w[1], 2)));
                // normalData1[i][0] = w[0] / distant;
                // normalData1[i][1] = w[1] / distant;
                neuron[1][0] = w[0] / distant;
                neuron[1][1] = w[1] / distant;
            }
        }
    }
        try{
            PrintWriter writeFinalW = new PrintWriter("finalWeight.txt");
            for(int i = 0; i<2; i++)
            {
                writeFinalW.print(neuron[i][0] + ", " + neuron[i][1]);
                writeFinalW.println();
            }
            writeFinalW.close();
        } catch(IOException e){
            e.getMessage();
        }
    }


    private static void writeToFile(double[][] array) {
        try{
            PrintWriter writer = new PrintWriter("WTA.txt", "UTF-8");
            for(int i = 0; i<150; i++)
            {
                writer.print(array[i][0] + ", " + array[i][1]);
                writer.println();
            }
            writer.close();
        }catch(IOException e){
            e.getMessage();
        }
    }
    private static void extracted(double[][] array) {
            for(int i = 0; i<150; i++)
            {
                for(int j = 0; j<2; j++)
                {
                    System.out.print(array[i][j] + "  ");
                    // writer.print(array[i][j] + ", ");
                }
                System.out.println();
                // writer.println();
            }
    }
    public static void main(String[] args){
        readFile();
        normalize(data1);
        wta();
        // extracted(normalData1);
        writeToFile(normalData1);

    }

}