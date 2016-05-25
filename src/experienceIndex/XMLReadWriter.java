package experienceIndex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLReadWriter {
	
	public XMLReadWriter(){
		
	}
	
	public Map<String,String> xmlRead(String reg) throws DocumentException{
		
		Map<String,String> mMap = new HashMap<String,String>();
		
		//�ȴ���һ��Reader
		SAXReader reader = new SAXReader();
		//��Reader����ȡ�ļ��õ�һ��document���е���HTML 
		Document document = reader.read(new File("C:/Users/huasuhui/Desktop/test.xml"));
		//�ڵõ����document�ĸ��ڵ�
		Element root = document.getRootElement();
		//���õ���������������Ԫ��
		Iterator it = root.elementIterator();
		while(it.hasNext()){
			Element element = (Element) it.next();
			String theme = element.elementText("theme");
			String answer = element.elementText("answer");
			if(theme.contains(reg) || answer.contains(reg)){
				mMap.put(theme, element.elementText("answer"));
			}
		}
		return mMap;
	}
	
	public boolean xmlWriter(String theme,String answer) throws DocumentException, IOException{
		//�ȴ���һ��Reader
		SAXReader reader = new SAXReader();
		//��Reader����ȡ�ļ��õ�һ��document���е���HTML 
		Document document = reader.read(new File("C:/Users/huasuhui/Desktop/test.xml"));
		//�ڵõ����document�ĸ��ڵ�
		Element root = document.getRootElement();
		Element ploblem = root.addElement("Ploblem");
		Element themeElement = ploblem.addElement("theme");
		themeElement.addText(theme);
		Element answerElement = ploblem.addElement("answer");
		answerElement.addText(answer);
		System.out.println("success!");
		XMLWriter writer=new XMLWriter(new FileOutputStream("C:/Users/huasuhui/Desktop/test.xml"));
		writer.write(document);
		writer.close();
		
		return false;
	}
	
	public static void main(String[] args){
		XMLReadWriter xml = new XMLReadWriter();
		try {
//			xml.xmlWriter("111","222");
			Map<String,String> map = xml.xmlRead("1");
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String theme = it.next();
				System.out.println(theme+" : "+map.get(theme));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
