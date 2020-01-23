import java.util.*; 
class SortByCost implements Comparator<Cell> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Cell a, Cell b) 
    { 
        return a.getTotalCost() - b.getTotalCost(); 
    } 
} 