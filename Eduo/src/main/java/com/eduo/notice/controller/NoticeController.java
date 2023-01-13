package com.eduo.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class NoticeController {
	private final NoticeService noticeService;
	
	@GetMapping("/notice")
	public ResponseEntity<?> getAllNotice() {
		try {
			List<Notice> noticeList = noticeService.selectAll();
			if(noticeList == null || noticeList.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Notice>>(noticeList, HttpStatus.OK);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/notice/{nid}")
	public ResponseEntity<?> getOneNotice(@PathVariable int nid) {
		try {
			Notice resultNotice = noticeService.selectOne(nid);
			if(resultNotice == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Notice>(resultNotice, HttpStatus.OK);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/notice")
	public ResponseEntity<?> insert(@RequestBody Notice notice) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			noticeService.insert(notice);
			resultMap.put("message", "공지사항 추가 완료.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
		} catch(Exception e) {
			resultMap.put("message", "공지사항 추가 실패!");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/notice/{nid}")
	public ResponseEntity<?> delete(@PathVariable int nid) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			noticeService.delete(nid);
			resultMap.put("message", "공지사항 삭제 완료.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
		} catch(Exception e) {
			resultMap.put("message", "공지사항 삭제 실패!");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/notice")
	public ResponseEntity<?> update(@RequestBody Notice notice) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			noticeService.update(notice);
			resultMap.put("message", "공지사항 삭제 완료.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
		} catch(Exception e) {
			resultMap.put("message", "공지사항 삭제 실패!");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
