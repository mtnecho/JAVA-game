package cuc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import cuc.gameObjects.Avatar;

//����˼�壬����������������Ϸ���磬���а��������е���Ϸ����
public class GameWorld extends GameState
{
	// ��ʼ������  
    public void init() {}  
      
    // ���³����߼�  
    public void update(float deltaTime) {}  
      
    // ��Ⱦ����  
    public void render() {}  
      
    // ���ٳ���  
    public void dispose() {  
        // ����������������Դ�Ĵ���  
    }  	
	public GameScene gs;
	//��������ͼ����Ϸ�ı���ͼ��ǰ��ͼ����2.5D��Ϸ���еļ�����
	public Image bImage;     //��Ϸ��������ͼ
	public Image fImage;     //��Ϸ����ǰ��ͼ
	
	public Avatar avatar;    //��Ϸ�е���һ�����󣬼���ҿ��Կ��Ƶ���Ϸ����
	public GameWorld(GameStarter game){
		super(game);
		//��ʼ����Ϸ��������ͼ��ǰ��ͼ	
		bImage = new ImageIcon(getClass().getClassLoader().getResource("images/�һ���-1.jpg")).getImage();
		fImage = new ImageIcon(getClass().getClassLoader().getResource("images/�һ���-1-2.png")).getImage();
    	
        //��ʼ����һ���
    	avatar = new Avatar(this,400,400,64,76); 
	}

	//��Ϸ�������Ⱦ����
	public void render(Graphics g){
		//�Ȼ������ı���ͼ
    	g.drawImage(bImage,0,0,game.getWidth(),game.getHeight(),null);
    	//�ٽ���Ϸ������Ⱦ���λ�����
    	avatar.render(g); 
    	//�󻭳������ڵ�ͼ
    	g.drawImage(fImage,0,0,game.getWidth(),game.getHeight(),null);
	}
	//��Ϸ�����״̬���º���������˵����Ϸ���溯��
	//�涨����Ϸ�еĶ��󾭹�һ֡���״̬�仯������ռ����ꡣ
	public void update(){
		//֪ͨ��Ϸ�����е���Ϸ�������״̬����
		//������ʱֻ��һ����Ϸ������һ���
		avatar.update();
		if(game.flag == true) {
			game.switchScene(game.gameScene);
		}
	}
		
	public void keyPressed(int key) {
		//�������¼�ת������һ������
		avatar.keyPressed(key);
		
		
	}
	
}

