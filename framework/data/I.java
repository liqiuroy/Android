package data;

/**
 * ������Ӧ�������еĵĽӿڵ�ַ��
 * �ӿ��е�ռλ����#s--�ַ���    #f--�ļ���
 * �ӿڸ�ʽ��<�ύ��ʽ:��ռλ����url> ����: POST:/someurl?paramName1=#s&paramName2=#f
 * ȱʡ�ύ��ʽΪget ����: GET:/someurl?paramName1=#s&paramName2=#s �� /someurl?paramName1=#s&paramName2=#s
 * 
 * @author Administrator
 * ע�⣺get�ύ���ͽӿڲ��ܴ��� #f �ļ����Ͳ���
 */
public final class I {
	/**
	 * �ӿڵ�ַ��Ŀ¼
	 */
	private final static String ROOT = "http://192.168.1.102:8088";
	public final static String SAVE_TIMELINE = "/hf.action?operate=saveTimeline&content=#s";
	
	/**
	 * ��ȡhttp���ʵ���ʵUrl
	 */
	public static String i(String url){
		return ROOT+url;
	}
}
