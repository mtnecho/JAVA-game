package cuc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Labelpaint extends Panel implements MouseMotionListener,MouseListener{
	Label lbs[];    //�����ǩ��������
    int width;      //����˵��Ŀ��
    int height;    //����˵��ĸ߶�
    GameStarter game;
    
    public Labelpaint(GameStarter gamep)
    {
    	game = gamep;
    	width = 220;
    	height = 120;
    	//���˵�������Ϸ���Ͻ�
    	setBounds(0,0,width,height);
    	setBackground(Color.DARK_GRAY);    //���ò˵�������ɫΪ���ɫ
    	lbs = new Label[3];    //��ʼ����ǩ��������
    	lbs[0] = new Label("NEXT");   //�ñ�ǩ������ʾ����һ�ء�
    	lbs[1] = new Label("���������߻�");
    	lbs[2] = new Label("ѧ�ţ�202312033031");
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
    		game.flag = true;
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
