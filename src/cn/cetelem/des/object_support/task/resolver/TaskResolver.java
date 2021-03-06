package cn.cetelem.des.object_support.task.resolver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.object_support.task.pojo.EndNode;
import cn.cetelem.des.object_support.task.pojo.StartNode;
import cn.cetelem.des.object_support.task.pojo.TaskChar;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务配置解析器
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public class TaskResolver {
	
	/**
	 * @return the blockWhenExhausted
	 */
	public boolean getBlockWhenExhausted() {
		return blockWhenExhausted;
	}

	/**
	 * @param blockWhenExhausted the blockWhenExhausted to set
	 */
	public void setBlockWhenExhausted(boolean blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}

	/**
	 * @return the maxIdlePerKey
	 */
	public int getMaxIdlePerKey() {
		return maxIdlePerKey;
	}

	/**
	 * @param maxIdlePerKey the maxIdlePerKey to set
	 */
	public void setMaxIdlePerKey(int maxIdlePerKey) {
		this.maxIdlePerKey = maxIdlePerKey;
	}

	/**
	 * @return the timeBetweenEvictionRunsMillis
	 */
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	/**
	 * @param timeBetweenEvictionRunsMillis the timeBetweenEvictionRunsMillis to set
	 */
	public void setTimeBetweenEvictionRunsMillis(
			long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	/**
	 * @return the testWhileIdle
	 */
	public boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * @param testWhileIdle the testWhileIdle to set
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * @return the numTestsPerEvictionRun
	 */
	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	/**
	 * @param numTestsPerEvictionRun the numTestsPerEvictionRun to set
	 */
	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	/**
	 * @return the maxTotal
	 */
	public int getMaxTotal() {
		return maxTotal;
	}

	/**
	 * @param maxTotal the maxTotal to set
	 */
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * @return the maxTotalPerKey
	 */
	public int getMaxTotalPerKey() {
		return maxTotalPerKey;
	}

	/**
	 * @param maxTotalPerKey the maxTotalPerKey to set
	 */
	public void setMaxTotalPerKey(int maxTotalPerKey) {
		this.maxTotalPerKey = maxTotalPerKey;
	}

	/**
	 * @return the maxWaitMillis
	 */
	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	/**
	 * @param maxWaitMillis the maxWaitMillis to set
	 */
	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	/**
	 * @return the minIdlePerKey
	 */
	public int getMinIdlePerKey() {
		return minIdlePerKey;
	}

	/**
	 * @param minIdlePerKey the minIdlePerKey to set
	 */
	public void setMinIdlePerKey(int minIdlePerKey) {
		this.minIdlePerKey = minIdlePerKey;
	}

	/**
	 * @return the testOnBorrow
	 */
	public boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	/**
	 * @param testOnBorrow the testOnBorrow to set
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * @return the testOnCreate
	 */
	public boolean getTestOnCreate() {
		return testOnCreate;
	}

	/**
	 * @param testOnCreate the testOnCreate to set
	 */
	public void setTestOnCreate(boolean testOnCreate) {
		this.testOnCreate = testOnCreate;
	}

	/**
	 * @return the testOnReturn
	 */
	public boolean getTestOnReturn() {
		return testOnReturn;
	}

	/**
	 * @param testOnReturn the testOnReturn to set
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	/**
	 * @return the minEvictableIdleTimeMillis
	 */
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	/**
	 * @param minEvictableIdleTimeMillis the minEvictableIdleTimeMillis to set
	 */
	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	/**
	 * @return the lifo
	 */
	public boolean getLifo() {
		return lifo;
	}

	/**
	 * @param lifo the lifo to set
	 */
	public void setLifo(boolean lifo) {
		this.lifo = lifo;
	}

	/**
	 * @return the fairness
	 */
	public boolean getFairness() {
		return fairness;
	}

	/**
	 * @param fairness the fairness to set
	 */
	public void setFairness(boolean fairness) {
		this.fairness = fairness;
	}

	/**
	 * @return the evictionPolicyClassName
	 */
	public String getEvictionPolicyClassName() {
		return evictionPolicyClassName;
	}

	/**
	 * @param evictionPolicyClassName the evictionPolicyClassName to set
	 */
	public void setEvictionPolicyClassName(String evictionPolicyClassName) {
		this.evictionPolicyClassName = evictionPolicyClassName;
	}

	/**
	 * @return the softMinEvictableIdleTimeMillis
	 */
	public long getSoftMinEvictableIdleTimeMillis() {
		return softMinEvictableIdleTimeMillis;
	}

	/**
	 * @param softMinEvictableIdleTimeMillis the softMinEvictableIdleTimeMillis to set
	 */
	public void setSoftMinEvictableIdleTimeMillis(
			long softMinEvictableIdleTimeMillis) {
		this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
	}

	/**
	 * taskContext.xml配置文件读取地址
	 */
	private String url;
	/**
	 * 表达式引擎类
	 */
	private String engine;
	private boolean blockWhenExhausted            ;
	private int maxIdlePerKey                 ;
	private long timeBetweenEvictionRunsMillis ;
	private boolean testWhileIdle                 ;
	private int numTestsPerEvictionRun        ;
	private int maxTotal                      ;
	private int maxTotalPerKey                ;
	private long maxWaitMillis                 ;
	private int minIdlePerKey                 ;
	private boolean testOnBorrow                  ;
	private boolean testOnCreate                  ;
	private boolean testOnReturn                  ;
	private long minEvictableIdleTimeMillis    ;
	private boolean lifo                          ;
	private boolean fairness                      ;
	private String evictionPolicyClassName       ;
	private long softMinEvictableIdleTimeMillis;
	
	public static final String DEFAULT_ENGINE = "cn.cetelem.des.expression.engine.impl.JsEngine";
	public static final String DEFAULT_URL = "classpath:cn/cetelem/des/config/taskContext.xml";

	public Map<String, BaseNode> getNodes() throws DocumentException,
			SAXException {
		Document document;
		if (StringUtils.isEmpty(url)) {
			document = TaskXMLUtil.getXMLDoc(DEFAULT_URL);
		} else {
			document = TaskXMLUtil.getXMLDoc(url);
		}
		Element rootE = TaskXMLUtil.getRoot(document);
		Map<String, Element> nodeElements = TaskXMLUtil
				.getAttElementList(rootE);
		Map<String, BaseNode> nodes = new ConcurrentHashMap<String, BaseNode>();
		String defaultInType = rootE.attributeValue("defaultInType");
		String defaultOutType = rootE.attributeValue("defaultOutType");
		for (String nodeName : nodeElements.keySet()) {
			Element ele = nodeElements.get(nodeName);
			BaseNode node;
			if ("start".equals(nodeName)) {
				node = new StartNode();
				node.setName(ele.attributeValue("name"));
				node.setExpression(ele.attributeValue("expression"));
				node.setInType((ele.attributeValue("inType") == null || ""
						.equals(ele.attributeValue("inType"))) ? defaultInType
						: ele.attributeValue("inType"));
				node.setOutType((ele.attributeValue("outType") == null || ""
						.equals(ele.attributeValue("outType"))) ? defaultOutType
						: ele.attributeValue("outType"));
				node.setMethod(ele.attributeValue("method"));
				String[] gotos = ele.attributeValue("goto").split("-");
				node.setGotoBean(gotos[0]);
				if (gotos.length == 2) {
					node.setGotoMethod(gotos[1]);
				}
			} else if ("end".equals(nodeName)) {
				node = new EndNode();
				node.setName(ele.attributeValue("name"));
				node.setExpression(ele.attributeValue("expression"));
				node.setInType((ele.attributeValue("inType") == null || ""
						.equals(ele.attributeValue("inType"))) ? defaultInType
						: ele.attributeValue("inType"));
				node.setOutType((ele.attributeValue("outType") == null || ""
						.equals(ele.attributeValue("outType"))) ? defaultOutType
						: ele.attributeValue("outType"));
				node.setMethod(ele.attributeValue("method"));
			} else {
				node = new TaskChar();
				node.setName(ele.attributeValue("name"));
				node.setExpression(ele.attributeValue("expression"));
				node.setInType((ele.attributeValue("inType") == null || ""
						.equals(ele.attributeValue("inType"))) ? defaultInType
						: ele.attributeValue("inType"));
				node.setOutType((ele.attributeValue("outType") == null || ""
						.equals(ele.attributeValue("outType"))) ? defaultOutType
						: ele.attributeValue("outType"));
				String[] gotos = ele.attributeValue("goto").split("-");
				node.setGotoBean(gotos[0]);
				if (gotos.length == 2) {
					node.setGotoMethod(gotos[1]);
				}
				node.setMethod(ele.attributeValue("method"));
			}
			nodes.put(nodeName, node);
		}
		return nodes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

}
