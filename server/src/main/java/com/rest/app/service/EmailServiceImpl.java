/**
 * 
 */
package com.rest.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.rest.app.util.EventUtil;
import com.rest.app.util.JWTUtil;

/**
 * @author danielf
 *
 */
@Component
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private JWTUtil jwt;

	@Autowired
	private EventUtil event;

	public ResponseEntity<Map<String, Object>> sendEmail(List<String> email, String message, String subject) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			final String token = request.getHeader("Authorization").substring(7);
			res.put("event", "Send email to " + email.size() + " recipients");
			MimeMessage mimemessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimemessage, "UTF-8");
			helper.setText("<html><body>" + message + "</body></html>", true);
			helper.setSubject(subject);
			String[] emails = new String[email.size()];
			for (int i = 0; i < email.size(); i++) {
				emails[i] = email.get(i);
			}
			helper.setTo(emails);
			javaMailSender.send(mimemessage);
			res.put("flag", "success");
			event.LOG_EVENT(jwt.getUsernameFromToken(token), res);

		} catch (Exception e) {
			e.printStackTrace();
			res.put("flag", "failed");
			return ResponseEntity.badRequest().body(res);
			// TODO: handle exception
		}
		return ResponseEntity.ok().body(res);
	}
}
