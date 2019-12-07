package Ex1;

public class ComplexFunction  implements complex_function{
	//private String mark;


	private function left;
	private function right;
	Operation operation;


	public ComplexFunction( String mark,function f1, function f2) {
		super();

		//this.mark=mark;
		this.left = f1;
		this.right = f2;

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
	public function initFromString(String s) {


		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
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
		return null;
	}






	@Override
	public String toString() {
		String s=""+operation+"("+left+","+right+")";
		return s;
		//return "ComplexFunction [mark=" + mark + ", left=" + left + ", right=" + right + "]";
	}


}
