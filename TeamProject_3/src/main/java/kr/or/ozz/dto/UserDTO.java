package kr.or.ozz.dto;

import lombok.Data;

public class UserDTO {

		    private String userid; 
		    private String pwd; 
		    private String username; 
		    private String email; 
		    private Double years; 
		    private String work;
		    private String concern; 
		    private String hashtag; 
		    private String created_at; 
		    private String usertype; 
		    private String intro;
		    
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
			public String getIntro() {
				return intro;
			}
			public void setIntro(String intro) {
				this.intro = intro;
			}




	}

