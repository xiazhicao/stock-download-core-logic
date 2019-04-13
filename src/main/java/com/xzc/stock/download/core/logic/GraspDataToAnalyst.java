package com.xzc.stock.download.core.logic;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.util.NodeList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xzc.stock.download.core.entity.Stock;
import com.xzc.stock.download.core.logic.dao.DownloadDao;
import com.xzc.stock.download.core.logic.entity.AdvancedData;
import com.xzc.stock.download.core.util.CommonMethod;

@RestController
public class GraspDataToAnalyst extends CommonMethod {
	private final static String CRLF = System.getProperty("line.separator");
	private DownloadDao dao;

	@RequestMapping("/hello")
	public Stock makeHtm(@RequestParam("name") String name) throws Exception {
		System.out.println("get name" + name);
		Stock stock = null;
		AdvancedData adv = new AdvancedData();

		NodeList nodelist = null;

		if (name != null && !name.equals("")) {
			stock = new Stock();
			stock.setName(name);

			try {
				URL ur = new URL("https://www.nasdaq.com/en/symbol/" + name);
				try {
					System.out.println("makeHtm:    " + ur.toString());
					nodelist = getNodeList(ur);
				} catch (ConnectException e) {
					System.out.println("chaoshile");
					makeHtm(name);
				}
				boolean flag = true;
				for (int i = 72; i < 74; i++) {
					try {
						stock.setEarningspershare(getEarningsPerShare(getHtmlBody(
								nodelist, i)));
						stock.setForwardpe(getForward_PE(getHtmlBody(nodelist,
								i)));
						stock.setFutureprice(getFuturePrice(getHtmlBody(
								nodelist, i)));
						stock.setPeratio(getPeRatio(getHtmlBody(nodelist, i)));
						stock.setPrice(getPrice(getHtmlBody(nodelist, i)));
						stock.setBeta(getBeta(getHtmlBody(nodelist, i)));
						flag = true;
					} catch (Exception e) {
						System.out.println("makeHtm Exception:" + e);
						flag = false;
						continue;
					}
					try {
						adv.setEarningspershare(stock.getEarningspershare());
						adv.setBeta(stock.getBeta());
						adv.setForwardpe(stock.getForwardpe());
						adv.setFutureprice(stock.getFutureprice());
						adv.setPeratio(stock.getPeratio());
						adv.setPrice(stock.getPrice());
						adv.setName(stock.getName());
						dao = new DownloadDao();
						dao.addUser(adv);
						Thread.sleep(5 * 60 * 1000);
					} catch (NumberFormatException e) {
						System.out.println("makeHtm writing Exception");
					}
					if (flag)
						break;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stock;
	}

	public static Float getPrice(String htmlBody) {
		if (htmlBody.indexOf("qwidget-dollar") >= 0)
			System.out.println("==>getPrice"
					+ htmlBody.indexOf("qwidget-dollar"));
		String price = htmlBody.substring(htmlBody.indexOf("qwidget-dollar"),
				htmlBody.indexOf("qwidget-dollar") + 20);

		return Float.parseFloat(getPattern(price));
	}

	public static Float getFuturePrice(String htmlBody) {
		try {
			if (htmlBody.indexOf("Year Target") >= 0)
				System.out.println("==>getFuturePrice"
						+ htmlBody.indexOf("Year Target"));
			String futurePrice = htmlBody.substring(
					htmlBody.indexOf("Year Target"),
					htmlBody.indexOf("Year Target") + 200);
			System.out.println(futurePrice);
			System.out.println(htmlBody);
			return Float.parseFloat(getPattern(futurePrice));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public static Float getPeRatio(String htmlBody) {
		try {
			if (htmlBody.indexOf("P/E Ratio") >= 0)
				System.out.println("==>getPeRatio"
						+ htmlBody.indexOf("P/E Ratio"));
			String peRatio = htmlBody.substring(htmlBody.indexOf("P/E Ratio"),
					htmlBody.indexOf("P/E Ratio") + 150);
			System.out.println(peRatio);
			return Float.parseFloat(getPattern(peRatio));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public static Float getForward_PE(String htmlBody) {
		try {
			if (htmlBody.indexOf("Forward P/E") >= 0)
				System.out.println("==>getForward_PE"
						+ htmlBody.indexOf("Forward P/E"));
			String forward_PE = htmlBody.substring(
					htmlBody.indexOf("Forward P/E") + 20,
					htmlBody.indexOf("Forward P/E") + 200);
			System.out.println(forward_PE);
			return Float.parseFloat(getPattern(forward_PE));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public static Float getEarningsPerShare(String htmlBody) {
		try {
			if (htmlBody.indexOf("Earnings Per Share") >= 0)
				System.out.println("==>getEarningsPerShare"
						+ htmlBody.indexOf("Earnings Per Share"));
			String earningsPerShare = htmlBody.substring(
					htmlBody.indexOf("Earnings Per Share"),
					htmlBody.indexOf("Earnings Per Share") + 200);
			System.out.println(earningsPerShare);
			return Float.parseFloat(getPattern(earningsPerShare));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public static Float getBeta(String htmlBody) {
		try {
			if (htmlBody.indexOf("Beta") >= 0)
				System.out.println("==>getBeta" + htmlBody.indexOf("Beta"));
			String beta = htmlBody.substring(htmlBody.indexOf("Beta"),
					htmlBody.indexOf("Beta") + 200);
			System.out.println(beta);
			return Float.parseFloat(getPattern(beta));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public static String getDate() {
		try {
			Date dt = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			return fmt.format(dt);
		} catch (Exception e) {
			return "1970-1-1";
		}
	}

	public static String getPattern(String data) {
		Pattern pattern = Pattern.compile("[^\\d+\\.\\d+]");
		Matcher matcher1 = pattern.matcher(data);
		return matcher1.replaceAll("");
	}

	/**
	 * public static void save(Stock r) { try { String sql =
	 * "insert into stock1 values(?,?,?,?,?,?,?)"; java.sql.Connection connect =
	 * new JdbcConnect().getConnection(); PreparedStatement ps =
	 * connect.prepareStatement(sql); ps.setString(1, r.getName());
	 * ps.setFloat(2, r.getEarningspershare()); ps.setFloat(3,
	 * r.getForwardpe()); ps.setFloat(4, r.getFutureprice()); ps.setFloat(5,
	 * r.getPeratio()); ps.setFloat(6, r.getPrice()); ps.setFloat(7,
	 * r.getBeta()); ps.execute(); connect.close(); } catch (Exception e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * public static void main(String[] args) throws Exception {
	 * GraspDataToAnalyst graspDataToAnalyst = new GraspDataToAnalyst(); String
	 * name = "bac"; List downLoadData = PublicDataUtil.getDownLoadData();
	 * String dir = ""; // int month = Calendar.getInstance().MONTH ; // int day
	 * = Calendar.getInstance().DAY_OF_MONTH; // int year =
	 * Calendar.getInstance().YEAR; dir =
	 * "C:/Users/xuzhi/Desktop/select22222.xlsx"; for (int i = 0; i <
	 * downLoadData.size(); i++) { name = (String) downLoadData.get(i);
	 * System.out.println(i); graspDataToAnalyst.makeHtm(name); try {
	 * System.out.println("Start waiting " + i); Thread.sleep(20000);
	 * 
	 * } catch (Exception e) {
	 * System.out.println("Something wrong is happening " + name); } } // } }
	 **/

}
