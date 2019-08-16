package Package01;
import java.util.*;

public class Newdir extends Newfile{

	Vector<Newfile> dir=new Vector<Newfile>();
	
	public Newdir() {
		
	}
	
	public Newdir(Newdir parent,String name) {
		super();
		length=0;
		this.parent=parent;
		this.name=name;
		endwith="";
		block=Blockmanage.Distribute();
		Blockmanage.blocks[block].file=this;
	}
	
	@Override
	public boolean isFile() {   					 //�Ƿ�Ϊ�ļ�
		return false;
	}
	
	@Override	
	public boolean isDir() {
		return true;
	}
	
	@Override	
	public Object clone() {
		Newdir n=null;
		n=(Newdir) super.clone();
		
		n.name=new String(name);
		n.dir=(Vector<Newfile>)n.dir.clone();
		return n;
	}
	
	public Newfile createFile(String name,String endwith) {
		if(dir.size()>=8) {
			System.out.print("���ļ��е��ļ����ڰ˸�");
			return null;
		}
			
		Newfile f=new Newfile(this,name,endwith);
		dir.add(f);
		return f;
	}
	
	public Newdir createDir(String name) {
		if(dir.size()>=8) {
			System.out.print("���ļ��е��ļ����ڰ˸�");
			return null;
		}
		
		Newdir d=new Newdir(this,name);
		dir.add(d);
		return d;
	}
	
	public void deleat(int index) {
		if(index<dir.size()) {

			Blockmanage.blocks[dir.get(index).block].state=0;
			Blockmanage.blocks[dir.get(index).block].file=null;     //���������� ʧȥҪɾ�����ļ������ã�JVM�ͻ������һ���ڴ�
			dir.remove(index);
		}
	}
	
	

	
	
}
