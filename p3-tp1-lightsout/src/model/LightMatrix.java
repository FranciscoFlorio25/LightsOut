package model;

import java.util.Random;

public class LightMatrix
{
	private boolean[][] lightMatrix;
	private boolean[][] copy;
	
	public void setLightMatrix(int x, int randomizaciones)
	{
		BooleanMatrix(x, randomizaciones);
		cloneMatrix();
	}

	public void cloneMatrix()
	{	
		this.copy = new boolean[lightMatrix.length][lightMatrix.length];
		for (int i = 0; i < this.lightMatrix.length; i++)
			for(int j = 0; j < this.lightMatrix.length; j++)
				copy[i][j] = lightMatrix[i][j];
	}
	
	public void setValueToMatrix(boolean value, int posX, int posY)
	{
		lightMatrix[posX][posY] = value;
	}

	private boolean[][] BooleanMatrix(int size, int movements)
	{
		lightMatrix = new boolean[size][size];

		Random random = new Random();
		while(!checkMatrixFalse(lightMatrix)) {
			for (int i = 0; i < movements; i++)
				setNewValues(random.nextInt(size), random.nextInt(size));
		}
		

		return lightMatrix;
	}
	
	public boolean checkMatrixFalse(boolean[][] matrix) {

        boolean acum= false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                acum=acum || matrix[i][j];
            }
        }
        return acum;
    }

	public boolean getValue(int x, int y)
	{
		return lightMatrix[x][y];
	}

	public void setNewValues(int i, int j)
	{
		
		this.lightMatrix[i][j] = !this.lightMatrix[i][j];
		if (isUpperValue(i, j))
			this.lightMatrix[i - 1][j] = !this.lightMatrix[i - 1][j];
		if (isLowerValue(i, j))
			this.lightMatrix[i + 1][j] = !this.lightMatrix[i + 1][j];
		if (isRightValue(i, j))
			this.lightMatrix[i][j + 1] = !this.lightMatrix[i][j + 1];
		if (isLeftValue(i, j))
			this.lightMatrix[i][j - 1] = !this.lightMatrix[i][j - 1];
	}

	public boolean checkVictory(){
		return !checkMatrixFalse(lightMatrix);
	}

	private boolean isUpperValue(int i, int j)
	{
		if (i - 1 >= 0)
			return true;
		return false;
	}

	private boolean isLowerValue(int i, int j)
	{
		if (i + 1 < this.lightMatrix.length)
			return true;
		return false;
	}

	private boolean isRightValue(int i, int j)
	{
		if (j + 1 < this.lightMatrix.length)
			return true;
		return false;
	}

	private boolean isLeftValue(int i, int j)
	{
		if (j - 1 >= 0)
			return true;
		return false;
	}

	public void resetLightMatrix()
	{
		this.lightMatrix = this.copy;
	}

	public boolean[][] getLightMatrix()
	{
		return lightMatrix;
	}

	/*public void printMatrix() {
		for (int i = 0; i < lightMatrix.length; i++) {
			for (int j = 0; j < lightMatrix.length; j++) {
				System.out.print(lightMatrix[i][j] + " ");

			}
			System.out.println();
		}
		
	}*/

	
}
