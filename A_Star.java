import java.util.*;


public class A_Star {
    //Declare currentCell, & counter
    static Cell currentCell;
    static int counter;
    static Cell goalCell;
    static Cell homeCell;
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
            grid[currRow][currCol - 1].setCost(currentCell.getCost() + 2);
            // grid[currRow][currCol - 1].setCost(findCost(grid, grid[currRow][currCol - 1]));
            //add expanded cell to the frontier
            frontier.add(grid[currRow][currCol - 1]);

            System.out.print("Frontier Value: ");
            for (int i = 0; i < frontier.size(); i++)
            {
                System.out.print(frontier.get(i).getValue() + " ");
            }
            System.out.println();


        } //endWestCheck

        //If North cell is not blocked & is empty
        if ((currRow - 1 >= 0) && grid[currRow-1][currCol].getValue().equals("[]"))
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow - 1][currCol].setValue(updateVal);
            grid[currRow - 1][currCol].setCost(currentCell.getCost() + 1);
            // grid[currRow - 1][currCol].setCost(findCost(grid, grid[currRow - 1][currCol]));
            //Update frontier
            frontier.add(grid[currRow - 1][currCol]);    

            System.out.print("Frontier Value: ");
            for (int i = 0; i < frontier.size(); i++)
            {
                System.out.print(frontier.get(i).getValue() + " ");
            }
            System.out.println();


        } //endNorthCheck

        //If East cell is not blocked & is empty
        if ((currCol + 1 < grid[currRow].length) && grid[currRow][currCol+1].getValue().equals("[]"))
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow][currCol + 1].setValue(updateVal);
            grid[currRow][currCol + 1].setCost(currentCell.getCost() + 2);
            // grid[currRow][currCol + 1].setCost(findCost(grid, grid[currRow][currCol + 1]));
            //Update frontier
            frontier.add(grid[currRow][currCol + 1]);

            System.out.print("Frontier Value: ");
            for (int i = 0; i < frontier.size(); i++)
            {
                System.out.print(frontier.get(i).getValue() + " ");
            }
            System.out.println();


        }//endEastCheck

        //If South cell is not blocked & is empty
        if ((currRow + 1 < grid.length) && grid[currRow+1][currCol].getValue().equals("[]"))
        {
            //update counter & set the value
            counter++;
            String updateVal = String.format("%02d" , counter); //add leading 0 if single digit
            grid[currRow + 1][currCol].setValue(updateVal);
            grid[currRow + 1][currCol].setCost(currentCell.getCost() + 3);
            // grid[currRow + 1][currCol].setCost(findCost(grid, grid[currRow + 1][currCol]));
            //Update frontier
            frontier.add(grid[currRow + 1][currCol]);


            System.out.print("Frontier Value: ");
            for (int i = 0; i < frontier.size(); i++)
            {
                System.out.print(frontier.get(i).getValue() + " ");
            }
            System.out.println();


        }//endSouthCheck        

        frontier.remove(currentCell);
    }

    //Returns next cell to move to
    public static Cell nextCell(Cell[][] grid, ArrayList<Cell> frontier)
    {
        // ArrayList<Cell> tempFrontier = new ArrayList<>();
        // tempFrontier.addAll(frontier);

        // for(int i = 0; i < tempFrontier.length(); i++)
        // {
        //     for(int j = 0; j < tempFrontier[i].length(); j++)
        //     tempFrontier.get(i).setCost(findCost(grid, tempFrontier));
        // }

        //Rearrange frontier in terms of cost
        Collections.sort(frontier, new SortByCost());
        

        System.out.print("Collections Frontier Value: ");
            for (int i = 0; i < frontier.size(); i++)
            {
                System.out.print(frontier.get(i).getValue() + " ");
            }
            System.out.println();



        //Rearrange frontier in terms of counter
        int tempCost = frontier.get(0).getCost();
        int min_index;
        for(int i = 0; i < frontier.size()-1 && frontier.get(i).getCost() == tempCost; i++)
        {
             min_index = i;
             for(int j = i+1; j < frontier.size() && frontier.get(i).getCost() == tempCost; j++)
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

            System.out.print("Sorted Frontier Value: ");
            for (int i = 0; i < frontier.size(); i++)
            {
                System.out.print(frontier.get(i).getValue() + " ");
            }
            System.out.println();


        //Return the first cell
        return frontier.get(0);
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
        // if (neighborCell.getCol() != homeCell.getCol())
        // {
            
        // }

        // if (neighborCell.getCol() < homeCell.getCol())  //If moving left
        // {
        //     // totalCost += (2 * (Math.abs(neighborCell.getCol() - grid[currentCell.getRow()][currentCell.getCol()+1].getCol())));
        //     totalCost += (2 + grid[currentCell.getRow()][currentCell.getCol()+1].getCost());
        // }
        // else if (neighborCell.getCol() > homeCell.getCol())     //If moving right
        // {
        //     // totalCost += (2 * (Math.abs(neighborCell.getCol() - homeCell.getCol())));
        //     totalCost += (2 + grid[currentCell.getRow()][currentCell.getCol()-1].getCost());
        // }
        // if (neighborCell.getRow() < homeCell.getRow())          //If moving up
        // {
        //     // totalCost += (1 * (Math.abs(neighborCell.getRow() - homeCell.getRow())));
        //     totalCost += (1 + grid[currentCell.getRow()+1][currentCell.getRow()].getCost());
        // }
        // else if (neighborCell.getRow() > homeCell.getRow())     //If moving down
        // {
        //     // totalCost += (3 * (Math.abs(neighborCell.getRow() - homeCell.getRow())));
        //     totalCost += (3 + grid[currentCell.getRow()-1][currentCell.getCol()].getCost());
        // }
        // totalCost += currentCell.getCost();
        
        //Cost toGoal:  West => neighbor cell col - goal cell col
        //              North => neighbor cell row - goal cell row
        //              East => neighbor cell col - goal cell col
        //              South => neighbor cell row - goal cell row
        //Cost final:   Cost NeighborCell + toGoal
        
        // if (neighborCell.getCol() != goalCell.getCol())
        // {
        //     totalCost += (2 * (Math.abs(neighborCell.getCol() - goalCell.getCol())));
        // }
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
        homeCell = grid[0][1];
        
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
                System.out.print(grid[row][col].getValue() + " ");
            }
            System.out.println();
        }
        
        System.out.println();
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                System.out.print(grid[row][col].getCost() + " ");
            }
            System.out.println();
        }
    }//End printGrid
}