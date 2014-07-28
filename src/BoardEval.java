
public class BoardEval {

	private Board evalBoard;
	private PieceCoord[] wCoords;
	private PieceCoord[] bCoords;
	private int numWKings;
	private int numWQueens;
	private int numWBishops;
	private int numWKnights;
	private int numWRooks;
	private int numWPawns;
	private int numBKings;
	private int numBQueens;
	private int numBBishops;
	private int numBKnights;
	private int numBRooks;
	private int numBPawns;
	
	public BoardEval(Board bd){
		evalBoard = bd;
		wCoords = evalBoard.getWCoords();
		bCoords = evalBoard.getBCoords();
		numWKings = 0;
		numWQueens = 0;
		numWBishops = 0;
		numWKnights = 0;
		numWRooks = 0;
		numWPawns = 0;
		numBKings = 0;
		numBQueens = 0;
		numBBishops = 0;
		numBKnights = 0;
		numBRooks = 0;
		numBPawns = 0;
	}
	
	private void whiteMaterial(){
		for(int i = 0; i < 16; i++){
			if(i < 8){
				if(wCoords[i] != null){
					numWPawns++;
				}
			}
			else if(i < 10){
				if(wCoords[i] != null){
					numWRooks++;
				}
			}
			else if(i < 12){
				if(wCoords[i] != null){
					numWKnights++;
				}
			}
			else if(i < 14){
				if(wCoords[i] != null){
					numWBishops++;
				}
			}
			else if(i == 14){
				if(wCoords[i] != null){
					numWKings++;
				}
			}
			else if(i == 15){
				if(wCoords[i] != null){
					numWQueens++;
				}
			}
		}
	} // 0-7 pawns, 8-9 rooks, 10-11 knights, 12-13 bishops, 14 king, 15 queen
	
	private void blackMaterial(){
		for(int i = 0; i < 16; i++){
			if(i < 8){
				if(bCoords[i] != null){
					numBPawns++;
				}
			}
			else if(i < 10){
				if(bCoords[i] != null){
					numBRooks++;
				}
			}
			else if(i < 12){
				if(bCoords[i] != null){
					numBKnights++;
				}
			}
			else if(i < 14){
				if(bCoords[i] != null){
					numBBishops++;
				}
			}
			else if(i == 14){
				if(bCoords[i] != null){
					numBKings++;
				}
			}
			else if(i == 15){
				if(bCoords[i] != null){
					numBQueens++;
				}
			}
		}
	}
	
	public int NegaMaxEval(){
		int score = 200*(numWKings - numBKings)
				+ 9*(numWQueens - numBQueens)
				+ 5*(numWRooks - numBRooks)
				+ 3*(numWBishops - numBBishops + numWKnights - numBKnights)
				+ 1*(numWPawns - numBPawns)
				;
	//score = materialWeight * (numWhitePieces - numBlackPieces) * who2move			
		return score;
	}
	
	/*
	 * f(p) = 200(K-K')
       + 9(Q-Q')
       + 5(R-R')
       + 3(B-B' + N-N')
       + 1(P-P')
       - 0.5(D-D' + S-S' + I-I')
       + 0.1(M-M') + ...
 
KQRBNP = number of kings, queens, rooks, bishops, knights and pawns
D,S,I = doubled, blocked and isolated pawns
M = Mobility (the number of legal moves)
	 */
	
}
