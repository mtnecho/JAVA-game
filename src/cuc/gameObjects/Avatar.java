package cuc.gameObjects;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import cuc.GameScene;
import cuc.GameState;
import cuc.GameWorld;
import cuc.states.ActorState;
import cuc.states.MoveState;
import cuc.states.StopState;

//游戏对象的一种：阿凡达类！即可以被玩家控制的游戏对象
public class Avatar {
	GameState gameState;  //反向引用着游戏世界
	GameScene gameScene;
    static int cx,cy;            //在游戏世界中的x坐标和y坐标
    int width,height;      //阿凡达在游戏世界里的宽和高    
    int curSpeed;          //当前的速度
    //阿凡达的两个状态：静止和行走
    StopState stop;        //静止状态
    MoveState move;        //行走状态
    public static ActorState curState;     //当前状态
    	
	public Avatar(GameState gamestate, int cx, int cy, int w, int h) {
		this.gameState = gamestate;
    	this.cx = cx;
    	this.cy = cy;
    	this.width = w;
    	this.height = h;     	
    	
    	//创建阿凡达的两个状态。注意：这两个状态的动画是硬编码，不灵活，后面还要优化！
    	stop = new StopState(this, 1000000000,0,"书生-stop.png",1,4,4,"");
    	move = new MoveState(this,1500,4,"书生-move.png",4,4,2,"走路声.wav");
    	curState = stop;    //设置当前状态为stop
	}
	public Avatar(GameScene gamestate2, int cx2, int cy2, int w, int h) {
		// TODO Auto-generated constructor stub
		this.gameScene = gamestate2;
    	this.cx = cx;
    	this.cy = cy;
    	this.width = w;
    	this.height = h;     	
    	
    	//创建阿凡达的两个状态。注意：这两个状态的动画是硬编码，不灵活，后面还要优化！
    	stop = new StopState(this, 1000000000,0,"书生-stop.png",1,4,4,"");
    	move = new MoveState(this,1500,4,"书生-move.png",4,4,2,"走路声.wav");
    	curState = stop;    //设置当前状态为stop
	}
	//阿凡达的更新函数
    public void update(){    	
    	curState.update();    	 //转发给当前状态进行更新
    }    
    /*
	 * 状态到期后的回调函数
	 * 让游戏角色负责决定下一个状态（而不是由状态自己决定）
	 */
	public void onStateFinish(ActorState state){
		if( state.getClass() == MoveState.class ){  //如果停止的是行走状态
			//转向停止状态
			switchState(new StopState(this, 1000000000,0,"书生-move.png",4,4,4,""));    //给阿凡达设置当前状态   
		}
	}
    
	//阿凡达的状态转换函数，参数为下一个状态对象
	public void switchState(ActorState nextState){
		nextState.setCurDirection(curState.getCurDirection()); //下一个状态的方向继承自上一个状态
		curState.stop();        //当前状态停止
		curState = nextState;   //当前状态切换到洗一个
		curState.start();       //当前状态开始		
	}
	//阿凡达的渲染函数
	public void render(Graphics g){		
		curState.render(g);		 
    }  
	
	//处理玩家的键盘命令
	public void keyPressed(int key) {		
		if( key == KeyEvent.VK_W ){  //按下w键
			curState.setCurDirection(0);  //设置状态的当前方向为左上方			
			this.switchState(move);       //请求阿凡达转换状态到MoveState
		}else if( key == KeyEvent.VK_D ){
			curState.setCurDirection(1);  //设置状态的当前方向为右上方			
			this.switchState(move);       //请求阿凡达转换状态到MoveState
		}else if( key == KeyEvent.VK_S ){
			curState.setCurDirection(2);  //设置状态的当前方向为右下方			
			this.switchState(move);       //请求阿凡达转换状态到MoveState
		}else if( key == KeyEvent.VK_A ){
			curState.setCurDirection(3);  //设置状态的当前方向为左下方			
			this.switchState(move);       //请求阿凡达转换状态到MoveState
		}					
	}
	//提供获取与设置坐标的接口
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
	//设置当前状态
	public void setCurState(ActorState next){
		curState = next;
	}
	//获取当前状态对象
	public ActorState getCurState(){
		return curState;
	}
}
