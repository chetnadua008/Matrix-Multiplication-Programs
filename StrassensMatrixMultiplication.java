import java.util.Scanner;
public class StrassensMatrixMultiplication{
	public static void main(String [] args){
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter order of square matrix");
		n=sc.nextInt();
		
		int [][] matrix1=new int[n][n];
		int [][] matrix2=new int[n][n];
		System.out.println("Enter matrix 1 elements:");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				matrix1[i][j]=sc.nextInt();
			}
		}
		System.out.println("Enter matrix 2 elements:");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				matrix2[i][j]=sc.nextInt();
			}
		}
		int [][] res=strassensMultiplication(matrix1,matrix2);
		System.out.println("Strassens Matrix multiplication result:");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static int[][] strassensMultiplication(int [][]A, int [][]B){
		int n=A.length;
		if(n==1){		//base condition
			int [][]C={{A[0][0]*B[0][0]}};
			return C;
		}
		//n/2 matrices
		int newSize=n/2;
		int[][] A11=new int[newSize][newSize];
		int[][] A12=new int[newSize][newSize];
		int[][] A21=new int[newSize][newSize];
		int[][] A22=new int[newSize][newSize];
		int[][] B11=new int[newSize][newSize];
		int[][] B12=new int[newSize][newSize];
		int[][] B21=new int[newSize][newSize];
		int[][] B22=new int[newSize][newSize];
		
		//filling A11,A12..B22 from A and B
		
		for(int i=0;i<newSize;i++){
			for(int j=0;j<newSize;j++){
				A11[i][j]=A[i][j];
				A12[i][j]=A[i][newSize+j];
				A21[i][j]=A[newSize+i][j];
				A22[i][j]=A[newSize+i][newSize+j];
				B11[i][j]=B[i][j];
				B12[i][j]=B[i][newSize+j];
				B21[i][j]=B[newSize+i][j];
				B22[i][j]=B[newSize+i][newSize+j];
			}
		}
		
		int [][][]S=calculateIntermediateMatrices(A11,A12,A21,A22,B11,B12,B21,B22);
		
		//recursively product matrices calculating (strassensMultiplication)
		
		int [][]P1=strassensMultiplication(A11,S[0]);
		int [][]P2=strassensMultiplication(S[1],B22);
		int [][]P3=strassensMultiplication(S[2],B11);
		int [][]P4=strassensMultiplication(A22,S[3]);
		int [][]P5=strassensMultiplication(S[4],S[5]);
		int [][]P6=strassensMultiplication(S[6],S[7]);
		int [][]P7=strassensMultiplication(S[8],S[9]);
		
		//calculating c11,c12,c21,c22 from product matrices
		int[][]C11=add(P5,add(sub(P4,P2),P6));		//add , sub defined at end :)
		int[][]C12=add(P1,P2);
		int [][]C21=add(P3,P4);
		int[][]C22=add(P5,sub(sub(P1,P3),P7));
		
		
		//combine C11,C12,C21,C22 to C
		int [][]C=new int[n][n];
		for(int i=0;i<newSize;i++){
			for(int j=0;j<newSize;j++){
				C[i][j]=C11[j][j];
				C[i][j+newSize]=C12[j][j];
				C[i+newSize][j]=C21[j][j];
				C[i+newSize][j+newSize]=C22[j][j];
			}
		}
		
		return C;
	}
	
	private static int[][][] calculateIntermediateMatrices(int[][] A11, int[][] A12, int[][] A21, int[][] A22,int[][] B11, int[][] B12, int[][] B21, int[][] B22) {
        int newSize = A11.length;
        int[][][] S = new int[10][newSize][newSize];
        S[0] = sub(B12, B22); // S1 = B12 - B22
        S[1] = add(A11, A12);      // S2 = A11 + A12
        S[2] = add(A21, A22);      // S3 = A21 + A22
        S[3] = sub(B21, B11); // S4 = B21 - B11
        S[4] = add(A11, A22);      // S5 = A11 + A22
        S[5] = add(B11, B22);      // S6 = B11 + B22
        S[6] = sub(A12, A22); // S7 = A12 - A22
        S[7] = add(B21, B22);      // S8 = B21 + B22
        S[8] = sub(A11, A21); // S9 = A11 - A21
        S[9] = add(B11, B12);      // S10 = B11 + B12
        return S;
    }
	private static int[][] add(int[][]A,int[][]B){
		int [][]C=new int[A.length][A.length];
		for(int i=0;i<A.length;i++){
			for(int j=0;j<B.length;j++){
				C[i][j]=A[i][j]+B[i][j];
			}
		}
		return C;
	}
	private static int[][] sub(int[][]A,int[][]B){
		
		int [][]C=new int[A.length][A.length];
		for(int i=0;i<A.length;i++){
			for(int j=0;j<B.length;j++){
				C[i][j]=A[i][j]-B[i][j];
			}
		}
		return C;
	}
}
	