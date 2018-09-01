package com.metro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metro.model.Answer;
import com.metro.request.QuestionUploadRequest;
import com.metro.service.QuestionService;

@Component
public class QuestionExcelParser {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static Logger logger = LoggerFactory.getLogger(QuestionExcelParser.class);
	
	@Autowired
	private QuestionService questionService;
	

	public static void parse(File file,QuestionUploadRequest req) throws Exception {

		InputStream inp = new FileInputStream(file);
		HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(inp);
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		Map<String, HSSFPictureData> map = new HashMap<String, HSSFPictureData>();

		// 读取图片
		for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
			try {

				HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				if (shape instanceof HSSFPicture) {
					HSSFPicture pic = (HSSFPicture) shape;
					int row = anchor.getRow2();
					int col = anchor.getCol2();
					int pictureIndex = pic.getPictureIndex() - 1;
					HSSFPictureData picData = pictures.get(pictureIndex);
					map.put(row + ":" + col, picData);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

		// 循环行Row
		List<QuestionUploadRequest> requests = new ArrayList<>();
		
		for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
			
			QuestionUploadRequest request = BeanUtils.transferB(req, QuestionUploadRequest.class);
			
			HSSFRow hssfRow = sheet.getRow(rowNum);
			int index = 0;
			try {
				List<Answer> answers = new ArrayList<>();
				String questionId = BaseUtil.getUUID();
				String totalSerialNo = getCellValue(hssfRow.getCell(index++), workbook);//总序号
				String knowledgePoint = getCellValue(hssfRow.getCell(index++), workbook);//知识点
				String qestionType = getCellValue(hssfRow.getCell(index++), workbook);//单选&多选&判断
				String serialNo = getCellValue(hssfRow.getCell(index++), workbook);//序号
				
				Map<String,String> qestion = getPicAndTextCellValue(questionId, 
						map.get(rowNum+":"+index), getCellValue(hssfRow.getCell(index++), workbook));//题目中含有图片	
				Map<String,String> A = getPicAndTextCellValue(questionId+"-A", 
						map.get(rowNum+":"+index), getCellValue(hssfRow.getCell(index++), workbook));//选项A
				Answer anwer = new Answer();
				anwer.setId(BaseUtil.getUUID());
				anwer.setAnswerDesc(A.get("desc"));
				anwer.setAnswerDesc(A.get("img"));
				
				anwer.setTmp("A");
				anwer.setCreateTime(new Date());
				anwer.setQuestionId(questionId);
				answers.add(anwer);
				
				Map<String,String> B = getPicAndTextCellValue(questionId+"-B", 
						map.get(rowNum+":"+index), getCellValue(hssfRow.getCell(index++), workbook));//选项B
				anwer = new Answer();
				anwer.setId(BaseUtil.getUUID());
				anwer.setAnswerDesc(B.get("desc"));
				anwer.setAnswerImage(B.get("img"));
				anwer.setTmp("B");
				anwer.setCreateTime(new Date());
				anwer.setQuestionId(questionId);
				answers.add(anwer);
				
				Map<String,String> C = getPicAndTextCellValue(questionId+"-C", 
						map.get(rowNum+":"+index), getCellValue(hssfRow.getCell(index++), workbook));//选项C
				anwer = new Answer();
				anwer.setId(BaseUtil.getUUID());
				anwer.setAnswerDesc(C.get("desc"));
				anwer.setAnswerDesc(C.get("img"));
				anwer.setTmp("C");
				anwer.setCreateTime(new Date());
				anwer.setQuestionId(questionId);
				answers.add(anwer);
				
				Map<String,String> D = getPicAndTextCellValue(questionId+"-D", 
						map.get(rowNum+":"+index), getCellValue(hssfRow.getCell(index++), workbook));//选项D
				anwer = new Answer();
				anwer.setId(BaseUtil.getUUID());
				anwer.setAnswerDesc(D.get("desc"));
				anwer.setAnswerDesc(D.get("img"));
				anwer.setTmp("D");
				anwer.setCreateTime(new Date());
				anwer.setQuestionId(questionId);
				answers.add(anwer);
				
				String answer = getCellValue(hssfRow.getCell(index++), workbook).trim();//正确答案
				String rightAnswerIds = "";
				
				if("判断题".equals(qestionType.trim())){
					//判断题则不保存选项，答案正确为0;错误为1
					rightAnswerIds = "正确".equals(getCellValue(hssfRow.getCell(index++), workbook).trim()) ? "0" : "1";
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
				request.setId(questionId);
				request.setQuestionDesc(qestion.get("desc"));
				request.setQuestionImage(qestion.get("img"));
				request.setScore("1");//###当前版本写死1分，因为excel中没有分数记录
				request.setAnswerId(rightAnswerIds);//正确答案
				request.setContentType(knowledgePoint);//知识点
				if(qestionType.contains("单选")){
					request.setQuestionType("1");
				}else if(qestionType.contains("多选")){
					request.setQuestionType("2");
				}else if(qestionType.contains("判断")){
					request.setQuestionType("3");
				}
				request.setCreateTime(new Date());//创建时间
				request.setOperater(SessionUtils.getLoginManager().getId());//创建人
				request.setAnswers(answers);//选项
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

		}
	}
	
	
	public static Map<String,String> getPicAndTextCellValue(String picName,HSSFPictureData data,String cellValue){
		String img = "";
		if(data != null){
			//图片，生成名称并保存
			img = savePic(picName, data);
			if(StringUtils.isBlank(img)){
				img = "";
			}
			
			if(StringUtils.isBlank(cellValue)){
				cellValue = "";
			}
		}
		Map result = new HashMap();
		result.put("desc", cellValue);
		result.put("img", img);
		return result;
	}

	public static String savePic(String picName, PictureData pic) {
		FileOutputStream out = null;
		try {
			String ext = pic.suggestFileExtension();
			String picAllName = picName + ext;
			byte[] data = pic.getData();
			out = new FileOutputStream(
					ResourceUtil.getPropertyValue("picturePath") + picAllName);
			out.write(data);
			return picAllName;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public static String getCellValue(HSSFCell brandIdHSSFCell,
			HSSFWorkbook hssfWorkbook) throws IOException, ParseException {
		String cellStr = null;
		FormulaEvaluator evaluator = hssfWorkbook.getCreationHelper()
				.createFormulaEvaluator();
		if (brandIdHSSFCell == null) {
			cellStr = "";
		} else if (brandIdHSSFCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
			cellStr = String.valueOf(brandIdHSSFCell.getBooleanCellValue());
		} else if (brandIdHSSFCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
			// 其中包含时间处理
			if (HSSFDateUtil.isCellDateFormatted(brandIdHSSFCell)) {
				Date theDate = brandIdHSSFCell.getDateCellValue();
				cellStr = sdf.format(theDate);
			} else {
				cellStr = brandIdHSSFCell.getNumericCellValue() + "";
			}
		} else if (brandIdHSSFCell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) { // 函数
			CellValue cellValue = evaluator.evaluate(brandIdHSSFCell);
			cellStr = cellValue.formatAsString();
		} else { // 其余按照字符串处理
			cellStr = brandIdHSSFCell.getStringCellValue();
		}

		return cellStr;

	}
}
