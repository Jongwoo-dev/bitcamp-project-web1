package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends AbstractServlet {
  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!teacherDao.existUserId(request.getParameter("userId"))) {
        out.println("유저아이디를 찾지 못했습니다.");
        return;
      }

      // teacher/update?userId=hong2&password=4444&name=신세계&email=newworld@test.com&tel=111-1111&age=39&subject=C&carrer=5&salary=5500&address=인천
      Teacher teacher = new Teacher();
      teacher.setUserId(request.getParameter("userId"));
      teacher.setPassword(request.getParameter("password"));
      teacher.setName(request.getParameter("name"));
      teacher.setEmail(request.getParameter("email"));
      teacher.setTel(request.getParameter("tel"));
      teacher.setAge(Integer.parseInt(request.getParameter("age")));
      teacher.setSubject(request.getParameter("subject"));
      teacher.setCarrer(Integer.parseInt(request.getParameter("carrer")));
      teacher.setSalary(Integer.parseInt(request.getParameter("salary")));
      teacher.setAddress(request.getParameter("address"));

      teacherDao.update(teacher);
      out.println("변경하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
