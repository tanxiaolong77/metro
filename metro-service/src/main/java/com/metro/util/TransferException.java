package com.metro.util;

/***
 * 转换异常
 * ClassName:TransferException.java
 * @author Sam Tan
 * @Description TODO
 * @date 2017年11月23日
 */
public class TransferException  extends Exception{
	
	
	private static final long serialVersionUID = 5553770087551719255L;

	public TransferException() {
	}

	public TransferException(String paramString) {
		super(paramString);
	}

	public TransferException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public TransferException(Throwable paramThrowable) {
		super(paramThrowable);
	}

	protected TransferException(String paramString, Throwable paramThrowable,
			boolean paramBoolean1, boolean paramBoolean2) {
		super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
	}
	
}
