package cuc.states;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import cuc.gameObjects.Avatar;


//��Ϸ��ɫ�ĳ���״̬
abstract public class ActorState {
	Avatar avatar;             //����������Ϸ��ɫ
	String name;               //״̬������
	long startTime;            //��ǰ״̬�Ŀ�ʼʱ��
	float maxLifeTime;         //״̬�����ʱ��
	int speed;                 //�˶��ٶ�
	
	//�������룺��Ϊÿ��״̬�����и��ԵĶ���
	Image animImage;          //��Ϸ��ɫ�Ķ����ϼ�ͼ�񣬼�ƶ�����ͼ		
	int maxFramePlayNum;    //һ������֡���ŵ�������
	int col,row;             //������ͼ�а������к�����
	int aw,ah;                //����Сͼ�Ŀ�͸�
	public static int curDirection;   //�����ﵱǰ�ĳ���:0Ϊ���ϣ�1Ϊ���ϣ�2Ϊ���£�3Ϊ����
	public static int curFrame;	    //��ǰ�Ķ���֡
	public static int curFramePlayNum;    //��ǰ����֡�Ѿ����ŵĴ���	
	
	AudioClip sound;        //״̬������
	
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
		
		//װ��״̬��Ч
		ClassLoader classLoader = this.getClass().getClassLoader(); 
		sound = Applet.newAudioClip(classLoader.getResource("sounds/"+soundFile)); 
	}
	//����״̬
	public void start(){
		setStartTime(System.currentTimeMillis());   //����״̬�Ŀ�ʼʱ��
		if( sound != null){
			sound.loop();    //����״̬����
		}		
	}
	//ֹͣ״̬
	public void stop(){
		if( sound != null){
			sound.stop();
		}
	}
	//״̬�ĸ��º���
	public void update(){
		animate();      //���Ž�ɫ����                  	
		move();         //�˶�		
	}
	
	//���Ž�ɫ��������
    void animate(){
    	curFramePlayNum++;   
    	if( curFramePlayNum > maxFramePlayNum ){  //���һ֡�������ŵĴ�������
    		curFrame = (curFrame+1)%col;   //������һ֡����
    		curFramePlayNum = 0;    //��������
    	} 
    }
    //4�����˶�����
    void move(){
    	if( curDirection == 0 ){   //�����˶�
    		avatar.setCx(avatar.getCx()-speed);
    		avatar.setCy(avatar.getCy()-speed);    		
    	}else if( curDirection == 1 ){  //�����˶�
    		avatar.setCx(avatar.getCx()+speed);
    		avatar.setCy(avatar.getCy()-speed); 
    	}else if( curDirection == 2 ){  //�����˶�
    		avatar.setCx(avatar.getCx()+speed);
    		avatar.setCy(avatar.getCy()+speed); 
    	}else if( curDirection == 3 ){  //�����˶�
    		avatar.setCx(avatar.getCx()-speed);
    		avatar.setCy(avatar.getCy()+speed); 
    	}
    }
    //���������Ⱦ����
  	public void render(Graphics g){		
  		//ע�⣺��ɫ����λ��λ�ڽ�ɫͼƬ���±��е�
      	g.drawImage( animImage,
      			     avatar.getCx()-avatar.getWidth()/2,avatar.getCy()-avatar.getHeight(), avatar.getCx()+avatar.getWidth()/2,avatar.getCy(),
      			     curFrame*aw,curDirection*ah,curFrame*aw+aw,curDirection*ah+ah,
      			     null);
    }      	
  	//����״̬�Ŀ�ʼʱ��
  	public void setStartTime(long time){
  		startTime = time;
  	}
  	//����״̬�ķ���
  	public void setCurDirection(int d){
  		curDirection = d;
  	}
  	//��ȡ״̬�ķ���
  	public int getCurDirection(){
  		return curDirection;
  	}
  	//����״̬������
  	public void playSound(){
  		sound.loop();
  	}
  	//ֹͣ״̬����
  	public void stopSound(){
  		sound.stop();
  	}
}
