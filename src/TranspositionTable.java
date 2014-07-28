import java.util.HashMap;
/*
 * Figure out how to check for linear independence
 */
public class TranspositionTable {

	private int[][][] transTable;
	private HashMap<Integer, PieceCoord[]> map;
	
	public TranspositionTable(){
		map = new HashMap<Integer, PieceCoord[]>();
		this.initZobrist();
	}
	
	/* k values:
	 * wP = 0, wR = 1, wN = 2, wB = 3, wQ = 4, wK = 5
	 * bP = 6, bR = 7, bN = 8, bB = 9, bQ = 10, bK = 11
	 */
	private void initZobrist(){
		transTable = new int[8][8][12];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				for(int k = 0; k < 12; k++){
					transTable[i][j][k] = (int)Math.floor(Math.random()*10000001);
				}
			}
		}
	}
	
	public void hash(PieceCoord[] pc){
		int hashVal = 0;
		PieceCoord[] coords = pc;
		for(int j = 0; j < 16; j++){
			if(coords[j] != null){
				if(j < 8){
					hashVal ^= transTable[coords[j].getRow()][coords[j].getCol()][0];
					hashVal ^= transTable[coords[j + 16].getRow()][coords[j + 16].getCol()][6];
				}
				else if(j < 10){
					hashVal ^= transTable[coords[j].getRow()][coords[j].getCol()][1];
					hashVal ^= transTable[coords[j + 16].getRow()][coords[j + 16].getCol()][7];
				}
				else if(j < 12){
					hashVal ^= transTable[coords[j].getRow()][coords[j].getCol()][2];
					hashVal ^= transTable[coords[j + 16].getRow()][coords[j + 16].getCol()][8];
				}
				else if(j < 14){
					hashVal ^= transTable[coords[j].getRow()][coords[j].getCol()][3];
					hashVal ^= transTable[coords[j + 16].getRow()][coords[j + 16].getCol()][9];
				}
				else if(j == 14){
					hashVal ^= transTable[coords[j].getRow()][coords[j].getCol()][4];
					hashVal ^= transTable[coords[j + 16].getRow()][coords[j + 16].getCol()][10];
				}
				else{
					hashVal ^= transTable[coords[j].getRow()][coords[j].getCol()][5];
					hashVal ^= transTable[coords[j + 16].getRow()][coords[j + 16].getCol()][11];
				}
			}
			map.put(hashVal, pc);
		}
	}
	
	// for testing
	public void checkDup(){
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int counter;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				for(int k = 0; k < 12; k++){
					if(hm.containsKey(transTable[i][j][k])){
						counter = hm.get(transTable[i][j][k]);
					}
					else{
						counter = 0;
					}
					counter++;
					hm.put(transTable[i][j][k], counter);
				}
			}
		}
		this.printDup(hm);
	}
	
	private void printDup(HashMap<Integer, Integer> hm){
		int total = 0;
		for(Integer key : hm.keySet()){
			Integer value = hm.get(key);
			System.out.println(key + " = " + value);
			total++;
		}
		System.out.println(total);
		System.out.println(768);
	}
	//for testing
	
}
