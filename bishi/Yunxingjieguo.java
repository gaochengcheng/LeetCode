package bishi;

public class Yunxingjieguo {
public static void main(String[] args) {
//	Super sup = new Sub();
//	System.out.println(sup.field);//0
//	System.out.println(sup.getField());
//	Sub sub = new Sub();
//	System.out.println(sub.field);
//	System.out.println(sub.getField());
//	System.out.println(sub.getSuperField());
//	System.out.println(-3 >> 2);
	String string1 = new String("demo");
	String string2 = "demo";
//	System.out.println(string1.equals(string2));
	System.out.println(string1.intern() == string2);
	System.out.println(string1 == string2);
	
}
	static class Super{
		public int field = 0;
		public int getField(){
			return field;
		}
	}
	
	static class Sub extends Super{
		public int field=1;
		public int getField(){
			return field;
		}
		public int getSuperField(){
			return super.field;
		}
	}
}

