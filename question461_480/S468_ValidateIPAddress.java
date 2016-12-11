package question461_480;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年12月11日 上午10:39:25
 *	reference:
 *		http://www.itmmd.com/201412/409.html
 */
public class S468_ValidateIPAddress {
	public String validIPAddress(String IP) {
        if(IP == null || IP.length() == 0){
        	return "Neither";
        }
        
        
        String[] strs = IP.split("\\.");//使用.做分隔符的时候需要使用转义符，而转义符本身需要再次转义
        if(strs.length == 4){
        	if(checkIpv4(IP))
        		return "IPv4";
        	else
        		return "Neither";
        }
       
        String[] strs2 = IP.split(":");
        if(strs2.length == 8){
        	for(String ele : strs2){
        		if(ele.equals(""))
        			return "Neither";
        	}
        	if(checkIpv6(IP)){
        		return "IPv6";
        	}
        	else
        		return "Neither";
        }
        else 
        	return "Neither";
		
        
        
    }
	public boolean checkIpv4(String IP){
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if(IP.matches(regex))
			return true;
		else
			return false;
	}
	
	public boolean checkIpv6(String IP){
		String regex ="^s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|"
				+ "(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|"
				+ "((25[0-5]|2[0-4]d|1dd|[1-9]?d)(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3})|:))|"
				+ "(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|"
				+ ":((25[0-5]|2[0-4]d|1dd|[1-9]?d)(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3})|:))|"
				+ "(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|"
				+ "((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]d|1dd|[1-9]?d)(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3}))|:))|"
				+ "(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:"
				+ "((25[0-5]|2[0-4]d|1dd|[1-9]?d)(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3}))|:))|"
				+ "(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|"
				+ "((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]d|1dd|[1-9]?d)(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3}))|:))|"
				+ "(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]d|1dd|[1-9]?d)"
				+ "(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:"
				+ "((25[0-5]|2[0-4]d|1dd|[1-9]?d)(.(25[0-5]|2[0-4]d|1dd|[1-9]?d)){3}))|:)))(%.+)?s*$";
		if(IP.matches(regex))
			return true;
		else
			return false;
	}
	
	@Test
	public void test(){
		String IP = "2001:db8:85a3:0::8a2E:0370:7334";
		System.out.println(validIPAddress(IP));
	}
}	
