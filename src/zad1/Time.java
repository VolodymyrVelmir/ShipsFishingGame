package zad1;

public class Time extends Thread{
	int t;
    boolean Pause;
    int c=500;

    double getTime(){
    	return c/1000*t;
    }
    
        Time() {
			System.out.println("Start Live");
			start(); 
			t=0;
			Pause=false;
		}

	
	   void setLiveThread(boolean Pause){
		   this.Pause=Pause;
	   }

		public void run() {
			try {
				while (t<=Main.TimeMax) {
					if(!Pause){
			
					t++;
					Main.Update();
					System.out.println("update ( Time: " + t+"/"+Main.TimeMax+" [ Fish population: "+Main.CountFish()+" ])");
					Thread.sleep(c);
					}
					if(t>=Main.TimeMax || Main.CountFish()<=0){
						Main.CreateWidjetStatistic();
						break;
					}
				}
			
			} catch (InterruptedException e) {
				System.out.println("Error live");
			}
			System.out.println("Finish Live");
		}
}
