package com.xzc.stock.download.core.advanced;

import java.io.IOException;
import java.net.URL;

import org.htmlparser.util.NodeList;

import com.xzc.stock.download.core.entity.Stock;
import com.xzc.stock.download.core.util.CommonMethod;

public class Advanced extends CommonMethod{
	private final static String CRLF = System.getProperty("line.separator");
	
//	public AdvancedData getAdvancedData(AdvancedData ad) throws IOException{
//		URL url1 = new URL("https://finance.yahoo.com/quote/"+ad.getName()+"/key-statistics?p="+ad.getName());
//		URL url2 = new URL("https://finance.yahoo.com/quote/"+ad.getName()+"/analysts?p="+ad.getName());
//		String str1 = retriveBody(url1,0);
//		String str2 = retriveBody(url2,0);
//		return setData(ad,str1,str2);
//	}
	
	public String getEPSHistory(String str){
		try{
		int start = str.indexOf("EPS Actual")+10;
		int end = str.indexOf("Difference");
		System.out.println("getEPSHistory --> " + start+" "+end);
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getEPSTrend(String str){
		try{
		int start = str.indexOf("Current Estimate")+16;
		int end = str.indexOf("7 Days Ago");
		return str.substring(start, end);
		}catch(Exception e){
			return "";
		}
		
	}
	
	public String getGrowthEstimate(String str){
		try{
		int start = str.indexOf("500Current Qtr.")+15;
		int end = str.indexOf("Next 5 Years");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String retriveBody(URL url, int index) throws IOException{
		NodeList nodelist = new NodeList();
		nodelist = getNodeList(url);
		String str = getHtmlBody(nodelist,index);
		str = str.replaceAll("<[^>]*>", "");
		return str;
		
		
	}
	
	public String getPEG(String str){
		try{
		int start = str.indexOf("PEG")+26;
		int end = str.indexOf("Price/Sales");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getEPSGrowth(String str){
		try{
		int start = str.indexOf("Quarterly Earnings Growth")+31;
		int end = str.indexOf("Balance SheetTotal Cash");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getCashPerShare(String str){
		try{
		int start = str.indexOf("Total Cash Per Share")+26;
		int end = str.indexOf("Total Debt");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getOperatingCashFlow(String str){
		try{
		int start = str.indexOf("Operating Cash Flow")+25;
		int end = str.indexOf("Levered Free Cash Flow");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getLeveredFreeCashFlow(String str){
		try{
		int start = str.indexOf("Levered Free Cash Flow")+28;
		int end = str.indexOf("Trading InformationStock");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getRevenuePerShare(String str){
		try{
		int start = str.indexOf("Revenue Per Share")+23;
		int end = str.indexOf("Quarterly Revenue Growth");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public String getQuarterlyRevenueGrowth(String str){
		try{
		int start = str.indexOf("Quarterly Revenue Growth")+30;
		int end = str.indexOf("Gross Profit");
		return str.substring(start,end);
		}catch(Exception e){
			return "";
		}
	}
	
	public Stock setData(Stock ad, String str,String str1){
		ad.setCashPerShare(getCashPerShare(str));
		ad.setEpsGrowth(getEPSGrowth(str));
		ad.setLeveredFreeCashFlow(getLeveredFreeCashFlow(str));
		ad.setOperatingCashFlow(getOperatingCashFlow(str));
		ad.setPeg(getPEG(str));
		ad.setQuarterlyRevenueGrowth(getQuarterlyRevenueGrowth(str));
		ad.setRevenuePerShare(getRevenuePerShare(str));
		ad.setEpsHistory(getEPSHistory(str1));
		ad.setEpsTrend(getEPSTrend(str1));
		ad.setGrowthEstimate(getGrowthEstimate(str1));

		return ad;
	}
	
//	public static void main(String args[]) throws IOException{
//		Advanced ad = new Advanced();
//		AdvancedData adv = new AdvancedData();
//		adv.setName("AAN");
//		ad.getAdvancedData(adv);
//	}
}
