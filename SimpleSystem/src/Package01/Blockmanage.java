package Package01;

public class Blockmanage {
	public static Block[] blocks=new Block[256];
	
	public Blockmanage() {
		for(int i=0;i<256;i++) 
			blocks[i]=new Block();
		
		for(int i=0;i<4;i++) {
			blocks[i].state=-1;
		}

	}
	
	public class Block{
		int state=0;
		Newfile file;   //Ã²ËÆÃ»ÓÃ
	}
	
	
	public static int Distribute() {
		int i;
		for(i=4;i<256;i++) {
			if(blocks[i].state==0)
				break;
			else
				continue;
		}
		blocks[i].state=-1;
		return i;
	}

}
