package cuc;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cuc.gameObjects.Avatar;

public abstract class GameState {
	protected GameStarter game;
	// 初始化场景  
    public abstract void init() ;  
      
    // 更新场景逻辑  
    public abstract void update(float deltaTime);  
      
    // 渲染场景  
    public abstract void render(); 
      
    // 销毁场景  
    public abstract void dispose() ; 
        // 这里可以添加清理资源的代码   
    //反向引用游戏主类对象		
	//下面两张图是游戏的背景图和前景图，是2.5D游戏特有的技术。
     //游戏场景背景图
    //游戏场景前景图
	
   //游戏中的玩家化身对象，即玩家可以控制的游戏对象
	public GameState(GameStarter game) {
		this.game = game;
	}

	//游戏世界的渲染方法
	public abstract void render(Graphics g);
	//游戏世界的状态更新函数，或者说是游戏仿真函数
	//规定着游戏中的对象经过一帧后的状态变化，比如空间坐标。
	public abstract void update();
	
	public abstract void keyPressed(int key) ;

}
