import java.util.Scanner;
public class MatrixMultiplicationN3{
	public static void main(String [] args){
		int row1, col1, row2,col2;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of rows matrix 1:");
		row1=sc.nextInt();
		System.out.println("Enter number of cols matrix 1:");
		col1=sc.nextInt();
		System.out.println("Enter number of rows matrix 2:");
		row2=sc.nextInt();
		System.out.println("Enter number of cols matrix 2:");
		col2=sc.nextInt();
		if(col1!=row2) {
		System.out.println("Incompatible for multiplication");
		return;
		};
		int [][] matrix1=new int[row1][col1];
		int [][] matrix2=new int[row2][col2];
		System.out.println("Enter matrix 1 elements:");
		for(int i=0;i<row1;i++){
			for(int j=0;j<col1;j++){
				matrix1[i][j]=sc.nextInt();
			}
		}
		System.out.println("Enter matrix 2 elements:");
		for(int i=0;i<row2;i++){
			for(int j=0;j<col2;j++){
				matrix2[i][j]=sc.nextInt();
			}
		}
		int [][] res= new int [row1][col2];
		for(int i=0;i<row1;i++){
			for(int j=0;j<col2;j++){
				res[i][j]=0;
				for(int k=0;k<col1;k++){
					res[i][j]=res[i][j]+matrix1[i][k]*matrix2[k][j];
				}
			}
		}
		
		System.out.println("Matrix multiplication result:");
		for(int i=0;i<row1;i++){
			for(int j=0;j<col2;j++){
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}