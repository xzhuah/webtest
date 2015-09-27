

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;


//数据载体T必须公开所有属性，且只能有String属性，第一个属性为主键,有默认constructor
//本类计数从0开始
//Suggested structure Top Layer Program<-->User's bean Object<-->Suitable pretreatment Object<T><-->TxtDBMS<-->TxtFile
public class SimpleTXTDBMS<T> {
	String Txtpath;
	T bean;
	Class<T> classType;
	Field[] field;
	//Method[] methods;
	@SuppressWarnings("rawtypes")
	Constructor[] constructor;//
	int [] uniqueKey;
	public SimpleTXTDBMS(String Txtpath,String classFullName,int [] uniqueKey) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		this.Txtpath=Txtpath;
		this.uniqueKey=uniqueKey;	
		@SuppressWarnings("unchecked")
		Class<T> classType = (Class<T>) Class.forName(classFullName);
		field = classType.getDeclaredFields();
		constructor=classType.getConstructors();
	}
	public Vector<T> getAll() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
		return getObjFromVector(readFileByLines());
	}
	//Add a java bean object to you txt database
	public boolean addObject(T obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException{
		Vector<T> testlist=getAll();
		for(int j=0;j<testlist.size();j++){
			for(int i=0;i<uniqueKey.length;i++){
				if(field[uniqueKey[i]].get(testlist.get(j)).equals(field[uniqueKey[i]].get(obj))){
					return false;
				}
			}
		}
		String s = "";
		for(int i=0;i<field.length;i++){
			s+=(field[i].get(obj)+"\t");
		}
		s+="\n";
		BufferedWriter bof=new BufferedWriter(new FileWriter(Txtpath,true));
		bof.write(s);
		bof.close();
		return true;
	}
	//Remove a java bean object from you txt database
	public void removeObj(T obj) throws Exception{
		String s = "";
		for(int i=0;i<field.length;i++){
			s+=(field[i].get(obj)+"\t");
		}
		s+="\n";
		TxtReader read=new TxtReader();
		String target=read.getTextFromTxt(Txtpath);
		target=target.replace(s, "");
		BufferedWriter bof=new BufferedWriter(new FileWriter(Txtpath));
		bof.write(target);
		bof.close();
		
		
	}
	//Update a java bean object in you txt database
	public void update(T obj,T newObj) throws Exception{
		this.removeObj(obj);
		this.addObject(newObj);
	}
	//Get a java bean object by primekey you txt database
	public T getByPrimeKey(String keyValue) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
		System.out.println(constructor[0].toString());
		@SuppressWarnings("unchecked")
		T object=(T) constructor[0].newInstance(null);
		Vector<T> result=getObjFromVector(readFileByLines());
		for(int i=0;i<result.size();i++){
			if(field[0].get(result.get(i)).equals(keyValue)) return result.get(i);
		}
		return null;		
	}
	//Get java bean objects by a key from you txt database
	public Vector<T> getByKey(int keynum,String keyValue) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
		System.out.println(constructor[0].toString());
		@SuppressWarnings("unchecked")
		T object=(T) constructor[0].newInstance(null);
		Vector<T> result=getObjFromVector(readFileByLines());
		Vector<T> list=new Vector<T> ();
		for(int i=0;i<result.size();i++){
			if(field[keynum].get(result.get(i)).equals(keyValue)) {
				list.add(result.get(i));
			}
		}
		return list;		
	}	
	private Vector<String> readFileByLines() {	
		File file = new File(this.Txtpath);		
		BufferedReader reader = null;
		Vector<String> result=new Vector<String>();
		try{
			reader = new BufferedReader(new FileReader(file));		
			String tempString = null;	
			
			while ((tempString = reader.readLine()) != null){	
				System.out.println(tempString);
				result.add(tempString);
				
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
				if(reader != null) {		
				try {	
					reader.close();
				}catch(IOException e1) {}		
				}
		}
		return result;
	}

	private Vector<T> getObjFromVector(Vector<String> result) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
		Vector<T> list=new Vector<T>();
		T temp=(T) constructor[0].newInstance(null);
		String[] every;
		for(int i=0;i<result.size();i++){
			every=result.get(i).split("\t");
			for(int j=0;j<field.length;j++){
				field[j].set(temp,every[j]);
			}
			list.add(temp);
		}
		return list;
		
	}
	private class TxtReader {   
	    public TxtReader() {           
	    }   
	    /**  
	     * @param filePath 文件路径  
	     * @return 读出的txt的内容  
	     */  
	    public String getTextFromTxt(String filePath) throws Exception {   
	           
	        FileReader fr = new FileReader(filePath);   
	        BufferedReader br = new BufferedReader(fr);   
	        StringBuffer buff = new StringBuffer();   
	        String temp = null;   
	        while((temp = br.readLine()) != null){   
	            buff.append(temp + "\r\n");   
	        }   
	        br.close();        
	        return buff.toString();        
	    }
	}
	
	
}

