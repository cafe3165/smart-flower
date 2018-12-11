package base.pagemodel;


//AlisaYe 2017/07/20
//最后返回的是这个MSG，就是页面执行的效果；有一个成员属性，叫做state
public class MSG {
String state;

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public MSG(String state) {
	this.state = state;
}

}
