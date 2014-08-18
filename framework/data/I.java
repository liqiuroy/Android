package data;

/**
 * 该类存放应用中所有的的接口地址，
 * 接口中的占位符：#s--字符串    #f--文件流
 * 接口格式：<提交方式:带占位符的url> 例如: POST:/someurl?paramName1=#s&paramName2=#f
 * 缺省提交方式为get 例如: GET:/someurl?paramName1=#s&paramName2=#s 或 /someurl?paramName1=#s&paramName2=#s
 * 
 * @author Administrator
 * 注意：get提交类型接口不能带有 #f 文件流型参数
 */
public final class I {
	/**
	 * 接口地址根目录
	 */
	private final static String ROOT = "http://192.168.1.102:8088";
	public final static String SAVE_TIMELINE = "/hf.action?operate=saveTimeline&content=#s";
	
	/**
	 * 获取http访问的真实Url
	 */
	public static String i(String url){
		return ROOT+url;
	}
}
