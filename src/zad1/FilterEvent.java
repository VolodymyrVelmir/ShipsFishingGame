package zad1;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class FilterEvent extends AbstractAction{
	FilterEvent(int a,JButton jb,String T){this.a=a;c=0;this.jb=jb;this.T=T;}
    int a,c;
    JButton jb;
    String T;
    
    void setC(){ c++; if (c>=3){c=0;}}
    
	public void actionPerformed(ActionEvent e) {
		 setC();
		 Main.FilterStatistic(a,c);
		 String t="";
		 if(c==0){t=T+"[ off ]";
		  }else if(c==1){ t=T+"[ Max ]";
		  }else if(c==2){ t=T+"[ Min ]";
		 }
		 jb.setText(t);
	}

}
