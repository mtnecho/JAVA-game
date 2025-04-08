package cuc;
import cuc.gameObjects.*;
import cuc.states.*;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Panel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
/*
 * ��Ϸ���ࣺ��main������
 * ��Ϸ���ڣ���Swing����еĴ�����JFrame������
 * ������һ����Ϸ����
 * �����û����룬��ת��
 */

enum State{
     GameRunning,
	 GamePaused,
	 GameFinished;
}
public class GameStarter extends JFrame implements KeyListener, Runnable{
	GameWorld gameWorld;   //���������һ����Ϸ�������
	GameScene gameScene;  //�����еڶ�����Ϸ�������
	Thread animThread;    //�������̶߳���
	static int width = 1500;   //������
	static int height = 1200;    //����߶�
	boolean levelUp;  //����ת������
	public Menu menu;    //�������ж�����Ϸ�˵�����
	public Labelpaint labpaint;
	boolean gameSaved;    //��Ϸ���ȱ���
	boolean gameLoaded;   //��Ϸ���ȼ���
	//����������������Ϸ˫���弼������
	Image offScreen;      //�λ���
	Graphics offScreenGraphics;   //�λ����ϵ�ͼ�ι��߶���
	State state = State.GameRunning;
	BufferedWriter bw = null;
	BufferedReader br = null;
	private Startup start;
	public boolean flag = false;    //�л�����
	public GameState gameState;
	public GameStarter(){     
		//���ô��ڵ�λ�úʹ�С
    	this.setBounds(100, 100, 1500, 1200);
    	//�����Ǹ����ڣ�Ҳ����һ�������¼�Դ����Ҫ��Ӽ����¼��������ߡ�
    	//��Ϊ���౾���Ѿ�ʵ���˼��������߽ӿڣ��������౾�����һ�������¼�������
    	this.addKeyListener(this);
    	//�����ڹرհ�ť���ӹرչ���
    	this.addWindowListener(new WindowAdapter(){
    		public void windowClosing(WindowEvent we){
    			System.exit(0);
    		}
    	}); 
        
    	
    	menu = new Menu(this);    //��ʼ����Ϸ�˵�����
    	setLayout(null);    //ȡ�����ֹ��������Զ���˵�����ʾλ��
    	add(menu);    //���˵�������뵽������
    	menu.setVisible(false);    //���ز˵�
    	
    	labpaint = new Labelpaint(this);    //��ʼ����Ϸ�˵�����
    	setLayout(null);    //ȡ�����ֹ��������Զ���˵�����ʾλ��
    	add(labpaint);    //���˵�������뵽������
    	  
    	gameWorld = new GameWorld(this);	//��ʼ����Ϸ���� 
    	gameScene = new GameScene(this);
    	
    	gameState = new Startup(this);
    	
    	animThread = new Thread(this);   //�����̶߳���
    	animThread.start();              //�����̣߳���ִ��run����     	
    	this.setVisible(true);          //��ʾ����
    	
         
    	} 

	//�����main����
    public static void main(String[] args) {	
    	//������������Լ���
    	new GameStarter();   
	} 
    //JFrame���ڵĹ��Ӻ��������������ͼ����Ⱦ
    public void paint(Graphics g){    	
    		if(offScreenGraphics == null){
        		//�����λ��棬��С����Ϸ����һ����
        		offScreen = createImage(this.getSize().width,this.getSize().height);
        		//��ôλ����ϵ�ͼ�ζ���
        		offScreenGraphics = offScreen.getGraphics();
            }  
    		//���ȶԴλ�����������Ȼ�����²���
        	offScreenGraphics.setColor(Color.white); //���ð�ɫ��ˢ
        	offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
    	//����Ϸ������Ⱦ���λ�����    	
    	    gameState.render(offScreenGraphics); 
    	//���λ���������������
    	g.drawImage(offScreen,0,0,this);
    }
	//ʵ����Runnable�ӿڵĽӿں���run��
	//�䵱��Ϸ�Ķ����߳�
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while( animThread != null ){    //��Ϸ����ѭ��
                gameState.update(); //��Ϸ������ͣ
    	    	try {
    	    		//�߳����ߣ���ͣ��40���룬�����Ϸ��������25֡/��
    				Thread.sleep(40);  
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    	//����ϵͳ�ػ������ٴε���paint����
    			repaint();  
		}	
	}
	//�����û����̰���
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();   //ȡ�ü����¼��ļ�ֵ
		if(state == State.GameRunning) {
			switch(key) {
			    case KeyEvent.VK_ESCAPE:
			    	menu.setLabel(0,"resume");
			        changeState(State.GamePaused);
			        break;
			}
		}
		//���̰����¼�ת������Ϸ���紦��
		gameWorld.keyPressed(key);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	public void gameUpdate() {
		if(state == State.GameRunning) {    //����ǰ��Ϸ״̬Ϊ����״̬
			menu.setVisible(false);    //������Ϸ�˵�
		}
		
		else if(state == State.GamePaused) {    //����ǰ��Ϸ״̬Ϊ��ͣ״̬
			menu.setVisible(true);    //��ʾ��Ϸ�˵�
		}
	}
	public void changeState(State a)
	{
		state = a;
		gameUpdate();
	}
	//�������ж���saveGame()�������ڱ��浱ǰ��Ϸ����
	public void saveGame() {
		String s1,s2 = "";
		//�ַ�������s1���ڱ��浱ǰ״̬����Ϸ��������ҵĸ�������ֵ���ö��ŷָ�
		s1 = String.valueOf(Avatar.getCx())+","    //������Һ�����ֵ
				+String.valueOf(Avatar.getCy())+","    //�������������ֵ
				//+String.valueOf(Avatar.curState)+","    //������ҵ�ǰ״̬
		        +String.valueOf(ActorState.curDirection)+","    //������ҵ�ǰ����
		        +String.valueOf(ActorState.curFrame)+","    //������ҵ�ǰ����֡
		        +String.valueOf(ActorState.curFramePlayNum)+","   //������ҵ�ǰ����֡�Ѿ����ŵĴ���
		        +String.valueOf(flag);    //���浱ǰ�ؿ����
		
		AudioClip sound;        //״̬������
		try {
			bw = new BufferedWriter(new FileWriter("save.txt"));
			bw.write(s1);    //��s1ֵд���ļ�
			bw.newLine();    //����һ��
			bw.write(s2);    //��s2��ֵд���ļ�
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		changeState(State.GameRunning);
	}
	
	//�������ж���loadGame()�������ڶ�ȡ��һ�����������Ϸ����
	public void loadGame() {
		int i;
		boolean j;
		String s1;
		String [] ss;
		try {
			//ʹ��BufferedReader����ӡ�save.txt"�ж�ȡ����������ֵ
			br = new BufferedReader(new FileReader("save.txt"));
			s1 = br.readLine();    //��ȡ�ַ�������s1��ֵ
			ss = s1.split(",");    //�Զ�����Ϊ�ָ����ָ��ַ���s1�����ָ����ַ������浽�ַ�������ss��
			j = Boolean.parseBoolean(ss[5]);
			flag = j;
			if(flag == true) {
				switchScene(gameScene);
			}
			if(flag == false) {
				switchScene(gameWorld);
			}
			i = Integer.parseInt(ss[0]);
		    Avatar.setCx(i);  //��ȡ��ұ��������ֵ
		    i = Integer.parseInt(ss[1]);
		    Avatar.setCy(i);    //��ȡ��ұ���������ֵ
		    i = Integer.parseInt(ss[2]);
		    ActorState.curDirection = i;    //��ȡ��ұ��泯��
		    i = Integer.parseInt(ss[3]);
		    ActorState.curFrame = i;    //��ȡ��ұ��涯��֡
		    i = Integer.parseInt(ss[4]);
		    ActorState.curFramePlayNum = i;   //��ȡ��ҵ�ǰ����֡�Ѿ����Ŵ���
		    br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		changeState(State.GameRunning);
	}

	public void changeStateTo(GameState gs) {
		 //TODO Auto-generated method stub
		this.gameState = gs;
	}

	public void switchScene(GameState gamestate) {
		this.gameState = gamestate;
	    }
}
        

  