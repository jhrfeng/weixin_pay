package WeiXinResult;

public class Notify_test {
/**
 
  /**微信回调函数
 * 结果通知
 * @author Administrator
 *
 */
public class PaySult extends HttpServlet{
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
    }
protected void doPost(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
System.out.println("----接收微信发来的消息---");
 
// 获取收到的报文
        BufferedReader reader = request.getReader();
         String line = "";
        StringBuffer inputString = new StringBuffer();
        try{
        	while ((line = reader.readLine()) != null) {
    	        inputString.append(line);
    	        }
    	        request.getReader().close();
    	        System.out.println("----接收到的报文---"+inputString.toString());
    	        Map<Object, Object> map = XMLUtil.doXMLParse(inputString.toString());
    	    for(Object keyValue : map.keySet()){
    	        System.out.println(keyValue+"="+map.get(keyValue)); 
    	    }
    	    if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
    	        //TODO 对数据库的操作
    	        response.getWriter().write(XMLUtil.setXML("SUCCESS", ""));   //告诉微信服务器，我收到信息了，不要在调用回调action了
    	        System.out.println("-------------"+XMLUtil.setXML("SUCCESS", ""));
    	    }
        }catch(Exception e){
        	e.printStackTrace();
        }
          
    }
}
  
 
 * ***
 **/
}
