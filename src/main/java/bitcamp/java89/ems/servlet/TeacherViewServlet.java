package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

@WebServlet("/teacher/view")
public class TeacherViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String userId = request.getParameter("userId");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 정보</h1>");
    out.println("<form action='update' method='POST'>");
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      Teacher teacher = teacherDao.getDetail(userId);
      
      if (teacher == null) {
        throw new Exception("해당 아이디의 연락처가 없습니다."); 
      }
      
      out.println("<table border='1'>");

      out.printf("<tr><th>아이디</th><td>"
          + "<input name='userId' type='text' value='%s' readonly></td></tr>\n", teacher.getUserId());
      
      out.printf("<tr><th>암호</th><td>"
          + "<input name='password' type='text' value='%s'></td></tr>\n", teacher.getPassword());
      
      out.printf("<tr><th>이름</th><td>"
          + "<input name='name' type='text' value='%s'></td></tr>\n", teacher.getName());
      
      out.printf("<tr><th>이메일</th><td>"
          + "<input name='email' type='text' value='%s'></td></tr>\n", teacher.getEmail());
      
      out.printf("<tr><th>전화</th><td>"
          + "<input name='tel' type='text' value='%s'></td></tr>\n", teacher.getTel());
      
      out.printf("<tr><th>나이</th><td>"
          + "<input name='age' type='number' value='%s'></td></tr>\n", teacher.getAge());
      
      out.printf("<tr><th>담당과목</th><td>"
          + "<input name='subject' type='text' value='%s'></td></tr>\n", teacher.getSubject());
      
      out.printf("<tr><th>경력</th><td>"
          + "<input name='carrer' type='number' value='%s'></td></tr>\n", teacher.getCarrer());
      
      out.printf("<tr><th>연봉</th><td>"
          + "<input name='salary' type='number' value='%s'></td></tr>\n", teacher.getSalary());
      
      out.printf("<tr><th>주소</th><td>"
          + "<input name='address' type='text' value='%s'></td></tr>\n", teacher.getAddress());
      
      
      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?userId=%s'>삭제</a>\n", teacher.getUserId());
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println(" <a href='list'>목록</a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
