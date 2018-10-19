package com.luolong.common.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.tag.rt.core.OutTag;


/**
 * 显示指定长度的信息
 * @author qahe
 *
 */
@SuppressWarnings("serial")
public class ToolTipTag extends OutTag {
	private int cut = 15;
	private boolean tooltip=true;
	private boolean escapeXml=false;

	public int getCut() {
		return cut;
	}
	public void setCut(int cut) {
		this.cut = cut;
	}
	public boolean isTooltip() {
		return tooltip;
	}
	public boolean isEscapeXml() {
		return escapeXml;
	}
	public void setEscapeXml(boolean escapeXml) {
		this.escapeXml = escapeXml;
	}

	public void setTooltip(boolean tooltip) {
		this.tooltip = tooltip;
	}
	
	public int doStartTag() throws JspException {

		try {
			if (value != null) {
				String shortResult = "";
				StringBuffer result = new StringBuffer();
				String valueStr = value.toString();
				valueStr = StringUtils.trim(StringUtils.replace(valueStr, "\r", ""));
				valueStr = StringUtils.trim(StringUtils.replace(valueStr, "\n", ""));
				if(valueStr.length()>cut){
					shortResult = valueStr.substring(0, cut);
					if(tooltip){
						valueStr = StringUtils.replace(valueStr, "\"", "'");
						shortResult = shortResult+"..";
						result.append("<span  title=\"")
						.append(valueStr).append("\">")
						.append(shortResult).append("</span>");	
					}else{
						result.append(shortResult);
					}
				}else{
					result.append(valueStr);
				}
				out(pageContext, escapeXml, result);
			}
		} catch (IOException ex) {
			throw new JspException(ex.toString(), ex);
		}
		return SKIP_BODY;
	}
	

	

}
