package com.eduo.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eduo.student.model.entity.Student;
import com.eduo.student.model.service.StudentService;

public class StudentController {
//	ResponseEntity<?>
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/eduo/student")
	public ResponseEntity<?> selectStudent(@RequestBody Student student) {
		Student result = studentService.selectStudent(student);
		return new ResponseEntity<?>(result, HttpStatus.OK);
	}
}

// 데이터 전처리 -> service
// service -> interface로 만듬
// ServiceImpl -> service 구현
// DAO -> Mapper(Interface)
//		-> Mapper가 DAO 역할
// Interface에 Mapper라고 걸어주면 SQL문 찾아서 돌려서 데이터 반환

// JOIN된 정보들 -> DTO 패키지
// entity -> DB랑 똑같이 만든 것, DTO -> 처리 시에 필요한 것들