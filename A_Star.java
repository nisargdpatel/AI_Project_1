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
            currentCell = nextCell(grid, frontier);
        }

        //Print the grid
        System.out.println("Username: NISARGP, MAITRABP");
        System.out.println("Outcome for A* Search:");
        printGrid(grid);
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

            //Update path cost
            grid[currRow][currCol - 1].setCost(currentCell.getCost() + 2);

            //Estimate cost to goal cell and update total cost
            grid[currRow][currCol - 1].setTotalCost(findCost(grid, grid[currRow][currCol - 1]));

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

            //Update path past
            grid[currRow - 1][currCol].setCost(currentCell.getCost() + 1);

            //Estimate cost to goal cell and update total cost
            grid[currRow - 1][currCol].setTotalCost(findCost(grid, grid[currRow - 1][currCol]));

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

            //Update path cost
            grid[currRow][currCol + 1].setCost(currentCell.getCost() + 2);

            //Estimate cost to goal cell and update total cost
            grid[currRow][currCol + 1].setTotalCost(findCost(grid, grid[currRow][currCol + 1]));

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

            //Update path cost
            grid[currRow + 1][currCol].setCost(currentCell.getCost() + 3);

            //Estimate cost to goal cell and update total cost
            grid[currRow + 1][currCol].setTotalCost(findCost(grid, grid[currRow + 1][currCol]));

            //Update frontier
            frontier.add(grid[currRow + 1][currCol]);

        }//endSouthCheck        

        frontier.remove(currentCell);
    }

    //Returns next cell to move to
    public static Cell nextCell(Cell[][] grid, ArrayList<Cell> frontier)
    {
        //Sort frontier by cost
        Collections.sort(frontier, new SortByCost());
        
        //Sort frontier by counter
        int tempCost = frontier.get(0).getTotalCost();
        int min_index;
        for(int i = 0; i < frontier.size()-1 && frontier.get(i).getTotalCost() == tempCost; i++)
        {
             min_index = i;
             for(int j = i+1; j < frontier.size() && frontier.get(i).getTotalCost() == tempCost; j++)
             {
                if(Integer.parseInt(frontier.get(j).getValue()) < Integer.parseInt(frontier.get(min_index).getValue()))
                {
                    min_index = j;
                    Cell temp = frontier.get(min_index);
                    frontier.set(min_index, frontier.get(j));
                    frontier.set(j, temp);  
                }
                
             }
        }

        //Return the first cell
        return frontier.get(0);
    }

    //Returns estimated cost from neighbor cell to goal cell
    public static int findCost(Cell[][] grid, Cell neighborCell) 
    {
        int totalCost = 0;

        if (neighborCell.getCol() < goalCell.getCol())      //If moving right
        {
            totalCost += (2 * (Math.abs(neighborCell.getCol() - goalCell.getCol())));
        }
        else if (neighborCell.getCol() > goalCell.getCol()) //If moving left
        {
            totalCost += (2 * (Math.abs(neighborCell.getCol() - goalCell.getCol())));
        }

        if (neighborCell.getRow() < goalCell.getRow())      //If moving down
        {
            totalCost += (3 * (Math.abs(neighborCell.getRow() - goalCell.getRow())));
        }
        else if (neighborCell.getRow() > goalCell.getRow()) //If moving up
        {
            totalCost += (1 * (Math.abs(neighborCell.getRow() - goalCell.getRow())));
        }

        return totalCost;
    }

    //Loads the initial components on grid
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

    
    public static void printGrid(Cell[][] grid) 
    {
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                
                if (grid[row][col].getValue() == "[]")
                {
                    System.out.print("   ");
                } else {
                    System.out.print(grid[row][col].getValue() + " ");    
                }
            }
            System.out.println();
        }
    }//End printGrid
}//End class A_Star