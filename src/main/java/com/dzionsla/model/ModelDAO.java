package com.dzionsla.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


@Component("modelDAO")
public class ModelDAO {

	private NamedParameterJdbcTemplate myJdbcTemplate;
	//private JdbcTemplate jdbcTemplate;
	
	public void createTable(String name) {
		
		MapSqlParameterSource myMap = new MapSqlParameterSource();

		String stmn = "create table if not exists " + name + " (" + 
				   "id integer PRIMARY KEY NOT NULL AUTO_INCREMENT , " +
				   "personFullName varchar(60), " + 
				   "projectName varchar(60), " + 
				   "date varchar(60), " + 
				   "task varchar(60), " +
				   "hours double )";
		
		myJdbcTemplate.update(stmn, myMap);
		
//		jdbcTemplate.execute("create table if not exists model1 (" + 
//							   "id integer PRIMARY KEY, " +
//							   "personFullName varchar(60), " + 
//							   "projectName varchar(60), " + 
//							   "date varchar(60), " + 
//							   "task varchar(60), " +
//							   "hours decimal )");
	}
	
	public void deleteTable(String name) {
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("name", name);
		
		myJdbcTemplate.update("drop table " + name, myMap);
	}
	
	public Boolean addFoodGroup(Model fg) {
		Boolean res = false;
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(fg);
		
		int numOfRowsAffected = myJdbcTemplate.update("insert into model (personFullName,projectName,date,task,hours) values (:personFullName,:projectName,:date,:task,:hours)",params);
		
		if (numOfRowsAffected == 1) {
			//System.out.println("One row added!!");
			res = true;
		} else {
			System.out.println("Problem during adding!!");
		}
		
		return res;
	}
	
	public Model getModelById(int id) {
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("id", id);
		
		return myJdbcTemplate.queryForObject("select * from model where id=:id", myMap, new RowMapper<Model>() {			
			public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
				Model fg = new Model();
				fg.setId(rs.getInt("id"));
				fg.setPersonFullName(rs.getString("personFullName"));
				fg.setProjectName(rs.getString("projectName"));
				fg.setDate(rs.getDate("date"));
				fg.setTask(rs.getString("task"));
				fg.setHours(rs.getDouble("hours"));
				
				return fg;
			}
		});
	}
	
	public List<Model> getModelsByPersonName(String personFullName){
		
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("personFullName", personFullName);
		
		return myJdbcTemplate.query("select * from model where personFullName=:personFullName",myMap ,new RowMapper<Model>() {

			public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
				Model fg = new Model();
				fg.setId(rs.getInt("id"));
				fg.setPersonFullName(rs.getString("personFullName"));
				fg.setProjectName(rs.getString("projectName"));
				fg.setDate(rs.getDate("date"));
				fg.setTask(rs.getString("task"));
				fg.setHours(rs.getDouble("hours"));
				
				return fg;
			}
			
		});
	}
	
	public List<Model> getModelsByProjectName(String projectName){
		
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("projectName", projectName);
		
		return myJdbcTemplate.query("select * from model where projectName=:projectName",myMap ,new RowMapper<Model>() {

			public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
				Model fg = new Model();
				fg.setId(rs.getInt("id"));
				fg.setPersonFullName(rs.getString("personFullName"));
				fg.setProjectName(rs.getString("projectName"));
				fg.setDate(rs.getDate("date"));
				fg.setTask(rs.getString("task"));
				fg.setHours(rs.getDouble("hours"));
				
				return fg;
			}
			
		});
	}
	
	public List<Model> getModelsByProjectDate(String from, String to){
		
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("dateFrom", from);
		myMap.addValue("dateTo", to);
		
		String sql= "select * from model where date between 'dateFrom=:dateFrom' and 'dateTo=:dateTo'";
		System.out.println(myMap.getValue("dateFrom"));

		return myJdbcTemplate.query("select * from model where date between ':dateFrom' and ':dateTo'",myMap ,new RowMapper<Model>() {
		//return myJdbcTemplate.query("select * from model where date between '" + from + "' and '" + to + "'",myMap ,new RowMapper<Model>() {
		//return myJdbcTemplate.query("select * from model where date between '2012-02-00' and '2013-02-00'",myMap ,new RowMapper<Model>() {
			public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
				Model fg = new Model();
				fg.setId(rs.getInt("id"));
				fg.setPersonFullName(rs.getString("personFullName"));
				fg.setProjectName(rs.getString("projectName"));
				fg.setDate(rs.getDate("date"));
				fg.setTask(rs.getString("task"));
				fg.setHours(rs.getDouble("hours"));
				
				return fg;
			}
			
		});
	}
	
	@Autowired
	public void setMyJdbcTemplate(DataSource ds) {
		this.myJdbcTemplate = new NamedParameterJdbcTemplate(ds);
		//this.jdbcTemplate = new JdbcTemplate(ds);
	}
}
