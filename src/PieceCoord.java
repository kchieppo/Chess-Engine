
public class PieceCoord {

	private int row;
	private int col;
	private char id; //type of piece
	
	public PieceCoord(char p, int r, int c){
		id = p;
		row = r;
		col = c;
	}
	public char getID(){
		return id;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void setID(char p){
		id = p;
	}
	public void setRow(int r){
		row = r;
	}
	public void setCol(int c){
		col = c;
	}
	
}
