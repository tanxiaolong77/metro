package com.metro.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.model.Answer;
import com.metro.model.AnswerExample;
import com.metro.model.Question;
import com.metro.request.QuestionUploadRequest;
import com.metro.service.AnswerService;
import com.metro.service.JobsService;
import com.metro.service.QuestionExcelService;
import com.metro.service.QuestionService;
import com.metro.util.BaseUtil;
import com.metro.vo.DataTransObj;

@Service("questionExcelService")
public class QuestionExcelServiceImpl  implements QuestionExcelService{

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private  Logger logger = LoggerFactory.getLogger(QuestionExcelServiceImpl.class);
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private JobsService jobService;
	
	private int sortIndex = 0;
	
	private Executor pool = Executors.newFixedThreadPool(5);
	

	public  DataTransObj parse(final File file,final QuestionUploadRequest request) throws Exception {
		//上传使用异步执行
		pool.execute(new Runnable() {
			public void run() {
				try {
					//用于答案排序
					setSortIndex(answerService.countByExample(new AnswerExample())); 
					// 循环行Row
					List<String> contextTypes = new ArrayList<>();//解决合并单元格问题
					
					OPCPackage oPCPackage = POIXMLDocument.openPackage(file.getPath());
					XWPFDocument xwpfDocument = new XWPFDocument(oPCPackage);

					// 保存word中的图片
					Map<String,String> picMap = new HashMap<String,String>();
					saveImage(request.getPicturePath(), picMap,xwpfDocument);
					
					// 获取页面中的表格
					Iterator<XWPFTable> it = xwpfDocument.getTablesIterator();
					while (it.hasNext()) {
						// 循环页面中的表格
						XWPFTable table = (XWPFTable) it.next();
						for(int i = 2;i < table.getRows().size();i++){
							// 获取表格中的行
							XWPFTableRow row = table.getRow(i);
							// 获取行中共有多少列
							List<XWPFTableCell> cells = row.getTableCells();
							//将cell插入数据库
							parseAdd(request, cells, contextTypes,picMap,sortIndex);

						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
		return DataTransObj.onSuccess(null,"上传成功");
	}
	
	public void saveImage(String absolutePath,Map<String, String> map,XWPFDocument xwpfDocument){
		List<XWPFPictureData> pictures = xwpfDocument.getAllPackagePictures();
		System.out.println("当次上传的图片数量为："+pictures.size());
		for(XWPFPictureData picture : pictures){
						
			String id = picture.getParent().getRelationId(picture);
			File folder = new File(absolutePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String rawName = picture.getFileName();
			String fileExt = rawName.substring(rawName.lastIndexOf("."));
			String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;
			File saveFile = new File(absolutePath + File.separator + newName);
			@SuppressWarnings("resource")
			FileOutputStream fos = null;
			try{
			fos = new FileOutputStream(saveFile); 
			fos.write(picture.getData());
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					fos = null;
				}
				
			}
            map.put(id,newName);
		}
	}
	
	
	public void parseAdd(
			QuestionUploadRequest request,
			List<XWPFTableCell> cells,
			List<String> contextTypes,
			Map<String,String> picMap,
			int sortIndex){
		try{
			List<Answer> answers = new ArrayList<>();
			String totalSerialNo = cells.get(0).getText();//总序号
			String questionId = BaseUtil.getUUID();
			String knowledgePoint = cells.get(1).getText();//知识点
			String qestionType = cells.get(2).getText();//单选&多选&判断
			String serialNo = cells.get(3).getText();//序号
			
			if(StringUtils.isNotBlank(knowledgePoint)){
				contextTypes.add(knowledgePoint);
			}else{
				knowledgePoint = contextTypes.get(contextTypes.size()-1);
			}
			Map<String,String> qestion = getPicAndTextCellValue(cells.get(4),picMap);//题目中含有图片	
			Map<String,String> A = getPicAndTextCellValue(cells.get(5),picMap);//选项A
			Answer anwer = new Answer();
			anwer.setId(BaseUtil.getUUID());
			anwer.setAnswerDesc(A.get("desc"));
			anwer.setAnswerImage(A.get("img"));
			anwer.setTmp("A");
			anwer.setCreateTime(new Date());
			anwer.setQuestionId(questionId);
			answers.add(anwer);
			
			Map<String,String> B = getPicAndTextCellValue(cells.get(6),picMap);//选项B
			anwer = new Answer();
			anwer.setId(BaseUtil.getUUID());
			anwer.setAnswerDesc(B.get("desc"));
			anwer.setAnswerImage(B.get("img"));
			anwer.setTmp("B");
			anwer.setCreateTime(new Date());
			anwer.setQuestionId(questionId);
			answers.add(anwer);
			
			Map<String,String> C = getPicAndTextCellValue(cells.get(7),picMap);//选项C
			anwer = new Answer();
			anwer.setId(BaseUtil.getUUID());
			anwer.setAnswerDesc(C.get("desc"));
			anwer.setAnswerImage(C.get("img"));
			anwer.setTmp("C");
			anwer.setCreateTime(new Date());
			anwer.setQuestionId(questionId);
			answers.add(anwer);
			
			Map<String,String> D =getPicAndTextCellValue(cells.get(8),picMap);//选项D
			anwer = new Answer();
			anwer.setId(BaseUtil.getUUID());
			anwer.setAnswerDesc(D.get("desc"));
			anwer.setAnswerImage(D.get("img"));
			anwer.setTmp("D");
			anwer.setCreateTime(new Date());
			anwer.setQuestionId(questionId);
			answers.add(anwer);

			String answer = cells.get(9).getText().trim();//正确答案
			String rightAnswerIds = "";
			
			if("判断题".equals(qestionType.trim())){
				//判断题则不保存选项，答案正确为0;错误为1
				rightAnswerIds = "正确".equals(answer.trim()) ? "0" : "1";
			}else if("单选题".equals(qestionType.trim()) || "多选题".equals(qestionType.trim())){
				
				char[] answerChars = answer.toCharArray();
				for (int i = 0; i < answerChars.length; i++) {
					for (int j = 0; j < answers.size(); j++) {
						if(answers.get(j).getTmp().equals(answerChars[i]+"")){
							rightAnswerIds = StringUtils.isNotBlank(rightAnswerIds) ? 
									rightAnswerIds + "," + answers.get(j).getId()
									: answers.get(j).getId();
						}
					}
				}
			}
			
			
			
			//封装题目实体
			Question question = new Question();
			question.setId(questionId);
			question.setQuestionDesc(qestion.get("desc"));
			question.setQuestionImage(qestion.get("img"));
			question.setScore("1");//###当前版本写死1分，因为excel中没有分数记录
			question.setAnswerId(rightAnswerIds);//正确答案
			question.setContentType(knowledgePoint);//知识点
			if(qestionType.contains("单选")){
				question.setQuestionType("1");
			}else if(qestionType.contains("多选")){
				question.setQuestionType("2");
			}else if(qestionType.contains("判断")){
				question.setQuestionType("3");
			}
			question.setCreateTime(new Date());//创建时间
			question.setOperater(request.getUserId());//创建人
			question.setJobsId(request.getJobsId());//岗位
			question.setJobsName(request.getJobsName());//岗位名称
			
			//doc会存在读取不到图片的情况，如果读取不到图片则不保存
			boolean isSave = true;
			if(!"3".equals(question.getQuestionType())){
				for (Answer answerEntity : answers) {
					if(StringUtils.isBlank(answerEntity.getAnswerDesc())
							&& StringUtils.isBlank(answerEntity.getAnswerImage())){
						isSave = false;
					}
				}
			}
			
			if(isSave){
				//题目保存
				questionService.insert(question);
				question.setAnswers(answers);
				if(!"3".equals(question.getQuestionType())){
					//选项保存
					for (Answer answerEntity : answers) {
						answerEntity.setSortId(increment());//用于答案排序
						answerService.insert(answerEntity);//选项
					}
				}
			
			}
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
	public Map<String,String> getPicAndTextCellValue(XWPFTableCell cell,Map<String,String> picMap){
		Map<String,String> result = new HashMap<>();
		String xml = "";
		if(cell.getCTTc() != null){
			xml = cell.getCTTc().toString();
		}
		if(xml.indexOf("<a:blip ") != -1){
	        xml = xml.substring(xml.indexOf("<a:blip "), xml.length());
	        xml = xml.substring(0,xml.indexOf(">")+1);
	        String id = xml.substring(xml.lastIndexOf("=")+2,xml.lastIndexOf("\""));
	        result.put("img",picMap.get(id));
		}
		 result.put("desc",cell.getText());
		 return result;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}
	
	public int increment() {
		return ++sortIndex;
	}
}
