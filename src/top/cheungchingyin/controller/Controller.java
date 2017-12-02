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
	public void keyPressed(KeyEvent e) {//检测按压下去的按键
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			if(ground.isMoveable(shape, Shape.ROTATE)){//首先要检测该图形是否能够移动后才能执行之后的动作
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

		gamePanel.display(ground, shape);//显示形状和障碍物
	}
	
	public void newGame(){
		shape=shapeFactory.getShape(this);
	}
	
	@Override
	public synchronized boolean isShapeMoveDownable(Shape shape) {//检测图形是否能够下落
		if(ground.isMoveable(shape, shape.DOWN)){
			return true;
		}
		ground.accept(shape);
		this.shape=shapeFactory.getShape(this);//不能和内部的变量混淆，不然第二个图形会不受键盘控制
		return false;
	}

	public Controller(ShapeFactory shapeFactory,Ground ground,GamePanel gamepanel){
		this.shapeFactory=shapeFactory;
		this.ground=ground;
		this.gamePanel=gamepanel;
	}
	
	
}
