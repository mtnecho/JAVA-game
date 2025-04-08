package cuc.states;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import cuc.gameObjects.Avatar;


//游戏角色的抽象状态
abstract public class ActorState {
	Avatar avatar;             //反向引用游戏角色
	String name;               //状态的名字
	long startTime;            //当前状态的开始时刻
	float maxLifeTime;         //状态持续最长时间
	int speed;                 //运动速度
	
	//动画代码：因为每种状态可能有各自的动画
	Image animImage;          //游戏角色的动画合集图像，简称动画大图		
	int maxFramePlayNum;    //一个动画帧播放的最大次数
	int col,row;             //动画大图中包含的行和列数
	int aw,ah;                //动画小图的宽和高
	public static int curDirection;   //阿凡达当前的朝向:0为左上，1为右上，2为右下，3为左下
	public static int curFrame;	    //当前的动画帧
	public static int curFramePlayNum;    //当前动画帧已经播放的次数	
	
	AudioClip sound;        //状态的配音
	
	public ActorState(Avatar awatar,float maxLifeTime,int speed,String animFile,int col,int row,int maxFramePlayNum,String soundFile){
		this.avatar = awatar;		
		this.maxLifeTime = maxLifeTime;
		this.speed = speed;
		animImage = new ImageIcon(getClass().getClassLoader().getResource("images/"+animFile)).getImage();
		this.col = col;
		this.row = row;
		aw = animImage.getWidth(null)/col;
		ah = animImage.getHeight(null)/row;
		this.maxFramePlayNum = maxFramePlayNum;
		
		//装载状态音效
		ClassLoader classLoader = this.getClass().getClassLoader(); 
		sound = Applet.newAudioClip(classLoader.getResource("sounds/"+soundFile)); 
	}
	//启动状态
	public void start(){
		setStartTime(System.currentTimeMillis());   //设置状态的开始时刻
		if( sound != null){
			sound.loop();    //播放状态配音
		}		
	}
	//停止状态
	public void stop(){
		if( sound != null){
			sound.stop();
		}
	}
	//状态的更新函数
	public void update(){
		animate();      //播放角色动画                  	
		move();         //运动		
	}
	
	//播放角色动画函数
    void animate(){
    	curFramePlayNum++;   
    	if( curFramePlayNum > maxFramePlayNum ){  //如果一帧动画播放的次数到了
    		curFrame = (curFrame+1)%col;   //换成下一帧动画
    		curFramePlayNum = 0;    //计数归零
    	} 
    }
    //4方向运动函数
    void move(){
    	if( curDirection == 0 ){   //左上运动
    		avatar.setCx(avatar.getCx()-speed);
    		avatar.setCy(avatar.getCy()-speed);    		
    	}else if( curDirection == 1 ){  //右上运动
    		avatar.setCx(avatar.getCx()+speed);
    		avatar.setCy(avatar.getCy()-speed); 
    	}else if( curDirection == 2 ){  //右下运动
    		avatar.setCx(avatar.getCx()+speed);
    		avatar.setCy(avatar.getCy()+speed); 
    	}else if( curDirection == 3 ){  //左下运动
    		avatar.setCx(avatar.getCx()-speed);
    		avatar.setCy(avatar.getCy()+speed); 
    	}
    }
    //阿凡达的渲染函数
  	public void render(Graphics g){		
  		//注意：角色所在位置位于角色图片的下边中点
      	g.drawImage( animImage,
      			     avatar.getCx()-avatar.getWidth()/2,avatar.getCy()-avatar.getHeight(), avatar.getCx()+avatar.getWidth()/2,avatar.getCy(),
      			     curFrame*aw,curDirection*ah,curFrame*aw+aw,curDirection*ah+ah,
      			     null);
    }      	
  	//设置状态的开始时刻
  	public void setStartTime(long time){
  		startTime = time;
  	}
  	//设置状态的方向
  	public void setCurDirection(int d){
  		curDirection = d;
  	}
  	//获取状态的方向
  	public int getCurDirection(){
  		return curDirection;
  	}
  	//播放状态的配音
  	public void playSound(){
  		sound.loop();
  	}
  	//停止状态配音
  	public void stopSound(){
  		sound.stop();
  	}
}
