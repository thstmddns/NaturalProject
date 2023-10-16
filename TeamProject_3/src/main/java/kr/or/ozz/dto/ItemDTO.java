package kr.or.ozz.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
	private List<String> title;
    private List<String> tags;
    private List<String> authors;
	public List<String> getTitle() {
		return title;
	}
	public void setTitle(List<String> title) {
		this.title = title;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
}
