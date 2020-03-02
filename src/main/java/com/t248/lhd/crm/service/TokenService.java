package com.t248.lhd.crm.service;

import org.springframework.stereotype.Component;

@Component
public interface TokenService {
	/**
	 * ����token
	 *
	 * @return
	 * @throws Exception
	 */
	public String generateToken() throws Exception;
	/**
	 * ����token
	 * @param token
	 * @param devUser
	 * @throws Exception
	 */
	public void save(String token, Object devUser) throws Exception;
	/**
	 * ��ѯ�Ƿ����token
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public boolean vaildate(String token) throws Exception;
	/**
	 * ɾ��token
	 * @param key
	 * @throws Exception
	 */
	public void delete(String key) throws Exception;
	public boolean hasKey(String token);
	public boolean expire(String key,long time);

}
