package cuc.states;
import java.awt.event.KeyEvent;

import cuc.gameObjects.Avatar;

public class StopState extends ActorState {

	public StopState(Avatar awatar, float maxLifeTime, int speed, String animFile, int col, int row,
			int maxFramePlayNum, String soundFile) {
		super(awatar, maxLifeTime, speed, animFile, col, row, maxFramePlayNum, soundFile);
		// TODO Auto-generated constructor stub
		curDirection = 0;
		curFrame = 0;
	}
	//��д����ĸ��·�������ֹ״̬ʲôҲ����
	public void update(){}
}
