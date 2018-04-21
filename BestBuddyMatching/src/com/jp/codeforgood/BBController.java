package com.jp.codeforgood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BBController {
	@GetMapping("/")
	public String getPage() {
		return "index";
	}
}
