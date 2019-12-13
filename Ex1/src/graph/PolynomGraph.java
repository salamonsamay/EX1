package graph;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSpinnerUI;

import Ex1.Functions_GUI;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.Range;
import Ex1.function;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.AbstractLineRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class PolynomGraph extends JFrame{
	private function f;

	public PolynomGraph(function f){
		f=f;
	}

	public double PolynomArea() {
		return ((Polynom) f).area(-2, 6,0.01);
	}

	public Point PolynomPoint() {

		Point p = new Point(0,0);
		Polynom  p1derivative = (Polynom) ((Polynom) f).derivative();

		for (double x = -6.0; x <= 6.0; x+=0.01) {
			if(p1derivative.f(x)>0 && p1derivative.f(x)<=0.01) {
				//System.out.println(p1derivative.f(x));
				p.setX(x);
				p.setY(f.f(x));
			}
		}
		return p;
	}

	public PolynomGraph(function f, int width,int height,Range rx,Range ry,int resolution) throws FileNotFoundException, IOException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		DataTable data = new DataTable(Double.class, Double.class);

		for (double x = -2.0; x <= 6.0; x+=0.1) {
			double y = f.f(x);
			data.add(x, y);
			System.out.println(x+", "+y );
		}
		XYPlot plot = new XYPlot(data);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data , lines);
	}

	public PolynomGraph(Functions_GUI fg, int width,int height,Range rx,Range ry,int resolution) throws FileNotFoundException, IOException {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		DataTable data=new DataTable(Double.class, Double.class);


		for(int i=0;i<fg.size();i++){
			for (double x = rx.get_min(); x <= rx.get_max(); x+=0.01) {

                double y=fg.get(i).f(x);
                if(!(y>ry.get_max() ||y<ry.get_min())){
                	data.add(x,y);
                }

			}	
		}

		XYPlot plot = new XYPlot(data);


		getContentPane().add(new InteractivePanel(plot));
		//LineRenderer lines = new DefaultLineRenderer2D();
		//plot.setLineRenderers(data , lines);

		setVisible(true);
	}
	public static boolean contain(ArrayList<Point> points ,Point p){
		for(int i=0;i<points.size();i++){
			if(points.get(i).equals(p))
				return true;
		}
		return false;
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*	Point p=new Point(3, 4);
		Point p2=new Point(3, 4);
		ArrayList<Point> points=new ArrayList<>();
		points.add(p);
		points.add(p2);
		System.out.println(points);
		 */
		Range r=new Range(120, 102);
		Polynom p=new Polynom("x^2");
		PolynomGraph pg=new PolynomGraph(p,400, 500, r, r,4);



	}

}