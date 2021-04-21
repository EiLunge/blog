package com.sx.blog.common.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Word2{
	private Scanner scanner;

	public Map<String, Integer> select(String tit) {
		
		String strE = tit;
		
		scanner = new Scanner(strE);
		
		//单词和数量映射表
		HashMap<String, Integer> hashMap=new HashMap<String,Integer>();
				
		while(scanner.hasNextLine()){
			
			String line=scanner.nextLine();
			
			//用非单词符来做分割，分割出来的就是一个个单词
			String[] lineWords=line.split("");
			
			//keySet()获取map中的键值， Set<K> keySet()返回此映射中所包含的键的 set 视图。
			Set<String> wordSet=hashMap.keySet();
			
			for(int i=0;i<lineWords.length;i++){
				
				//如果已经有这个单词了，
				if(wordSet.contains(lineWords[i])){
					
					//获取该文字下标
					Integer number=hashMap.get(lineWords[i]);
					
					number++;
					
					//文字和数量
					hashMap.put(lineWords[i], number);
					
				}else 
				{
					hashMap.put(lineWords[i], 1);
					
				}
			}
			
		}
		return hashMap;	
	}
}
