package top.cheungchingyin.listener;

import top.cheungchingyin.entties.Shape;

public interface ShapeListener {//��״�����Ľӿڷ���

	void shapeMoveDown(Shape shape);
	boolean isShapeMoveDownable(Shape shape);
	
}
