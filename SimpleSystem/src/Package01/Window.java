package Package01;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class Window extends JFrame {

	private JPanel contentPane;
	Newdir root;
	Newdir current_dir;
	Vector<JLabel> labels=new Vector<JLabel>();
	Color blue=new Color(204, 255, 255);
	Color white=new Color(255, 200, 0);
	int deleat;
	JLabel lblNewLabel;  //显示当前路径


	public Window(Newdir root) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode()==e.VK_ESCAPE)
					Window.this.dispose();
			}
		});
		this.root=root;
		current_dir=root;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//装标签的panel
		JPanel panel = new JPanel();
		panel.setBounds(0, 69, 317, 453);
		contentPane.add(panel);
		panel.setLayout(null);

	
		//用来克隆的母标签 hhh
		JLabel lblSSsss = new JLabel("");
		lblSSsss.setBackground(Color.ORANGE);
		lblSSsss.setOpaque(true);
		lblSSsss.setBounds(panel.getX(), panel.getY(), panel.getWidth(), 40);
		
		//两种上下文窗口
	    JPopupMenu popupMenu = new JPopupMenu();
	    
	    
	    JMenuItem menuItem = new JMenuItem("删除");    //删除
	    popupMenu.add(menuItem);
	    menuItem.addActionListener(new ActionListener() {

	    	public void actionPerformed(ActionEvent e) {
	    		current_dir.deleat(deleat);
	    		rebuild();
	    		Window.this.requestFocus();
	    	}
	    });
	    
	    JMenuItem menuItem_1 = new JMenuItem("显示内容");  //显示内容
	    popupMenu.add(menuItem_1);
	    
	    
	    
		JPopupMenu popupMenu_1 = new JPopupMenu();
		popupMenu_1.setBounds(0, 0, 200, 50);

		
		JMenuItem menuItem_2 = new JMenuItem("新建文本文件");
		popupMenu_1.add(menuItem_2);
		menuItem_2.addActionListener(new ActionListener() {

	    	public void actionPerformed(ActionEvent e) {
	    		current_dir.createFile("文件"+Integer.toString(current_dir.dir.size()), ".txt");
	    		rebuild();
	    		Window.this.requestFocus();
	    	}
	    });
		
		JMenuItem menuItem_3 = new JMenuItem("新建可执行文件");
		popupMenu_1.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener() {

	    	public void actionPerformed(ActionEvent e) {
	    		current_dir.createFile("文件"+Integer.toString(current_dir.dir.size()), ".exe");
	    		rebuild();
	    		Window.this.requestFocus();
	    	}
	    });
		
		JMenuItem menuItem_4 = new JMenuItem("新建文件夹");
		popupMenu_1.add(menuItem_4);
		menuItem_4.addActionListener(new ActionListener() {

	    	public void actionPerformed(ActionEvent e) {
	    		current_dir.createDir("文件夹"+Integer.toString(current_dir.dir.size()));
	    		rebuild();
	    		Window.this.requestFocus();
	    	}
	    });
		

		//克隆8个标签加入panel中
		try {
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			ObjectOutputStream objout=new ObjectOutputStream(out);
			for(int i=0;i<8;i++)
				objout.writeObject(lblSSsss);
			objout.close();       //objout关闭后，out应该也关闭了
			
			
			//labels.add(lblSSsss);
			JLabel s;
			for(int i=0;i<8;i++) {
				ByteArrayInputStream in=new ByteArrayInputStream(out.toByteArray());
				ObjectInputStream objin=new ObjectInputStream(in);
				s=(JLabel)objin.readObject();
				objin.close();
				if(i!=0)
					s.setLocation(s.getX(), labels.get(i-1).getY()+labels.get(i-1).getHeight());
				addPopup(s, popupMenu,popupMenu_1);
				s.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int i=labels.indexOf((JLabel)(e.getComponent()));
						if(e.getClickCount()==2&&current_dir.dir.size()>i&&current_dir.dir.get(i).isDir()) {
							//clearlabels();
							current_dir=(Newdir) current_dir.dir.get(i);
							rebuild();
							
						}
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(((JLabel)e.getComponent()).getText()!=null)
							((JLabel)e.getComponent()).setBackground(blue);
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						if(((JLabel)e.getComponent()).getBackground()==blue)
							((JLabel)e.getComponent()).setBackground(white);
					}


				});
				
				
				
				panel.add(s);
				labels.add(s);
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		

	    //测试用的“返回”按钮
	    JButton button = new JButton("\u8FD4\u56DE");

	    button.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if(current_dir.parent!=null) {
					current_dir=current_dir.parent;
					rebuild();
	    		}
	    		Window.this.requestFocus();

	    	}
	    });
	    button.setBounds(383, 117, 80, 37);
	    contentPane.add(button);
	    
	    lblNewLabel = new JLabel("");
	    lblNewLabel.setBounds(10, 29, 307, 30);
	    contentPane.add(lblNewLabel);
		
		Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize(); 
	    Rectangle   bounds   =   new   Rectangle(screenSize);
	    setUndecorated(true);
	    setBounds(bounds);
	    setVisible(true);
	    
	    
	    //绘制初始画面  加载root
	    
	        //button会抢去JFrame的焦点  导致键盘事件无法响应  所以要让JFrame获得焦点
	    //this.setFocusable(true);    不一定获得焦点
	    requestFocus();              //一定获得焦点
	    rebuild();

	    
	    
	}
	
	
	//用于清空所有标签
	public void clearlabels() {
		for(JLabel l : labels) {
			l.setText(null);
			l.setBackground(white);
		}
	}
	
	//用于重绘标签
	public void rebuild() {
		clearlabels();
	    for(int i =0;i<current_dir.dir.size();i++) {
	    	labels.get(i).setText(current_dir.dir.get(i).getName());
	    }
	    lblNewLabel.setText(current_dir.getAbsolutePath());
	    
	    repaint();
	}
	
	//用于为标签设置响应右键释放的事件（弹出上下文菜单）
	private  void addPopup(Component component, final JPopupMenu popup_file,final JPopupMenu popup_null) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					deleat=labels.indexOf(e.getComponent());
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					deleat=labels.indexOf(e.getComponent());
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				if(((JLabel)e.getComponent()).getText()==null)
					popup_null.show(e.getComponent(), e.getX(), e.getY());
				else
					popup_file.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
