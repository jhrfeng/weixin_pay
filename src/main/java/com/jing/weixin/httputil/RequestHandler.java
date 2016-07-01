package com.jing.weixin.httputil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.jing.weixin.utils.MD5Util;
import com.jing.weixin.utils.TenpayUtil;

public class RequestHandler {
	
	private String Token;
	private String charset;
	private SortedMap parameters; //请求参数
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/** 商户参数 */
	private String appid;
	private String appkey;
	private String partnerkey;
	private String appsecret;
	private String key;
	
	 //设置密钥
	
	public void setKey(String key) {
		this.partnerkey = key;
	}

	public String getKey() {
		return key;
	}

	//设置微信密钥
	public void  setAppKey(String key){
		this.appkey = key;
	}
	
	
	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	public RequestHandler(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.charset = "UTF-8";
		this.parameters = new TreeMap();
		
	}
	
	/**
	 * 初始化函数。
	 */
	public void init(String app_id, String app_secret,	String partner_key) {
		this.Token = "token_";
		this.appid = app_id;
		this.appsecret = app_secret;
		this.partnerkey = partner_key;
		this.key = partner_key;
	}
	
	public void init() {
	}
	
	// 特殊字符处理
	public String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
	}
	
	// 获取package的签名包
	public String genPackage(SortedMap<String, String> packageParams, String key)throws UnsupportedEncodingException {
		String sign = createSign(packageParams, key);
		
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}

		// 去掉最后一个&
		String packageValue = sb.append("sign=" + sign).toString();
		System.out.println("UrlEncode后 packageValue=" + packageValue);
		return packageValue;
	}
	
	public static String createSign(SortedMap<String, String> packageParams, String key){
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null!=v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)){
				sb.append(k+"=" + v + "&");
			}
		}
		sb.append("key=" +key);
		System.out.println("MD% sb:" + sb);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		System.out.println("packge签名：" +sign);
		return sign;
	}

	/**
	 * 创建package签名
	 */
	public boolean createMd5Sign(String signParams){
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		// 算出摘要
		String enc = TenpayUtil.getCharacterEncoding(this.request,
				this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();

		// debug信息
		System.out.println(sb.toString() + " => sign:" + sign + " tenpaySign:"
				+ tenpaySign);

		return tenpaySign.equals(sign);
	}
	
	//输出XML

    //输出XML
	   public static String parseXML(SortedMap<String, String> packageParams) {
		   StringBuffer sb = new StringBuffer();
	       sb.append("<xml>");
	       Set es = packageParams.entrySet();
	       Iterator it = es.iterator();
	       while(it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				String k = (String)entry.getKey();
				String v = (String)entry.getValue();
				if(null != v && !"".equals(v) && !"appkey".equals(k)) {
					
					sb.append("<" + k +">" + v + "</" + k + ">\n");
				}
			}
	       sb.append("</xml>");
			return sb.toString();
		}

	   /**
	     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	     *
	     * @param strxml
	     * @return
	     */
	    public static Map doXMLParse(String strxml) throws Exception {
	        if (null == strxml || "".equals(strxml)) {
	            return null;
	        }

	        Map m = new HashMap();
	        InputStream in = String2Inputstream(strxml);
	        SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(in);
	        Element root = doc.getRootElement();
	        List list = root.getChildren();
	        Iterator it = list.iterator();
	        while (it.hasNext()) {
	            Element e = (Element) it.next();
	            String k = e.getName();
	            String v = "";
	            List children = e.getChildren();
	            if (children.isEmpty()) {
	                v = e.getTextNormalize();
	            } else {
	                v = getChildrenText(children);
	            }

	            m.put(k, v);
	        }

	        //关闭流
	        in.close();

	        return m;
	    }
	    
	    public static InputStream String2Inputstream(String str) {
	        return new ByteArrayInputStream(str.getBytes());
	    }
	    
	    /**
	     * 获取子结点的xml
	     *
	     * @param children
	     * @return String
	     */
	    public static String getChildrenText(List children) {
	        StringBuffer sb = new StringBuffer();
	        if (!children.isEmpty()) {
	            Iterator it = children.iterator();
	            while (it.hasNext()) {
	                Element e = (Element) it.next();
	                String name = e.getName();
	                String value = e.getTextNormalize();
	                List list = e.getChildren();
	                sb.append("<" + name + ">");
	                if (!list.isEmpty()) {
	                    sb.append(getChildrenText(list));
	                }
	                sb.append(value);
	                sb.append("</" + name + ">");
	            }
	        }

	        return sb.toString();
	    }
	    
	//格式化32位时间随机数字
	 public static String getRandomDateNo() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		  String dateString = formatter.format(currentTime)+TenpayUtil.buildRandom(4);
		  return dateString;
	 }
	 
	 //
	 public static String newDate() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	 }

}


