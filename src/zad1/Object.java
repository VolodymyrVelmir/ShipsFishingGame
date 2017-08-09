package zad1;

public class Object {
	int X,Y,Speed;
	Object(int X, int Y,int Speed){
		this.X=X;
		this.Y=Y;
		this.Speed=Speed;
	}
	
	void MuveX(int exis){
		X=X+Speed*exis;
		if(X>Main.WorldMaxX){X=Main.WorldMaxX-20;}
		if(X<0){X=20;}
		
	}
	void MuveY(int exis){
		Y=Y+Speed*exis;
		if(Y>Main.WorldMaxY){Y=Main.WorldMaxY-20;}
		if(Y<0){Y=20;}
	}

	

}
