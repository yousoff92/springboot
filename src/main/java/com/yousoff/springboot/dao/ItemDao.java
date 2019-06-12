package com.yousoff.springboot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.yousoff.springboot.exception.RepositoryException;
import com.yousoff.springboot.model.Item;

/**
 * Reference : https://examples.javacodegeeks.com/enterprise-java/spring/spring-jdbctemplate-crud-operations-tutorial/
 * 
 * @author Yousoff Effendy
 *
 */
@Repository
public class ItemDao {

	private static final Logger logger = LoggerFactory.getLogger(ItemDao.class);
	private JdbcTemplate jdbcTemplate;
	
	public int create(Item item) throws RepositoryException {
		final String query = "INSERT INTO ITEM (name,description,enabled,created_date,created_by)" +
				" VALUES (?,?,?,?,?) ";
		
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		try {
			return jdbcTemplate.update(query, 
					item.getName(),
					item.getDescription(),
					item.getEnabled(),
					dateStr,
					item.getCreatedBy());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RepositoryException(e);
		}

	}
	
	public Item getItemById(long id) throws RepositoryException {
		final StringBuffer query = new StringBuffer("SELECT * FROM ITEM WHERE enabled = ? AND ID = ?");

		PreparedStatementSetter pstmt = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setObject(1, "Y");
				ps.setObject(2, id);
			}
		};
		
		List<Item> items;
		try {
			items = jdbcTemplate.query(query.toString(), pstmt, new ItemMapper());
			if(!CollectionUtils.isEmpty(items)) {
				return items.get(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RepositoryException(e);
		}
		return null;
	}
	
	public List<Item> getAllItems() throws RepositoryException {
		final StringBuffer query = new StringBuffer("SELECT * FROM ITEM WHERE enabled = ? ");
		try {
			return jdbcTemplate.query(query.toString(), new Object[] {"Y"}, new ItemMapper());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RepositoryException(e);
		}
	}
	
	public int update(Item item) throws RepositoryException {
		final String query = "UPDATE ITEM " + 
				" SET name=?, description=?, enabled=?, updated_date=?, updated_by=? " + 
				" WHERE id=? ";
		
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		try {
			return jdbcTemplate.update(query, 
					item.getName(),
					item.getDescription(),
					item.getEnabled(),
					dateStr,
					item.getUpdatedBy(),
					item.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RepositoryException(e);
		}
	}
	
	public int delete(Item item) throws RepositoryException {
		final String query = "DELETE FROM ITEM WHERE id=?";
		
		try {
			return jdbcTemplate.update(query, item.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RepositoryException(e);
		}
	}
	
	private static final class ItemMapper implements RowMapper<Item> {

		@Override
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item item = new Item();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setEnabled(rs.getString("enabled"));
			item.setCreatedDate(rs.getTimestamp("created_date"));
			item.setCreatedBy(rs.getString("created_by"));
			item.setUpdatedDate(rs.getTimestamp("created_date"));
			item.setUpdatedBy(rs.getString("updated_by"));
			return item;
		}

	}
}
