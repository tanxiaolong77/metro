package com.metro.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.metro.model.Answer;
import com.metro.model.Question;

/***
 * 题库上传
 * @author dell
 *
 */
public class QuestionUploadRequest extends Question implements Request  {
	
	private List<Answer> answers = new ArrayList<>();

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
