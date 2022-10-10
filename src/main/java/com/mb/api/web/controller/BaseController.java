package com.mb.api.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mb.api.constants.GenericConstanst.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
@CrossOrigin(origins = "*" ,allowedHeaders = "*")
public class BaseController
{

}
