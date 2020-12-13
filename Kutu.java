import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public class Kutu extends JLabel{

	public int mYon = YON.SAG;
	public int mGenislik = 20;
	Kutu(){
		setBounds(100, 100, mGenislik, mGenislik);
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2= (Graphics2D)g;
		super.paint(g);
		
	
		
		Rectangle2D rect = new Rectangle2D.Double(2,2,getWidth()-3,getHeight()-3);
		g2.setColor(Color.black);
	
		g2.setStroke(new BasicStroke(2));
		g2.draw(rect);
		g2.setColor(Color.red);
		g2.fill(rect);
		
	}
	public void SolaGit() {
		int posX = getX();
  	  int posY = getY();
  	  posX -= mGenislik;
  	  setBounds(posX,posY,mGenislik,mGenislik);
	}
	public void sagaGit() {
		int posX = getX();
  	  int posY = getY();
  	  posX += mGenislik;
  	 setBounds(posX,posY,mGenislik,mGenislik);
	}
	public void yukariGit() {
		int posX = getX();
	  	  int posY = getY();
	  	  posY -= mGenislik;
	  	 setBounds(posX,posY,mGenislik,mGenislik);
	}
	public void asagiGit() {
		int posX = getX();
	  	  int posY = getY();
	  	  posY += mGenislik;
	  	 setBounds(posX,posY,mGenislik,mGenislik);
	}
	public Kutu KutuOlustur() {
		Kutu k = new Kutu();
		int X = getX();
		int Y = getY();
		k.setBounds(X, Y, mGenislik, mGenislik);
		k.mYon = -mYon;
		k.Hareket();
		k.mYon = mYon;
		
		
		
		
		return k;
		
		
	}
	public void Hareket() {
		if(mYon == YON.SOL) {
			SolaGit();
		}
		else if(mYon == YON.SAG) {
			sagaGit();
		}
		else if(mYon == YON.YUKARI) {
			yukariGit();
		}
		else {
			asagiGit();
		}
	}
}
