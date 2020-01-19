public class Cell {

    private String value; //store the actual value contained in this cell
    private int row;
    private int col;
    private boolean goal;

    Cell(){
        setValue("  ");    //"X" -> Obstacle, "  " -> Empty, "#" -> Counter Values
        setGoal(false);
    }
    //Retrieve col
    public int getCol() {
		return col;
	}
    //Pre: Must pass in a col of type int
    //Set col 
	public void setCol(int col) {
		this.col = col;
	}
    //Retrieve row
	public int getRow() {
		return row;
	}
    //Pre: Must pass in a row of type int
    //Set row 
	public void setRow(int row) {
		this.row = row;
	}

	//Retrieve value inside this cell
    public String getValue() {
		return value;
    }
    //Pre: Must pass in a value of type string
    //Set value inside this cell
	public void setValue(String value) {
		this.value = value;
    }
    
    //Retrieve Goal inside this cell
    public boolean getGoal() {
		return goal;
    }
    //Pre: Must pass in a goal of type boolean
    //Set goal inside this cell
	public void setGoal(boolean goal) {
		this.goal = goal;
	}
}

