import java.util.ArrayList;

public class Search{


    public static boolean linearSearch(ArrayList<Integer> numbers, int toFind){
        for(int value : numbers){
            if(value == toFind){
                return true;
            }
        }
        return false;
    }


    public static boolean binarySearch(ArrayList<Integer> numbers, int toFind){
        if(numbers.size() == 0){
            return false;
        }

        int lowerBound = 0;
        int upperBound = numbers.size() - 1;

        // check the end points first
        if(numbers.get(lowerBound) == toFind || numbers.get(upperBound) == toFind){
            return true;
        }

        while(upperBound - lowerBound > 1){
            // Dividing 2 integers so this is integer division and returns the floor of the result
            int midPoint = (upperBound + lowerBound) / 2;
            int midValue = numbers.get(midPoint);

            if(midValue == toFind){
                return true;
            }else if(toFind < midValue){
                upperBound = midPoint;
            }else{
                // It's not equal to or less than so it must be greater than
                lowerBound = midPoint;
            }
        }

        return false;
    }


    public static void main(String[] args){

        int numberOfValues = 50000;
        int searchFor = -1;
        boolean stressTest = true;

        long startTime;
        long endTime;
        long elapsedTime;


        /*** Setup sorted data structure ***/

        startTime = System.currentTimeMillis();

        ArrayList<Integer> bigData = new ArrayList<>();
        for(int i = 0; i < numberOfValues; i++){
            bigData.add(i);
        }

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Building the data structure: " + elapsedTime + "ms\n");


        /*** Linear Search Timing ***/

        startTime = System.currentTimeMillis();

        if(stressTest){
            for(int i = 0; i < numberOfValues; i++){
                boolean expectTrue = linearSearch(bigData, i);
                if(!expectTrue){
                    System.out.println("Error!!");
                }
            }
        }else{
            linearSearch(bigData, searchFor);
        }

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("Linear Search: " + elapsedTime + "ms");


        /*** Binary Search Timing ***/

        startTime = System.currentTimeMillis();
        if(stressTest){
            for(int i = 0; i < numberOfValues; i++){
                boolean expectTrue = binarySearch(bigData, i);
                if(!expectTrue){
                    System.out.println("Error!!");
                }
            }
        }else{
            binarySearch(bigData, searchFor);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("Binary Search: " + elapsedTime + "ms");

    }

}
