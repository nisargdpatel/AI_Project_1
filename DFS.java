import java.util.*;


public class DFS {
    public static void main(String[] args) {
        Cell[][] grid = new Cell[6][7];
        loadGrid(grid);
        printGrid(grid);
    }
    
    public static void loadGrid(Cell[][] grid) {
        //Initialize Grid
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[row].length; col++)
            {
                grid[row][col] = new Cell();
            }
        }
        //Load cells 
        grid[3][1].value = "XX";
        grid[3][2].value = "XX";
        grid[2][2].value = "XX";
        grid[1][2].value = "XX";
        grid[1][3].value = "XX";
        grid[1][4].value = "XX";
        grid[1][5].value = "XX";
        grid[2][5].value = "XX";
        grid[3][5].value = "XX";
        grid[4][5].value = "XX";
        grid[4][4].value = "XX";

        //Goal
        grid[3][4].value = "GG";
        
        //Starting point
        grid[0][1].value = "00";
    }
    
    public static void printGrid(Cell[][] grid) {
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                System.out.print(grid[row][col].value + " ");
            }
            System.out.println();
        }
    }
}



