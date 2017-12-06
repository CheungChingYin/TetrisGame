package top.cheungchingyin.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import top.cheungchingyin.entties.Global;
import top.cheungchingyin.entties.Ground;
import top.cheungchingyin.entties.Shape;

public class GamePanel extends JPanel {

	private Ground ground;
	private Shape shape;
	
	public void display(Ground ground,Shape shape){
		System.out.println("Gamepanel's display");
		this.ground=ground;
		this.shape=shape;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//redrawing
		g.setColor(new Color(0xcfcfcf));//设置画笔颜色为灰色
//		g.setColor(Color.GRAY);//设置背景颜色
		g.fillRect(1, 0, Global.WIDTH*Global.CELL_SIZE, Global.HEIGHT*Global.CELL_SIZE);//解决拖影问题，填充了灰色与背景颜色相同所以解决了拖影问题
		if(shape!=null && ground!=null){
			shape.drawMe(g);
			ground.drawMe(g);
		}
	}
	public GamePanel(){
		this.setSize(Global.WIDTH*Global.CELL_SIZE,Global.HEIGHT*Global.CELL_SIZE);
		
	}
	
}
