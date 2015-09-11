package com.hkust.matrix;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel TopPanel,BottomPanel;
	private MatrixPanel matrix;
	private JTextField rowNum,colNum;
	private JLabel times,hint;
	private JButton form,calc1,calc2;
	public static void main(String[] args){
		new UI();
	}
	
	public UI(){
		TopPanel=new JPanel();BottomPanel=new JPanel();
		hint=new JLabel("矩阵大小 行×列：",JLabel.CENTER);
		times=new JLabel("×",JLabel.CENTER);
		rowNum=new JTextField(2);colNum=new JTextField(2);
		form=new JButton("生成新矩阵");
		calc1=new JButton("化阶梯矩阵");
		calc2=new JButton("求逆矩阵");
		colNum.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calculator.renewProcess();
				try{
					UI.this.remove(matrix);
				}catch(Exception e){
					
				}
				matrix=new MatrixPanel(Integer.parseInt(rowNum.getText()),Integer.parseInt(colNum.getText()));
				try{
					UI.this.add(matrix);
				}catch(Exception e){
					
				}
				try{
					UI.this.pack();
					((MyPanel)matrix.getComponent(0)).getComponent(0).requestFocus();
					UI.this.validate();
				}catch(Exception e){
					
				}
				
			}
			
		});
		rowNum.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calculator.renewProcess();
				try{
					UI.this.remove(matrix);
				}catch(Exception e){
					
				}
				matrix=new MatrixPanel(Integer.parseInt(rowNum.getText()),Integer.parseInt(colNum.getText()));
				try{
					UI.this.add(matrix);
				}catch(Exception e){
					
				}
				try{
					UI.this.pack();
					((MyPanel)matrix.getComponent(0)).getComponent(0).requestFocus();
					UI.this.validate();
				}catch(Exception e){
					
				}
				
			}
			
		});
		form.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calculator.renewProcess();
				try{
					UI.this.remove(matrix);
				}catch(Exception e){
					
				}
				matrix=new MatrixPanel(Integer.parseInt(rowNum.getText()),Integer.parseInt(colNum.getText()));
				try{
					UI.this.add(matrix);
				}catch(Exception e){
					
				}
				try{
					UI.this.pack();
					((MyPanel)matrix.getComponent(0)).getComponent(0).requestFocus();
					UI.this.validate();
				}catch(Exception e){
					
				}
				
			}
			
		});
		calc1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Calculator.renewProcess();
					Calculator.ladder(matrix);
				} catch (Exception e) {
					
					
				}
				
			}
			
		});
		calc2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				try {
					Calculator.renewProcess();			
					Calculator.OutputInverse(matrix);
				} catch (Exception e) {
					
				}
			}
			
		});
		TopPanel.add(hint);
		TopPanel.add(rowNum);TopPanel.add(times);TopPanel.add(colNum);
		TopPanel.add(form);
		BottomPanel.add(calc1);BottomPanel.add(calc2);
		this.add(TopPanel,BorderLayout.NORTH);
		this.add(BottomPanel,BorderLayout.SOUTH);
		
		this.setTitle("矩阵计算器");
		this.setLocation(200, 200);
		this.pack();
		this.setVisible(true);
		rowNum.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
