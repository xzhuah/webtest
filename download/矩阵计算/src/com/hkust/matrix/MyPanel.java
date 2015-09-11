package com.hkust.matrix;

import javax.swing.*;
public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int unitsNum;
	private double[] row;
	public MyPanel(int unitsNum){
		super();
		this.row=new double[unitsNum];
		this.unitsNum=unitsNum;
		for(int i=0;i<unitsNum;i++){
			this.add(new JTextField(4));
		}
	}
	public double[] getRow(){
		for(int i=0;i<unitsNum;i++){
			if(((JTextField)this.getComponent(i)).getText().equals(""))
				((JTextField)this.getComponent(i)).setText("0");
		}
		try{
		for(int i=0;i<unitsNum;i++){
			row[i]=Double.parseDouble(((JTextField)this.getComponent(i)).getText());
		}
		}catch(Exception e){
			return null;
		}
		return row;
	}
	public void setRow(double[] newrow){
		if(newrow.length==this.unitsNum){
			for(int i=0;i<unitsNum;i++){
				((JTextField)this.getComponent(i)).setText(newrow[i]+"");
				this.row[i]=newrow[i];
			}
		}
		
	}
	public void setNumberj(int j,double newNum){
		try{
			((JTextField)this.getComponent(j)).setText(newNum+"");
			this.row[j]=newNum;
		}catch(Exception e){
			
		}
	}
	public void fix(){
		for(int i=0;i<unitsNum;i++){
			((JTextField)this.getComponent(i)).setSelectionStart(0);
			((JTextField)this.getComponent(i)).setEditable(false);
		}
	}
	public void unfix(){
		for(int i=0;i<unitsNum;i++){
			((JTextField)this.getComponent(i)).setEditable(true);	
		}
	}
}
