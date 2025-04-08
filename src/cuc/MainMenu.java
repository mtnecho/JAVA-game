package cuc;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainMenu extends GameState{
    public Image background; //����ͼ
    public Button start,exit;    //��ʼ���˳���ť
    public MainMenu(GameStarter game){
    	super(game);   	
    	start = new Button("��ʼ��Ϸ"); 
    	exit = new Button("�˳���Ϸ");
    	
    	start.setBounds(650,700,200,50);
    	exit.setBounds(650,600,200,50);
    	//����ť��ӵ�����
    	game.setLayout(null);
    	game.add(start); 
    	game.add(exit);
    	start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				transactionState();
				game.requestFocus();//�����ť���ȡ����
			}    		
    	});
    	exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}    		
    	});    	
      	
    	background = new ImageIcon(getClass().getResource("/images/mainMenu.jpg")).getImage();
    }
	public void update(){
	}
	public void render(Graphics g) {
		g.drawImage(background,0,0,game.getWidth(),game.getHeight(),null);
	}
	public void keyPressed(KeyEvent ke) {
		
	}
	public void mouseClicked(MouseEvent me){	    
	}
	
	public void transactionState() {		
		//����ť�Ӵ�����ɾ��
		game.remove(start);
		game.remove(exit);
		GameState gw = new GameWorld(game);
		game.changeStateTo(gw);
		game.repaint();
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
	