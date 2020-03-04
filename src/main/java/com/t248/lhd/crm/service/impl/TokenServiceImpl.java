package com.t248.lhd.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.entity.UserToken;
import com.t248.lhd.crm.service.TokenService;
import com.t248.lhd.crm.tools.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public String generateToken(String userAgentStr, String username) {
		StringBuilder token = new StringBuilder("token:");
		//设备
		/*UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
		if (userAgent.getOperatingSystem().isMobileDevice()) {
			token.append("MOBILE-");
		} else {
			token.append("PC-");
		}*/
		//加密的用户名
		token.append(DigestUtils.md5Hex(username) + "-");
		//时间
		token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
		//六位随机字符串
		token.append(new Random().nextInt(999999 - 111111 + 1) + 111111 );
		System.out.println("token-->" + token.toString());
		return token.toString();
	}
	@Transactional
	@Override
	public void save(String token, User devUser) throws Exception {

		redisUtil.set(token,JSON.toJSONString(devUser),2*60*60);
		
	}

	@Override
	public boolean vaildate(String token) throws Exception {
		if (!redisUtil.hasKey(token)) {
			return false;
		}
		return true;
	}

	@Override
	public void delete(String key) throws Exception {
		redisUtil.del(key);
		
	}

	@Override
	public boolean hasKey(String token) {
		return redisUtil.hasKey(token);
	}

	@Override
	public boolean expire(String key, long time) {
		return redisUtil.expire(key,time);
	}

}
