package online.checkbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import online.checkbook.service.OnlineCheckbookService;

@RestController
@RequestMapping("/online_checkbook/")
@Slf4j
public class OnlineCheckbookController {

	@Autowired
	private OnlineCheckbookService onlineCheckbookService;
}
