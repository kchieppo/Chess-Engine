import java.util.Scanner;

public class MakeMove {

	private String[][] board;
	private PieceCoord[] coords;
	private boolean turn;
	private int[] srcDRDCV;
	
	public MakeMove(boolean t, String[][] bd, PieceCoord[] pc){
		turn = t;
		board = bd;
		coords = pc;
		srcDRDCV = new int[4];
	}
	
	private boolean validSource(String in) {
		if(in == ""){
			return false;
		}
		try{
			srcDRDCV[0] = Integer.parseInt(in);
		}
		catch (NumberFormatException e){
			System.out.println("Invalid format.");
			return false;
		}
		if(srcDRDCV[0] < 0 || srcDRDCV[0] > 15){
			System.out.println("Out of range.");
			return false;
		}
		if(coords[srcDRDCV[0] + 16] != null){
			return true;
		}
		else{
			System.out.println("Piece does not exist.");
			return false;
		}
	}

	private int validDest(String in) {
		if(in == ""){
			return 0;
		}
		String delims = "[,]";
    	String[] tokens = in.split(delims);
    	try{
    		srcDRDCV[1] = Integer.parseInt(tokens[0]);
    		srcDRDCV[2] = Integer.parseInt(tokens[1]);
    	}
    	catch(NumberFormatException e){
    		System.out.println("Invalid format.");
    		return 0;
    	}
    	if(srcDRDCV[1] >= 0 && srcDRDCV[1] <= 7){
    		if(srcDRDCV[2] >= 0 && srcDRDCV[2] <= 7){
    			Mobility mob = new Mobility(turn, board, coords);
    			srcDRDCV[3] = mob.validMove(srcDRDCV[0], srcDRDCV[1], srcDRDCV[2]);
    			if(srcDRDCV[3] == 0){//invalid
    				System.out.println("Invalid destination.");
    				return 0;
    			}
    			else{
    				System.out.println("Valid!");
    				return srcDRDCV[3];
    			}
    		}
    		else{
        		System.out.println("Out of range.");
        		return 0;
        	}
    	}
    	else{
    		System.out.println("Out of range.");
    		return 0;
    	}
	}
	
	public int[] promptPlayer(){
		Scanner console = new Scanner(System.in);
		String src = "";
		boolean validSrc = this.validSource(src);
		while(!validSrc){
			System.out.println("Choose piece to move (0-7: pawns, 8-9: rooks, 10-11: knights, "
					+ "12-13: bishops, 14: king, 15: queen).");
			src = console.nextLine();
			validSrc = this.validSource(src);
		}
		String dest = "";
		int validDest = this.validDest(dest);
		while(validDest == 0){
			System.out.println("Enter destination coords (x,y).");
			dest = console.nextLine();
			validDest = this.validDest(dest);
		}
		console.close();
		return srcDRDCV;
	}
	
}
