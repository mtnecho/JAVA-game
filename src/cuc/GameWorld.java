package cuc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import cuc.gameObjects.Avatar;

//顾名思义，这个类代表着整个游戏世界，其中包含着所有的游戏对象。
public class GameWorld extends GameState
{
	// 初始化场景  
    public void init() {}  
      
    // 更新场景逻辑  
    public void update(float deltaTime) {}  
      
    // 渲染场景  
    public void render() {}  
      
    // 销毁场景  
    public void dispose() {  
        // 这里可以添加清理资源的代码  
    }  	
	public GameScene gs;
	//下面两张图是游戏的背景图和前景图，是2.5D游戏特有的技术。
	public Image bImage;     //游戏场景背景图
	public Image fImage;     //游戏场景前景图
	
	public Avatar avatar;    //游戏中的玩家化身对象，即玩家可以控制的游戏对象
	public GameWorld(GameStarter game){
		super(game);
		//初始化游戏场景背景图和前景图	
		bImage = new ImageIcon(getClass().getClassLoader().getResource("images/桃花岛-1.jpg")).getImage();
		fImage = new ImageIcon(getClass().getClassLoader().getResource("images/桃花岛-1-2.png")).getImage();
    	
        //初始化玩家化身
    	avatar = new Avatar(this,400,400,64,76); 
	}

	//游戏世界的渲染方法
	public void render(Graphics g){
		//先画场景的背景图
    	g.drawImage(bImage,0,0,game.getWidth(),game.getHeight(),null);
    	//再将游戏对象渲染到次画面上
    	avatar.render(g); 
    	//后画场景的遮挡图
    	g.drawImage(fImage,0,0,game.getWidth(),game.getHeight(),null);
	}
	//游戏世界的状态更新函数，或者说是游戏仿真函数
	//规定着游戏中的对象经过一帧后的状态变化，比如空间坐标。
	public void update(){
		//通知游戏世界中的游戏对象进行状态更新
		//这里暂时只有一个游戏对象：玩家化身
		avatar.update();
		if(game.flag == true) {
			game.switchScene(game.gameScene);
		}
	}
		
	public void keyPressed(int key) {
		//将键盘事件转发给玩家化身对象
		avatar.keyPressed(key);
		
		
	}
	
}

