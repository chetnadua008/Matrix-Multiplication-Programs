import java.util.Scanner;
public class MatrixChainMultiplicationUsingDynamicProgramming{
	public static void main(String [] args){
		System.out.println("Enter number of matrices:");
		Scanner sc= new Scanner(System.in);
		int n=sc.nextInt();
		System.out.format("Enter %d dimensions:",n+1);
		int []p=new int[n+1];
		for(int i=0;i<p.length;i++)
			p[i]=sc.nextInt();
		int[][] minMul=new int[n][n];
		int [][] split=new int[n][n];
		for(int i=0;i<n;i++){
			minMul[i][i]=0;			//single matrix ,noofmul=0
		}
		System.out.println("Length|i|j|splitIndex|minimumMultiplications");
		for(int length=2;length<=n;length++){
			for(int i=0;i<n-length+1;i++){
				int j=i+length-1;
				minMul[i][j]=Integer.MAX_VALUE;
				for(int k=i;k<j;k++){
					int cost=minMul[i][k]+minMul[k+1][j]+p[i]*p[k+1]*p[j+1];
					if(cost<minMul[i][j]){
						minMul[i][j]=cost;
						split[i][j]=k;
					}
				}
				System.out.format("%d | %d | %d | %d | %d\n",length,i,j,split[i][j],minMul[i][j]);
			}
			
		}
		System.out.println("Optimal Substructure result for optimized number of multiplications: "+minMul[0][n-1]);
		printParenthesis(split,0,n-1);
		System.out.println();
		System.out.println("End of my program!! :)");
	}
	public static void printParenthesis(int [][]split,int i,int j){
		if(i==j){
			System.out.print("M"+i);
			return;
		}
		System.out.print("(");
		printParenthesis(split,i,split[i][j]);
		printParenthesis(split,split[i][j]+1,j);
		System.out.print(")");
	}
}