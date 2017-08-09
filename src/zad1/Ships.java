package zad1;

public class Ships extends Object{
	double MaxAmmount,Ammount;int cellI;
	 int reage;   cell[] hangar;
	 double Timedownloading;
	 boolean Aflag=false;
	 String Name;
	 
	Ships(int X, int Y, double MaxAmmount,int cellI,int Speed,int reage,String Name) {
		super(X, Y,Speed);
		 this.Name=Name;
		 this.MaxAmmount=MaxAmmount;
		 this.cellI=cellI;
		 Ammount=0;
		 this.reage=reage;
		 Timedownloading=0;
		 hangar=new cell[cellI];
		 for(int i=0;i<cellI;i++){
			 hangar[i]=new cell(MaxAmmount/cellI);
		 }
	}
	double getAmmount(){
		double a=0;
		for(cell h: hangar){
			a=a+h.Ammount;
		}
		return a;
	}
	 String GetFishType(String FishType){
		 String F="0";
	
		 for(cell c: hangar){
			 if(c.Type==FishType){
				F=c.Ammount+""; 
			 }
			 
		 }
		 
		 return F;
	 }
	 
	 
	 
	 
	double getPrice(){
		double a=0;
		double[] price={2.0,2.3,2.5,2.8,3.0,3.4,3.8};
		String[] z={"A","B","C","D","E","F","G"};
		for(cell h: hangar){
			 for(int i=0; i<7;i++){
				if(h.Type==z[i]){a=a+h.Ammount*price[i];}
			}
		}
		return a;
	}
	
	
 
	boolean catching(Fish f){
		 boolean isAdd=false;
		 for(cell h: hangar){
			 if(h.Ammount==0){
				 h.setType(f.FishType);
				 h.addAmmount(f.Masa);
				 isAdd=true;
				 break;
			 }else if(h.addAmmount(f.Masa)){
				 isAdd=true;
				 break;
			 }
		 }
		 
		 if(Aflag==false && getAmmount()>=MaxAmmount){Timedownloading=Main.Timer.getTime(); Aflag=true;}
		 return isAdd;
	 }

	public String toString(){
		return Name;
	}
	String GetAmmoutToStr(){
		return ""+getAmmount();
	}
	String GetPriceToStr(){
		return ""+getPrice();
	}
	String GetCellToStr(){
		return ""+hangar.length;
	}

}
