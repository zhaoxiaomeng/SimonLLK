import java.awt.*;
import java.math.*;
import java.awt.event.*;
import javax.swing.*;
class Spot{
	public int x,y;
	public Spot() {}
	public Spot(int a,int b){
		x=a;y=b;
	}
}
class Match{
	private int [][]map=new int[32][32];
	private int []cnt=new int[21];
	private int nrow,ncol,nkind;
	private int nres,nhint;
	private Spot marked;
	public void Print(){//Just for Debugging
		System.out.println("nrow="+nrow+" ncol="+ncol+" nkind="+nkind+" nrestart="+nres+" nhint="+nhint);
		System.out.println("The Count Array:");
		for (int i=1;i<=nkind;i++) System.out.print(cnt[i]+'\t');
		System.out.println("\nThe Map Matrix:");
		for (int i=1;i<=nrow;i++){
			for (int j=1;j<=ncol;j++) System.out.print(map[i][j]+"\t");
			System.out.println("");
		}
	}
	public void Reshuffle(){
		int []tmp=new int[21];
		int i,j,x;
		for (i=1;i<=nkind;i++) tmp[i]=cnt[i];
		for (i=1;i<=nrow;i++)
			for (j=1;j<=ncol;j++){
				do{
					x=(int)(nkind*Math.random())+1;
				}while (tmp[x]==0);
				tmp[x]--;
				map[i][j]=x;
			}
		//Print();
	}
	public Match(){
		nrow=ncol=nkind=20;
		nres=2;nhint=5;
		int i;
		for (i=1;i<=nkind;i++) cnt[i]=nrow*ncol/nkind;
		Reshuffle();
	}
	public void Display(){//Repaint
	}
	public boolean isSame(Spot x,Spot y){
		return (map[x.x][x.y]==map[y.x][y.y]);
	}
	public boolean isConnected(Spot x,Spot y){
		return false;
	}
	public void FindOnePair(Spot x,Spot y){
	}
	public void Clickon(Spot x){//Mouse Clicking on the spot x
		if(marked==null) marked=x;
		else if(!isSame(marked,x)) marked=x;
		else if(!isConnected(marked,x)) marked=x;
		else{
			FindOnePair(marked,x);
			marked=null;
		}
	}
	public void GiveHint(){
	}
}
public class S04_LLK_frame extends JFrame{
	private Match match=new Match();
	private JLabel admin_l=new JLabel("Administrator Control Panel:");
	private JTextArea admin_dis=new JTextArea(11,40);
	private JPanel admin_p=new JPanel();
	private JButton admin_add=new JButton("Add New Books");
	public S04_LLK_frame(){
		setSize(1000,700);
		setTitle("Lian Lian Kan");
		setVisible(true);
		admin_add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
			}
		});
		admin_p.add(admin_l);
		admin_p.add(admin_add);
		admin_p.add(admin_dis);
		admin_dis.setLineWrap(true);
		admin_dis.setEditable(false);
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add("North",admin_p);
	}
	public static void main(String []args){
		S04_LLK_frame gui=new S04_LLK_frame();
		gui.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);//Quit the application
			}
		});
	}
}
