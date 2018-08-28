
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/***
 * 通用mybatis ognl
 * 
 * 使用规范：@com.fhqb.common.util@equals(payFlag,'1')
 * payFlag为外部传入参数
 * ‘1’为需要对比的参数
 * 
 * 可根据自己的需求任意扩展，但因为是通用的原因，
 * 所以如果新加了通用mapper校验条件，
 * 需反复测逻辑保证所有用到的地方都是正确的。
 * ClassName:Ognl.java
 * @author Sam Tan
 * @Description TODO
 * @date 2018年3月21日
 */
public class Ognl {
    public Ognl() {
    }

    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
        if(o == null) {
            return true;
        } else {
            if(o instanceof String) {
                if(((String)o).length() == 0) {
                    return true;
                }
            } else if(o instanceof Collection) {
                if(((Collection)o).isEmpty()) {
                    return true;
                }
            } else if(o.getClass().isArray()) {
                if(Array.getLength(o) == 0) {
                    return true;
                }
            } else {
                if(!(o instanceof Map)) {
                    return false;
                }

                if(((Map)o).isEmpty()) {
                    return true;
                }
            }

            return false;
        }
    }
    
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isBlank(String s) {
        return StringUtils.isBlank(s);
    }

    public static boolean isNotBlank(String s) {
        return StringUtils.isNotBlank(s);
    }
    
	public static boolean equals(String param,String param2) {
        if(param == null || param2 == null){
        	return false;
        }
        return param.equals(param2);
    }
	
	public static boolean notEquals(String param,String param2) {
        if(param == null || param2 == null){
        	return false;
        }
        return !param.equals(param2);
    }
	

	public static boolean isNotEmptyAnd(Object... os) {
        if(os == null || os.length == 0){
        	return false;
        }
        for (Object o : os) {
        	if(isEmpty(o)){
        		return false;
        	}
		}
        return true;
    }
	
	public static boolean isNotEmptyOr(Object... os) {
        if(os == null || os.length == 0){
        	return false;
        }
        for (Object o : os) {
        	if(isNotEmpty(o)){
        		return true;
        	}
		}
        return false;
    }
	
	/**
	 * 大于
	 * @return
	 */
	public static boolean gt(int a,int b) {
        if(a > b){
        	return true;
        }
        return false;
    }
	
	/**
	 * 小于
	 * @return
	 */
	public static boolean lt(int a,int b) {
        return !gt(a, b);
    }
	
	
	
	/**
	 * 大于等于
	 * @return
	 */
	public static boolean gte(int a,int b) {
        if(a >= b){
        	return true;
        }
        return false;
    }
	
	/**
	 * 小于等于
	 * @return
	 */
	public static boolean lte(int a, int b) {
		if (a <= b) {
			return true;
		}
		return false;
	}
    
}
