package zad1;
/**
 *
 *  @author Chyrka Volodymyr S13596
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main {
	 protected static int WorldMaxX=300;
	 protected static int WorldMaxY=300;
	 protected static int TimeMax=12;
	 protected static int Puding=5;
	 static JFrame RootF;
	 static JPanel PGraphics;
	 static List<Fish> fishs;
	 static List<Ships> ships;
	 static Ships Player;
	 static JButton[] button;
	 static Time Timer;
	 public static String[][] data;
	 static JScrollPane sPane;
	 static JFrame Statistic;
	 static int ia=1;
	 static boolean ib=false;
	 static String[] ShipsName={"AAAA","sfsfes","asas","rsvsd","dzfz","SDFSF1","010SF","1AD"};
	 static List<Ships> sBufferD=new ArrayList<Ships>();
		 
		 
		 
	 public static void main(String[] args) {
		 fishs = new ArrayList<Fish>();
		 ships = new ArrayList<Ships>();
		 CreateWidjets(); 
		 CreateFish(70);         
		 Player=new Ships(100,100, 300.00,5,2,45,"player");  ships.add(Player);
		 CreateShips(8);
		 Timer =new Time();
	 }
	 

	 static void CreateShips(int max){
		 int X; int Y;int cell;int reage;
		 for(int i=0; i<max;i++){
			 X=(int) (Math.random()*WorldMaxX);
		     Y=(int) (Math.random()*WorldMaxY);
		     cell=(int) (Math.random()*7+1);
		     reage=(int) (Math.random()*25+20);
			 ships.add(new Ships(X,Y,(double)(cell*60),cell,14-(cell*2),reage,ShipsName[i]));
		 }
	 }
	 
	 static void CreateFish(int max){
		 String[] fT={"A","B","C","D","E","F","G"};
		 int X; int Y;int FishType;
		 for(int i=0; i<max;i++){
			 X=(int) (Math.random()*WorldMaxX);
		     Y=(int) (Math.random()*WorldMaxY);
		     FishType=(int)(Math.random()*7);
			 fishs.add(new Fish(X, Y,fT[FishType],0.15*(FishType+1),2));
		 }
	 }


	 static void Update(){
		 if(!ib){ia=1;ib=true;}else{ ia=-1; ib=false;} //muve Update Debug 
		 RootF.setSize(WorldMaxX, WorldMaxY+ia);
		 muveAllAI();
		 ShowGraphicsPanel(true);
			  ships.stream().filter(p->p.getAmmount()<=p.MaxAmmount).forEach(w->
			     fishs.stream().filter(p->p.isFreedom).filter(p->Math.sqrt(w.X*w.X-p.X*p.X+w.Y*w.Y-p.Y*p.Y)<=w.reage/2).filter(p->w.catching(p)).
			     forEach(p->p.setFreedom(false))); //fishing
			  fishs=fishs.stream().filter(p->p.isFreedom).collect(Collectors.toList()); //RemoveDeathFish
	 }
	 
	 static int CountFish(){return fishs.size();}
	 
	 static void muveAllAI(){
		 ships.stream().filter(p->p!=Player).forEach(p->{p.MuveX(Recalculate()); p.MuveY(Recalculate());});
		 fishs.stream().filter(p->p.isFreedom).forEach(p->{p.MuveX(Recalculate()); p.MuveY(Recalculate());});  
	 }
	 
	 static int Recalculate(){
		 int i=(int) (Math.random()*2);
		 if(i==0){i=-1;}else{i=1;}
		 return  i;
	 }
	 
	static void CreateWidjets(){
		RootF= new JFrame("ShipsFishing");
		RootF.pack();
		RootF.setVisible(true);
		RootF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RootF.setSize(WorldMaxX, WorldMaxY);
		RootF.setLocation(280, 0); 
	 	ShowGraphicsPanel(true);
		RootF.addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent e) {
	                int key = e.getKeyCode();
	                int s=Player.Speed;
	                if (key == KeyEvent.VK_W) { Player.MuveY(-1*s);
	                }else if(key == KeyEvent.VK_S){ Player.MuveY(1*s);
	                }else if(key == KeyEvent.VK_A){ Player.MuveX(-1*s);
	                }else if(key == KeyEvent.VK_D){ Player.MuveX(1*s);
	                }
	            }
	        });
		 RootF.setFocusable(true);		
	}
	
	static JPanel ShowGraphicsPanel(boolean isShowObjects){
		PGraphics = new JPanel(){
    	 	public void paint(Graphics g) {
    	 	  super.paint(g);
    	      if(isShowObjects){
        	 	  g.setColor(new Color (200,0,0));
        		  for(Fish f: fishs){
        			  if(f.isFreedom){
        			g.drawOval(f.X, f.Y, 2, 2);
        			  }
      		      } 
        		  g.setColor(new Color (0,120,0));
        		  for(Ships s: ships){
          			g.drawOval(s.X, s.Y, 3, 3);
        		  } 
        		  g.setColor(new Color (0,200,0));
        		  g.drawOval(Player.X, Player.Y, 4, 4);
        		  g.setColor(new Color (0,100,100));
        		  int ii=Player.reage/2;
        		  g.drawOval(Player.X-ii/2+2, Player.Y-ii/2+2, ii, ii); 
    	      }
    	    }}; 
       PGraphics.setBackground(new  Color(0,20,0));
       RootF.add(PGraphics); 
       return PGraphics;
	}
	
	static JButton createButton(String T,JPanel jp,int i){
		JButton bt =new JButton();
		FilterEvent FE= new FilterEvent(i,bt,T);
		bt.setAction(FE);
		bt.getActionMap().put(T, FE);
		bt.setSize(400, 50);
		bt.setText(T+" [ off ]");
		jp.add(bt);
		return bt;
	}
 
	static JButton[] nn=new JButton[5];
	static String[] columnNames = {"PlayerName","Ammount","CountCell","Price","FishType-A","FishType-B","FishType-C","FishType-D","FishType-E","FishType-F","FishType-G"};
	static void CreateWidjetStatistic(){
		Statistic= new JFrame("StatisticFishing");
		JPanel jkj= new JPanel();
		Statistic.add(jkj,BorderLayout.NORTH);
		nn[0]=createButton("Player alphabet sorting",jkj,1);
		nn[1]=createButton("Ammount sorting",jkj,2);
		nn[2]=createButton("Cell sorting",jkj,3);
		nn[3]=createButton("Price sorting",jkj,4);
		nn[4]=createButton("downloaded ships",jkj,5);
		sBufferD=ships;//Memorry
       sPane = new JScrollPane(new JTable(GenerateStatistic(ships), columnNames));
       Statistic.getContentPane().add(sPane);
       Statistic.setPreferredSize(new Dimension(450, 200));
	   Statistic.pack();
	   Statistic.setVisible(true);
	   Statistic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   Statistic.setSize(1000, 600);
	}
	
	static String[][]  GenerateStatistic(List<Ships> sL){
        int count=sL.size();
        data =new String[count][11];
        for(int i=0;i<count;i++){
      	 Ships s=sL.get(i);
      	 data[i][0]=s.Name;
      	 data[i][1]=s.getAmmount()+"";
      	 data[i][3]=s.getPrice()+"";
      	 data[i][2]=s.hangar.length+"";
      	 data[i][4]=s.GetFishType( "A");
      	 data[i][5]=s.GetFishType( "B");
      	 data[i][6]=s.GetFishType( "C");
      	 data[i][7]=s.GetFishType( "D");
      	 data[i][8]=s.GetFishType( "E");
      	 data[i][9]=s.GetFishType( "F");
      	 data[i][10]=s.GetFishType( "G");
       }
	return data;
	}
	
	static void updateDataBase(List<Ships> sL){
		sPane = new JScrollPane(new JTable(GenerateStatistic(sL), columnNames));
	    Statistic.getContentPane().add(sPane);
	}
     
	static void FilterStatistic(int a,int c){
	   if(a==1){ 
		   if(c==0){ updateDataBase(sBufferD); 
	       }else if(c==2){ 
	    	   ships= ships.stream().sorted((p1,p2)->p2.Name.compareTo(p1.Name)).collect(Collectors.toList());
		     updateDataBase(ships);
	       }else if (c==1){    
	    	   ships= ships.stream().sorted((p1,p2)->p1.Name.compareTo(p2.Name)).collect(Collectors.toList());
	         updateDataBase(ships);
	       }
	   }else if(a==5){
		   if(c==0){  updateDataBase(sBufferD); 
		   }else if(c==2){
			   ships=ships.stream().sorted((p1,p2)->p1.GetAmmoutToStr().compareTo(p2.GetAmmoutToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }else if(c==1){   
			   ships=ships.stream().sorted((p1,p2)->p2.GetAmmoutToStr().compareTo(p1.GetAmmoutToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }
	   }else if(a==2){   
		   if(c==0){  updateDataBase(sBufferD); 
		   }else if(c==2){
			   ships=ships.stream().filter(p->p.getAmmount()>0).sorted((p1,p2)->p1.GetAmmoutToStr().compareTo(p2.GetAmmoutToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }else if(c==1){   
			   ships=ships.stream().filter(p->p.getAmmount()>0).sorted((p1,p2)->p2.GetAmmoutToStr().compareTo(p1.GetAmmoutToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }
	   }else if(a==3){   
		   if(c==0){  updateDataBase(sBufferD); 
		   }else if(c==2){
			   ships=ships.stream().filter(p->p.getAmmount()>0).sorted((p1,p2)->p1.GetCellToStr().compareTo(p2.GetCellToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }else if(c==1){   
			   ships=ships.stream().filter(p->p.getAmmount()>0).sorted((p1,p2)->p2.GetCellToStr().compareTo(p1.GetCellToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }
	   }else if(a==4){   
		   if(c==0){  updateDataBase(sBufferD); 
		   }else if(c==2){
			   ships=ships.stream().filter(p->p.getAmmount()>0).sorted((p1,p2)->p1.GetPriceToStr().compareTo(p2.GetPriceToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }else if(c==1){   
			   ships=ships.stream().filter(p->p.getAmmount()>0).sorted((p1,p2)->p2.GetPriceToStr().compareTo(p1.GetPriceToStr())).collect(Collectors.toList());
			   updateDataBase( ships);
		   }
	   } 
   }	
}
