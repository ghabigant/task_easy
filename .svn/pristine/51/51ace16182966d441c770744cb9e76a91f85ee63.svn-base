package cn.cetelem.des.object_support.task.resolver;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type XML解析器
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public class TaskXMLUtil {
	/**
	 * 获取document
	 * 
	 * @param url
	 * @return
	 * @throws DocumentException
	 * @throws SAXException
	 */
	static Document getXMLDoc(String url) throws DocumentException,
			SAXException {
		SAXReader reader = new SAXReader();
		reader.setValidation(false);
		reader.setFeature(
				"http://apache.org/xml/features/nonvalidating/load-external-dtd",
				false);
		Document document;
		if(url.startsWith("classpath:")){
			document = reader.read(TaskXMLUtil.class.getClassLoader()
				.getResource(url.split("classpath:")[1]));
		}else{
			document = reader.read(url);
		}
		return document;
	}

	/**
	 * 获取root节点
	 * 
	 * @param document
	 * @return
	 */
	static Element getRoot(Document document) {
		Element rootE = document.getRootElement();
		return rootE;

	}

	/**
	 * 获取目标节点
	 * 
	 * @param id
	 * @param name
	 * @param element
	 * @return
	 */
	static Map<String, Element> getAttElementList(Element element) {
		Map<String, Element> nodes = new HashMap<String, Element>();
		for (Iterator<?> iterator = element.elementIterator(); iterator
				.hasNext();) {
			Element ele = (Element) iterator.next();
			nodes.put((String) ele.attribute("name").getData(), ele);
		}
		return nodes;

	}


}
