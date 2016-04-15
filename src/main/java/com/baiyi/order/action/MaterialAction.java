package com.baiyi.order.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.baiyi.order.model.Material;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.util.VideoConvertUtil;
import com.baiyi.order.util.WebContext;

@SuppressWarnings("serial")
public class MaterialAction extends CommonsAction {

	// 上传文件并保存素材
	public String save() {
		if (CollectionUtils.isEmpty(upload) || CollectionUtils.isEmpty(nameList)) {
			jsonData.put("result", Feedback.ERROR.toString());
			return SUCCESS;
		}

		for (String name : nameList) {
			if (materialService.exist(null, name)) {
				jsonData.put("result", Feedback.EXIST.toString());
				return SUCCESS;
			}
		}

		// ServletContext servletContext =
		// ServletActionContext.getServletContext();
		String rootPath = WebContext.webRootPath;
		String saveDir = WebContext.UPLOAD;// 存放上传文件目录

		for (int i = 0; i < upload.size(); i++) {
			StringBuffer pathBuffer = new StringBuffer(saveDir + File.separator);
			String fileName = uploadFileName.get(i);// 上传文件名

			// 1.根据素材类型进行相应处理
			MaterialTypeEnum type = ValidateUtil.checkType(fileName);
			boolean needConvert = false;
			switch (type) {
			case IMAGE:
				pathBuffer.append("images");
				type = MaterialTypeEnum.IMAGE;
				break;
			case AUDIO:
				pathBuffer.append("audios");
				break;
			case VIDEO:
				pathBuffer.append("videos");
				needConvert = true;
				break;
			}

			pathBuffer.append(File.separator);
			pathBuffer.append(FormatUtil.dateToString(new Date(), "yyyyMMddHHmmss"));// 日期
			pathBuffer.append(UUID.randomUUID().toString() + "_");// 文件名前缀，防止重名
			pathBuffer.append(fileName);

			String path = pathBuffer.toString();

			// 2-1.保存文件
			File srcFile = upload.get(i);// 上传文件
			File destFile = new File(rootPath + path);// 存放文件
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				jsonData.put("result", Feedback.ERROR.toString());
				e.printStackTrace();
				return SUCCESS;
			}
			// 2-2.视频转换/压缩/截图
			if (needConvert) {
				try {
					VideoConvertUtil.convert(rootPath + path);
				} catch (Exception e) {
					jsonData.put("result", Feedback.ERROR.toString());
					return SUCCESS;
				}
				path = path.substring(0, path.lastIndexOf(".")) + ".jpg";
			}

			// 3.保存数据
			Material material = new Material();
			material.setName(nameList.get(i));
			material.setCreatetime(new Date());

			material.setType(type);

			material.setPath(path.replace("\\", "/"));
			materialService.save(material);
		}
		jsonData.put("result", Feedback.UPLOAD.toString());
		return SUCCESS;
	}

	// 删除素材和文件
	public String delete() {
		if (ValidateUtil.isEmpty(ids)) {
			jsonData.put("result", Feedback.ERROR.toString());
			return SUCCESS;
		}
		if (materialService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());// 关联
			return SUCCESS;
		}
		String rootPath = ServletActionContext.getServletContext().getRealPath("/");
		for (Integer id : ids) {// TODO 由service处理
			Material material = materialService.find(id);
			if (material != null) {
				path = rootPath + material.getPath().replace("/", "\\");
				File delFile = new File(path);
				if (delFile.exists()) {
					delFile.delete();
				}
			}
		}
		materialService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (materialService.exist(id, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return SUCCESS;
		}
		Material material = materialService.find(id);
		material.setName(name);
		materialService.update(material);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		MaterialTypeEnum materialType = FormatUtil.getEnum(MaterialTypeEnum.class, type);

		List<Material> list = materialService.findList(name, materialType, userId, sort, order, pageNo, pageSize);
		int count = materialService.count(name, materialType, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {// TODO 区分批量上传与修改
		boolean isExist = false;
		if (CollectionUtils.isNotEmpty(nameList)) {// 上传时
			for (String name : nameList) {
				isExist = materialService.exist(null, name);
				if (isExist) {
					jsonData.put("name", name);
					break;
				}
			}
		} else if (StringUtils.isNotBlank(name)) {// 修改时
			isExist = materialService.exist(id, name);
			if (isExist) {
				jsonData.put("name", name);
			}
		}
		jsonData.put("exist", isExist);
		return SUCCESS;
	}

	private Integer id;
	private Integer[] ids;
	private String name;
	private String type;
	private String path;

	/* 上传 */
	private List<String> nameList;// 保存文件名:List<String>或者 String[]
	private List<File> upload;// 上传文件
	private List<String> uploadFileName;// 上传文件名
	private List<String> uploadContentType;// 上传文件类型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
}
