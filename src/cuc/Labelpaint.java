package cuc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Labelpaint extends Panel implements MouseMotionListener,MouseListener{
	Label lbs[];    //定义标签对象数组
    int width;      //定义菜单的宽度
    int height;    //定义菜单的高度
    GameStarter game;
    
    public Labelpaint(GameStarter gamep)
    {
    	game = gamep;
    	width = 220;
    	height = 120;
    	//将菜单置于游戏左上角
    	setBounds(0,0,width,height);
    	setBackground(Color.DARK_GRAY);    //设置菜单背景颜色为深灰色
    	lbs = new Label[3];    //初始化标签对象数组
    	lbs[0] = new Label("NEXT");   //该标签用于显示“下一关”
    	lbs[1] = new Label("姓名：王高慧");
    	lbs[2] = new Label("学号：202312033031");
    	Font font = new Font("Arial",Font.BOLD,20);    //定义字体对象
    	for(int i = 0 ; i < lbs.length ; i++)    //循环对每个标签进行设置
    	{
    		lbs[i].setFont(font);    //设置标签文本字体
    		lbs[i].setForeground(Color.cyan);    //设置标签前景颜色
    		lbs[i].setBackground(Color.darkGray);    //设置标签背景颜色
    		lbs[i].addMouseListener(this);    //添加鼠标事件监听
    		lbs[i].addMouseMotionListener(this);    //添加鼠标移动事件监听
    		add(lbs[i]);
    	}
    	
    }
    //设置标签上的文本方法
    public void setLabel(int i , String text) {
    	lbs[i].setText(text);
    }
    //处理鼠标按键事件，当鼠标左键被按下时触发该方法
    public void mousePressed(MouseEvent arg0) {
    	Label lb = (Label)arg0.getSource();    //获取当前鼠标所点击的标签对象
    	if(lb.equals(lbs[0])) {
    		//游戏开始标签被点击时，游戏状态切换到运行状态
    		game.flag = true;
    	}
    }
    //处理鼠标进入事件，当鼠标指针移动到标签对象上时触发该方法
    public void mouseEntered(MouseEvent arg0) {
    	for(int i = 0 ; i < lbs.length ; i++){    //当前鼠标指针进入某个标签
    		if(arg0.getSource().equals(lbs[i])) {    //将该标签前景色设为红色
    			lbs[i].setForeground(Color.red);
    			break;
    		}
    	}
    }
    //处理鼠标移出事件，当鼠标指针移出标签对象时触发该方法
    public void mouseExited(MouseEvent arg0) {    //当前鼠标指针移出某个标签
    	for(int i = 0 ; i < lbs.length ; i++)    //将该标签前景色设为蓝色
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
