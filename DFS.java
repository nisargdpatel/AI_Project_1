import java.util.*;


public class DFS {

    //Keep track of current cell
    static Cell currentCell;
    static int counter;
    public static void main(String[] args) {
        Cell[][] grid = new Cell[6][7];
        Stack<Cell> frontier = new Stack<Cell>();
        Stack<Cell> exploredSet = new Stack<Cell>();
        

        loadGrid(grid, frontier);

        //Loop until goal state found
        while(currentCell.getGoal() != true)
        {
            //Call expand function
            expand(grid, frontier, exploredSet);
            
            //update currentCell to top value of frontier
            currentCell = frontier.peek();
        }
        
        if (currentCell.getGoal() && !exploredSet.peek().getGoal())
        {
            exploredSet.push(currentCell);
        }

        System.out.println("Username: NISARGP, MAITRABP");
        System.out.println("Outcome for DFS:");
        printGrid(grid);
        System.out.println();
        modifyGridperPath(exploredSet, grid);
        System.out.println("Path for DFS:");
        printGrid(grid);
    }//End main
    
    public static void loadGrid(Cell[][] grid, Stack<Cell> frontier) 
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
        

        //Starting point
        grid[0][1].setValue("00");
        currentCell = grid[0][1];
        counter = 0;
        frontier.push(currentCell);
    }//End loadGrid
    
    public static void printGrid(Cell[][] grid) 
    {
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                System.out.print(grid[row][col].getValue() + " ");
            }
            System.out.println();
        }
    }//End printGrid

    //Expand the cell on 4 sides and update frontier & exploredSet
    public static void expand(Cell[][] grid, Stack<Cell> frontier, Stack<Cell> exploredSet)
    {
        int currRow = currentCell.getRow();
        int currCol = currentCell.getCol();
        boolean deadEnd = true;
        //Add currCell to explored set
        exploredSet.push(currentCell);

        
        
        //If West cell is not blocked & is empty
        if((currCol - 1 >= 0) && grid[currRow][currCol - 1].getValue().equals("[]")) 
        {
            deadEnd = false;
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow][currCol - 1].setValue(updateVal);

            //Update frontier
            frontier.push(grid[currRow][currCol - 1]);
        } //endWestCheck

        //If North cell is not blocked & is empty
        if ((currRow - 1 >= 0) && grid[currRow-1][currCol].getValue().equals("[]"))
        {
            deadEnd = false;
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow - 1][currCol].setValue(updateVal);

            //Update frontier
            frontier.push(grid[currRow - 1][currCol]);    
        } //endNorthCheck

        //If East cell is not blocked & is empty
        if ((currCol + 1 < grid[currRow].length) && grid[currRow][currCol+1].getValue().equals("[]"))
        {
            deadEnd = false;
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow][currCol + 1].setValue(updateVal);

            //Update frontier
            frontier.push(grid[currRow][currCol + 1]);
        }//endEastCheck

        //If South cell is not blocked & is empty
        if ((currRow + 1 < grid.length) && grid[currRow+1][currCol].getValue().equals("[]"))
        {
            deadEnd = false;
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow + 1][currCol].setValue(updateVal);

            //Update frontier
            frontier.push(grid[currRow + 1][currCol]);
        }//endSouthCheck

        if(deadEnd)
        {
            //Call backtrack function
            backtrack(frontier, exploredSet);
        }
    }//End Expand


    public static void backtrack(Stack<Cell> frontier, Stack<Cell> exploredSet)
    {
        //If top value of frontier and top value of exploredSet matches
        while (!frontier.empty() && !exploredSet.empty() && frontier.peek().getValue().equals(exploredSet.peek().getValue()))
        {
            //Pop values from frontier and exploredSet
            frontier.pop();
            exploredSet.pop();
        }
    }//End backtrack

    public static void modifyGridperPath(Stack<Cell> tempStack, Cell[][] grid)
    {
        loadGrid(grid, tempStack);
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[row].length; col++)
            {
                grid[row][col].setValue("  ");
                
            }
        }
        while(!tempStack.empty())
        {
            grid[tempStack.peek().getRow()][tempStack.peek().getCol()].setValue(tempStack.peek().getValue());
            tempStack.pop();
        }
    }

}//End class DFS