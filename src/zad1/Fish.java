package zad1;

public class Fish extends Object{
	String FishType;
	double Masa=0;
    boolean isFreedom;
	
	Fish(int X, int Y,String FishType,double Masa,int Speed) {
		super(X, Y,Speed);
		this.Masa=Masa;
		this.FishType=FishType;
		isFreedom=true;
	}
        void setFreedom(boolean isFreedom){
        	this.isFreedom=isFreedom;
        }
	   
		public String toString(){
			return FishType +" Ammount: "+Masa;
		}
		

}
