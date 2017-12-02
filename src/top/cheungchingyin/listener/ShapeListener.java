package top.cheungchingyin.listener;

import top.cheungchingyin.entties.Shape;

public interface ShapeListener {//形状监听的接口方法

	void shapeMoveDown(Shape shape);
	boolean isShapeMoveDownable(Shape shape);
	
}
