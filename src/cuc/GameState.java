package cuc;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cuc.gameObjects.Avatar;

public abstract class GameState {
	protected GameStarter game;
	// ��ʼ������  
    public abstract void init() ;  
      
    // ���³����߼�  
    public abstract void update(float deltaTime);  
      
    // ��Ⱦ����  
    public abstract void render(); 
      
    // ���ٳ���  
    public abstract void dispose() ; 
        // ����������������Դ�Ĵ���   
    //����������Ϸ�������		
	//��������ͼ����Ϸ�ı���ͼ��ǰ��ͼ����2.5D��Ϸ���еļ�����
     //��Ϸ��������ͼ
    //��Ϸ����ǰ��ͼ
	
   //��Ϸ�е���һ�����󣬼���ҿ��Կ��Ƶ���Ϸ����
	public GameState(GameStarter game) {
		this.game = game;
	}

	//��Ϸ�������Ⱦ����
	public abstract void render(Graphics g);
	//��Ϸ�����״̬���º���������˵����Ϸ���溯��
	//�涨����Ϸ�еĶ��󾭹�һ֡���״̬�仯������ռ����ꡣ
	public abstract void update();
	
	public abstract void keyPressed(int key) ;

}
