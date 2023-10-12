package kr.or.ozz.dto;

import lombok.Data;

public class UserDTO {

		    private String userid; // 회원 아이디
		    private String pwd; // 회원 비밀번호
		    private String username; // 회원 이름
		    private String email; // 회원 이메일
		    private Double years; // 회원 경력
		    private String work; // 회원 관련직무
		    private String concern; // 회원 관심사
		    private String hashtag; // 회원 해시태그
		    private String created_at; // 회원 가입일자
		    private String usertype; // 회원 유형
		    private String intro; // 회원 자기소개
		    
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

