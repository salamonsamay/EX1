package graph;

public class test {
	public static int [][][] rotate90(int [][][]im){
		int [][][]rotate = new int[im.length][im.length][im.length];
		int [][][]temp = new int [im.length][im.length][im.length];
		for (int x = 0; x < 3 ; x++) {
			for (int i = 0; i < im.length; i++) {
				for (int j = 0; j < im.length; j++) {
					
				}
			}
		}
		return rotate;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][][]a=new int[3][10][10];
		int[][][]b=new int[3][10][10];
		for(int x= 0;x<a.length;x++){
			for(int i=0;i<a[x].length;i++){
				for(int j=0;j<a[x][i].length;j++){
					a[x][i][j]=j;
					b[x][i][j]=j;
				}
			}
		}
		for(int x= 0;x<a.length;x++){
			for(int i=0;i<a[x].length;i++){
				for(int j=0;j<a[x][i].length;j++){
					
					b[x][i][j]=j;
				}
			}
		}
		
		for(int x=0;x<a.length;x++){
			System.out.println();
			for(int i=0;i<a[x].length;i++){
			  
				for(int j=0;j<a[x][i].length;j++){
		
					System.out.print(a[x][i][j]);
				}
				System.out.println();
			}
		}
		
		///rotate90(a);

	}

}
