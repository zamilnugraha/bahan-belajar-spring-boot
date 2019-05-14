package com.banner.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import com.banner.entity.Banner;
import com.banner.entity.BannerPK;

public interface BannerDao extends JpaRepository<Banner, BannerPK> {
        
    public Banner findOneByTitle (String tittle);
    public Banner findOneById(String id);
	public List<Banner> findAllByApprovalNoAndRowStatusOrderByRowTimeUpdateDesc(int frontendApprovalNo,
			String frontendRowStatus);

    @Modifying(clearAutomatically = true)
	@Query(value="UPDATE EKR_10_BANNER SET ROW_STATUS = ?, ROW_TIME_UPDATE = ? WHERE ID = ?",nativeQuery=true)
	public Integer deleteBanner(String rowStatus, Date rowTimeUpdate, String id);
    
    @Query(value="SELECT * FROM EKR_10_BANNER WHERE ROW_STATUS = '1' AND APPROVAL_NO = '100' ORDER BY ROW_TIME_UPDATE DESC", nativeQuery=true)
	public List<Banner> findAllRedis();
    
    @Query(value="SELECT * FROM EKR_10_BANNER WHERE ROW_STATUS = '1' ORDER BY ROW_TIME_UPDATE DESC", nativeQuery=true)
	public List<Banner> findAllData();

    @Query("select n from Banner n where n.rowStatus <>'0' order by n.rowTimeUpdate desc")
	public List<Banner> findAllRedisWithApproval(); 
    
   	 @Query(value="SELECT ID, TITLE, IMAGE_FILENAME, DESCRIPTION, URL "
   	 		+ "FROM EKR_10_BANNER "
   	 		+ "WHERE APPROVAL_NO = 100 AND ROW_STATUS = 1 "
   	 		+ "ORDER BY ROW_TIME_UPDATE DESC", nativeQuery=true)
   	 public List<Object[]> frontendLangIndo();
   	 
   	 @Query(value="SELECT ID, "
   	 		+ "TITLE_EN, "
   	 		+ "IMAGE_FILENAME, "
   	 		+ "DESCRIPTION_EN, "
   	 		+ "URL "
   		 	+ "FROM EKR_10_BANNER "
   		   	+ "WHERE APPROVAL_NO = 100 AND ROW_STATUS = 1 "
   		 	+ "ORDER BY ROW_TIME_UPDATE DESC", nativeQuery=true)
   	 public List<Object[]> frontendLangEn();
   	 
   	 //preview
   	 @Query(value="SELECT ID, TITLE, IMAGE_FILENAME, DESCRIPTION, URL "
   	 		+ "FROM EKR_10_BANNER "
   		   	+ "WHERE ROW_STATUS = 1 "
   	 		+ "ORDER BY ROW_TIME_UPDATE DESC", nativeQuery=true)
   	 public List<Object[]> frontendPreviewLangIndo();
   	 
   	 @Query(value="SELECT ID, "
   	 		+ "TITLE_EN, "
   	 		+ "IMAGE_FILENAME, "
   	 		+ "DESCRIPTION_EN, "
   	 		+ "URL "
   		 	+ "FROM EKR_10_BANNER "
   		   	+ "WHERE ROW_STATUS = 1 "
   		 	+ "ORDER BY ROW_TIME_UPDATE DESC", nativeQuery=true)
   	 public List<Object[]> frontendPreviewLangEn();
	 
	 @Modifying(clearAutomatically = true)
	 @Query(value="UPDATE EKR_10_BANNER SET APPROVAL_NO = ?, ROW_TIME_UPDATE = ?, APPROVAL_DATE = ?, APPROVAL_USER_ID = ?, ROW_STATUS = '1' WHERE ID = ?",nativeQuery=true)
	 public Integer updateStatus(String approvalStatus, Date rowTimeUpdate, Date approvalDate, String idUser, String id);
	 
	 @Modifying(clearAutomatically = true)
	 @Query(value="SELECT APPROVAL_NO FROM EKR_98_USER_GROUP_MENU  WHERE PARENT_ID = ? AND MENU_ID = (SELECT ID FROM EKR_98_MENU WHERE URL = ?)",nativeQuery=true)
	 public Integer getApprovalValue(String parentID, String menuID);
	 
	 @Query(value="SELECT * FROM EKR_98_USER_GROUP_MENU  WHERE PARENT_ID = ? AND MENU_ID = (SELECT ID FROM EKR_98_MENU WHERE URL = ?)",nativeQuery=true)
	 public List<Object[]> mapApprovalValue(String parentID, String menuID);

	@Query(value="SELECT hp.*, ctg.NAME FROM EKR_10_BANNER hp "
			+ "INNER JOIN EKR_10_CATEGORY ctg ON hp.CATEGORY_ID = CTG.ID "
			+ "WHERE hp.ROW_STATUS = '1' ORDER BY hp.ROW_TIME_UPDATE DESC", nativeQuery = true)
	public List<Object[]> findAllJoin();
	
	@Query(value="SELECT hp.*, ctg.NAME, ctg.PARENT_ID FROM EKR_10_BANNER hp "
			+ "INNER JOIN EKR_10_CATEGORY ctg ON hp.CATEGORY_ID = CTG.ID "
			+ "WHERE hp.ROW_STATUS = '1' AND hp.id = ? ORDER BY hp.ROW_TIME_UPDATE DESC", nativeQuery = true)
	public List<Object[]> findHashmapById(String id);
	
	@Query(value="SELECT tbl_user_group_menu.ID, tbl_user_group_menu.PARENT_ID, tbl_user_group_menu.APPROVAL_NO, tbl_user_group.NAME \r\n" + 
	 		"FROM EKR_98_USER_GROUP_MENU tbl_user_group_menu\r\n" + 
	 		"JOIN EKR_98_USER_GROUP tbl_user_group ON tbl_user_group.ID = tbl_user_group_menu.PARENT_ID\r\n" + 
	 		"WHERE tbl_user_group_menu.MENU_ID = (SELECT ID FROM EKR_98_MENU WHERE URL = ?) AND tbl_user_group_menu.PARENT_ID <> 'LS5HuJew6afHMaz9uwfr' \r\n" + 
	 		"ORDER BY APPROVAL_NO ASC",nativeQuery=true)
	public List<Object[]> findAllApprovalValue(String menuID);

	public List<Banner> findAllByRowStatusOrderByRowTimeUpdateDesc(String frontendRowStatus);
	
	 @Query(value="SELECT * FROM EKR_98_USER_GROUP_MENU "
		 		+ "WHERE PARENT_ID = (SELECT GROUP_ID FROM EKR_98_USER  WHERE ID = (SELECT CREATE_USER_ID FROM EKR_10_BANNER WHERE ID = ? AND ROW_STATUS = 1)) "
		 	    + "AND MENU_ID = (SELECT ID FROM EKR_98_MENU WHERE URL = ?)",nativeQuery=true)
		 public List<Object[]> mapApprovalReject(String parentID, String menuID);
		 
		@Modifying(clearAutomatically = true)
		@Query(value="UPDATE EKR_10_BANNER SET APPROVAL_NO = ?, REJECT_USER_ID = ?, ROW_TIME_UPDATE = ?, REJECT_DATE = ?, ROW_STATUS = '2' WHERE ID = ?",nativeQuery=true)
		public Integer rejectBanner(String approvalNo, String rejectUserId, Date rowTimeUpdate, Date rejectDate, String id);
		
		@Query(value="SELECT COUNT(*) FROM EKR_10_BANNER  WHERE ROW_STATUS <>'0' AND APPROVAL_NO < ? ",nativeQuery=true)
		public List<Object[]> pending(Integer approvalNoUser);
		public List<Banner> findAllByApprovalNoAndRowStatusOrderBySortNoAscRowTimeUpdateDesc(int frontendApprovalNo,
				String frontendRowStatus);
}
