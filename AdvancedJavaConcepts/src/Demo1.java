
public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable task1=new MyTask();
		
		Thread t1=new Thread(task1);
		t1.start();
		
		Thread t2=new Thread(task1);
		t2.start();

	}

}
