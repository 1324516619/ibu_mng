package com.luolong.common.taglib;

import java.text.DecimalFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

@SuppressWarnings("serial")
public class DoubleValueTag extends TagSupport{
	private Object doubleValue;	//2012-7-19 修改类型
	private Integer isInt;
	public Object getDoubleValue() {
		if(null == doubleValue ||"".equals(doubleValue.toString())){
			return 0;
		}
		return doubleValue;
	}

	DecimalFormat df =new DecimalFormat("####.##");
	public void setDoubleValue(Object doubleValue) {
		this.doubleValue = doubleValue;
		try {
		      if (doubleValue != null)
		        this.doubleValue = ExpressionEvaluatorManager.evaluate("doubleValue", doubleValue.toString(), Object.class, this, this.pageContext);
		      else
		        this.doubleValue = null;
		    } catch (JspException e) {
		      e.printStackTrace();
		    }
	}

	public Integer getIsInt() {
		return isInt;
	}




	public void setIsInt(Integer isInt) {
		this.isInt = isInt;
	}




	@Override
	public int doStartTag() throws JspException {
		if(null != isInt){
			 df =new DecimalFormat("####");
		}
		try {
			this.pageContext.getOut().print(formatDoubleValue());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return EVAL_PAGE;
	}


	private  String formatDoubleValue(){
		String formatStr =df.format(Double.valueOf(this.getDoubleValue().toString()));
		if(".00".equals(formatStr)){
			formatStr ="0.00";
		}
		return formatStr;
	}

	
	


}
