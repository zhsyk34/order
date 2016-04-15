package com.baiyi.order.util;

public enum Feedback {

	/* CRUD... */
	CREATE(1, "增加"), RETRIEVE(2, "查询"), UPDATE(3, "修改"), DELETE(4, "删除"), UPLOAD(5, "上传"),
	/* 状态判断 */
	EXIST(11, "数据已存在"), NOTEXIST(12, "数据不存在"), RELATE(13, "数据正被使用中"),
	/* 操作结果 */
	SUCCESS(21, "成功"), FAIL(22, "失败"), ERROR(23, "出错"), ENABLE(24, "启用"), DISABLE(25, "禁用"), DEAL(26, "处理"), REVOKE(27, "撤销"), OFFLINE(28, "未登录");
	private Feedback(Integer index, String name) {
		this.index = index;
		this.name = name;
	}

	private Integer index;

	private String name;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}
