
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		X x = new X();
		Y y = new Y();
		x = y;
		y = (Y)x;
		
		X[] xa = new X[3];
		Y[] ya = new Y[3];
		xa = ya;
		ya = (Y[])xa;
		
		//y.f1(1,2);
		System.out.println(X.value);
		System.out.println(I.value);
		
		Y y1 = new Y();
		((X)y1).f1();
		
		
		
		
	}

}
