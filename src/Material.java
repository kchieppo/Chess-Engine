
public class Material {

	private int mat;
	
	public Material(){
		mat = 0;
	}
	
	private void calc(PieceCoord[] pc){
		for(int i = 0; i < 8; i++){ //pawns
			if(pc[i] != null){
				mat++;
			}
		}
		for(int i = 8; i < 10; i++){ //rooks
			if(pc[i] != null){
				mat+=5;
			}
		}
		for(int i = 10; i < 14; i++){ //knights, bishops
			if(pc[i] != null){
				mat+=3;
			}
		}
		if(pc[14] != null){ //queen
			mat+=9;
		}
		if(pc[15] != null){ //king
			mat+=200;
		}
	}
}
