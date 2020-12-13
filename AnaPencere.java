import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class AnaPencere extends JFrame{

	private int mGenislik = 600;
	private int mYukseklik = 600;
	private static AnaPencere mPencere = null;
	public AnaPencere() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDimension(mGenislik,mYukseklik);
		setResizable(false);
		Yilan yilan = new Yilan();
		add(yilan);
	
		
	}
	public static AnaPencere PencereGetir() {
		if(mPencere == null) {
			mPencere = new AnaPencere();
			return mPencere;
		}
		return new AnaPencere();
		
		
	}
	public void setDimension(int Genislik,int Yukseklik) {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
		int posX = 0;
		int posY = 0;
		
		if(mGenislik+100 > dim.width) {
			mGenislik = dim.width-100;
		}
		if(mYukseklik+100 > dim.height) {
			mYukseklik = dim.height-100;
		}
		
		posX = (dim.width - mGenislik)/2; 
		posY = (dim.height - mYukseklik)/2;
		setBounds(posX, posY, mGenislik, mYukseklik);
}
}
