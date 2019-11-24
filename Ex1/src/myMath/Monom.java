package myMath;




import java.awt.event.MouseMotionAdapter;
import java.util.Comparator;
import java.util.InputMismatchException;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 * @author Boaz
 *
 */
public class Monom implements function{

	private double _coefficient;
	private int _power;

	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {return _Comp;}

	public Monom(double a, int b){

		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/**
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/**
	 * get String and convert it to monom
	 * @param monom
	 */
	/**
	 * get String and convert it to monom
	 * @param monom
	 */
	public Monom(String monom) {
		String s[]=checkValue(monom);
		String _coef=s[0];
		String x=s[1];
		String mark=s[2];
		String power_=s[3];

		//We will divide all existing cases

		if(power_.equals("") && !x.equals("") && mark.equals(""))
			power_="1";

		else if(!power_.equals("") && !x.equals("") && mark.equals("^")) {

		}
		else if(x.equals("") && power_.equals("") && mark.equals("") ){
			power_="0";
		}
		else  {
			throw new RuntimeException("invaild input");

		}

		try{
			if(_coef.equals(""))
				_coef="1";
			if(_coef.equals("-"))
				_coef="-1";
			double a=Double.parseDouble(_coef);
			int b= Integer.parseInt(power_);

			set_coefficient(a);
			set_power(b);
		}
		catch(NumberFormatException e){
			System.out.println("invaild input");
		}
	}
	/**
	 * helper function
	 * get string Represents monom and return array of String
	 * Containing in each index the all value of monom [a,x,^,b]
	 * if the input invalid  we throw Exception in momnon constructor
	 * @param monom
	 * @return array of String
	 */
	private String[] checkValue(String monom){
		String _coef="";
		String x="";
		String mark="";
		String power_="";
		boolean ceack_coef=true;

		//boolean minus=false;
		//  if(monom.charAt(0)=='-')
		//	minus=true;

		for(int i=0;i<monom.length();i++){
			//if is a digit or dot
			if((monom.charAt(i)>='0' && monom.charAt(i)<='9' ||monom.charAt(i)=='.' || monom.charAt(i)=='-')&& ceack_coef ){
				_coef+=monom.charAt(i);
			}
			else if(monom.charAt(i)=='x'){
				x+=monom.charAt(i);
				ceack_coef=false;
			}
			else if(monom.charAt(i)=='^'){
				mark+=monom.charAt(i);
				ceack_coef=false;
			}
			else {

				power_+=monom.charAt(i);
				ceack_coef=false;
			}
		}
		String monom_data[]={_coef,x,mark,power_};
		return monom_data;
	}

	public boolean equals(Monom monom){ 
		if(get_coefficient()==0 && monom.get_coefficient()==0)
			return true ;

		if(Math.abs(get_coefficient()-monom.get_coefficient())<=EPSILON  && get_power()==monom.get_power())
			return true;
		return false;
	}
	public void add(Monom m)  {

		if(get_power()==m.get_power()){
			set_coefficient(get_coefficient()+m.get_coefficient());
		}
		else throw new RuntimeException("the the power not the same ");

	}

	public void subtract(Monom m){
		Monom minus=new Monom(m.get_coefficient()*-1,m._power);
		add(minus);

	}
	public void multipy(Monom d) {
		set_coefficient(get_coefficient()*d._coefficient);
		set_power(get_power()+d.get_power());
	}


	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	public boolean isTheSamePower(Monom m){
		if(get_power()==m.get_power())
			return true;
		return false;
	}
	@Override
	public String toString() {
		String str="";
		//	str=get_coefficient()+"^"+get_power();

		if(get_coefficient()==0)
			str+=0;
		else if(get_power()==0){
			str+= get_coefficient();
		}
		else if(get_power()==1){
			str+= get_coefficient()+"x";
		}
		else {
			str+=get_coefficient()+"x^"+get_power();
		}

		return  str;
	}
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}



}
