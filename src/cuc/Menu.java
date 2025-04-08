package cuc;

import java.awt.Panel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;

public class Menu extends Panel implements MouseMotionListener,MouseListener{
    Label lbs[];    //定义标签对象数组
    int width;      //定义菜单的宽度
    int height;    //定义菜单的高度
    GameStarter game;
    
    public Menu(GameStarter gamep)
    {
    	game = gamep;
    	width = 100;
    	height = 160;
    	//将菜单置于游戏正中央
    	setBounds((GameStarter.width-width)/2,(GameStarter.height-height)/2,width,height);
    	setBackground(Color.DARK_GRAY);    //设置菜单背景颜色为深灰色
    	lbs = new Label[4];    //初始化标签对象数组
    	lbs[0] = new Label("Start");   //该标签用于显示“游戏开始”
    	lbs[1] = new Label("save");    //该标签用于显示“保存游戏进度”
    	lbs[2] = new Label("load");    //该标签用于显示“读取游戏进度”
    	lbs[3] = new Label("exit");    //该标签用于显示“退出游戏”
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
    		game.changeState(State.GameRunning);
    	}
    	else if(lb.equals(lbs[1])) {    //保存进度被点击
    		game.gameSaved = true;    //将游戏保存标志设为true
    		game.saveGame();    //保存游戏进度
    		try {
    			Thread.sleep(200);    //设置时间延迟，避免反复保存
    		}catch(InterruptedException el) {
    			el.printStackTrace();
    		}
    		game.gameSaved = false;    //将游戏保存标志重设为false
    	}
    	else if(lb.equals(lbs[2])) {    //读取游戏进度标签被点击
    		game.gameLoaded = true;    //读取游戏标志设为TRUE
    		game.loadGame();    //读取游戏进度
    		try {
    			Thread.sleep(200);    //设置游戏延迟，避免反复读取
    		}catch(InterruptedException el)
    		{
    			el.printStackTrace();    //将游戏读取标志设为false
    		}
    	}
    	else
    	{
    		System.exit(0);     //退出游戏标志被点击，游戏结束
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
