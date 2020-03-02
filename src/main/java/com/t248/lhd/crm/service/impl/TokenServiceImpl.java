package com.t248.lhd.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import com.t248.lhd.crm.entity.UserToken;
import com.t248.lhd.crm.service.TokenService;
import com.t248.lhd.crm.tools.RedisUtil;
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
	public String generateToken() throws Exception {
		String uid=UUID.randomUUID().toString().replace("-", "");
		StringBuffer sb=new StringBuffer();
		sb.append("token:");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"-");
		sb.append(uid);
		// TODO Auto-generated method stub
		return sb.toString();
	}
	@Transactional
	@Override
	public void save(String token,Object devUser) throws Exception {
		UserToken userToken=new UserToken();
		BeanUtils.copyProperties(devUser,userToken);
		redisUtil.set(token,JSON.toJSONString(userToken),2*60*60);
		
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
