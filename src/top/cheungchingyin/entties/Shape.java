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
	private int top;//�߽�
	
	
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

	private ShapeListener listener;//����һ����������
	
	private boolean getFlagByPoint(int x,int y){
		return body[status][y*4+x]==1;//??????????����
	}
	
	public void drawMe(Graphics g){//������״
		System.out.println("Shape'draw");
		g.setColor(Color.blue);//����״��Ϊ��ɫ
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				if(getFlagByPoint(x, y)){//������
					g.fill3DRect((left+x)*Global.CELL_SIZE, (top+y)*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	public boolean isMember(int x,int y,boolean rotate){//����ȥ��ͼ��������ÿ��ͼ�εĵ㣬�ж��Ƿ��Ѿ�����ͼ������
		int tempStatus =status;
		if(rotate){//��ͼ����ת��ʱ��
			tempStatus = (status + 1)% body.length;//��һ����ת��ͼ��
		}
		return body[tempStatus][y * 4 +x] == 1;
	}
	
	public void rotate(){//������״��ת
		System.out.println("shape's rotate");
		status=(status+1)%body.length;//��״��ת
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
		
		public void run(){//���򲻶�����
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
		
		left=0;//ͼ�γ�����λ��
		top=0;
		new Thread(new ShapeDriver()).start();
	}
	
	public void addShapeListener(ShapeListener l){//������ע��
		
		if(l!=null){
			this.listener=l;
		}
	}
	
}
