package com.hkust.matrixcalc;


import android.support.v7.app.ActionBarActivity;
import android.text.method.DigitsKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	private LinearLayout layout;
	private LinearLayout[] rows;
	private EditText[][] Eij;
	private double [][] aij;
	private double [][] oaij;
	private Button form,labber,inverse;
	private String process="";
	private EditText rowNum,colNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout =(LinearLayout)findViewById(R.id.layout_matrix);
        form=(Button)findViewById(R.id.btn_form);
      	labber=(Button)findViewById(R.id.btn_labber);
        inverse=(Button)findViewById(R.id.btn_inverse);
        rowNum=(EditText)findViewById(R.id.num_row);
        colNum=(EditText)findViewById(R.id.num_col);
        form.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					int row=Integer.parseInt(rowNum.getText().toString());
					int col=Integer.parseInt(colNum.getText().toString());
					if(row<=0||col<=0){
						Toast.makeText(MainActivity.this,"������ֻ��Ϊ����",Toast.LENGTH_SHORT).show();
						return;
					}
					if(row>15||col>15){
						Toast.makeText(MainActivity.this,"���������ɳ���15",Toast.LENGTH_SHORT).show();
						return;
					}
					Eij=new EditText[row][col];
					aij=new double[row][col];
					oaij=new double[row][col];
					rows=new LinearLayout[row];
					try{
						layout.removeAllViews();
					}catch(Exception w){
						
					}
					for(int i=0;i<row;i++){
						
						rows[i]=new LinearLayout(MainActivity.this);
						rows[i].setOrientation(LinearLayout.HORIZONTAL);
						for(int j=0;j<col;j++){
							EditText temp=new EditText(MainActivity.this);
							temp.setSingleLine(true);
							DigitsKeyListener numericOnlyListener = new DigitsKeyListener(true,true);
							temp.setKeyListener(numericOnlyListener);
							temp.setWidth(90);
							Eij[i][j]=temp;
							rows[i].addView(Eij[i][j]);
	
						}
						layout.addView(rows[i]);
					}
				}catch(Exception e){
					Toast.makeText(MainActivity.this,"���ִ����޷����ɾ���",Toast.LENGTH_SHORT).show();
				}
				
			}
        	
        });
        labber.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				clearProcess();
				MainActivity.this.getaij();
				MainActivity.this.OutputLabber(aij);
				MainActivity.this.showProcess();
			}
        	
        });
        inverse.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				clearProcess();
				MainActivity.this.getaij();
				MainActivity.this.OutputInverse(aij);
				MainActivity.this.showProcess();
			}
        	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void writeProcess(double[][] aij){
    	this.process+="----------------------------\n";
    	for(int i=0;i<aij.length;i++){
    		for(int j=0;j<aij[i].length;j++){
    			this.process+=(aij[i][j]+"\t\t");
    		}
    		this.process+="\n";
    	}
    	this.process+="----------------------------\n";
    }
    private void writeProcess(String s){
    	this.process=this.process+s+"\n";
    }
    private void clearProcess(){
    	this.process="";
    }
    private void showProcess(){	
    	Intent intent=new Intent();
    	intent.setClass(this, Process.class);
    	Bundle bundle=new Bundle();
    	bundle.putString("process",process);
    	intent.putExtras(bundle);
    	startActivity(intent);
    }
    private void getaij(){
    	for(int i=0;i<aij.length;i++){
    		for(int j=0;j<aij[i].length;j++){
    			try{
    				aij[i][j]=Double.parseDouble(Eij[i][j].getText().toString());
    				oaij[i][j]=Double.parseDouble(Eij[i][j].getText().toString());
    			}catch(Exception e){
    				aij[i][j]=0;
    				oaij[i][j]=0;
    			}
    		}
    	}
    }
    
    
    
    
    private void OutputLabber(double[][] aij){//����ֱ�Ӷ�aij���в���
    	writeProcess("ԭ����Ϊ:");
    	writeProcess(aij);
    	labber(aij,1,1,true,0);
    }
    private void OutputInverse(double[][] aij){
    	if(aij.length!=aij[0].length){
    		writeProcess("ԭ����Ϊ:");
        	writeProcess(aij);
        	writeProcess("����������ȣ�û�������");
    		return;
    	}
    	writeProcess("ԭ����Ϊ:");
    	writeProcess(aij);
		double h=detCalc(aij);
		writeProcess("�þ��������ʽ��ֵΪ"+h);
		if(h!=0){
			writeProcess("ԭ����İ������Ϊ:");
			writeProcess(getAccompany(aij));
			writeProcess("ԭ����������Ϊ:");
			writeProcess(getInverse(aij));
		}else{
			writeProcess("�þ��󲻴�����");
		}
	
    	
    }
    private double[][] getInverse(double[][] aij){
    	//����aij�����޸�
    	double d=detCalc(aij);
    	double[][] bij=getAccompany(aij);
    	for(int i=0;i<aij.length;i++){
			for(int j=0;j<aij[0].length;j++){
				bij[i][j]/=d;
			}
		}
    	return bij;
    	
    }
    private double[][] getAccompany(double[][] aij){
    	//����aij�����޸�
		double[][] bij=new double[aij.length][aij[0].length];
		for(int i=0;i<aij.length;i++){
			for(int j=0;j<aij[0].length;j++){
				bij[i][j]=getAij(aij,j,i);
			}
		}
		return bij;
    	
    }
    private double getAij(double[][] aij,int i,int j){
    	//����aij�����޸�
		double[][] bij=new double[aij.length-1][aij[0].length-1];
		for(int ii=0;ii<aij.length;ii++){
			for(int jj=0;jj<aij[0].length;jj++){
				if(ii<i&&jj<j){
					bij[ii][jj]=aij[ii][jj];				
				}else if(ii>i&&jj<j){
					bij[ii-1][jj]=aij[ii][jj];				
				}else if(ii<i&&jj>j){
					bij[ii][jj-1]=aij[ii][jj];				
				}else if(ii>i&&jj>j){
					bij[ii-1][jj-1]=aij[ii][jj];					
				}	
			}
		}
		return detCalc(bij)*Math.pow(-1,i+j);
    	
    }
    private double detCalc(double[][] aij){
    	//����aij�����޸�
    	double[][] bij=new double[aij.length][aij[0].length];
    	for(int i=0;i<aij.length;i++){
    		for(int j=0;j<aij[i].length;j++){
    			bij[i][j]=aij[i][j];
    		}
    	}
    	int interchage=labber(bij,1,1,false,0);
    	double h=1;
		for(int i=1;i<=bij.length;i++){
			h*=getaij(bij,i,i);
		}
		return h*Math.pow(-1,interchage);
    }
    private int[] getNextNoneZero(double[][] aij,int startrow,int startcol){
    	int[] pos=new int[2];
    	for(int j=startcol;j<=aij[0].length;j++){
    		for(int i=startrow;i<=aij.length;i++){
        		if(getaij(aij,i,j)!=0) {
        			pos[0]=i;pos[1]=j;
        			return pos;
        		}
        	}
    	}
    	pos[0]=0;pos[1]=0;
    	return pos;
    	
    }
    private int labber(double[][] aij,int startNoneZeroRow,int startNoneZeroCol,boolean whetherWrite,int interchageNum){
    	//aij��Ϊ���ݾ���
    	int noneZeroRow,noneZeroCol;
		int loca[]=new int[2];
		loca=getNextNoneZero(aij,startNoneZeroRow,startNoneZeroCol);
		noneZeroRow=loca[0];
		noneZeroCol=loca[1];
		if(noneZeroRow==0&&noneZeroCol==0){ //��������
			if(whetherWrite) writeProcess("�õ����ݾ�������");
			if(whetherWrite) writeProcess(aij);
			
			if(whetherWrite) writeProcess("�þ������Ϊ"+countNotZeroRow(aij));
			
			if(aij.length==aij[0].length){
				double h=1;
				for(int i=1;i<=aij.length;i++){
					h*=getaij(aij,i,i);
				}
				if(whetherWrite) writeProcess("�þ��������ʽ��ֵΪ"+h);
			}
			return interchageNum;
		}else{
			if(noneZeroRow!=startNoneZeroRow){
				if(whetherWrite) writeProcess("��������ĵ�"+startNoneZeroRow+"�к͵�"+noneZeroRow+"�е�:");
				exchangeRowij(aij,noneZeroRow, startNoneZeroRow);
				interchageNum++;
				if(whetherWrite) writeProcess(aij);
			}

			for(int i=startNoneZeroRow+1;i<=aij.length;i++){
				if(getaij(aij,i, noneZeroCol)!=0){
					if(whetherWrite) writeProcess("����"+startNoneZeroRow+"�е�"+-getaij(aij,i, noneZeroCol)/getaij(aij,startNoneZeroRow, noneZeroCol)+"���ӵ���"+i+"���ϵ�:");
					RowiPluskRowj
					(aij,i, startNoneZeroRow, -getaij(aij,i, noneZeroCol)/getaij(aij,startNoneZeroRow, noneZeroCol));
					if(whetherWrite) writeProcess(aij);
				}
				
			}
			startNoneZeroRow+=1;startNoneZeroCol=noneZeroCol+1;	
			return labber(aij,startNoneZeroRow,startNoneZeroCol,whetherWrite,interchageNum);
		}
    }
    
    
   
    

    private double getaij(double[][] aij,int i,int j){
    	return aij[i-1][j-1];
    }
    private void exchangeRowij(double[][] aij,int i,int j){
    	for(int ii=0;ii<aij[i-1].length;ii++){
    		double t=aij[i-1][ii];
    		aij[i-1][ii]=aij[j-1][ii];
    		aij[j-1][ii]=t;
    	}
    }
    private void RowiPluskRowj(double[][] aij,int i,int j,double k){
    	for(int ii=0;ii<aij[i-1].length;ii++){
    		aij[i-1][ii]+=(k*aij[j-1][ii]);
    	}
    }
    private boolean isAllZero(double a[]){
		for(int i=0;i<a.length;i++){
			if(a[i]!=0) return false;
		}
		return true;
	}
    private int countNotZeroRow(double a[][]){
		int count=0;
		for(int i=0;i<a.length;i++){
			if(!isAllZero(a[i])) count++;
		}
		return count;
	}
    
    
}
