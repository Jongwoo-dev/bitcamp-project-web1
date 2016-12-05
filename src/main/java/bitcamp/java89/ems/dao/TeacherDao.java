package bitcamp.java89.ems.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.vo.Teacher;

// ContactController에서 호출할 메서드 규칙
public interface TeacherDao {
  ArrayList<Teacher> getList() throws Exception;
  ArrayList<Teacher> getListByUserId(String userId) throws Exception;
  void insert(Teacher contact) throws Exception;
  void update(Teacher contact) throws Exception;
  void delete(String userId) throws Exception;
  boolean existUserId(String userId) throws Exception;
}
