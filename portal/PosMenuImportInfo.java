package com.orderufo.pos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.orderufo.pos.db.JNDIConnection;
import com.orderufo.pos.utils.AlphaId;
import com.orderufo.pos.utils.Utils;

public class PosMenuImportInfo extends BasePosInfo {
	public void importMenu(HttpServletRequest request) {
		String exportMerchantId = request.getParameter("export");
		String importMerchantId = request.getParameter("import");
		if(exportMerchantId.length()>1 && exportMerchantId != null && importMerchantId.length()>1 && importMerchantId != null) {
			int importHistoryId = generateImportHistory(exportMerchantId, importMerchantId);
			boolean exportResult = exportMenuToTemp(exportMerchantId, importMerchantId);
			boolean importResult = importMenuFromTemp(importMerchantId);
			boolean removeResult = removeMenuTemp(importMerchantId);

			if(exportResult && importResult && removeResult) {
				updateImportHistoryStatus(importHistoryId, "complete");
			}else {
				updateImportHistoryStatus(importHistoryId, "fail");
			}
		}
	}
	
	public boolean exportMenuToTemp (String exportMerchantId, String importMerchantId) {
		boolean result = false;
		boolean categoryResult      = exportCategoryToTemp(exportMerchantId, importMerchantId);
		boolean modifierGroupResult = exportModifierGroupToTemp(exportMerchantId, importMerchantId);
		boolean modifierResult      = exportModifierToTemp(exportMerchantId, importMerchantId);
		boolean taxRateResult       = exportTaxRateToTemp(exportMerchantId, importMerchantId);
		boolean itemResult          = exportItemToTemp(exportMerchantId, importMerchantId);
		
		System.out.println("exportMenuToTemp.categoryResult      => "+categoryResult);
		System.out.println("exportMenuToTemp.modifierGroupResult => "+modifierGroupResult);
		System.out.println("exportMenuToTemp.modifierResult      => "+modifierResult);
		System.out.println("exportMenuToTemp.taxRateResult       => "+taxRateResult);
		System.out.println("exportMenuToTemp.itemResult          => "+itemResult);
		
		result = categoryResult && modifierGroupResult && modifierResult && modifierResult && taxRateResult && itemResult;
		return result;
	}
	
	public boolean importMenuFromTemp(String importMerchantId) {
		boolean result = false;
		boolean categoryResult = importCategoryFromTemp(importMerchantId);
		boolean modifierGroupResult = importModifierGroupFromTemp(importMerchantId);
		boolean modifierResult = importModifierFromTemp(importMerchantId);
		boolean taxRateResult = importTaxRateFromTemp(importMerchantId);
		boolean itemResult =  importItemFromTemp(importMerchantId);
		
		System.out.println("exportMenuToTemp.categoryResult      => "+categoryResult);
		System.out.println("exportMenuToTemp.modifierGroupResult => "+modifierGroupResult);
		System.out.println("exportMenuToTemp.modifierResult      => "+modifierResult);
		System.out.println("exportMenuToTemp.taxRateResult       => "+taxRateResult);
		System.out.println("exportMenuToTemp.itemResult          => "+itemResult);
		
		result = categoryResult && modifierGroupResult && modifierResult && taxRateResult && itemResult;
		return result;
	}
	
	public boolean exportCategoryToTemp(String exportMerchantId, String importMerchantId) {
		boolean result = false;
//		try {
//			JNDIConnection db = new JNDIConnection(0);
//			myConn = db.getConnection();
//			pstmt = myConn.createStatement();
//			String _sql = "SELECT id, original_id, name, merchant_id, category_ufo, script, alcohol, hidden, flag, display_name, CAST( service_type AS SIGNED) service_type FROM t_category WHERE merchant_id = '" +exportMerchantId+ "' AND flag = 0";
//			rs = pstmt.executeQuery(_sql);
//			
//			while (rs.next()) {
//				String _id           = "ufo_" + AlphaId.Default().getEncodeId(1001);
//				String _original_id  = rs.getString("original_id");
//				String _name         = rs.getString("name");
//				String _script       = "";
//				String _merchant_id  = importMerchantId;
//				String _alcohol      = rs.getString("alcohol");
//				String _category_ufo = rs.getString("category_ufo");
//				String _display_name = rs.getString("display_name");
//				String _hidden       = rs.getString("hidden");
//				String _service_type = rs.getString("service_type");
//				String _old_id       = rs.getString("id");
//				
//				String[] _pstmt = { _id, _original_id, _name, _script, _merchant_id, _alcohol, _category_ufo, _display_name, _hidden, _service_type, _old_id };
//				String sQuery = "INSERT INTO t_category_temp (id, original_id, name, script, merchant_id, created_time, updated_time, "
//						+ "alcohol, category_ufo, display_name, hidden, service_type, old_id) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?, ?, ?, ?, ?, ? )";
//				executeQuery(sQuery, _pstmt);
//			}
//			result = true;
//		} catch (SQLException e) {
//			System.err.println("exportCategoryToTemp sql error:" + e.getMessage());
//		} finally {
//			 closeAll();
//		}
		
		
		
		
		String selectSql = "SELECT id, original_id, name, merchant_id, category_ufo, script, alcohol, hidden, flag, display_name, CAST( service_type AS SIGNED) service_type FROM t_category WHERE merchant_id = '"
				+ exportMerchantId + "' AND flag = 0";
		String insertSql = "INSERT INTO t_category_temp (id, original_id, name, script, merchant_id, "
				+ "alcohol, category_ufo, display_name, hidden, service_type, old_id, created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
		try (Connection c = new JNDIConnection(0).getConnection(); Statement s = c.createStatement(); PreparedStatement ps = c.prepareStatement(insertSql);) {
			ResultSet rs = s.executeQuery(selectSql);
			while (rs.next()) {
				ps.setString(1, "ufo_" + AlphaId.Default().getEncodeId(1001));
				ps.setString(2, rs.getString("original_id"));
				ps.setString(3, rs.getString("name"));
				ps.setString(4, "");
				ps.setString(5, importMerchantId);
				ps.setString(6, rs.getString("alcohol"));
				ps.setString(7, rs.getString("category_ufo"));
				ps.setString(8, rs.getString("display_name"));
				ps.setString(9, rs.getString("hidden"));
				ps.setString(10, rs.getString("service_type"));
				ps.setString(11, rs.getString("id"));

				System.out.println("selectSql => " + selectSql);
				System.out.println("insertSql => " + insertSql);

				ps.execute();
			}
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean exportModifierGroupToTemp(String exportMerchantId, String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, merchant_id, default_modifier_ids, `min`, `max`, hidden, script, display_name FROM t_modifier_group WHERE merchant_id = '" +exportMerchantId+ "' AND Flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id                   = "ufo_" + AlphaId.Default().getEncodeId(1001);
				String _original_id          = rs.getString("original_id");
				String _name                 = rs.getString("name");
				String _merchant_id          = importMerchantId;
				String _default_modifier_ids = "";
				String _min                  = rs.getString("min");
				String _max                  = rs.getString("max");
				String _hidden               = rs.getString("hidden");
				String _script               = rs.getString("script");
				String _display_name         = rs.getString("display_name");
				String _old_id               = rs.getString("id");
				
				String[] _pstmt = { _id, _original_id, _name, _merchant_id, _default_modifier_ids, _min, _max, _hidden, _script, _display_name, _old_id };
				String sQuery = "INSERT INTO t_modifier_group_temp ( id, original_id, name, merchant_id, default_modifier_ids, min, max, hidden, script,"
						+ " display_name, old_id, created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("exportModifierGroupToTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean exportModifierToTemp(String exportMerchantId, String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, display_name, price, script, modifier_group_id, merchant_id, CAST( status AS SIGNED) status, stock FROM t_modifier WHERE merchant_id = '" +exportMerchantId+ "' AND flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _old_modifier_group_id = rs.getString("modifier_group_id");
				String _id                = "ufo_" + AlphaId.Default().getEncodeId(1001);
				String _original_id       = rs.getString("original_id");
				String _name              = rs.getString("name");
				String _display_name      = rs.getString("display_name");
				String _price             = rs.getString("price");
				String _script            = "";
				String _modifier_group_id = getIdByOldId("t_modifier_group_temp", _old_modifier_group_id);
				String _merchant_id       = importMerchantId;
				String _status            = rs.getString("status");
				String _stock             = rs.getString("stock");
				String _old_id            = rs.getString("id");
				
				String[] _pstmt = { _id, _original_id, _name, _display_name, _price, _script, _modifier_group_id, 
						_merchant_id, _status, _stock, _old_id, _old_modifier_group_id };
				String sQuery = "INSERT INTO t_modifier_temp ( id, original_id, name, display_name, price, script, modifier_group_id, merchant_id, status,"
						+ " stock, old_id, old_modifier_group_id, created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("exportModifierToTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean exportTaxRateToTemp(String exportMerchantId, String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, rate, is_default, merchant_id FROM t_taxrate WHERE merchant_id = '" +exportMerchantId+ "' AND Flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id          = "ufo_" + AlphaId.Default().getEncodeId(1001);
				String _original_id = rs.getString("original_id");
				String _name        = rs.getString("name");
				String _rate        = rs.getString("rate");
				String _is_default  = rs.getString("is_default");
				String _merchant_id = importMerchantId;
				String _old_id      = rs.getString("id");
				
				String[] _pstmt = { _id, _original_id, _name, _rate, _is_default, _merchant_id, _old_id };
				String sQuery = "INSERT INTO t_taxrate_temp ( id, original_id, name, rate, is_default, merchant_id, "
						+ "old_id, created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("exportTaxRateToTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
			
	public boolean exportItemToTemp(String exportMerchantId, String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, image_url, description, price, price_type, price_currency, script, tax_rates_ids, category_ids, category_ufo, modifier_group_ids, merchant_id, CAST( status AS SIGNED) status, display_name, CAST( service_type AS SIGNED ) service_type, stock FROM t_item WHERE merchant_id = '" +exportMerchantId+ "' AND Flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _old_id                 = rs.getString("id");
				String _old_tax_rates_ids      = rs.getString("tax_rates_ids");
				String _old_category_ids       = rs.getString("category_ids");
				String _old_modifier_group_ids = rs.getString("modifier_group_ids");

				String _id                     = "ufo_" + AlphaId.Default().getEncodeId(1001);
				String _original_id            = rs.getString("original_id");
				String _name                   = rs.getString("name");
				String _image_url              = rs.getString("image_url");
				String _description            = rs.getString("description");
				String _price                  = rs.getString("price");
				String _price_type             = rs.getString("price_type");
				String _price_currency         = rs.getString("price_currency");
				String _script                 = "";
				String _tax_rates_ids          = getIdsByOldIds("t_taxrate_temp", _old_tax_rates_ids);
				String _category_ids           = getIdsByOldIds("t_category_temp", _old_category_ids);
				String _category_ufo           = rs.getString("category_ufo");
				String _modifier_group_ids     = getIdsByOldIds("t_modifier_group_temp", _old_modifier_group_ids);
				String _merchant_id            = importMerchantId;
				String _status                 = rs.getString("status");
				String _display_name           = rs.getString("display_name");
				String _service_type           = rs.getString("service_type");
				String _stock                  = rs.getString("stock");
				
				String[] _pstmt = { _id, _original_id, _name, _image_url, _description, _price, 
						_price_type, _price_currency, _script, _tax_rates_ids, _category_ids, _category_ufo, 
						_modifier_group_ids, _merchant_id, _status, _display_name, _service_type, _stock,
						_old_id, _old_tax_rates_ids, _old_category_ids, _old_modifier_group_ids };
				
				String sQuery = "INSERT INTO t_item_temp ( id, original_id, name, image_url, description, price, "
						+ "price_type, price_currency, script, tax_rates_ids, category_ids, category_ufo, "
						+ "modifier_group_ids, merchant_id, status, display_name, service_type, stock, old_id, old_tax_rates_ids, old_category_ids, old_modifier_group_ids, "
						+ " created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("exportItemToTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public String getIdsByOldIds(String table, String old_ids) {
		if(old_ids != null && old_ids.length()>2) {
			List<String> listNewIds = new ArrayList<String>();
			String strNewIds = "";
			try {
				JNDIConnection db = new JNDIConnection(0);
				myConn = db.getConnection();
				pstmt1 = myConn.createStatement();
				String _sql = "SELECT id, old_id FROM " +table+ " WHERE old_id in( %s )";
				String _where = old_ids.replace("[", "").replace("]", "");
				_sql = String.format(_sql, _where);
				System.out.println(_sql);
				rs1 = pstmt1.executeQuery(_sql);
				while (rs1.next()) {
					String _id  = rs1.getString("id");
					listNewIds.add(_id);
				}
				strNewIds = Utils.getIdsString(listNewIds);
			} catch (SQLException e) {
				System.err.println("getIdsByOldIds sql error:" + e.getMessage());
			} finally {
				try {
					if (rs1 != null) {
						rs1.close();
					}
					if (pstmt1 != null) {
						pstmt1.close();
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return strNewIds;
		}
		return "";
	}
	
	public String getIdByOldId(String table, String oldId) {
		if(oldId != null && oldId.length()>1) {
			String newId = "";
			try {
				JNDIConnection db = new JNDIConnection(0);
				myConn = db.getConnection();
				pstmt1 = myConn.createStatement();
				String _sql = "SELECT id, old_id FROM " +table+ " WHERE old_id in( '" +oldId+ "' )";
				rs1 = pstmt1.executeQuery(_sql);
				while (rs1.next()) {
					newId = rs1.getString("id");
				}
			} catch (SQLException e) {
				System.err.println("getIdByOldId sql error:" + e.getMessage());
				System.err.println(pstmt);
			} finally {
				try {
					if (rs1 != null) {
						rs1.close();
					}
					if (pstmt1 != null) {
						pstmt1.close();
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return newId;
		}
		return "";
	}
	
	public boolean importCategoryFromTemp(String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, merchant_id, category_ufo, script, alcohol, hidden, flag, display_name, CAST( service_type AS SIGNED) service_type FROM t_category_temp WHERE merchant_id = '" +importMerchantId+ "' AND flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id           = rs.getString("id");
				String _original_id  = rs.getString("original_id");
				String _name         = rs.getString("name");
				String _script       = rs.getString("script");
				String _merchant_id  = rs.getString("merchant_id");
				String _alcohol      = rs.getString("alcohol");
				String _category_ufo = rs.getString("category_ufo");
				String _display_name = rs.getString("display_name");
				String _hidden       = rs.getString("hidden");
				String _service_type = rs.getString("service_type");
				
				String[] _pstmt = { _id, _original_id, _name, _script, _merchant_id, _alcohol, _category_ufo, _display_name, _hidden, _service_type };
				String sQuery = "INSERT INTO t_category (id, original_id, name, script, merchant_id, alcohol, category_ufo, "
						+ "display_name, hidden, service_type, created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("importCategoryFromTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean importModifierGroupFromTemp(String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, merchant_id, default_modifier_ids, `min`, `max`, hidden, script, display_name FROM t_modifier_group_temp WHERE merchant_id = '" +importMerchantId+ "' AND Flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id                   = rs.getString("id");
				String _original_id          = rs.getString("original_id");
				String _name                 = rs.getString("name");
				String _merchant_id          = rs.getString("merchant_id");
				String _default_modifier_ids = rs.getString("default_modifier_ids");
				String _min                  = rs.getString("min");
				String _max                  = rs.getString("max");
				String _hidden               = rs.getString("hidden");
				String _script               = rs.getString("script");
				String _display_name         = rs.getString("display_name");
				
				String[] _pstmt = { _id, _original_id, _name, _merchant_id, _default_modifier_ids, _min, _max, _hidden, _script, _display_name };
				String sQuery = "INSERT INTO t_modifier_group ( id, original_id, name, merchant_id, default_modifier_ids, min, max, hidden, script,"
						+ " display_name, created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("importModifierGroupFromTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean importModifierFromTemp(String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, display_name, price, script, modifier_group_id, merchant_id, CAST( status AS SIGNED) status, stock FROM t_modifier_temp WHERE merchant_id = '" +importMerchantId+ "' AND flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id                = rs.getString("id");
				String _original_id       = rs.getString("original_id");
				String _name              = rs.getString("name");
				String _display_name      = rs.getString("display_name");
				String _price             = rs.getString("price");
				String _script            = rs.getString("script");
				String _modifier_group_id = rs.getString("modifier_group_id");
				String _merchant_id       = rs.getString("merchant_id");
				String _status            = rs.getString("status");
				String _stock             = rs.getString("stock");
				
				String[] _pstmt = { _id, _original_id, _name, _display_name, _price, _script, _modifier_group_id, _merchant_id, _status, _stock };
				String sQuery = "INSERT INTO t_modifier ( id, original_id, name, display_name, price, script, modifier_group_id, merchant_id, status,"
						+ " stock,  created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("importModifierFromTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean importTaxRateFromTemp(String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, rate, is_default, merchant_id FROM t_taxrate_temp WHERE merchant_id = '" +importMerchantId+ "' AND Flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id          = rs.getString("id");
				String _original_id = rs.getString("original_id");
				String _name        = rs.getString("name");
				String _rate        = rs.getString("rate");
				String _is_default  = rs.getString("is_default");
				String _merchant_id = rs.getString("merchant_id");
				
				String[] _pstmt = { _id, _original_id, _name, _rate, _is_default, _merchant_id };
				String sQuery = "INSERT INTO t_taxrate ( id, original_id, name, rate, is_default, merchant_id, "
						+ " created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("importTaxRateFromTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean importItemFromTemp(String importMerchantId) {
		boolean result = false;
		try {
			JNDIConnection db = new JNDIConnection(0);
			myConn = db.getConnection();
			pstmt = myConn.createStatement();
			String _sql = "SELECT id, original_id, name, image_url, description, price, price_type, price_currency, script, tax_rates_ids, category_ids, category_ufo, modifier_group_ids, merchant_id, CAST( status AS SIGNED) status, display_name, CAST( service_type AS SIGNED ) service_type, stock FROM t_item_temp WHERE merchant_id = '" +importMerchantId+ "' AND Flag = 0";
			rs = pstmt.executeQuery(_sql);
			
			while (rs.next()) {
				String _id                     = rs.getString("id");
				String _original_id            = rs.getString("original_id");
				String _name                   = rs.getString("name");
				String _image_url              = rs.getString("image_url");
				String _description            = rs.getString("description");
				String _price                  = rs.getString("price");
				String _price_type             = rs.getString("price_type");
				String _price_currency         = rs.getString("price_currency");
				String _script                 = rs.getString("script");
				String _tax_rates_ids          = rs.getString("tax_rates_ids");
				String _category_ids           = rs.getString("category_ids");
				String _category_ufo           = rs.getString("category_ufo");
				String _modifier_group_ids     = rs.getString("modifier_group_ids");
				String _merchant_id            = rs.getString("merchant_id");
				String _status                 = rs.getString("status");
				String _display_name           = rs.getString("display_name");
				String _service_type           = rs.getString("service_type");
				String _stock                  = rs.getString("stock");
				
				String[] _pstmt = { _id, _original_id, _name, _image_url, _description, _price, 
						_price_type, _price_currency, _script, _tax_rates_ids, _category_ids, _category_ufo, 
						_modifier_group_ids, _merchant_id, _status, _display_name, _service_type, _stock };
				
				String sQuery = "INSERT INTO t_item ( id, original_id, name, image_url, description, price, "
						+ "price_type, price_currency, script, tax_rates_ids, category_ids, category_ufo, "
						+ "modifier_group_ids, merchant_id, status, display_name, service_type, stock, "
						+ " created_time, updated_time ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
				executeQuery(sQuery, _pstmt);
			}
			result = true;
		} catch (SQLException e) {
			System.err.println("importItemFromTemp sql error:" + e.getMessage());
		} finally {
			 closeAll();
		}
		return result;
	}
	
	public boolean removeMenuTemp(String importMerchantId) {
		boolean result = false;
		String categorySql             = "Delete FROM t_category_temp WHERE merchant_id = '" +importMerchantId+ "' ";
		String modifierGroupSql        = "Delete FROM t_modifier_group_temp WHERE merchant_id = '" +importMerchantId+ "' ";
		String modifierSql             = "Delete FROM t_modifier_temp WHERE merchant_id = '" +importMerchantId+ "' ";
		String taxRateSql              = "Delete FROM t_taxrate_temp WHERE merchant_id = '" +importMerchantId+ "' ";
		String itemSql                 = "Delete FROM t_item_temp WHERE merchant_id = '" +importMerchantId+ "' ";
		boolean categoryResult         = executeQuery(categorySql);
		boolean modifierGroupSqlResult = executeQuery(modifierGroupSql);
		boolean modifierSqlResult      = executeQuery(modifierSql);
		boolean taxRateSqlResult       = executeQuery(taxRateSql);
		boolean itemSqlResult          = executeQuery(itemSql);
		result                         = categoryResult && modifierGroupSqlResult && modifierSqlResult && taxRateSqlResult && itemSqlResult;
		
		return result;
	}
	
	public int generateImportHistory(String exportMerchantId, String importMerchantId) {
		int id = 0;
		String sql = "INSERT INTO t_import_history ( id, export_merchant_id, import_merchant_id, status, created_time ) VALUES ( null, ?, ?, ?, NOW() )";
		try (Connection c = new JNDIConnection(0).getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, exportMerchantId);
			ps.setString(2, importMerchantId);
			ps.setString(3, "procedure");
			ps.execute();
			System.out.println(ps);
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("removeMenuTemp sql error:" + e.getMessage());
		}
		return id;
	}
	
	public boolean updateImportHistoryStatus(int id, String status) {
		boolean result = false;
		String _sql = " UPDATE t_import_history SET status = '" +status+ "' WHERE id = '" +id+ "' ";
		result = executeQuery(_sql);
		return result;
	}
}
