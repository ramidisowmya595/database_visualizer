package com.example.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
@Autowired
UserRepository userRepository;
@GetMapping
public String check() throws SQLException
{
return "hello";
}
@GetMapping(path = "/gettickers")
public List<String> getAlltickers() throws SQLException
{
return userRepository.getAlltickers();
}
@GetMapping(path = "/type")
public String metaData() throws SQLException
{
return userRepository.metaData();
}
}