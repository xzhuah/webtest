package com.hkust.matrix;

import java.awt.GridLayout;

import javax.swing.*;

public class MatrixPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	public MatrixPanel(int row,int col){
		super();
		this.row=row;
		this.col=col;
		super.setLayout(new GridLayout(row,1));
		for(int i=0;i<row;i++){
			this.add(new MyPanel(col));
		}	
	}

	public int getRow() {
		return row;
	}

	
	public int getCol() {
		return col;
	}

	public void setAij(int i,int j,double k) throws Exception{ //start from 1 1
		if(i>row||i<=0||j<=0||j>col) throw new Exception("Out of range");
		((MyPanel)this.getComponent(i-1)).setNumberj(j-1, k);
		
	}

	public double getAij(int i,int j) throws Exception{ //start from 1 1
		if(i>row||i<=0||j<=0||j>col) throw new Exception("Out of range");
		return ((MyPanel)this.getComponent(i-1)).getRow()[j-1];
	}
	public void exchangeRowij(int i,int j) throws Exception{
		if(i>row||i<=0||j<=0||j>col) throw new Exception("Out of range");
		double[] temprow=new double[this.col];
		for(int ii=0;ii<this.col;ii++){
			temprow[ii]=((MyPanel)this.getComponent(i-1)).getRow()[ii];
		}
		((MyPanel)this.getComponent(i-1)).setRow(((MyPanel)this.getComponent(j-1)).getRow());
		((MyPanel)this.getComponent(j-1)).setRow(temprow);
		
	}
	public void timesRowiByk(int i,double k) throws Exception{
		if(i>row||i<=0) throw new Exception("Out of range");
		double[] temprow=((MyPanel)this.getComponent(i-1)).getRow();
		for(int j=0;j<temprow.length;j++){
			temprow[j]*=k;
		}
		((MyPanel)this.getComponent(i-1)).setRow(temprow);
	}
	public void RowiPluskRowj(int i,int j,double k) throws Exception{
		if(i>row||i<=0||j<=0||j>col) throw new Exception("Out of range");
		double[] temprow=((MyPanel)this.getComponent(j-1)).getRow();
		double[] temprow2=((MyPanel)this.getComponent(i-1)).getRow();
		for(int jj=0;jj<temprow.length;jj++){
			temprow[jj]*=k;
			temprow[jj]+=temprow2[jj];
		}
		((MyPanel)this.getComponent(i-1)).setRow(temprow);
	}
	public void fix(){
		for(int i=0;i<row;i++){
			((MyPanel)this.getComponent(i)).fix();
		}
	}
	public void unfix(){
		for(int i=0;i<row;i++){
			((MyPanel)this.getComponent(i)).unfix();
		}
	}
	
}
