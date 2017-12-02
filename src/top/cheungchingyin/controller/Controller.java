package top.cheungchingyin.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import top.cheungchingyin.entties.Ground;
import top.cheungchingyin.entties.Shape;
import top.cheungchingyin.entties.ShapeFactory;
import top.cheungchingyin.listener.ShapeListener;
import top.cheungchingyin.view.GamePanel;

public class Controller extends KeyAdapter implements ShapeListener {

	private Shape shape;
	private Ground ground;
	private ShapeFactory shapeFactory;
	private GamePanel gamePanel;
	
	@Override
	public void keyPressed(KeyEvent e) {//��ⰴѹ��ȥ�İ���
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			if(ground.isMoveable(shape, Shape.ROTATE)){//����Ҫ����ͼ���Ƿ��ܹ��ƶ������ִ��֮��Ķ���
				shape.rotate();
			}
			break;
		case KeyEvent.VK_DOWN:
			if(ground.isMoveable(shape, Shape.DOWN)){
				shape.moveDown();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(ground.isMoveable(shape, Shape.LEFT)){
				shape.moveLeft();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(ground.isMoveable(shape, Shape.RIGHT)){
				shape.moveRight();
			}
			break;
		}
	}

	@Override
	public void shapeMoveDown(Shape shape) {

		gamePanel.display(ground, shape);//��ʾ��״���ϰ���
	}
	
	public void newGame(){
		shape=shapeFactory.getShape(this);
	}
	
	@Override
	public synchronized boolean isShapeMoveDownable(Shape shape) {//���ͼ���Ƿ��ܹ�����
		if(ground.isMoveable(shape, shape.DOWN)){
			return true;
		}
		ground.accept(shape);
		this.shape=shapeFactory.getShape(this);//���ܺ��ڲ��ı�����������Ȼ�ڶ���ͼ�λ᲻�ܼ��̿���
		return false;
	}

	public Controller(ShapeFactory shapeFactory,Ground ground,GamePanel gamepanel){
		this.shapeFactory=shapeFactory;
		this.ground=ground;
		this.gamePanel=gamepanel;
	}
	
	
}
