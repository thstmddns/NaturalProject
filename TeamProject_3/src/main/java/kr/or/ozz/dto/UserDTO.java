package kr.or.ozz.dto;

import lombok.Data;

public class UserDTO {

		    private String userid; // ȸ�� ���̵�
		    private String pwd; // ȸ�� ��й�ȣ
		    private String username; // ȸ�� �̸�
		    private String email; // ȸ�� �̸���
		    private Double years; // ȸ�� ���
		    private String work; // ȸ�� ��������
		    private String concern; // ȸ�� ���ɻ�
		    private String hashtag; // ȸ�� �ؽ��±�
		    private String created_at; // ȸ�� ��������
		    private String usertype; // ȸ�� ����
		    
			public String getUserid() {
				return userid;
			}
			public void setUserid(String userid) {
				this.userid = userid;
			}
			public String getPwd() {
				return pwd;
			}
			public void setPwd(String pwd) {
				this.pwd = pwd;
			}
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public Double getYears() {
				return years;
			}
			public void setYears(Double years) {
				this.years = years;
			}
			public String getWork() {
				return work;
			}
			public void setWork(String work) {
				this.work = work;
			}
			public String getConcern() {
				return concern;
			}
			public void setConcern(String concern) {
				this.concern = concern;
			}
			public String getHashtag() {
				return hashtag;
			}
			public void setHashtag(String hashtag) {
				this.hashtag = hashtag;
			}
			public String getCreated_at() {
				return created_at;
			}
			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}
			public String getUsertype() {
				return usertype;
			}
			public void setUsertype(String usertype) {
				this.usertype = usertype;
			}
			
			@Override
			public String toString() {
				return "UserDTO [userid=" + userid + ", pwd=" + pwd + ", username=" + username + ", email=" + email
						+ ", years=" + years + ", work=" + work + ", concern=" + concern + ", hashtag=" + hashtag
						+ ", created_at=" + created_at + ", usertype=" + usertype + "]";
			}




	}
