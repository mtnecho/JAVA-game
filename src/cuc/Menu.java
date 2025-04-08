package cuc;

import java.awt.Panel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;

public class Menu extends Panel implements MouseMotionListener,MouseListener{
    Label lbs[];    //�����ǩ��������
    int width;      //����˵��Ŀ��
    int height;    //����˵��ĸ߶�
    GameStarter game;
    
    public Menu(GameStarter gamep)
    {
    	game = gamep;
    	width = 100;
    	height = 160;
    	//���˵�������Ϸ������
    	setBounds((GameStarter.width-width)/2,(GameStarter.height-height)/2,width,height);
    	setBackground(Color.DARK_GRAY);    //���ò˵�������ɫΪ���ɫ
    	lbs = new Label[4];    //��ʼ����ǩ��������
    	lbs[0] = new Label("Start");   //�ñ�ǩ������ʾ����Ϸ��ʼ��
    	lbs[1] = new Label("save");    //�ñ�ǩ������ʾ��������Ϸ���ȡ�
    	lbs[2] = new Label("load");    //�ñ�ǩ������ʾ����ȡ��Ϸ���ȡ�
    	lbs[3] = new Label("exit");    //�ñ�ǩ������ʾ���˳���Ϸ��
    	Font font = new Font("Arial",Font.BOLD,20);    //�����������
    	for(int i = 0 ; i < lbs.length ; i++)    //ѭ����ÿ����ǩ��������
    	{
    		lbs[i].setFont(font);    //���ñ�ǩ�ı�����
    		lbs[i].setForeground(Color.cyan);    //���ñ�ǩǰ����ɫ
    		lbs[i].setBackground(Color.darkGray);    //���ñ�ǩ������ɫ
    		lbs[i].addMouseListener(this);    //�������¼�����
    		lbs[i].addMouseMotionListener(this);    //�������ƶ��¼�����
    		add(lbs[i]);
    	}
    }
    //���ñ�ǩ�ϵ��ı�����
    public void setLabel(int i , String text) {
    	lbs[i].setText(text);
    }
    //������갴���¼�����������������ʱ�����÷���
    public void mousePressed(MouseEvent arg0) {
    	Label lb = (Label)arg0.getSource();    //��ȡ��ǰ���������ı�ǩ����
    	if(lb.equals(lbs[0])) {
    		//��Ϸ��ʼ��ǩ�����ʱ����Ϸ״̬�л�������״̬
    		game.changeState(State.GameRunning);
    	}
    	else if(lb.equals(lbs[1])) {    //������ȱ����
    		game.gameSaved = true;    //����Ϸ�����־��Ϊtrue
    		game.saveGame();    //������Ϸ����
    		try {
    			Thread.sleep(200);    //����ʱ���ӳ٣����ⷴ������
    		}catch(InterruptedException el) {
    			el.printStackTrace();
    		}
    		game.gameSaved = false;    //����Ϸ�����־����Ϊfalse
    	}
    	else if(lb.equals(lbs[2])) {    //��ȡ��Ϸ���ȱ�ǩ�����
    		game.gameLoaded = true;    //��ȡ��Ϸ��־��ΪTRUE
    		game.loadGame();    //��ȡ��Ϸ����
    		try {
    			Thread.sleep(200);    //������Ϸ�ӳ٣����ⷴ����ȡ
    		}catch(InterruptedException el)
    		{
    			el.printStackTrace();    //����Ϸ��ȡ��־��Ϊfalse
    		}
    	}
    	else
    	{
    		System.exit(0);     //�˳���Ϸ��־���������Ϸ����
    	}
    }
    //�����������¼��������ָ���ƶ�����ǩ������ʱ�����÷���
    public void mouseEntered(MouseEvent arg0) {
    	for(int i = 0 ; i < lbs.length ; i++){    //��ǰ���ָ�����ĳ����ǩ
    		if(arg0.getSource().equals(lbs[i])) {    //���ñ�ǩǰ��ɫ��Ϊ��ɫ
    			lbs[i].setForeground(Color.red);
    			break;
    		}
    	}
    }
    //��������Ƴ��¼��������ָ���Ƴ���ǩ����ʱ�����÷���
    public void mouseExited(MouseEvent arg0) {    //��ǰ���ָ���Ƴ�ĳ����ǩ
    	for(int i = 0 ; i < lbs.length ; i++)    //���ñ�ǩǰ��ɫ��Ϊ��ɫ
    	{
    		if(arg0.getSource().equals(lbs[i])) {
    			lbs[i].setForeground(Color.cyan);
    			break;
    		}
    	}
    }
    public void mouseDragged(MouseEvent arg0) {}
    public void mouseMoved(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}
