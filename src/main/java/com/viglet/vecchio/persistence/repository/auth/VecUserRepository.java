/*
 * Copyright (C) 2016-2019 Alexandre Oliveira <alexandre.oliveira@viglet.com> 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.viglet.vecchio.persistence.repository.auth;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.viglet.vecchio.persistence.model.auth.VecUser;

public interface VecUserRepository extends JpaRepository<VecUser, String> {

	List<VecUser> findAll();

	Optional<VecUser> findById(String id);

	@SuppressWarnings("unchecked")
	VecUser save(VecUser vecRole);
	
	VecUser findByUsernameAndPassword(String username, String password);
	@Modifying
	@Query("delete from VecUser u where u.id = ?1")
	void delete(String id);
}