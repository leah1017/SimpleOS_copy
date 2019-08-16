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
	public boolean isFile() {   					 //是否为文件
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
			System.out.print("该文件夹的文件多于八个");
			return null;
		}
			
		Newfile f=new Newfile(this,name,endwith);
		dir.add(f);
		return f;
	}
	
	public Newdir createDir(String name) {
		if(dir.size()>=8) {
			System.out.print("该文件夹的文件多于八个");
			return null;
		}
		
		Newdir d=new Newdir(this,name);
		dir.add(d);
		return d;
	}
	
	public void deleat(int index) {
		if(index<dir.size()) {

			Blockmanage.blocks[dir.get(index).block].state=0;
			Blockmanage.blocks[dir.get(index).block].file=null;     //这样做可以 失去要删除的文件的引用，JVM就会回收这一块内存
			dir.remove(index);
		}
	}
	
	

	
	
}
