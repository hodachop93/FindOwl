package hodachop.animation;

public class Affine {
	public float x[][] = new float [3][3];
	public Affine()
	{
		int i,j;
		for (i=0;i<3;i++)
			for (j=0;j<3;j++)
				x[i][j]=0;
		
	}

		
}
