package Package01;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Blockmanage blockmanage=new Blockmanage();
		Newdir root=new Newdir(null,"��Ŀ¼");

		//System.out.println(Blockmanage.blocks[5].state);
		
		root.createFile("�ļ�һ",".txt");
		System.out.println(root.dir.size());
		System.out.println(root.dir.get(0).getName());
		System.out.println(root.dir.get(0).getAbsolutePath());
		System.out.println(root.dir.get(0).block+"\n");
		
		
		Newdir dir1=root.createDir("�ļ���һ");
		System.out.println(root.dir.size());
		System.out.println(root.dir.get(1).name);
		System.out.println(root.dir.get(1).getAbsolutePath());
		System.out.println(root.dir.get(1).block+"\n");
		
		dir1.createFile("�ļ���", ".exe");
		System.out.println(dir1.dir.size());
		System.out.println(dir1.dir.get(0).getName());
		System.out.println(dir1.dir.get(0).getAbsolutePath());
		System.out.println(dir1.dir.get(0).block+"\n");
		
		Window window=new Window(root);
		
	}

}
