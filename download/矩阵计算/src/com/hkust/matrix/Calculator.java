package com.hkust.matrix;


import java.awt.Dimension;



import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Calculator {
	
	static JTextArea processText=new JTextArea();
	
	public static void OutputInverse(MatrixPanel matrix) throws Exception{
		printMatrix("ԭ����Ϊ:");
		printMatrix(matrix);
		if(matrix.getRow()==matrix.getCol()){
			double h=detCalc(matrix);
			printMatrix("�þ��������ʽ��ֵΪ"+h);
			if(h!=0){
				printMatrix("ԭ����İ������Ϊ:");
				printMatrix(getAccompany(matrix));
				printMatrix("ԭ����������Ϊ:");
				printMatrix(getInverse(matrix));
			}else{
				printMatrix("�þ��󲻴�����");
			}
			showProcess("��������");
		}
	}
	public static MatrixPanel getInverse(MatrixPanel matrix) throws Exception{
		//�������
		double d=detCalc(matrix);
		MatrixPanel newmatrix=getAccompany(matrix);
		for(int i=1;i<=matrix.getRow();i++){
			for(int j=1;j<=matrix.getCol();j++){
				newmatrix.setAij(i, j, newmatrix.getAij(i, j)/d);
			}
		}
		return newmatrix;
	}
	public static MatrixPanel getAccompany(MatrixPanel matrix) throws Exception{
		//����������
		if(matrix.getRow()!=matrix.getCol()) throw new Exception("Out of Range");
		MatrixPanel newmatrix=new MatrixPanel(matrix.getRow(),matrix.getCol());
		for(int i=1;i<=matrix.getRow();i++){
			for(int j=1;j<=matrix.getCol();j++){
				newmatrix.setAij(i, j, getAij(matrix,j,i));
			}
		}
		return newmatrix;
	}
	public static double getAij(MatrixPanel matrix,int i,int j) throws Exception{
		//����aij�Ĵ�������ʽ
		if(matrix.getRow()!=matrix.getCol()) throw new Exception("Out of Range");		
		MatrixPanel newmatrix=new MatrixPanel(matrix.getRow()-1,matrix.getCol()-1);
		for(int ii=1;ii<=matrix.getRow();ii++){
			for(int jj=1;jj<=matrix.getCol();jj++){
				if(ii<i&&jj<j){
					newmatrix.setAij(ii, jj,matrix.getAij(ii, jj));
				}else if(ii>i&&jj<j){
					newmatrix.setAij(ii-1, jj,matrix.getAij(ii, jj));
				}else if(ii<i&&jj>j){
					newmatrix.setAij(ii, jj-1,matrix.getAij(ii, jj));
				}else if(ii>i&&jj>j){
					newmatrix.setAij(ii-1, jj-1,matrix.getAij(ii, jj));
				}	
			}
		}
		
		return detCalc(newmatrix)*Math.pow(-1,i+j);
		
	}
	
	
	private static int[] getNextNoneZero(MatrixPanel matrix,int startrow,int startcol) throws Exception{
		int[] next=new int[2];
		int row=matrix.getRow();
		int col=matrix.getCol();
		for(int i=startcol;i<=col;i++){
			for(int j=startrow;j<=row;j++){
				if(matrix.getAij(j, i)!=0){
					next[0]=j;next[1]=i;return next;
				}
			}
		}
		next[0]=0;next[1]=0;
		return next;
		
	}
	public static void ladder(MatrixPanel matrix) throws Exception{
		MatrixPanel newmatrix=new MatrixPanel(matrix.getRow(),matrix.getCol());
		
		for(int i=1;i<=matrix.getRow();i++){
			for(int j=1;j<=matrix.getCol();j++){
				newmatrix.setAij(i, j,matrix.getAij(i, j));		
				
			}
		}
		printMatrix("ԭ����Ϊ:");
		printMatrix(newmatrix);
		ladder(newmatrix,1,1);
	}
	private static void ladder(MatrixPanel matrix,int startNoneZeroRow,int startNoneZeroCol) throws Exception{
		int noneZeroRow,noneZeroCol;
		int loca[]=new int[2];
		loca=getNextNoneZero(matrix,startNoneZeroRow,startNoneZeroCol);
		noneZeroRow=loca[0];
		noneZeroCol=loca[1];
		if(noneZeroRow==0&&noneZeroCol==0){ //��������
			printMatrix("�õ����ݾ�������");
			printMatrix(matrix);	
			
			printMatrix("�þ������Ϊ "+countNotZeroRow(matrix));	
			
			if(matrix.getRow()==matrix.getCol()){
				double h=1;
				for(int i=1;i<=matrix.getRow();i++){
					h*=matrix.getAij(i,i);
				}
				printMatrix("�þ��������ʽ��ֵΪ"+h);
			}
			matrix.fix();
			showProcess("������ݾ���");
			return;
		}else{
			if(noneZeroRow!=startNoneZeroRow){
				printMatrix("��������ĵ�"+startNoneZeroRow+"�к͵�"+noneZeroRow+"�е�:");
				matrix.exchangeRowij(noneZeroRow, startNoneZeroRow);
				printMatrix(matrix);
			}

			for(int i=startNoneZeroRow+1;i<=matrix.getRow();i++){
				if(matrix.getAij(i, noneZeroCol)!=0){
					printMatrix("����"+startNoneZeroRow+"�е�"+-matrix.getAij(i, noneZeroCol)/matrix.getAij(startNoneZeroRow, noneZeroCol)+"���ӵ���"+i+"���ϵ�:");
					matrix.RowiPluskRowj
					(i, startNoneZeroRow, -matrix.getAij(i, noneZeroCol)/matrix.getAij(startNoneZeroRow, noneZeroCol));
					printMatrix(matrix);
				}
				
			}
			startNoneZeroRow+=1;startNoneZeroCol=noneZeroCol+1;	
			ladder(matrix,startNoneZeroRow,startNoneZeroCol);
		}
	}
	
	static double detCalc(MatrixPanel newmatrix) throws Exception{
		MatrixPanel matrix=ladderMatrix(newmatrix);
		if(matrix.getRow()==matrix.getCol()){
			double h=1;
			for(int i=1;i<=matrix.getRow();i++){
				h*=matrix.getAij(i,i);
			}
			return h;
		}
		throw new Exception("Out of Range");
		
	}
	
	public static MatrixPanel ladderMatrix(MatrixPanel matrix) throws Exception{
		MatrixPanel newmatrix=new MatrixPanel(matrix.getRow(),matrix.getCol());
		
		for(int i=1;i<=matrix.getRow();i++){
			for(int j=1;j<=matrix.getCol();j++){
				newmatrix.setAij(i, j,matrix.getAij(i, j));		
				
			}
		}
		
		return ladderMatrix(newmatrix,1,1);
	}
	private static MatrixPanel ladderMatrix(MatrixPanel matrix,int startNoneZeroRow,int startNoneZeroCol) throws Exception{
		int noneZeroRow,noneZeroCol;
		int loca[]=new int[2];
		loca=getNextNoneZero(matrix,startNoneZeroRow,startNoneZeroCol);
		noneZeroRow=loca[0];
		noneZeroCol=loca[1];
		if(noneZeroRow==0&&noneZeroCol==0){ //��������	
			return matrix;
		}else{
			if(noneZeroRow!=startNoneZeroRow){		
				matrix.exchangeRowij(noneZeroRow, startNoneZeroRow);
			}
			for(int i=startNoneZeroRow+1;i<=matrix.getRow();i++){
				if(matrix.getAij(i, noneZeroCol)!=0){				
					matrix.RowiPluskRowj
					(i, startNoneZeroRow, -matrix.getAij(i, noneZeroCol)/matrix.getAij(startNoneZeroRow, noneZeroCol));
					
				}	
			}
			startNoneZeroRow+=1;startNoneZeroCol=noneZeroCol+1;	
			return ladderMatrix(matrix,startNoneZeroRow,startNoneZeroCol);
		}
	}
	
	
	
	
	
	private static void printMatrix(MatrixPanel matrix) throws Exception{
		appendProcessln("-------------------------");
		for(int i=1;i<=matrix.getRow();i++){
			for(int j=1;j<=matrix.getCol();j++){
				appendProcess(matrix.getAij(i, j)+"\t");
			}
			appendProcessln();
		}
		appendProcessln("-------------------------");
	}
	private static void printMatrix(String hint){
		appendProcessln(hint);
	}
	private static void appendProcess(String s){
		processText.setText(processText.getText()+s);
	}
	private static void appendProcessln(String s){
		processText.setText(processText.getText()+s+"\n");
	}
	private static void appendProcessln(){
		processText.setText(processText.getText()+"\n");
	}
	public static void renewProcess(){
		processText.setText("");
	}
	public static void showProcess(String title){
		JFrame processframe=new JFrame();
		JTextArea newprocessText=new JTextArea();
		newprocessText.setText(processText.getText());
		JScrollPane scroll=new JScrollPane(newprocessText);
		processframe.add(scroll);
		processframe.setTitle(title);
		processframe.setLocation(500, 200);
		processframe.setPreferredSize(new Dimension(800,700));
		processframe.pack();
		processframe.setVisible(true);
		processframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private static boolean isAllZero(double a[]){
		for(int i=0;i<a.length;i++){
			if(a[i]!=0) return false;
		}
		return true;
	}
	private static int countNotZeroRow(MatrixPanel matrix){
		int count=0;
		for(int i=0;i<matrix.getRow();i++){
			if(!isAllZero(((MyPanel)matrix.getComponent(i)).getRow())) count++;
		}
		return count;
	}
}
	
