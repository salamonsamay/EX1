package Ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import graph.PolynomGraph;

public class Functions_GUI implements functions {
	private ArrayList<function> myFunctions=new ArrayList<>();


	public function get(int i){
		return myFunctions.get(i);
	}
	public int size() {
		// TODO Auto-generated method stub
		return myFunctions.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return myFunctions.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub

		return myFunctions.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub

		return myFunctions.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub

		return myFunctions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub

		return myFunctions.toArray(a);
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return myFunctions.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return myFunctions.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return myFunctions.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return myFunctions.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return myFunctions.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return myFunctions.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		myFunctions.clear();

	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		File f=new File(file);
		Scanner scanner=new Scanner(f);

		while(scanner.hasNextLine()){
			try{
				myFunctions.add((new ComplexFunction()).initFromString(scanner.nextLine()));
			}
			catch(Exception e){
				scanner.nextLine();
			}



		}
		scanner.close();

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		File f=new File(file);
		PrintWriter pw=new PrintWriter(f);
		Iterator<function> it=iterator();
		while(it.hasNext()){
			pw.println(it.next());
		}
		pw.close();



	}
	//	ComplexFunction f = new ComplexFunction();



	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		try {
			PolynomGraph pg=new PolynomGraph(this,width,height,rx,ry,resolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		String json=gson.toJson(json_file);
		
		
	}

	
	public String toString(){
		String data="";
		for(function f:myFunctions){
			data+=f.toString()+"\n";
		}
		return data;

	}
}
