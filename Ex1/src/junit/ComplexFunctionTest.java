package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;

public class ComplexFunctionTest {

	@Test
	public void initFromStringTest() {
		ComplexFunction cf=new ComplexFunction("Plus",new Polynom("x^2+4"),new Polynom("6x-2"));
		ComplexFunction cf2=new ComplexFunction();
		cf2=(ComplexFunction) cf.initFromString("plus(x^2+4,6x-2)");

		assertEquals(cf, cf2);
		assertNotSame(cf, cf2);
	}

	@Test
	public void plusTest(){
		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.plus(new Polynom("-x^6-10x-89"));
		ComplexFunction cf2=(ComplexFunction) cf.initFromString("Plus(Plus(45x,3x-4x^4),-x^6-10x-89)");
		assertEquals(cf2, cf);
		assertNotSame(cf2, cf);

	}

	@Test
	public void multiTest(){
		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.mul(new Polynom("3x^5+4"));

		ComplexFunction cf2=(ComplexFunction) cf.initFromString("Mul(Plus(45x,3x-4x^4),3x^5+4)");

		assertEquals(cf2, cf);
		assertNotSame(cf2, cf);

	}

	@Test
	public void maxTest(){

		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.max(new Polynom("3x^5+4"));

		ComplexFunction cf2=(ComplexFunction) cf.initFromString("Max(Plus(45x,3x-4x^4),3x^5+4)");
		assertEquals(cf2, cf);
		assertNotSame(cf2, cf);

	}
	
	@Test
	public void minTest(){
		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.min(new Polynom("3x^5+4"));

		ComplexFunction cf2=(ComplexFunction) cf.initFromString("min(Plus(45x,3x-4x^4),3x^5+4)");
		assertEquals(cf2, cf);
		assertNotSame(cf2, cf);
	}

	@Test
	public void compTest(){
		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.comp(new Polynom("3x^5+4"));

		ComplexFunction cf2=(ComplexFunction) cf.initFromString("Comp(Plus(45x,3x-4x^4),3x^5+4)");
		assertEquals(cf2, cf);
		assertNotSame(cf2, cf);
	}
	
	@Test
	public void dividTest(){
		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.div(new Polynom("3x^5+4"));

		ComplexFunction cf2=(ComplexFunction) cf.initFromString("Divid(Plus(45x,3x-4x^4),3x^5+4)");
		assertEquals(cf2, cf);
		assertNotSame(cf2, cf);
	}




}
