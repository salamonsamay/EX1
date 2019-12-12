package Ex1;
import java.util.ArrayList;

public class ComplexFunction  implements complex_function{



	private function left;
	private function right;
	Operation operation;

	public ComplexFunction(){};

	public ComplexFunction(Operation operation,function left, function right ) {
		super();
		this.left = left;
		this.right = right;
		this.operation = operation;
	}

	public ComplexFunction( String mark,function f1, function f2) {
		super();

		//this.mark=mark;
		this.left = f1;
		this.right = f2;
		mark=mark.toLowerCase();

		switch(mark){
		case "plus"  :
			operation=Operation.Plus;
			break;
		case "mul":
			operation=Operation.Times;
			break;
		case "divid":
			operation=Operation.Divid;
			break;
		case "max":
			operation=Operation.Max;
			break;
		case "min":
			operation=Operation.Min;
			break;
		case "comp":
			operation=Operation.Comp;
			break;
		default:
			operation=Operation.Error;


		}

	}
	public ComplexFunction( function f1) {
		super();

		this.left = f1;
		this.right = null;
		operation=Operation.None;

	}



	@Override
	public double f(double x) {
		switch (operation) {
		case Plus:
			return left.f(x)+right.f(x);
		case Times:
			return left.f(x)*right.f(x);
		case Divid:
			return left.f(x)/right.f(x);
		case Max:
			return Math.max(left.f(x), right.f(x));
		case Min:
			return Math.min(left.f(x), right.f(x));
		case Comp:
			return left.f(right.f(x));
		default:
			break;
		}

		return -1;
	}

	@Override
	public  function initFromString(String s) {

		try{
			return new Polynom(s);
		}
		catch (Exception e){
           
		}
		ArrayList<String> operator=new ArrayList<>();
		ArrayList<String> fun=new ArrayList<>();
		int start=0;
		boolean b=false;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='('){
				operator.add(s.substring(start,i));

				start=i+1;

			}
			else if(s.charAt(i)==')' && i==s.length()-1){
				fun.add(s.substring(start,i));
			}
			else if(s.charAt(i)==','&& s.charAt(i-1)!=')'){
				fun.add(s.substring(start,i));
				start=i+1;
				b=true;
			}
			else if(s.charAt(i)==','&& s.charAt(i-1)==')'){
				fun.add(s.substring(start,i-1));
				start=i+1;
			}
		}

		ComplexFunction cf=null;
		int i=0;
		if(b){
			ArrayList<String> a=new ArrayList<>();
			for(int j=operator.size()-1;j>=0;j--){
				a.add(operator.get(j));
			}
			operator=a;
			cf=new ComplexFunction(operator.get(0),new Polynom(fun.get(0)),new Polynom(fun.get(1)));

			i=1;
		}
		else{
			cf=new ComplexFunction(operator.get(0),new Polynom(fun.get(0)),null);
			i=0;
		}
		int j=2;
		for(;i<operator.size();i++){
			if(operator.get(i).equals("Plus")){
				cf.plus(new Polynom(fun.get(j++)));


			}
			else if(operator.get(i).equals("Mul")){
				cf.mul(new Polynom(fun.get(j++)));

			}
			else if(operator.get(i).equals("Divid")){
				cf.div(new Polynom(fun.get(j++)));
			}
			else if(operator.get(i).equals("Max")){
				cf.max(new Polynom(fun.get(j++)));

			}
			else if(operator.get(i).equals("Min")){
				cf.min(new Polynom(fun.get(j++)));
			}else if(operator.get(i).equals("Comp")){
				cf.comp(new Polynom(fun.get(j++)));

			}
			else{
				System.out.println(",,,,,,,,,,,");
			}
		}


		return cf;

	}



	@Override
	public function copy() {
		// TODO Auto-generated method stub
		if(right==null){
			function other=new ComplexFunction(operation.name().toLowerCase(),left.copy(),  null);
			return other;
		}
		function other=new ComplexFunction(operation.name().toLowerCase(),left.copy(),  right.copy());
		return other ;
	}

	@Override
	public void plus(function f1) {
		ComplexFunction temp=(ComplexFunction)copy();
		left=temp;
		right=f1;
		//mark="plus";
		operation=Operation.Plus;


	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		ComplexFunction temp=(ComplexFunction)copy();
		left=temp;
		right=f1;
		//mark="mul";
		operation=Operation.Times;

	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		ComplexFunction temp=(ComplexFunction)copy();
		left=temp;
		right=f1;
		//mark="div";
		operation=Operation.Divid;

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		ComplexFunction temp=(ComplexFunction)copy();
		left=temp;
		right=f1;
		//mark="max";
		operation=Operation.Max;


	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		ComplexFunction temp=(ComplexFunction)copy();
		left=temp;
		right=f1;
		//mark="min";
		operation=Operation.Min;

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		ComplexFunction temp=(ComplexFunction)copy();
		left=temp;
		right=f1;
		//mark="comp";
		operation=Operation.Comp;

	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return operation;
	}


	public boolean equals(Object object){
		ComplexFunction cf=(ComplexFunction) object;
		int i=0;

		while(i++<100){
			double d=Math.random()*100000;
			if(f(d)!=cf.f(d))
				return false;
		}
		return true;
	}



	@Override
	public String toString() {
		String s=""+operation+"("+left+","+right+")";
		return s;
		//return "ComplexFunction [mark=" + mark + ", left=" + left + ", right=" + right + "]";
	}
	public static void main(String[] args){
		ComplexFunction cf=new ComplexFunction("Plus",new Monom("45x"),new Polynom("3x-4x^4"));
		cf.mul(new ComplexFunction("Div",new Polynom("2x-2"),new Polynom("7x^7")));
		System.out.println(cf);
	}


}
