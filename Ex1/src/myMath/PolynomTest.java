package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		test3();
		test1();
		test2();
	}

	public static void test3(){
		System.out.println("test3");
		String s="-9x+3x^7+9x^2-5";
		Polynom_able p1 = new Polynom(s);
		Polynom_able p2=new Polynom(p1.toString());
		System.out.println("p1 "+p1.toString());
		System.out.println("p2 "+p2.toString());
		System.out.println();
		p1.add(p2);
		Polynom_able p3=p1.copy();
		System.out.println("p3=(p1+p2)= "+p3.toString());
		System.out.println();

		p3.multiply(p2);
		System.out.println("p3*p2 : "+p3.toString());

		Polynom_able p4=new Polynom();
		
		for(int i=0;i<4;i++){
			p4.add(new Monom(i+1,i));
		}

		System.out.println();

		p3.substract(p2);

		System.out.println("p4:"+p4.toString());
		for(int i=0;i<10;i++){
			System.out.println("p4.f("+i+")"+(p3.f(i)));
		}
		System.out.println();
		System.out.println("p3 :"+p3.toString());
		System.out.println("p3 area " +p3.area(3, 5, 0.004));

		System.out.println();
		System.out.println("p4 :"+p4.toString());
		System.out.println("p4 area " +p4.area(3, 5, 0.004));

		System.out.println();
		System.out.println("p2 :"+p2.toString());
		System.out.println("p2 area " +p2.area(3, 5, 0.004));

		System.out.println("root:"+p4.root(-3, 94, 0.02));

		Polynom_able p5=new Polynom("6x^3-12x^2+x+4");
		System.out.println("p5 :"+p5.toString());
		System.out.println("p5 derivative:"+p5.derivative());
		System.out.println("///////////test 3 finsh//////////////////");
		System.out.println();
		System.out.println();
	} 
	public static void test1() {
		System.out.println("test1////////////////////////////");
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};

		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		System.out.println("test2//////////////////////////");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		//	Polynom_able pp1 = Polynom.parse(s1);
		//	System.out.println("from string: "+pp1);
	}
}
