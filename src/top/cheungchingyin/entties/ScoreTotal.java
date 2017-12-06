package top.cheungchingyin.entties;

public class ScoreTotal {
	private int score=0;
	
	public void addScore(){//加分
		score+=10;
	}
	
	public int getScore(){//获得当前分数
		return score;
	}
	
	
}
