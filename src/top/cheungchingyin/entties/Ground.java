package top.cheungchingyin.entties;

import java.awt.Color;
import java.awt.Graphics;

public class Ground {
	private int[][] obstacles=new int[Global.WIDTH][Global.HEIGHT];//储存表示障碍的障碍物

	public void accept(Shape shape){							//接收图形，把图形变为障碍物
		System.out.println("Ground's appept");
		for(int x = 0;x < 4;x++){
			for(int y = 0;y < 4;y++){
				if(shape.isMember(x, y, false)){
					obstacles[shape.getLeft()+x][shape.getTop()+y]=1;
				}
			}
		}
		for(int y=Global.HEIGHT-1;y>=0;y--){
			
			deltetFullLine();
		}
	}
	
	public void drawMe(Graphics g){										// 画出障碍物形状
		g.setColor(Color.GREEN);//当图形变为障碍物时变为绿色
		System.out.println("Ground's drawMe");
		for(int x = 0;x<Global.WIDTH;x++){
			for(int y = 0;y<Global.HEIGHT;y++){
				if(obstacles[x][y]==1){
					g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	public boolean isMoveable(Shape shape,int action){//得到当前位置信息，测试图形是否能够移动
		int left = shape.getLeft();
		int top = shape.getTop();
		switch (action) {//动作行为
		case Shape.LEFT:
			left--;
			break;
		case Shape.RIGHT:
			left++;
			break;
		case Shape.DOWN:
			top++;
			break;
		}
		for(int x =0;x < 4;x++){//左移，右移，下移判断是否出边界
			for(int y = 0 ;y < 4;y++){
				if(shape.isMember(x, y,action == Shape.ROTATE)){
					if(top + y>=Global.HEIGHT || left + x<0 || left +x>=Global.WIDTH || obstacles[left+x][top+y]==1){//由于一个图形的大小占用是4x4，需要保证4x4的右上的点位不超出边界，这里的条件是草畜边界的，返回false
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void deltetFullLine(){
		
		for(int y = Global.HEIGHT-1;y>0;y--){//一行一行地判断是否满足一行
			boolean full=true;
			for(int x=0;x<Global.WIDTH;x++){//判断这一行是否有空白
				if(obstacles[x][y] == 0){
					full=false;
				}
			}
			if(full){
				deleteLine(y);//消除这一行
			}
		}
		
	}
	
	private void deleteLine(int lineNum){//消去一行的方法
		for(int y=lineNum ; y>0 ; y--){//从满行的地方开始，往上一行一行检测
			for(int x=0;x<Global.WIDTH;x++){
				obstacles[x][y]=obstacles[x][y-1];
			}
		}
		for(int x=0;x<Global.WIDTH;x++){//第一行全部变成空白
			obstacles[x][0]=0;
		}
	}
}
