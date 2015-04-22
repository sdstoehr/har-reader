package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarTiming {

	private Integer blocked;
	private Integer dns;
	private Integer connect;
	private Integer send;
	private Integer wait;
	private Integer receive;
	private Integer ssl;
	private String comment;

	public Integer getBlocked() {
		return blocked;
	}

	public void setBlocked(Integer blocked) {
		this.blocked = blocked;
	}

	public Integer getDns() {
		return dns;
	}

	public void setDns(Integer dns) {
		this.dns = dns;
	}

	public Integer getConnect() {
		return connect;
	}

	public void setConnect(Integer connect) {
		this.connect = connect;
	}

	public Integer getSend() {
		return send;
	}

	public void setSend(Integer send) {
		this.send = send;
	}

	public Integer getWait() {
		return wait;
	}

	public void setWait(Integer wait) {
		this.wait = wait;
	}

	public Integer getReceive() {
		return receive;
	}

	public void setReceive(Integer receive) {
		this.receive = receive;
	}

	public Integer getSsl() {
		return ssl;
	}

	public void setSsl(Integer ssl) {
		this.ssl = ssl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
