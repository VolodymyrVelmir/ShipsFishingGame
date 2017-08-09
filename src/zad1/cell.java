package zad1;

public class cell {
	double MaxAmmount,Ammount;
	String Type;
	cell(double MaxAmmount ){
		this.MaxAmmount=MaxAmmount;
		Ammount=0;
	}
	void setType(String Type){
		this.Type=Type;
	}
	boolean addAmmount(double value){
		if(Ammount+value<=MaxAmmount){
			Ammount=Ammount+value;
			return true;
		}
		return false;
	}

}
