package cuc.states;
import java.awt.event.KeyEvent;

import cuc.gameObjects.Avatar;

public class MoveState extends ActorState {

	public MoveState(Avatar awatar, float maxLifeTime, int speed, String animFile, int col, int row,
			int maxFramePlayNum, String soundFile) {
		super(awatar, maxLifeTime, speed, animFile, col, row, maxFramePlayNum, soundFile);
		// TODO Auto-generated constructor stub
		
	}
	//重写更新函数
	public void update(){
		if( System.currentTimeMillis() - startTime >= maxLifeTime ){ //如果本状态时间到了
			avatar.onStateFinish(this);   //通知阿凡达本状态到期了
    	}else{
    		super.update();   
    	}		
	}
}
