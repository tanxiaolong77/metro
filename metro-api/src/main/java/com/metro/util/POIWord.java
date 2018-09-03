package com.metro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * POI解析WORD文档
 * 
 * @author 4L
 *
 */
public class POIWord {


	/**
	 * Word2007以上版本后缀名
	 */
	private final static String DOCX = "docx";
	
	private Map<String, String> picMap = new HashMap<String,String>();


	/**
	 * 
	 */
	private OPCPackage oPCPackage;
	private XWPFDocument xwpfDocument;

	public POIWord(File file) {
		String suffixName = file.getPath().split("\\.")[1];
		try {
			if (DOCX.equals(suffixName)) {
				oPCPackage = POIXMLDocument.openPackage(file.getPath());
				xwpfDocument = new XWPFDocument(oPCPackage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveImage(String absolutePath,Map<String, String> map){
		List<XWPFPictureData> pictures = xwpfDocument.getAllPackagePictures();
		System.out.println(pictures.size());
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
            map.put(id, saveFile.getName());
		}
	}
	
	public Map<String,String> getPicMap(){
		return picMap;
	}

	/**
	 * 取出word文档表格第cellRowIdx行，第cellColIdx列的值（DOCX）
	 * 
	 * @param file
	 *            解析文件
	 * @param cellRowIdx
	 *            行
	 * @param cellColIdx
	 *            列
	 */
	@SuppressWarnings("unused")
	public String getSpecifyDataForDocx(String absolutePath) {
//		try {
			
			// 保存word中的图片
			
			saveImage(absolutePath, picMap);
			
//			// 获取页面中的表格
//			Iterator<XWPFTable> it = xwpfDocument.getTablesIterator();
//			while (it.hasNext()) {
//				// 循环页面中的表格
//				XWPFTable table = (XWPFTable) it.next();
//				for(int i = 2;i < table.getRows().size();i++){
//					StringBuffer str = new StringBuffer();
//					// 获取表格中的行
//					XWPFTableRow row = table.getRow(i);
//					// 获取行中共有多少列
//					List<XWPFTableCell> cells = row.getTableCells();
//					
//					for (XWPFTableCell xwpfTableCell : cells) {
//						// 获取列
//						XWPFTableCell cell = xwpfTableCell;
//						System.out.println(cell.getText());
//						String xml = "";
//						if(cell.getCTTc() != null){
//							xml = cell.getCTTc().toString();
//						}
//						if(xml.indexOf("<a:blip ") != -1){
//					        xml = xml.substring(xml.indexOf("<a:blip "), xml.length());
//					        xml = xml.substring(0,xml.indexOf(">")+1);
//					        String id = xml.substring(xml.lastIndexOf("=")+2,xml.lastIndexOf("\""));
//					        System.out.println(picMap.get(id));
//						}
						
//						// 获取列中的段落
//						StringBuffer allFilePath = new StringBuffer();
//						for (int j = 0; j < cell.getParagraphs().size(); j++) {
//							List<XWPFRun> runs = cell.getParagraphs().get(i).getRuns();
//							for (XWPFRun r : runs) {
//								// 获取单个对象
//								r.getCTR().xmlText();
//								String text = r.getText(r.getTextPosition());
//								// 如果字符为空，可能是附件一类的文件，比如图片之类的，需要另外解析,此处处理为图片
//
//								allFilePath.append(text);
//								}
//						}
//					}
//
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	/**
	 * 关闭
	 */
	public void closeForDocx() {
		try {
			oPCPackage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public static void main(String[] args) {
		String importPath = "C:\\Users\\Administrator\\Desktop\\123.docx";
		String absolutePath = "D:\\metro-upload\\";
		File file = new File(importPath);
		POIWord poiParseWord = new POIWord(file);
//		poiParseWord.getSpecifyDataForDoc(2, 5);

		System.out.println(poiParseWord.getSpecifyDataForDocx(absolutePath));
	}


	public void setPicMap(Map<String, String> picMap) {
		this.picMap = picMap;
	}
}