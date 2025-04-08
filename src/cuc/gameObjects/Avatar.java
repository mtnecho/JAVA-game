package cuc.gameObjects;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import cuc.GameScene;
import cuc.GameState;
import cuc.GameWorld;
import cuc.states.ActorState;
import cuc.states.MoveState;
import cuc.states.StopState;

//��Ϸ�����һ�֣��������࣡�����Ա���ҿ��Ƶ���Ϸ����
public class Avatar {
	GameState gameState;  //������������Ϸ����
	GameScene gameScene;
    static int cx,cy;            //����Ϸ�����е�x�����y����
    int width,height;      //����������Ϸ������Ŀ�͸�    
    int curSpeed;          //��ǰ���ٶ�
    //�����������״̬����ֹ������
    StopState stop;        //��ֹ״̬
    MoveState move;        //����״̬
    public static ActorState curState;     //��ǰ״̬
    	
	public Avatar(GameState gamestate, int cx, int cy, int w, int h) {
		this.gameState = gamestate;
    	this.cx = cx;
    	this.cy = cy;
    	this.width = w;
    	this.height = h;     	
    	
    	//���������������״̬��ע�⣺������״̬�Ķ�����Ӳ���룬�������滹Ҫ�Ż���
    	stop = new StopState(this, 1000000000,0,"����-stop.png",1,4,4,"");
    	move = new MoveState(this,1500,4,"����-move.png",4,4,2,"��·��.wav");
    	curState = stop;    //���õ�ǰ״̬Ϊstop
	}
	public Avatar(GameScene gamestate2, int cx2, int cy2, int w, int h) {
		// TODO Auto-generated constructor stub
		this.gameScene = gamestate2;
    	this.cx = cx;
    	this.cy = cy;
    	this.width = w;
    	this.height = h;     	
    	
    	//���������������״̬��ע�⣺������״̬�Ķ�����Ӳ���룬�������滹Ҫ�Ż���
    	stop = new StopState(this, 1000000000,0,"����-stop.png",1,4,4,"");
    	move = new MoveState(this,1500,4,"����-move.png",4,4,2,"��·��.wav");
    	curState = stop;    //���õ�ǰ״̬Ϊstop
	}
	//������ĸ��º���
    public void update(){    	
    	curState.update();    	 //ת������ǰ״̬���и���
    }    
    /*
	 * ״̬���ں�Ļص�����
	 * ����Ϸ��ɫ���������һ��״̬����������״̬�Լ�������
	 */
	public void onStateFinish(ActorState state){
		if( state.getClass() == MoveState.class ){  //���ֹͣ��������״̬
			//ת��ֹͣ״̬
			switchState(new StopState(this, 1000000000,0,"����-move.png",4,4,4,""));    //�����������õ�ǰ״̬   
		}
	}
    
	//�������״̬ת������������Ϊ��һ��״̬����
	public void switchState(ActorState nextState){
		nextState.setCurDirection(curState.getCurDirection()); //��һ��״̬�ķ���̳�����һ��״̬
		curState.stop();        //��ǰ״ֹ̬ͣ
		curState = nextState;   //��ǰ״̬�л���ϴһ��
		curState.start();       //��ǰ״̬��ʼ		
	}
	//���������Ⱦ����
	public void render(Graphics g){		
		curState.render(g);		 
    }  
	
	//������ҵļ�������
	public void keyPressed(int key) {		
		if( key == KeyEvent.VK_W ){  //����w��
			curState.setCurDirection(0);  //����״̬�ĵ�ǰ����Ϊ���Ϸ�			
			this.switchState(move);       //���󰢷���ת��״̬��MoveState
		}else if( key == KeyEvent.VK_D ){
			curState.setCurDirection(1);  //����״̬�ĵ�ǰ����Ϊ���Ϸ�			
			this.switchState(move);       //���󰢷���ת��״̬��MoveState
		}else if( key == KeyEvent.VK_S ){
			curState.setCurDirection(2);  //����״̬�ĵ�ǰ����Ϊ���·�			
			this.switchState(move);       //���󰢷���ת��״̬��MoveState
		}else if( key == KeyEvent.VK_A ){
			curState.setCurDirection(3);  //����״̬�ĵ�ǰ����Ϊ���·�			
			this.switchState(move);       //���󰢷���ת��״̬��MoveState
		}					
	}
	//�ṩ��ȡ����������Ľӿ�
	public static int getCx(){
		return cx;
	}
	public static int getCy(){
		return cy;
	}
	public static void setCx(int x){
		cx = x;
	}
	public static void setCy(int y){
		cy = y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	//���õ�ǰ״̬
	public void setCurState(ActorState next){
		curState = next;
	}
	//��ȡ��ǰ״̬����
	public ActorState getCurState(){
		return curState;
	}
}
