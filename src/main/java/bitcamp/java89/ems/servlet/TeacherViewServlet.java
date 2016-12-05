package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

// 톰캣 서버가 실행할 수 있는 클래스는 반드시 Servlet 규격에 맞추어 제작해야 한다.
// 그러나 Servlet 인터페이스의 메서드가 많아서 구현하기 번거롭다.
// 그래서 AbstractServlet이라는 추상 클래스를 만들어서, 
// 이 클래스를 상속받아 간접적으로 Servlet 인터페이스를 구현하는 방식을 취한다.
// 이 클래스를 상속받게되면 오직 service() 메서드만 만들면 되기 때문에 코드가 편리하다.
@WebServlet("/teacher/view")
public class TeacherViewServlet extends AbstractServlet {
  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getListByUserId(request.getParameter("userId"));
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      for (Teacher teacher : list) {
        out.println("---------------------------");
        out.printf("아이디: %s\n", teacher.getUserId());
        out.printf("암호: (***)\n");
        out.printf("이름: %s\n", teacher.getName());
        out.printf("이메일: %s\n", teacher.getEmail());
        out.printf("전화: %s\n", teacher.getTel());
        out.printf("나이: %d\n", teacher.getAge());
        out.printf("담당과목: %s\n", teacher.getSubject());
        out.printf("경력: %d\n", teacher.getCarrer());
        out.printf("연봉: %d\n", teacher.getSalary());
        out.printf("주소: %s\n", teacher.getAddress());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
