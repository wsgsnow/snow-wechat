package com.snow.business.service;

import com.snow.business.entity.Config;

public interface ConfigService {
	
	public Config saveConfig(Long userId,String favoritesId);

	public void updateConfig(Long id ,String type,String defaultFavorites);
}
