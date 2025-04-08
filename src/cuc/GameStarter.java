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
 * 游戏主类：带main函数。
 * 游戏窗口：是Swing框架中的窗口类JFrame的子类
 * 包含着一个游戏世界
 * 接收用户输入，并转发
 */

enum State{
     GameRunning,
	 GamePaused,
	 GameFinished;
}
public class GameStarter extends JFrame implements KeyListener, Runnable{
	GameWorld gameWorld;   //主类包含了一个游戏世界对象
	GameScene gameScene;  //主类中第二个游戏世界对象
	Thread animThread;    //代表动画线程对象
	static int width = 1500;   //画面宽度
	static int height = 1200;    //画面高度
	boolean levelUp;  //场景转换设置
	public Menu menu;    //在主类中定义游戏菜单对象
	public Labelpaint labpaint;
	boolean gameSaved;    //游戏进度保存
	boolean gameLoaded;   //游戏进度加载
	//下面两个属性是游戏双缓冲技术所需
	Image offScreen;      //次画面
	Graphics offScreenGraphics;   //次画面上的图形工具对象
	State state = State.GameRunning;
	BufferedWriter bw = null;
	BufferedReader br = null;
	private Startup start;
	public boolean flag = false;    //切换场景
	public GameState gameState;
	public GameStarter(){     
		//设置窗口的位置和大小
    	this.setBounds(100, 100, 1500, 1200);
    	//主类是个窗口，也就是一个键盘事件源，需要添加键盘事件的倾听者。
    	//因为主类本身已经实现了键盘倾听者接口，所以主类本身就是一个键盘事件倾听者
    	this.addKeyListener(this);
    	//给窗口关闭按钮增加关闭功能
    	this.addWindowListener(new WindowAdapter(){
    		public void windowClosing(WindowEvent we){
    			System.exit(0);
    		}
    	}); 
        
    	
    	menu = new Menu(this);    //初始化游戏菜单对象
    	setLayout(null);    //取消布局管理器，自定义菜单的显示位置
    	add(menu);    //将菜单对象加入到主类中
    	menu.setVisible(false);    //隐藏菜单
    	
    	labpaint = new Labelpaint(this);    //初始化游戏菜单对象
    	setLayout(null);    //取消布局管理器，自定义菜单的显示位置
    	add(labpaint);    //将菜单对象加入到主类中
    	  
    	gameWorld = new GameWorld(this);	//初始化游戏世界 
    	gameScene = new GameScene(this);
    	
    	gameState = new Startup(this);
    	
    	animThread = new Thread(this);   //创建线程对象
    	animThread.start();              //启动线程，即执行run函数     	
    	this.setVisible(true);          //显示窗口
    	
         
    	} 

	//主类的main函数
    public static void main(String[] args) {	
    	//创建主类对象（自己）
    	new GameStarter();   
	} 
    //JFrame窗口的钩子函数，在这里进行图形渲染
    public void paint(Graphics g){    	
    		if(offScreenGraphics == null){
        		//创建次画面，大小与游戏窗口一样大
        		offScreen = createImage(this.getSize().width,this.getSize().height);
        		//获得次画面上的图形对象
        		offScreenGraphics = offScreen.getGraphics();
            }  
    		//首先对次画面清屏，不然会留下残留
        	offScreenGraphics.setColor(Color.white); //设置白色画刷
        	offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
    	//将游戏世界渲染到次画面上    	
    	    gameState.render(offScreenGraphics); 
    	//将次画面贴到主画面上
    	g.drawImage(offScreen,0,0,this);
    }
	//实现了Runnable接口的接口函数run。
	//充当游戏的动画线程
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while( animThread != null ){    //游戏动画循环
                gameState.update(); //游戏动画暂停
    	    	try {
    	    		//线程休眠（暂停）40毫秒，如此游戏动画就是25帧/秒
    				Thread.sleep(40);  
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    	//请求系统重画，即再次调用paint方法
    			repaint();  
		}	
	}
	//处理用户键盘按下
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();   //取得键盘事件的键值
		if(state == State.GameRunning) {
			switch(key) {
			    case KeyEvent.VK_ESCAPE:
			    	menu.setLabel(0,"resume");
			        changeState(State.GamePaused);
			        break;
			}
		}
		//键盘按下事件转发给游戏世界处理
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
		if(state == State.GameRunning) {    //若当前游戏状态为运行状态
			menu.setVisible(false);    //隐藏游戏菜单
		}
		
		else if(state == State.GamePaused) {    //若当前游戏状态为暂停状态
			menu.setVisible(true);    //显示游戏菜单
		}
	}
	public void changeState(State a)
	{
		state = a;
		gameUpdate();
	}
	//在主类中定义saveGame()方法用于保存当前游戏进度
	public void saveGame() {
		String s1,s2 = "";
		//字符串变量s1用于保存当前状态下游戏场景及玩家的各个变量值，用逗号分隔
		s1 = String.valueOf(Avatar.getCx())+","    //保存玩家横坐标值
				+String.valueOf(Avatar.getCy())+","    //保存玩家纵坐标值
				//+String.valueOf(Avatar.curState)+","    //保存玩家当前状态
		        +String.valueOf(ActorState.curDirection)+","    //保存玩家当前朝向
		        +String.valueOf(ActorState.curFrame)+","    //保存玩家当前动画帧
		        +String.valueOf(ActorState.curFramePlayNum)+","   //保存玩家当前动画帧已经播放的次数
		        +String.valueOf(flag);    //保存当前关卡编号
		
		AudioClip sound;        //状态的配音
		try {
			bw = new BufferedWriter(new FileWriter("save.txt"));
			bw.write(s1);    //将s1值写入文件
			bw.newLine();    //另起一行
			bw.write(s2);    //将s2的值写入文件
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		changeState(State.GameRunning);
	}
	
	//在主类中定义loadGame()方法用于读取上一次所保存的游戏进度
	public void loadGame() {
		int i;
		boolean j;
		String s1;
		String [] ss;
		try {
			//使用BufferedReader对象从“save.txt"中读取各个变量的值
			br = new BufferedReader(new FileReader("save.txt"));
			s1 = br.readLine();    //读取字符串变量s1的值
			ss = s1.split(",");    //以逗号作为分隔符分割字符串s1，将分割后的字符串保存到字符串数组ss中
			j = Boolean.parseBoolean(ss[5]);
			flag = j;
			if(flag == true) {
				switchScene(gameScene);
			}
			if(flag == false) {
				switchScene(gameWorld);
			}
			i = Integer.parseInt(ss[0]);
		    Avatar.setCx(i);  //读取玩家保存横坐标值
		    i = Integer.parseInt(ss[1]);
		    Avatar.setCy(i);    //读取玩家保存纵坐标值
		    i = Integer.parseInt(ss[2]);
		    ActorState.curDirection = i;    //读取玩家保存朝向
		    i = Integer.parseInt(ss[3]);
		    ActorState.curFrame = i;    //读取玩家保存动画帧
		    i = Integer.parseInt(ss[4]);
		    ActorState.curFramePlayNum = i;   //读取玩家当前动画帧已经播放次数
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
        

  