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
 * ��Ϸһ��ʼ�ͽ��뱾״̬��������ʾһ��ͼƬ��
 * ��һ��ʱ��󣬻���������������ʾ����һ��ͼƬ��
 * ֱ��ͼƬ��ʵ��ϣ�������һ����Ϸ״̬�����˵�״̬
 */
public class Startup extends GameState{
  //�����ã��洢�������
	public Vector<Image> images;  //����һϵ�п�������ͼƬ
	public int index;    //��ǰ����ͼƬ�����
	public int startTime;     //��ǰͼƬ��ʼ��ʱ��
	public int currTime;     //��ǰʱ��
	public int life;        //ͼƬ���Դ��ڵ��ʱ��  
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
		//��Ⱦ��index��ͼƬ
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
		if( index < images.size()-1 ){ //������滹��ͼƬ
			index++;
			startTime = currTime;
		}else{//�������ͼƬ�Ѿ���ʾ��ϣ���ת����һ����Ϸ״̬�����˵�״̬
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
