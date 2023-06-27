package com.alexis.service;

import org.springframework.data.domain.Page;

import com.alexis.model.Usuario;

public interface Pageservice {

	public Page<Usuario> findAllByPage(int skip, int take);
}
