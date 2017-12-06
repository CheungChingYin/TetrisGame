package top.cheungchingyin.entties;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import top.cheungchingyin.listener.ShapeListener;
import top.cheungchingyin.test.Game;

public class Shape {
	
	public static final int ROTATE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	private int[][]body;
	private int status;
	private int left;
	private int top;//边界
	
	
	public int[][] getBody() {
		return body;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public void setBody(int[][] body) {
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ShapeListener getListener() {
		return listener;
	}

	public void setListener(ShapeListener listener) {
		this.listener = listener;
	}

	private ShapeListener listener;//创建一个监听属性
	
	private boolean getFlagByPoint(int x,int y){
		return body[status][y*4+x]==1;//??????????提问
	}
	
	public void drawMe(Graphics g){//画出形状
		System.out.println("Shape'draw");
		g.setColor(Color.blue);//把形状变为蓝色
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				if(getFlagByPoint(x, y)){//画方格
					g.fill3DRect((left+x)*Global.CELL_SIZE, (top+y)*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	public boolean isMember(int x,int y,boolean rotate){//依次去除图形中属于每个图形的点，判断是否已经超出图形区域
		int tempStatus =status;
		if(rotate){//当图形旋转的时候
			tempStatus = (status + 1)% body.length;//下一个旋转的图像
		}
		return body[tempStatus][y * 4 +x] == 1;
	}
	
	public void rotate(){//画出形状旋转
		System.out.println("shape's rotate");
		status=(status+1)%body.length;//形状旋转
	}
	
	public void moveDown(){
		System.out.println("shape's moveDown");
		top++;
	}
	
	public void moveLeft(){
		System.out.println("shape's moveLeft");
		left--;
	}
	
	public void moveRight(){
		System.out.println("shape's moveRight");
		left++;
	}
	
	private class ShapeDriver implements Runnable{
		
		public void run(){//方框不断下落
			while(listener.isShapeMoveDownable(Shape.this)){
				moveDown();
				listener.shapeMoveDown(Shape.this);
				try{
					Thread.sleep(400);
					
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public Shape(){
		
		left=0;//图形出生的位置
		top=0;
		new Thread(new ShapeDriver()).start();
	}
	
	public void addShapeListener(ShapeListener l){//监听器注册
		
		if(l!=null){
			this.listener=l;
		}
	}
	
}
