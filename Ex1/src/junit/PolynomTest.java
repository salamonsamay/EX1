package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

public class PolynomTest {

	@Test
	public void addTest() {

		
		Polynom p=new Polynom("3x^2+4x-6");
		Polynom p2=new Polynom("3x^2+4x-6");
		p2.add(p);
		assertEquals(new Polynom("6x^2+8x-12"), p2);
		assertNotSame(p, p2);
			
	}
	
	@Test
	public void mulTest() {
		
		Polynom_able p=new Polynom("x+1");
		Polynom_able p2=new Polynom("x+1");
		p2.multiply(p);
	
		
		assertEquals(new Polynom("x^2+2x+1"), p2);
		assertNotSame(p, p2);
			
	}
	
	@Test
	public void copyTest() {
		
		Polynom_able p=new Polynom("2x^4-7x^3+3x^2-6");
		Polynom_able p2=p.copy();
		
		assertEquals(p, p2);
		assertNotSame(p, p2);
			
	}
	
	@Test
	public void areaTest() {
		
		Polynom_able p=new Polynom("2x^4-7x^3+3x^2-6");
		p.area(0, 10, 0.000001)	;
		double eps=0.000001;
		
		assertTrue(23440-p.area(0, 10, 0.000001)<eps);
	}

	@Test
	public void initFromStringTest() {
		
		Polynom_able p=new Polynom("2x^4-7x^3+3x^2-6");
		Polynom_able p2=new Polynom();
		p2.add(new Monom(2,4));
		p2.add(new Monom(-7,3));
		p2.add(new Monom(3,2));
		p2.add(new Monom(-6,0));
		assertEquals(p2, p);
	}

	@Test
	public void substractTest() {
		
		Polynom_able p=new Polynom("2x^4-7x^36");
		Polynom_able p2=new Polynom("2x^4-7x^36-6x");
		p.substract(p2);
		assertEquals(new Polynom("6x"), p);
		assertNotSame(new Polynom("6x"), p);
	}
   
	@Test
	public void rootTest() {
		
		Polynom_able p=new Polynom("2x^3-7x^2+3x-2");
		Polynom_able p2=new Polynom("2x^4-7x^36-6x");
		double d=p.root(2, 300, 0.003);
		
		assertTrue(3.12214-d<0.003);
	}

	@Test
	public void zeroTest() {
		
		Polynom_able p=new Polynom("2x^3-7x^2+3x-2");
		assertTrue(4==((Polynom) p).getSize());
		
		Polynom_able p2=new Polynom();
		assertTrue(0==((Polynom) p2).getSize());
		
	}
	
	
	
}
