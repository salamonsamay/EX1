package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import Ex1.Monom;

public class MonomTest {
    
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void addTest() {
		Monom m=new Monom("2x^4");
		Monom m2=new Monom("4x^4");
		m2.add(m);
	    
	   assertEquals(new Monom("6x^4"), m2);
	}
	
	
	@Test
	public void fTest() {
		Monom m=new Monom("2x^4");
		
		double f=0;
		f=m.f(3);
		
		assertTrue(f==2*(Math.pow(3, 4)));
	   
	    
	    
	}
	
	public static void main(String[] args){
		
	}

}
