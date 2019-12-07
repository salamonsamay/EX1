package Ex1;




import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;


/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	private  ArrayList<Monom> monoms;
	private int size=0;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom()
	{
		monoms=new ArrayList<>();
	}

	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		monoms=new ArrayList<>();
		ArrayList<String> polynom_string=spiltPolynom(s);

		for(int i=0;i<polynom_string.size();i++){
			Monom m=new Monom(polynom_string.get(i));
			add(new Monom(m));

		}

	}

	/**
	 * the function get String
	 * And returns an arrayList that each cell has Monom
	 * is separated by the mark  + or  - including
	 *
	 * Example
	 * s="-2x^5+x^4-5x+9"
	 * arrayList={"-2x^5","x^4","-5x",9}
	 * @param s
	 * @return ArrayList<String>
	 */
	private ArrayList<String> spiltPolynom(String s){
		ArrayList<String> str=new ArrayList<>();
		int start=0;

		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='-' && i>0){
				str.add(s.substring(start, i));
				start=i;
			}
			else if(s.charAt(i)=='+'){
				str.add(s.substring(start,i));
				start=i+1;
			}
		}
		str.add(s.substring(start,s.length()));
		return str;
	}

	/**
	 * @param x the value
	 * @return the polynomial value at a point x
	 */
	@Override
	public double f(double x) {
		double result=0;
		for(Monom m:monoms){
			result+=m.f(x);
		}
		return result;
	}

	/**
	 * The function receives a Polynom_able and add it to it
	 * Example (x^4+x^3-5x)+(4x^4)=5x^4+x^3-5x
	 * @param p1 polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it=p1.iteretor();
		while (it.hasNext()){
			add(it.next());
		}

	}

	/**
	 * The function receives a monom and add it to it
	 * Example (x^4+x^3-5x)+(4x^4)=5x^4+x^3-5x
	 * @param m1 Monom
	 */
	@Override
	public void add(Monom m1) {
		if(m1.get_coefficient()==0)
			return;

		for(int i=0;i<monoms.size();i++){
			if(monoms.get(i).get_power()<m1.get_power()){
				monoms.add(i,m1);

				return;
			}
			if(monoms.get(i).get_power()==m1.get_power()){
				monoms.get(i).add(m1);
				return;
			}

		}
		monoms.add(m1);

	}

	/**
	 * The function receives a polynomial and subtracts it from it
	 * Example (x^4+x^3-5x)-(4x^4+x^3+4x)=-3x^4-9x
	 * @param p1 polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		if(p1 instanceof  Polynom){
			Iterator<Monom> list=p1.iteretor();
			while (list.hasNext()){
				substract(list.next());
			}
		}

	}

	/**
	 * helped function
	 *For each monom if the coefficient is negative we will activate the ADD function
	 * And multiply the coefficient by 1
	 * @param m the monom Multiplied
	 */
	private void substract(Monom m) {
		for(int i=0;i<monoms.size();i++){
			if(monoms.get(i).isTheSamePower(m)){
				monoms.get(i).subtract(m);
				return;
			}
		}
		add(new Monom(-1*m.get_coefficient(),m.get_power()));

	}

	/**
	 * For each monom, we multiply it by a polynom
	 * @param p1 polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {
		if(p1 instanceof Polynom){
			Polynom_able p_temp=new Polynom();
			for(int i=0;i<monoms.size();i++){
				for(int j=0;j<((Polynom) p1).monoms.size();j++){
					Monom monom_temp=new Monom(monoms.get(i));
					monom_temp.multipy(((Polynom) p1).monoms.get(j));
					p_temp.add(monom_temp);

				}
			}
			monoms=((Polynom) p_temp).monoms;
		}
	}

	/**
	 *
	 * @param p1 the polynom we Compare to
	 * @return true if is equals else false
	 */
	@Override
	public boolean equals(Object p1) {
		if(equals(p1))
			return true;
		else return false;
		
		
	/*	if(p1 instanceof Polynom ){
			if(getSize()== ((Polynom) p1).getSize()){
				Iterator<Monom> thisIterator=iteretor();
				Iterator<Monom> otherIterator=p1.iteretor();

				while (thisIterator.hasNext()){
					if(!(thisIterator.next().equals(otherIterator.next()))){
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return false;
*/

	}

	/**
	 *
	 * @return if the size of polynom is 0
	 */
	@Override
	public boolean isZero() {
		if(size==0)
			return true;
		return false;
	}
	/**
	 * this function finding point x between two point x0 and x1 such that f(x) smaller eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return f(x) such that: x0 smaller than x that smaller than x1
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double x;
		x = (x0 + x1) / 2;

		if(f(x0)*f(x1)>0)
			throw new RuntimeException("eror");
		while(Math.abs(f(x)) > eps){
			x = (x0 + x1) / 2;
			if(f(x0)*f(x)>0)
				x0 = x;
			else if(f(x0)*f(x)<0){
				x1=x;
			}
			if(Math.abs(f(x))<eps)
				return x;


		}

		return x;

	}

	/**
	 *
	 * @return deep copy of Polynom
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able other=new Polynom();
		Iterator<Monom> thisIterator=iteretor();
		while (thisIterator.hasNext()){
			Monom next=thisIterator.next();
			other.add(new Monom(next.get_coefficient(),next.get_power()));
		}

		return other;
	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return derivative of this
	 */
	@Override
	public Polynom_able derivative() {
		Polynom_able derivative=new Polynom();
		for(Monom m :monoms){
			derivative.add(new Monom(m.derivative()));
		}
		return derivative;
	}
	/**
	 * the method Space calculus of polynom
	 * By dividing the area to 1/eps Rectangles we Calculate the Rectangles area base*height  1/eps time
	 * When the value is smaller The result is more accurate
	 * @param x0 the the left limit
	 * @param x1 the right limit
	 * @param eps The margin of error
	 * @return the area of f between x0 and x1
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if(x0>=x1)
			return 0;
		double delta_Xi=(x1-x0)*eps;
		double width=(x1-x0)*eps;
		double area=0;

		for(int i=0;i<=1/eps;i++){
			area+=delta_Xi*f(x0+width*i);
		}
		return area;
	}

	/**
	 * this function finding point x between two point x0 and x1 such that f(x) smaller eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return f(x) such that: x0 smaller than x that smaller than x1
	 */



	/**
	 *
	 * @return all the data about polynom
	 */
	public String toString() {
		String data="";
		for(int i=0;i<monoms.size();i++){
			if(monoms.get(i).get_coefficient()>0 && i>0){
				data+="+"+monoms.get(i).toString();
			}
			else{
				data+=monoms.get(i).toString();
			}
		}
		return data;
	}

	/**
	 *
	 * @return the Iterator of the Polynoms value
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return monoms.iterator();
	}

	/**
	 * get monmom
	 * if the monom is 0  we Set the size of the array to 0
	 * else we multiply all the values of the Polynoms with m1
	 * @param m1 the monom That we multiply
	 */
	@Override
	public void multiply(Monom m1) {
		if(m1.get_coefficient()==0){
			monoms=(new Polynom()).monoms;
			return;
		}
		for(Monom m: monoms){
			m.multipy(m1);

		}

	}

	/**
	 *
	 * @return the size of polynom
	 */
	public  int getSize(){
		return monoms.size();
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function f=new Polynom(s);
		return f;	
	}
}