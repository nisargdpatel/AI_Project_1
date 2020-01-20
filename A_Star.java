import java.util.*;


public class A_Star {
    //Declare currentCell, & counter
    static Cell currentCell;
    static int counter;
    static Cell goalCell;
    public static void main(String[] args) {
        Cell[][] grid = new Cell[6][7];
        ArrayList<Cell> frontier = new ArrayList<>();
        
        //Load the grid with target & starting point
        loadGrid(grid, frontier);
        
        //Loop until goal state found 
        while(currentCell.getGoal() != true)
        {
            //Call expand function
            expand(grid, frontier);
            //Find next current cell and set it
            
        }
    }

    //Expands the current cell on all sides
    public static void expand(Cell[][] grid, ArrayList<Cell> frontier)
    {
        int currRow = currentCell.getRow();
        int currCol = currentCell.getCol();
        //If West cell is not blocked & is empty
        if((currCol - 1 >= 0) && grid[currRow][currCol - 1].getValue().equals("[]")) 
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow][currCol - 1].setValue(updateVal);
            grid[currRow][currCol - 1].setCost(findCost(grid, grid[currRow][currCol - 1]));
            //add expanded cell to the frontier
            frontier.add(grid[currRow][currCol - 1]);
        } //endWestCheck

        //If North cell is not blocked & is empty
        if ((currRow - 1 >= 0) && grid[currRow-1][currCol].getValue().equals("[]"))
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow - 1][currCol].setValue(updateVal);
            grid[currRow - 1][currCol].setCost(findCost(grid, grid[currRow - 1][currCol]));
            //Update frontier
            frontier.add(grid[currRow - 1][currCol]);    
        } //endNorthCheck

        //If East cell is not blocked & is empty
        if ((currCol + 1 < grid[currRow].length) && grid[currRow][currCol+1].getValue().equals("[]"))
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow][currCol + 1].setValue(updateVal);
            grid[currRow][currCol + 1].setCost(findCost(grid, grid[currRow][currCol + 1]));
            //Update frontier
            frontier.add(grid[currRow][currCol + 1]);
        }//endEastCheck

        //If South cell is not blocked & is empty
        if ((currRow + 1 < grid.length) && grid[currRow+1][currCol].getValue().equals("[]"))
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow + 1][currCol].setValue(updateVal);
            grid[currRow + 1][currCol].setCost(findCost(grid, grid[currRow + 1][currCol]));
            //Update frontier
            frontier.add(grid[currRow + 1][currCol]);
        }//endSouthCheck        

        frontier.remove(currentCell);
    }

    //Returns next cell to move to
    public static Cell nextCell(Cell[][] grid, ArrayList<Cell> frontier)
    {
        //Rearrange frontier in terms of cost
        
        
        //Rearrange frontier in terms of counter
        

        //Return the first cell


    }

    public static int findCost(Cell[][] grid, Cell neighborCell) 
    {
        int totalCost = 0;
        //Equation: 2w + n + 2e + 3s => w=west, n=north, e=east, s=south
        //Given: current cell row/col & neighbor cell row/col
        //Cost NeighborCell:    West => neighbor cell col - current cell col
        //                      North => neighbor cell row - current cell row
        //                      East => neighbor cell col - current cell col
        //                      South => neighbor cell row - current cell row
        totalCost += (2 * Math.abs(neighborCell.getCol() - currentCell.getCol())) + 
                     (1 * Math.abs(neighborCell.getRow() - currentCell.getRow())) + 
                     (2 * Math.abs(neighborCell.getCol() - currentCell.getCol())) +
                     (3 * Math.abs(neighborCell.getRow() - currentCell.getRow())); 
        
        //Cost toGoal:  West => neighbor cell col - goal cell col
        //              North => neighbor cell row - goal cell row
        //              East => neighbor cell col - goal cell col
        //              South => neighbor cell row - goal cell row
        //Cost final:   Cost NeighborCell + toGoal
        totalCost += (2 * Math.abs(neighborCell.getCol() - goalCell.getCol())) + 
                     (1 * Math.abs(neighborCell.getRow() - goalCell.getRow())) + 
                     (2 * Math.abs(neighborCell.getCol() - goalCell.getCol())) +
                     (3 * Math.abs(neighborCell.getRow() - goalCell.getRow())); 
        return totalCost;
    }


    public static void loadGrid(Cell[][] grid, ArrayList<Cell> frontier)
    {
        //Initialize Grid
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[row].length; col++)
            {
                grid[row][col] = new Cell();
                grid[row][col].setRow(row);
                grid[row][col].setCol(col);
                grid[row][col].setValue("[]");
                
            }
        }
        //Load cells 
        grid[3][1].setValue("XX");
        grid[3][2].setValue("XX");
        grid[2][2].setValue("XX");
        grid[1][2].setValue("XX");
        grid[1][3].setValue("XX");
        grid[1][4].setValue("XX");
        grid[1][5].setValue("XX");
        grid[2][5].setValue("XX");
        grid[3][5].setValue("XX");
        grid[4][5].setValue("XX");
        grid[4][4].setValue("XX");

        

        //Goal
        grid[3][4].setGoal(true);
        goalCell = grid[3][4];
        
        //Starting point
        grid[0][1].setValue("00");
        currentCell = grid[0][1];
        counter = 0;
        frontier.add(currentCell);
    } //End loadGrid
}