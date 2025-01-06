package com.larandvik.webapp.web;

import com.larandvik.webapp.model.ContactType;
import com.larandvik.webapp.model.Resume;
import com.larandvik.webapp.storage.Storage;
import com.larandvik.webapp.util.Config;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Writer writer = response.getWriter();
        writer.write(
                """
                        <html>
                        <head>
                            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                            <link rel="stylesheet" href="css/style.css">
                            <title>Список всех резюме</title>
                        </head>
                        <body>
                        <section>
                        <table border="1" cellpadding="8" cellspacing="0">
                            <tr>
                                <th>Имя</th>
                                <th>Email</th>
                            </tr>
                        """);
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                    "     <td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n" +
                    "     <td>" + resume.getContact(ContactType.MAIL) + "</td>\n" +
                    "</tr>\n");
        }
        writer.write("""
                </table>
                </section>
                </body>
                </html>
                """);
    }
}
