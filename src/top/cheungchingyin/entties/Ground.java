package top.cheungchingyin.entties;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import top.cheungchingyin.test.Game;
import top.cheungchingyin.view.GamePanel;

public class Ground {
	private int[][] obstacles=new int[Global.WIDTH][Global.HEIGHT];//�����ʾ�ϰ����ϰ���
	ScoreTotal st=new ScoreTotal();//ȫ�ֱ���
	
	public void accept(Shape shape){							//����ͼ�Σ���ͼ�α�Ϊ�ϰ���
		System.out.println("Ground's appept");
		for(int x = 0;x < 4;x++){
			for(int y = 0;y < 4;y++){
				if(shape.isMember(x, y, false)){
					obstacles[shape.getLeft()+x][shape.getTop()+y]=1;//�������ռ���������Ϊ1
				}
			}
		}
		for(int y=Global.HEIGHT-1;y>=0;y--){
			
			deltetFullLine();//ɾ������
		}
	}
	
	public void drawMe(Graphics g){										// �����ϰ�����״
		g.setColor(Color.GREEN);//��ͼ�α�Ϊ�ϰ���ʱ��Ϊ��ɫ
		System.out.println("Ground's drawMe");
		for(int x = 0;x<Global.WIDTH;x++){
			for(int y = 0;y<Global.HEIGHT;y++){
				if(obstacles[x][y]==1){
					g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	public boolean isMoveable(Shape shape,int action){//�õ���ǰλ����Ϣ������ͼ���Ƿ��ܹ��ƶ�
		int left = shape.getLeft();
		int top = shape.getTop();
		switch (action) {//������Ϊ
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
		for(int x =0;x < 4;x++){//���ƣ����ƣ������ж��Ƿ���߽�
			for(int y = 0 ;y < 4;y++){
				if(shape.isMember(x, y,action == Shape.ROTATE)){
					if(top + y>=Global.HEIGHT  || left + x<0 || left +x>=Global.WIDTH|| obstacles[left+x][top+y]==1){//����һ��ͼ�εĴ�Сռ����4x4����Ҫ��֤4x4�����ϵĵ�λ�������߽磬����������ǲ���߽�ģ�����false
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void deltetFullLine(){
		
		for(int y = Global.HEIGHT-1;y>0;y--){//һ��һ�е��ж��Ƿ�����һ��
			int fullLine=0;
			for(int x=0;x<Global.WIDTH;x++){//�ж���һ���Ƿ��пհ�
				if(obstacles[x][y] == 1){
					fullLine+=1;
				}
			}
			if(fullLine==Global.WIDTH){
				deleteLine(y);//������һ��
//				ScoreTotal st =new ScoreTotal();//������new�Ļ������ۼ�
				st.addScore();
				Game.setJl2(""+st.getScore());
			}
		}
		
		
	}
	
	private void deleteLine(int lineNum){//��ȥһ�еķ���
		for(int y=lineNum ; y>0 ; y--){//�����еĵط���ʼ������һ��һ�м��
			for(int x=0;x<Global.WIDTH;x++){
				obstacles[x][y]=obstacles[x][y-1];
				
			}
			
		}
		for(int x=0;x<Global.WIDTH;x++){//��һ��ȫ����ɿհ�
			obstacles[x][0]=0;
		}
	}
}
