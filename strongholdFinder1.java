import java.awt.Graphics;
import java.awt.Point;
import java.util.*;
import java.lang.Math;


public class strongholdFinder1 {

    double[] inputCoords = new double[2];
    double[] splitLines = new double[6];
    Scanner input = new Scanner(System.in);
    ArrayList<Double> xyAngle = new ArrayList<Double>();

    
    public static void main(String[] args){
        strongholdFinder1 stronghold = new strongholdFinder1();
        String[] pastedInput = stronghold.getInputs();
        ArrayList<Double> coordInfo = stronghold.splitInputs(pastedInput);
        double[] finalStrongholdCoordinates = stronghold.getCoords(coordInfo);
        System.out.printf("The Coordinates of the Stronghold are %.3f, %.3f\n", finalStrongholdCoordinates[0], finalStrongholdCoordinates[1]);
        stronghold.close();
    }

    public ArrayList<Double> splitInputs(String[] copiedCoords){
        ArrayList<Double> xyAndAngle = new ArrayList<Double>();
        for (int i = 0; i < 2; i++){
            String[] components = copiedCoords[i].split(" ");
            xyAndAngle.add(Double.parseDouble(components[6]));
            xyAndAngle.add(Double.parseDouble(components[8]));
            xyAndAngle.add(Double.parseDouble(components[9]));
        }
        this.xyAngle = xyAndAngle;
        return xyAndAngle;
    }

    public double[] getCoords(ArrayList<Double> allCoords){
        double[] coords = new double[2];

        double m0 = Math.tan(Math.toRadians(xyAngle.get(2)+90.0));
		double m1 = Math.tan(Math.toRadians(xyAngle.get(5)+90.0));

		double b0 = xyAngle.get(1) - ((xyAngle.get(0))*(Math.tan(Math.toRadians(xyAngle.get(2)+90.0))));
        double b1 = xyAngle.get(4) - ((xyAngle.get(3))*(Math.tan(Math.toRadians(xyAngle.get(5)+90.0))));

        double finalX = (b1 - b0) / (m0 - m1);
        double finalY = m0 * finalX + b0;
        
        coords[0] = finalX;
        coords[1] = finalY;
        
        return coords;
    }

    public String[] getInputs(){
        String[] copiedLines = new String[2];
        for (int turns = 0; turns < 2; turns++){
            System.out.println("Please paste the coordinates you copied from Minecraft with F3+C");
            copiedLines[turns] = input.nextLine();
        }
        return copiedLines;
    }

    public void close(){
        input.nextLine();
		input.close();
    }
}
