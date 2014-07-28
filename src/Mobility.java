// validity, mobility, possible moves
// all piece moves can be condensed
// does not include castling, en passant
import java.util.LinkedList;

public class Mobility {

	private String[][] board;
	private PieceCoord[] coords; // 0-7 pawns, 8-9 rooks, 10-11 knights,
	private int moveCount;			  // 12-13 bishops, 14 king, 15 queen
	private boolean turn;
	private char homeColor;
	private char opColor;
	
	public Mobility(boolean t, String[][] bd, PieceCoord[] pc){
		board = bd;
		coords = pc;
		turn = t;
		moveCount = 0;
		this.initColors(t);
	}
	
	private void initColors(boolean t){
		if(t == true){
			homeColor = 'w';
			opColor = 'b';
		}
		else{
			homeColor = 'b';
			opColor = 'w';
		}
	}
	/*
	 * validity start
	 */
	public int validMove(int index, int dR, int dC){
		int sR, sC;
		if(turn == true){//white
			sR = coords[index + 16].getRow();
			sC = coords[index + 16].getCol();
		}
		else{//black
			sR = coords[index].getRow();
			sC = coords[index].getCol();
		}
		if(index >= 0 && index <= 7){ // pawn
			return checkPawn(sR, sC, dR, dC);
		}
		else if(index == 8 || index == 9){ // rooks
			return checkRook(sR, sC, dR, dC);
		}
		else if(index == 10 || index == 11){ // knights
			return checkKnight(sR, sC, dR, dC);
		}
		else if(index == 12 || index == 13){ // bishops
			return checkBishop(sR, sC, dR, dC);
		}
		else if(index == 14){ // king
			return checkKing(sR, sC, dR, dC);
		}
		else{ // index == 15 queen
			return checkQueen(sR, sC, dR, dC);
		}
	}
	
	private int checkPawn(int sR, int sC, int dR, int dC){
		int diffR = sR - dR;
		int diffC = sC - dC;
		if(diffR == 0 && diffC == 0){
			return 0; //same position; invalid
		}
		if(turn == true){//white
			if(diffR == 1 && Math.abs(diffC) < 2){
				return this.checkPMoveValid(sR, sC, dR, dC, 1, diffC);
			}
			if(diffR == 2 && diffC == 0 && sR == 6){
				return this.checkPMoveValid(sR, sC, dR, dC, 1, 0);
			}
		}
		else{//black
			if(diffR == -1 && Math.abs(diffC) < 2){
				return this.checkPMoveValid(sR, sC, dR, dC, -1, diffC);
			}
			if(diffR == -2 && diffC == 0 && sR == 1){
				return this.checkPMoveValid(sR, sC, dR, dC, -1, 0);
			}
		}
		return 0; //destination out of range; invalid
	}

	private int checkRook(int sR, int sC, int dR, int dC){
		int diffR = sR - dR;
		int diffC = sC - dC;
		int unitR;
		int unitC;
		if(diffR == 0 && diffC == 0){
			return 0; //same position; invalid
		}
		else if(diffR == 0){
			unitR = 0;
			unitC = (sC - dC)/Math.abs(sC - dC);
			return this.checkMoveValid(sR, sC, dR, dC, unitR, unitC);
		}
		else if(diffC == 0){
			unitR = (sR - dR)/Math.abs(sR - dR);
			unitC = 0;
			return this.checkMoveValid(sR, sC, dR, dC, unitR, unitC);
		}
		return 0;//not side move; invalid
	}
	
	private int checkKnight(int sR, int sC, int dR, int dC){
		int diffR = sR - dR;
		int diffC = sC - dC;
		if(diffR == 0 && diffC == 0){
			return 0; //same position; invalid
		}
		if(Math.abs(diffR) == 2){
			if(Math.abs(diffC) == 1){
				return this.checkMoveValid(sR, sC, dR, dC, diffR, diffC);
			}
			else
				return 0; //not knight move; invalid
		}
		else if(Math.abs(diffR) == 1){
			if(Math.abs(diffC) == 2){
				return this.checkMoveValid(sR, sC, dR, dC, diffR, diffC);
			}
			else
				return 0; //not knight move; invalid
		}
		else
			return 0; //not knight move; invalid
	}

	private int checkBishop(int sR, int sC, int dR, int dC){
		int diffR = sR - dR;
		int diffC = sC - dC;
		int unitR;
		int unitC;
		if(diffR == 0 && diffC == 0){
			return 0; //same position; invalid
		}
		if(Math.abs(diffR) == Math.abs(diffC)){
			unitR = (sR - dR)/Math.abs(sR - dR);
			unitC = (sC - dC)/Math.abs(sC - dC);
			return this.checkMoveValid(sR, sC, dR, dC, unitR, unitC);
		}
		else
			return 0; //not on diagonal; invalid
	}
	
	private int checkKing(int sR, int sC, int dR, int dC){
		int diffR = sR - dR;
		int diffC = sC - dC;
		if(diffR == 0 && diffC == 0){
			return 0; //same location; invalid
		}
		if(Math.abs(diffR) > 1 || Math.abs(diffC) > 1){
			return 0; //more than one space away; invalid
		}
		else{
			return this.checkMoveValid(sR, sC, dR, dC, diffR, diffC);
		}
	}
	
	private int checkQueen(int sR, int sC, int dR, int dC){
		int diffR = sR - dR;
		int diffC = sC - dC;
		int unitR;
		int unitC;
		if(diffR == 0 && diffC == 0){
			return 0; //same position; invalid
		}
		else if(diffR == 0){
			unitR = 0;
			unitC = (sC - dC)/Math.abs(sC - dC);
			return this.checkMoveValid(sR, sC, dR, dC, unitR, unitC); //left or right
		}
		else if(diffC == 0){
			unitR = (sR - dR)/Math.abs(sR - dR);
			unitC = 0;
			return this.checkMoveValid(sR, sC, dR, dC, unitR, unitC); //up or down
		}
		else if(Math.abs(diffR) == Math.abs(diffC)){
			unitR = (sR - dR)/Math.abs(sR - dR);
			unitC = (sC - dC)/Math.abs(sC - dC);
			return this.checkMoveValid(sR, sC, dR, dC, unitR, unitC); //diagonal
		}
		else
			return 0; //not on side or diagonal; invalid
	}
	//for any non-pawn piece
	private int checkMoveValid(int sR, int sC, int dR, int dC, int vecR, int vecC){
		sR-=vecR;
		sC-=vecC;
		while(dR != sR && dC != sC){
			if(board[sR][sC] != "Nu"){
				return 0; //not path to dest; invalid
			}
			sR-=vecR;
			sC-=vecC;
		}
		if(board[sR][sC].charAt(0) == 'N')
			return 1; //non-capture
		else if(board[sR][sC].charAt(0) == opColor)
			return 2; //capture
		else
			return 0; //occupied by same color; invalid
	}
	//for pawns only
	private int checkPMoveValid(int sR, int sC, int dR, int dC, int vecR, int vecC){
		sR-=vecR;
		sC-=vecC;
		if(vecC != 0){
			if(board[sR][sC].charAt(0) == opColor)
				return 2;//capture
			else
				return 0; //invalid destination
		}
		else{
			while(dR != sR && dC != sC){
				if(board[sR][sC].charAt(0) == 'N'){
					return 0;//no path to dest; invalid
				}
				sR-=vecR;
				sC-=vecC;
			}
			if(board[sR][sC].charAt(0) == 'N')
				return 1;//non-capture
			else
				return 0;//piece on move destination; invalid
		}
	}
	/*
	 * validity end
	 */
	
	/*
	 * possible moves start
	 */
	private LL nonPawnBoardGen(int index, int vecR, int vecC){
		LL boardSubLst = new LL();
		int sR, sC;
		if(turn == true){//white
			if(coords[index + 16] == null){
				return null;
			}
			sR = coords[index + 16].getRow();
			sC = coords[index + 16].getCol();
		}
		else{//black
			if(coords[index] == null){
				return null;
			}
			sR = coords[index].getRow();
			sC = coords[index].getCol();
		}
		int curR = sR;
		int curC = sC;
		curR+=vecR;
		curC+=vecC;
		while(curR > -1 && curR < 8 && curC > -1 && curC < 8){
			if(turn == true){//white
				if(board[curR][curC].charAt(0) != 'w'){
					coords[index + 16].setRow(curR);
					coords[index + 16].setCol(curC);
					if(board[curR][curC].charAt(0) == 'b'){
						int r, c;
						for(int j = 0; j < 16; j++){
							r = coords[j].getRow();
							c = coords[j].getCol();
							if(curR == r && curC == c){
								coords[j] = null;
								boardSubLst.insert(coords);
								break;
							}
						}
						break;
					}
					else{
						boardSubLst.insert(coords);
					}
				}
				else{
					break;
				}
			}
			else{//black
				if(board[curR][curC].charAt(0) != 'b'){
					coords[index].setRow(curR);
					coords[index].setCol(curC);
					if(board[curR][curC].charAt(0) == 'w'){
						int r, c;
						for(int j = 0; j < 16; j++){
							r = coords[j + 16].getRow();
							c = coords[j + 16].getCol();
							if(curR == r && curC == c){
								coords[j + 16] = null;
								boardSubLst.insert(coords);
								break;
							}
						}
						break;
					}
					else{
						boardSubLst.insert(coords);
					}
				}
				else{
					break;
				}
			}
			curR+=vecR;
			curC+=vecC;
		}//reset coords
		if(turn == true){//white
			coords[index + 16].setRow(sR);
			coords[index + 16].setCol(sC);
		}
		else{//black
			coords[index].setRow(sR);
			coords[index].setCol(sC);
		}
		return boardSubLst;
	}
	
	public LL possibleMoves(){
		//not sure about subLL names
		LL children = new LL();
		/*
		 * check each piece
		 */
		for(int i = 0; i < 16; i++){
			if(i < 8){//pawns
				
			}
			else if(i < 10){//rooks
				children.joinWith(nonPawnBoardGen(i, 0, -1));
				children.joinWith(nonPawnBoardGen(i, 0, 1));
				children.joinWith(nonPawnBoardGen(i, -1, 0));
				children.joinWith(nonPawnBoardGen(i, 1, 0));
			}
			else if(i < 12){
			//	checkKnight(r, c, int dR, int dC)
			}
			else if(i < 14){//bishops

			}
			else{ //king, queen

			}
		}
		return children;
	}
	/*
	 * possible moves end
	 */
}
