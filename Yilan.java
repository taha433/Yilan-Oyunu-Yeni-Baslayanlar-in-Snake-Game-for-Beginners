import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Yilan extends JLabel {
	public Kutu mHead = new Kutu();

	public Yem mYem = new Yem();
	public ArrayList<Kutu> kutular = new ArrayList<Kutu>();
	public Timer mTimer = null;
	public Random random =null;
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2= (Graphics2D)g;
		Rectangle2D rect = new Rectangle2D.Double(0,0,getWidth(),getHeight());
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke(10));
		g2.draw(rect);
		
	}

	Yilan(){
		random = new Random(System.currentTimeMillis());
		addKeyListener(new KlavyeKontrol());
		setFocusable(true);
		
		mTimer = new Timer(100,new TimerKontrol());
		mTimer.start();

		kutular.add(mHead);
		for(int i = 1;i<10;i++) {
KuyrukEkle();			
		}
	add(mYem);
	add(mHead);
	}
	public void yemEkle() {
		int Width = getWidth() - 30-mYem.mGenislik;
		int Height = getHeight() - 30- mYem.mGenislik;
		
		int posX = 10+Math.abs(random.nextInt()) % Width;
		int posY =10+ Math.abs(random.nextInt()) % Height;
	posX = posX - posX%20;
		posY = posY - posY%20;	
		for(int i = 0;i< kutular.size();i++) {
			if((posX == mHead.getX()) && (posY==kutular.get(i).getY())) {
				yemEkle();
				return;
			}
		}
		mYem.setPosition(posX, posY);
		
	}
	public void KuyrukEkle() {
		Kutu K = kutular.get(kutular.size()-1).KutuOlustur();
		kutular.add(K);
		add(K);
		
	}
	public void HepsiniHareketEttir() {
		for(int i = kutular.size()-1; i > 0;i--) {
			Kutu onceki = kutular.get(i-1);
			Kutu sonraki = kutular.get(i);
			kutular.get(i).Hareket();
			sonraki.mYon = onceki.mYon;

		}
		mHead.Hareket();
	}
	public boolean CarpismaVarmi() {
		int Kalem = 10;
		int Genislik = getWidth();
		int Yukseklik = getHeight();
		
		
		if(mHead.getX() <= 10) {
			return true;
		}
		if(mHead.getX()+mHead.mGenislik >= Genislik-Kalem) {
			return true;
		}
		if(mHead.getY() <= 10) {
			return true;
		}
		if(mHead.getY()+mHead.mGenislik >= Yukseklik-Kalem) {
			return true;
		}
		for(int i = 1;i< kutular.size(); i++) {
			int X = kutular.get(i).getX();
			int Y = kutular.get(i).getY();

			if((X == mHead.getX()) && (Y == mHead.getY())){
				return true;
			}
					}
		if((mYem.getX() == mHead.getX()) && (mYem.getY() == mHead.getY())) {
			KuyrukEkle();
			yemEkle();
		}
		return false;
	}
	class KlavyeKontrol implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
          if(e.getKeyCode() == KeyEvent.VK_LEFT)	{
        	  if(mHead.mYon !=YON.SAG)
        	    mHead.mYon = YON.SOL;
          }
          if(e.getKeyCode() == KeyEvent.VK_RIGHT)	{
        	  if(mHead.mYon !=YON.SOL)
        	    mHead.mYon = YON.SAG;
          }
          if(e.getKeyCode() == KeyEvent.VK_UP)	{
        	  if(mHead.mYon !=YON.ASAGI)
        	     mHead.mYon = YON.YUKARI;
          }
          if(e.getKeyCode() == KeyEvent.VK_DOWN)	{
        	  if(mHead.mYon !=YON.YUKARI)
        	     mHead.mYon = YON.ASAGI;
          }
		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			
		}
		
	}
	class TimerKontrol implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
	     
			
			HepsiniHareketEttir();
			if(CarpismaVarmi()) {
				mTimer.stop();
			}
		}
		
	}
}
