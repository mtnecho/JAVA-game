package cuc.states;
import java.awt.event.KeyEvent;

import cuc.gameObjects.Avatar;

public class MoveState extends ActorState {

	public MoveState(Avatar awatar, float maxLifeTime, int speed, String animFile, int col, int row,
			int maxFramePlayNum, String soundFile) {
		super(awatar, maxLifeTime, speed, animFile, col, row, maxFramePlayNum, soundFile);
		// TODO Auto-generated constructor stub
		
	}
	//��д���º���
	public void update(){
		if( System.currentTimeMillis() - startTime >= maxLifeTime ){ //�����״̬ʱ�䵽��
			avatar.onStateFinish(this);   //֪ͨ�����ﱾ״̬������
    	}else{
    		super.update();   
    	}		
	}
}
