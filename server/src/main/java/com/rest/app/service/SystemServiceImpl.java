/**
 * 
 */
package com.rest.app.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rest.app.repo.AudittrailRepository;
import com.rest.app.repo.UseraccountRepository;
import com.rest.app.table.Audittrail;
import com.rest.app.table.Useraccount;
import com.rest.app.util.EventUtil;
import com.rest.app.util.PaginateValues;
import com.rest.app.util.SystemProperties;

/**
 * @author danielf
 *
 */
@Component
public class SystemServiceImpl implements SystemService {
	@Autowired
	private Environment env;
	@Autowired
	private AudittrailRepository auditRepository;
	@Autowired
	private UseraccountRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EventUtil event;

	public ResponseEntity<SystemProperties> getProperties() {
		// TODO Auto-generated method stub
		SystemProperties p = new SystemProperties();
		p.setDburl(env.getProperty("spring.datasource.url"));
		p.setDbuser(env.getProperty("spring.datasource.username"));
		p.setEmail(env.getProperty("spring.mail.username"));
		p.setMailhost(env.getProperty("spring.mail.host"));
		p.setMailport(env.getProperty("spring.mail.port"));
		return ResponseEntity.ok(p);
	}

	@Override
	public List<Audittrail> getAudittrail(Integer row, Integer page) {
		// TODO Auto-generated method stub
		try {
			Pageable pagination = PageRequest.of(page, row, Sort.by("id").descending());
			return auditRepository.findAll(pagination).getContent();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return new ArrayList<Audittrail>();
	}

	public ResponseEntity<PaginateValues> getPageValues(int row) {
		try {
			List<Integer> options = new ArrayList<Integer>();
			PaginateValues values = new PaginateValues();
			values.setCount(auditRepository.count());
			values.setRow(row);
			if (values.getCount() >= 20) {
				options = new ArrayList<Integer>();
				options.add(10);
				options.add(15);
				options.add(20);
			} else if (values.getCount() >= 15 && values.getCount() < 20) {
				options = new ArrayList<Integer>();
				options.add(10);
				options.add(15);
			} else if (values.getCount() >= 10 && values.getCount() < 15) {
				options = new ArrayList<Integer>();
				options.add(10);
			} else {
				options = new ArrayList<Integer>();
				options.add((int) values.getCount());
			}
			values.setRowoptions(options);
			return ResponseEntity.ok(values);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ResponseEntity.ok(new PaginateValues());
	}

	@Override
	public ResponseEntity<Map<String, Object>> validateReset(Useraccount user) {
		// TODO Auto-generated method stub
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
			response.put("flag", "failed");
			response.put("event", "User Account Recovery validation failed");
			if (user.getUsername() != null && user.getBday() != null) {
				Useraccount u = userRepository.findByUsername(user.getUsername());
				if (dateformatter.format(u.getBday()).equals(dateformatter.format(user.getBday()))) {
					response.put("flag", "success");
					response.put("event", "User Account Recovery validated");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("flag", "failed");
			return ResponseEntity.badRequest().body(response);
			// TODO: handle exception
		}
		return ResponseEntity.ok().body(response);
	}

	@Override
	public ResponseEntity<Map<String, Object>> resetPassword(Useraccount user) {
		// TODO Auto-generated method stub
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response.put("flag", "failed");
			response.put("event", "User Account Reset");
			Useraccount u = userRepository.findByUsername(user.getUsername());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(u);
			response.put("flag", "success");
			event.LOG_EVENT(u.getUsername(), response);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("flag", "failed");
			return ResponseEntity.badRequest().body(response);
			// TODO: handle exception
		}
		return ResponseEntity.ok().body(response);
	}
}
