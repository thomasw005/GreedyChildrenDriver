/* Thomas Wilson
Dr. Steinberg
COP3503 Spring 2025
Programming Assignment 3
*/

// Imports.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// Main class for GreedyChildren.
public class GreedyChildren
{
    private int[] greedyFactors;
    private int[] sweetnessFactors;
    private int happyChildren;
    private int angryChildren;

    // Constructor for GreedyChildren.
    public GreedyChildren(int m, int n, String childFile, String candyFile) throws IOException
    {
        greedyFactors = new int[n];
        sweetnessFactors = new int[m];
        happyChildren = 0;
        angryChildren = 0;
        read(childFile, candyFile);
    }

    // Reads text files and updates greedy/sweetness factor arrays.
    public void read(String childFile, String candyFile) throws IOException
    {
        BufferedReader childReader = new BufferedReader(new FileReader(childFile));
        BufferedReader candyReader = new BufferedReader(new FileReader(candyFile));

        // Read each line of files and add to respective array.
        String line;
        int i = 0;
        while ((line = childReader.readLine()) != null)
        {
            greedyFactors[i++] = Integer.parseInt(line.trim());
        }

        i = 0;
        while ((line = candyReader.readLine()) != null)
        {
            sweetnessFactors[i++] = Integer.parseInt(line.trim());
        }

        childReader.close();
        candyReader.close();
    }

    // Greedy algorithm to solve GreedyChildren
    public void greedyCandy() {

        // Runtime of O(nlgn + mlgn), therefore O(mlgm).
        Arrays.sort(greedyFactors);
        Arrays.sort(sweetnessFactors);

        int childIndex = 0, candyIndex = 0;

        // Two pointer approach to match lowest satisfactory candy to child.
        while (childIndex < greedyFactors.length && candyIndex < sweetnessFactors.length)
        {
            if (sweetnessFactors[candyIndex] >= greedyFactors[childIndex])
            {
                happyChildren++;
                childIndex++;
            }
            candyIndex++;
        }

        // Update # angry children dependent on # of happy children.
        angryChildren = greedyFactors.length - happyChildren;
    }

    // Displays number of happy and angry children.
    public void display()
    {
        System.out.println("There are " + happyChildren + " happy children.");
        System.out.println("There are " + angryChildren + " angry children.");
    }
}
