package com.yousoff.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yousoff.springboot.dao.ItemDao;
import com.yousoff.springboot.exception.RepositoryException;
import com.yousoff.springboot.model.Item;
import com.yousoff.springboot.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Yousoff Effendy
 *
 */
@RestController
@RequestMapping("/rest/v1")
@Api(description = "Controller to create, read, update and delete item in database.")
@NoArgsConstructor
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemDao itemDao;
	
	public ItemController(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	/**
	 * Create an item. Created date will always be current date.
	 * 
	 * @param item
	 *            : The item ID is not necessary if the ID is autoincrement in DB.
	 * @return {@link Response}
	 */
	@ApiOperation(value = "Create an item", notes="Please specify created by.")
	@RequestMapping(value = "/createItem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> createItem(@RequestBody Item item) {

		logger.debug("Request /createItem " + item.toString());

		try {
			int result = itemDao.create(item);
			
			if(result != 0) {
				logger.debug("Response /createItem " + item.toString());
				return new ResponseEntity<Response>(new Response("Successfully created a new item.", "success", item),
						HttpStatus.OK);
			} else {
				logger.debug("Response /createItem failed " + item.toString());
				return new ResponseEntity<Response>(new Response("Failed to create a new item.", "failed", item),
						HttpStatus.OK);
			}
			
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Response>(new Response("Failed to created a new item.", "failed", item),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Return a single item provided item ID
	 * 
	 * @param itemId
	 * @return : {@link Response}
	 */
	@ApiOperation(value = "Return a single item provided item ID")
	@RequestMapping(value = "/getItem/{itemId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Response> getItem(
			@PathVariable @ApiParam(name = "itemId", value = "Item ID.", example = "1") long itemId) {

		logger.debug("Request /getItem : itemId " + itemId);

		Item item;
		try {
			item = itemDao.getItemById(itemId);
			
			if(item != null) {
				logger.debug("Response /getItem : item " + item.toString());
				return new ResponseEntity<Response>(new Response("Successfully retrieve an item.", "success", item),
						HttpStatus.OK);
			} else {
				logger.debug("Response /getItem : item -");
				return new ResponseEntity<Response>(new Response("No item can be found.", "success", item),
						HttpStatus.OK);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Response>(new Response("Failed to retrieve an item.", "failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieve all items available in DB.
	 * 
	 * @return : {@link Response}
	 */
	@ApiOperation(value = "Return all items")
	@RequestMapping(value = "/getAllItems", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Response> getAllItems() {

		logger.debug("Request /getAllItems");

		try {
			List<Item> items = itemDao.getAllItems();

			if(!CollectionUtils.isEmpty(items)) {
				if (logger.isDebugEnabled()) {
					logger.debug("Response /getAllItems");
					for (Item item : items) {
						logger.debug(item.toString());
					}
				}
				return new ResponseEntity<Response>(new Response("Successfully retrieve all items.", "success", items),
						HttpStatus.OK);
				
			} else {
				logger.debug("Response /getAllItems : item -");
				return new ResponseEntity<Response>(new Response("No item can be found.", "success", items),
						HttpStatus.OK);
			}
			
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Response>(new Response("Failed to retrieve all items.", "failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update an item in DB based on item ID. Updated date will always be current date.
	 * 
	 * @param item
	 * @return : {@link Response}
	 */
	@ApiOperation(value = "Update an item", notes="Please specify update by.")
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> updateItem(@RequestBody Item item) {

		logger.debug("Request /updateItem " + item.toString());

		try {
			int result = itemDao.update(item);
			
			if(result != 0) {
				logger.debug("Response /updateItem " + item.toString());
				return new ResponseEntity<Response>(new Response("Successfully update an item.", "success", item),
						HttpStatus.OK);
			} else {
				logger.debug("Response /updateItem failed " + item.toString());
				return new ResponseEntity<Response>(new Response("Failed to update an item.", "failed", item),
						HttpStatus.OK);
			}
			
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Response>(new Response("Failed to update an item.", "failed", item),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete an item in DB based on item ID.
	 * 
	 * @param item
	 * @return {@link Response}
	 */
	@ApiOperation(value = "Delete an item")
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> deleteItem(@RequestBody Item item) {

		logger.debug("Request /deleteItem : item " + item.toString());

		try {
			int result = itemDao.delete(item);
			
			if(result != 0 ) {
				logger.debug("Response /deleteItem : item " + item.toString());
				return new ResponseEntity<Response>(new Response("Successfully delete an item.", "success", item),
						HttpStatus.OK);
			} else {
				logger.debug("Response /deleteItem : item failed " + item.toString());
				return new ResponseEntity<Response>(new Response("Failed to delete an item.", "failed", item),
						HttpStatus.OK);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Response>(new Response("Failed to delete an item.", "failed", item),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
