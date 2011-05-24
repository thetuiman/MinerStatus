package me.davidgreene.minerstatus.service;

public interface ConfigService {

	public String getConfigValue(String key);
	public void setConfigValue(String key, String value);
	public void deleteConfigValue(String key);
}
