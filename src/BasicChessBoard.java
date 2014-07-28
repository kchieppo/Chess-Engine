
public class BasicChessBoard {

	private String[][] boardGraphic;
	public PieceCoord[] pc;
	private boolean gameOver;
	private boolean turn; //true: white, false: black
	private int[] playerMove;
	// first 16 black, second 16 white
	public BasicChessBoard(){
		boardGraphic = new String[8][8];
		pc = new PieceCoord[32]; // order: b: 8 pawns, rooks, knights, bishops, king, queen; w: same
		gameOver = false;
		turn = true;
		playerMove = new int[3];
	}
	
	private void initCoords(){
		int rookCount = 0;
		int knightCount = 1;
		int bishopCount = 2;
		for(int i = 0; i < 16; i++){
			if(i < 8){ // pawns
				pc[i] = new PieceCoord('P', 1, i);
				pc[i + 16] = new PieceCoord('P', 6, i);
			}
			else if(i < 10){ // rooks
				pc[i] = new PieceCoord('R', 0, rookCount);
				pc[i + 16] = new PieceCoord('R', 7, rookCount);
				rookCount += 7;
			}
			else if(i < 12){ // knights
				pc[i] = new PieceCoord('N', 0, knightCount);
				pc[i + 16] = new PieceCoord('N', 7, knightCount);
				knightCount += 5;
			}
			else if(i < 14){ // bishops
				pc[i] = new PieceCoord('B', 0, bishopCount);
				pc[i + 16] = new PieceCoord('B', 7, bishopCount);
				bishopCount += 3;
			}
			else if(i == 14){ // king
				pc[i] = new PieceCoord('Q', 0, 3);
				pc[i + 16] = new PieceCoord('Q', 7, 3);
			}
			else if(i == 15){ // queen
				pc[i] = new PieceCoord('K', 0, 4);
				pc[i + 16] = new PieceCoord('K', 7, 4);
			}
		}
	}
	
	public String[][] getBoardGraphic(){
		return boardGraphic;
	}
	
	public PieceCoord[] getCoords(){
		return pc;
	}
	
	public void setCoords(PieceCoord[] p){
		pc = p;
	}
	
	private boolean isGameDone(){
		return gameOver;
	}
	
	private void setGameDone(boolean g){
		gameOver = g;
	}
	
	private boolean getTurn(){
		return turn;
	}
	
	private void setTurn(boolean t){
		turn = t;
	}
	
	private void setPlayerMove(int[] i){
		playerMove = i;
	}
	
	private int[] getPlayerMove(){
		return playerMove;
	}
	
	private void editCoordsBoard(int[] srcDRDCV){
		if(srcDRDCV[3] > 0){//non-capture or capture
			if(turn == true){//white
				int oldRow = pc[srcDRDCV[0] + 16].getRow();
				int oldCol = pc[srcDRDCV[0] + 16].getCol();
				boardGraphic[oldRow][oldCol] = "Nu";
				pc[srcDRDCV[0] + 16].setRow(srcDRDCV[1]);
				pc[srcDRDCV[0] + 16].setCol(srcDRDCV[2]);
				boardGraphic[srcDRDCV[1]][srcDRDCV[2]] = "w" + pc[srcDRDCV[0] + 16].getID();
				if(srcDRDCV[3] == 2){//capture
					for(int i = 0; i < 16; i++){//search all opp color coords for match
						int r = pc[i].getRow();
						int c = pc[i].getCol();
						if(r == srcDRDCV[1] && c == srcDRDCV[2]){
							pc[i] = null;//piece removed from game
						}
					}
				}
			}
			else{//black
				int oldRow = pc[srcDRDCV[0]].getRow();
				int oldCol = pc[srcDRDCV[0]].getCol();
				boardGraphic[oldRow][oldCol] = "Nu";
				pc[srcDRDCV[0]].setRow(srcDRDCV[1]);
				pc[srcDRDCV[0]].setCol(srcDRDCV[2]);
				boardGraphic[srcDRDCV[1]][srcDRDCV[2]] = "b" + pc[srcDRDCV[0]].getID();
				if(srcDRDCV[3] == 2){//capture
					for(int i = 0; i < 16; i++){//search all opp color coords for match
						int r = pc[i + 16].getRow();
						int c = pc[i + 16].getCol();
						if(r == srcDRDCV[1] && c == srcDRDCV[2]){
							pc[i + 16] = null;//piece removed from game
						}
					}
				}
			}
		}
	}
	
	private void updateBoard(){
		//init with all null
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				boardGraphic[i][j] = "Nu";
			}
		}
		//black
		for(int i = 0; i < 16; i++){
			if(pc[i] != null){
				boardGraphic[pc[i].getRow()][pc[i].getCol()] = "b" + pc[i].getID();
			}
		}
		//white
		for(int i = 16; i < 32; i++){
			if(pc[i] != null){
				boardGraphic[pc[i].getRow()][pc[i].getCol()] = "w" + pc[i].getID();
			}
		}
	}
	
	private void drawBoard(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.print(boardGraphic[i][j]);
			}
			System.out.println();
		}
	}
	
	private void initialize(){
		this.initCoords();
		this.updateBoard();
	}
	
	public static void main(String[] args){
		BasicChessBoard b = new BasicChessBoard();
		b.initialize();
		PieceCoord[] pc = b.getCoords();
		pc[8 + 16].setRow(3);//white rook
		pc[8 + 16].setCol(3);
		b.setCoords(pc);
		b.updateBoard();
		b.drawBoard();
		System.out.println();
		Mobility m = new Mobility(b.getTurn(), b.getBoardGraphic(), b.getCoords());
		LL possMoves = m.possibleMoves();
		possMoves.initIterator();
		b.setCoords(possMoves.getNextEle());
		b.updateBoard();
		b.drawBoard();
		System.out.println();
/*		while(possMoves.hasNext()){
			b.setCoords(possMoves.getNextEle());
			b.updateBoard();
			b.drawBoard();
			System.out.println();
		}*/
/*		MakeMove mm = new MakeMove(b.getTurn(), b.getBoardGraphic(), b.getCoords());
		b.editCoordsBoard(mm.promptPlayer());
		b.drawBoard();
		BasicChessBoard b = new BasicChessBoard();
		b.initialize();
		GameStateStructure gss = new GameStateStructure();
		while(!b.isGameDone()){
			if(b.getTurn() == true){ //white
				MakeMove mm = new MakeMove(turn, boardGraphic, pc);
				mm.promptPlayer();
			}
			else{ //black
				
			}
		}*/
	}
}
