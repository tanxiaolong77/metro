package com.metro.service;

import java.io.File;

import com.metro.request.QuestionUploadRequest;
import com.metro.vo.DataTransObj;


public interface QuestionExcelService {

	public DataTransObj parse(File file,QuestionUploadRequest req) throws Exception;
}
