package Package01;
import java.io.*;
import java.util.*;
public class Newfile implements Serializable,Cloneable{
	String name;
	String endwith;
	int block ;
	int length;
	Newdir parent ;
	private Vector<String> content=new Vector<String>();
	
	
	public Newfile() {
		
	}
	
	public Newfile(Newdir parent,String name,String endwith) {
		length=0;
		this.parent=parent;
		this.name=name;
		this.endwith=endwith;
		block=Blockmanage.Distribute();
		Blockmanage.blocks[block].file=this;
	}
	
	public boolean isFile() {
		return true;
	}
	
	public boolean isDir() {
		return false;
	}
	
	@Override	
	public Object clone() {
		Newfile n=null;
		try {
			n=(Newfile) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		n.name=new String(name);
		n.endwith=new String(endwith);
		return n;
	}
	
	public String getName() {
		return name+endwith;
	}
	
	public String getAbsolutePath() {
		if(parent!=null)
			return parent.getAbsolutePath()+"\\"+getName();
		else
			return getName();
	}
	
//	public void deleat() {
//		parent.dir.remove(this);
//		Blockmanage.blocks[block].state=0;
//		Blockmanage.blocks[block].file=null;
//	}
	
	

	

}
