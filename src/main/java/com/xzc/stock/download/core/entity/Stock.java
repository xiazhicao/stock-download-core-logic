package com.xzc.stock.download.core.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stock {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonProperty("peg")
	private String peg;

	@JsonProperty("epsGrowth")
	private String epsGrowth;

	@JsonProperty("cashPerShare")
	private String cashPerShare;

	@JsonProperty("operatingCashFlow")
	private String operatingCashFlow;

	@JsonProperty("leveredFreeCashFlow")
	private String leveredFreeCashFlow;

	@JsonProperty("revenuePerShare")
	private String revenuePerShare;

	@JsonProperty("quarterlyRevenueGrowth")
	private String quarterlyRevenueGrowth;

	@JsonProperty("epsHistory")
	private String epsHistory;

	@JsonProperty("epsTrend")
	private String epsTrend;

	@JsonProperty("growthEstimate")
	private String growthEstimate;

	@JsonProperty("name")
	private String name;

	@JsonProperty("futureprice")
	private float futureprice;

	@JsonProperty("peRatio")
	private float peRatio;

	@JsonProperty("forward_PE")
	private float forward_PE;

	@JsonProperty("earningsPerShare")
	private float earningsPerShare;

	@JsonProperty("price")
	private float price;

	@JsonProperty("beta")
	private float beta;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getFutureprice() {
		return futureprice;
	}
	public void setFutureprice(float futureprice) {
		this.futureprice = futureprice;
	}
	public float getPeratio() {
		return peRatio;
	}
	public void setPeratio(float peRatio) {
		this.peRatio = peRatio;
	}
	public float getForwardpe() {
		return forward_PE;
	}
	public void setForwardpe(float forward_PE) {
		this.forward_PE = forward_PE;
	}
	public float getEarningspershare() {
		return earningsPerShare;
	}
	public void setEarningspershare(float earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getBeta() {
		return beta;
	}
	public void setBeta(float beta) {
		this.beta = beta;
	}
	public String getEpsGrowth() {
		return epsGrowth;
	}
	public void setEpsGrowth(String epsGrowth) {
		this.epsGrowth = epsGrowth;
	}
	public String getEpsHistory() {
		return epsHistory;
	}
	public void setEpsHistory(String epsHistory) {
		this.epsHistory = epsHistory;
	}
	public String getEpsTrend() {
		return epsTrend;
	}
	public void setEpsTrend(String epsTrend) {
		this.epsTrend = epsTrend;
	}
	public String getGrowthEstimate() {
		return growthEstimate;
	}
	public void setGrowthEstimate(String growthEstimate) {
		this.growthEstimate = growthEstimate;
	}
	public String getPeg() {
		return peg;
	}
	public void setPeg(String peg) {
		this.peg = peg;
	}
	public String getCashPerShare() {
		return cashPerShare;
	}
	public void setCashPerShare(String cashPerShare) {
		this.cashPerShare = cashPerShare;
	}
	public String getOperatingCashFlow() {
		return operatingCashFlow;
	}
	public void setOperatingCashFlow(String operatingCashFlow) {
		this.operatingCashFlow = operatingCashFlow;
	}
	public String getLeveredFreeCashFlow() {
		return leveredFreeCashFlow;
	}
	public void setLeveredFreeCashFlow(String leveredFreeCashFlow) {
		this.leveredFreeCashFlow = leveredFreeCashFlow;
	}
	public String getRevenuePerShare() {
		return revenuePerShare;
	}
	public void setRevenuePerShare(String revenuePerShare) {
		this.revenuePerShare = revenuePerShare;
	}
	public String getQuarterlyRevenueGrowth() {
		return quarterlyRevenueGrowth;
	}
	public void setQuarterlyRevenueGrowth(String quarterlyRevenueGrowth) {
		this.quarterlyRevenueGrowth = quarterlyRevenueGrowth;
	}

}
