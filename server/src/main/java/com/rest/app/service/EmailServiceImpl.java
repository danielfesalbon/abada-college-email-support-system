/**
 * 
 */
package com.rest.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.rest.app.util.EventUtil;
import com.rest.app.util.Hashfile;
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

	@Autowired
	private Environment env;

	private static final String filepath = System.getProperty("user.dir");

	public ResponseEntity<Map<String, Object>> sendEmail(List<String> email, String message, String subject,
			List<Hashfile> files) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			for (Hashfile f : files) {
				File file = new File(filepath + env.getProperty("attachments") + f.getFilename());
				byte[] bytes = decoder.decode(f.getBase64().split("base64,")[1]);
				OutputStream os = new FileOutputStream(file);
				os.write(bytes);
				os.close();
			}
			final String token = request.getHeader("Authorization").substring(7);
			res.put("event", "Send email to " + email.size() + " recipients");
			MimeMessage mimemessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimemessage, true, "UTF-8");
			helper.setText("<html><body>" + message + "</body></html>", true);
			helper.setSubject(subject);
			for (Hashfile f : files) {
				FileSystemResource file = new FileSystemResource(
						new File(filepath + env.getProperty("attachments") + f.getFilename()));
				helper.addAttachment(f.getFilename(), file);
			}
			String[] emails = new String[email.size()];
			for (int i = 0; i < email.size(); i++) {
				emails[i] = email.get(i);
			}
			helper.setTo(emails);
			javaMailSender.send(mimemessage);
			res.put("flag", "success");
			for (Hashfile f : files) {
				File file = new File(filepath + env.getProperty("attachments") + f.getFilename());
				file.delete();
			}
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
