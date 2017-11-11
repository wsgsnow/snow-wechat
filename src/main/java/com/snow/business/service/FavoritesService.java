package com.snow.business.service;

import com.snow.business.entity.Favorites;

public interface FavoritesService {
	
	public Favorites saveFavorites(Long userId,Long count,String name);

}
