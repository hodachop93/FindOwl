package hodachop.animation;

public class Rotate {
	public static void Change(Point A,Affine B)
	{
	     //B[0][0]=A[0];      B[0][1]=A[1];    B[0][2]=1;
	     B.x[0][0]=A.x[0]; 
	     B.x[0][1]=A.x[1];
	     B.x[0][2]=1;
	}
	
	public static void MatMul(Affine A,Affine B,Affine C,int m,int n)
	{
	     int i,j,k;
	     for(i=0;i<m;i++)
	     for(j=0;j<n;j++)
	     	{
	             C.x[i][j]=0;
	             for(k=0;k<n;k++) 
	            	 C.x[i][j]+=A.x[i][k]*B.x[k][j];
	        }
	}
	
	public static void Quay(Affine T,double fi){
	     T.x[0][0]=(float)Math.cos(fi);     
	     T.x[0][1]=(float)Math.sin(fi);         
	     T.x[0][2]=0;
	     T.x[1][0]=(float)-Math.sin(fi);    
	     T.x[1][1]=(float)Math.cos(fi);         
	     T.x[1][2]=0;
	     T.x[2][0]=0;         
	     T.x[2][1]=0;             
	     T.x[2][2]=1;
	}
	
	public static void Tinhtien(Affine T,float x,float y){
	     T.x[0][0]=1;      T.x[0][1]=0;       T.x[0][2]=0;
	     T.x[1][0]=0;      T.x[1][1]=1;       T.x[1][2]=0;
	     T.x[2][0]=x;      T.x[2][1]=y;       T.x[2][2]=1;
	}
	
	public static void Tich(Affine A,Affine B,Affine C){
	     Affine Tr1,Tr;
	     Tr1 = new Affine();
	     Tr = new Affine();
	     Tr1.x[0][0]=A.x[2][0];   Tr1.x[0][1]=A.x[2][1];  Tr1.x[0][2]=1;
	     MatMul(A,B,C,2,2);
	     MatMul(Tr1,B,Tr,1,2);
	     C.x[2][0]=Tr.x[0][0]+B.x[2][0];
	     C.x[2][1]=Tr.x[0][1]+B.x[2][1];
	}

	public static void QuayTamO(Affine T,double fi,float x,float y){
	     Affine T1,T2,Q,tam;
	     T1 = new Affine();
	     T2 = new Affine();
	     Q = new Affine();
	     tam = new Affine();
	     tam.x[0][2]=0;
	     Tinhtien(T1,-x,-y);
	     Tinhtien(T2,x,y);
	     Quay(Q,fi);
	     Tich(T1,Q,tam);
	     Tich(tam,T2,T);
	}

}
