package Ex1;

import java.util.Iterator;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynom_able p1=new Polynom("x^2");
		Polynom_able p2=new Polynom("x^4+2");
		ComplexFunction cf=new ComplexFunction("plus",p1,p2);
		System.out.println(cf);
		cf.comp(new Monom("3x"));
		cf.plus(new Polynom("2x^4+43"));
		System.out.println(cf);
		System.out.println(cf.f(3));
		
	}

}
