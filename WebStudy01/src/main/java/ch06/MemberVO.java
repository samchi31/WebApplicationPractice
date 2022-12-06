package ch06;

public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private String postNum;
	private String phone1;
	private String phone2;
	private String phone3;
	private String gender;
	private String hobby1;
	private String hobby2;
	private String hobby3;
	private String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public MemberVO() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby1() {
		return hobby1;
	}
	public void setHobby1(String hobby1) {
		this.hobby1 = hobby1;
	}
	public String getHobby2() {
		return hobby2;
	}
	public void setHobby2(String hobby2) {
		this.hobby2 = hobby2;
	}
	public String getHobby3() {
		return hobby3;
	}
	public void setHobby3(String hobby3) {
		this.hobby3 = hobby3;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberVO [id=");
		builder.append(id);
		builder.append(", passwd=");
		builder.append(passwd);
		builder.append(", name=");
		builder.append(name);
		builder.append(", postNum=");
		builder.append(postNum);
		builder.append(", phone1=");
		builder.append(phone1);
		builder.append(", phone2=");
		builder.append(phone2);
		builder.append(", phone3=");
		builder.append(phone3);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", hobby1=");
		builder.append(hobby1);
		builder.append(", hobby2=");
		builder.append(hobby2);
		builder.append(", hobby3=");
		builder.append(hobby3);
		builder.append(", comment=");
		builder.append(comment);
		builder.append("]");
		return builder.toString();
	}
	
}
