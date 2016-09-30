package bishi;

public class Yunxingjieguo2 {
	public static void main(String[] args) {
//		Demo<Integer> iDeomTest = new Demo(0);
//		Demo<Float> fDemoTest = new Demo(0F);
//		System.out.println(iDeomTest.getClass() == fDemoTest.getClass());
//		Demo<Object> nDemoTest = iDeomTest;
		
		System.out.println(new Integer(100) > new Integer(0)); //true
		
		
	}
	int a;
	public void test(){
		class A{
		}
	}
}

class Demo<T>{
	private T m;
	Demo(T data){}
	public void setDate(T data){
		m = data;
	}
	public T getData(){
		return m;
	}
}
