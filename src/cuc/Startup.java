package cuc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
/*
 * 游戏一开始就进入本状态，首先显示一张图片；
 * 过一段时间后，或者鼠标点击画面后，显示另外一张图片；
 * 直到图片现实完毕，进入下一个游戏状态：主菜单状态
 */
public class Startup extends GameState{
  //反引用，存储主类对象
	public Vector<Image> images;  //保存一系列开场动画图片
	public int index;    //当前动画图片的序号
	public int startTime;     //当前图片开始的时间
	public int currTime;     //当前时间
	public int life;        //图片可以存在的最长时间  
	public Startup(GameStarter game){
		super(game);
		images = new Vector<Image>();
		Image i1 = 
		new ImageIcon(getClass().getResource("/images/gu.png")).getImage();
		images.add(i1);
		Image i2 = 
		new ImageIcon(getClass().getResource("/images/rose.png")).getImage();
		images.add(i2);
		Image i3 = 
		new ImageIcon(getClass().getResource("/images/sun.png")).getImage();
		images.add(i3);
		index = 0;
		life = 20;
		
		startTime = 0;
		currTime = 0;
	}
	public void update() {
		if( currTime - startTime > life ){
			transactionState();			
		}else{
			currTime++;
		}
	}
	public void render(Graphics g) {
		//渲染第index张图片
		Image i = images.get(index);
		g.drawImage(i,0,0,game.getWidth(),game.getHeight(),null);
	}
	public void keyPressed(KeyEvent ke) {
		transactionState();
	}
	public void mouseClicked(MouseEvent me) {	
		transactionState();
	}
	public void transactionState(){
		if( index < images.size()-1 ){ //如果后面还有图片
			index++;
			startTime = currTime;
		}else{//如果动画图片已经显示完毕，则转向下一个游戏状态：主菜单状态
			MainMenu mm = new MainMenu(game);
			game.changeStateTo(mm);			
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}
		
}
