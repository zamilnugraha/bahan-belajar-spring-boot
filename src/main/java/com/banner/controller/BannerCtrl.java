/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banner.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banner.common.RestResponse;
import com.banner.common.SecurityUtil;
import com.banner.dao.BannerDao;
import com.banner.dao.RedisDao;
import com.banner.dto.BannerDto;
import com.banner.dto.RejectDto;
import com.banner.dto.TokenDto;
import com.banner.service.ApplicationSvc;
import com.banner.service.BannerSvc;
import com.banner.service.ConfigSvc;
import com.banner.service.RejectSvc;
import com.banner.service.StatisticSvc;
import com.banner.service.TokenSvc;

/**
 *
 * @author eKreasi Chubb Team ( Fairuz Fatin, Bayu Sugara, Maretta, Muhamad
 *         Zamil, Fhad Saleh )
 */

@RestController
public class BannerCtrl {

	@Autowired
	private BannerSvc bannerSvc;

	@Autowired
	RedisDao redisDao;

	@Autowired
	TokenSvc tokenSvc;

	@Autowired
	StatisticSvc statisticSvc;

	@Autowired
	BannerDao bannerDao;

	@Autowired
	ApplicationSvc applicationSvc;

	@Autowired
	ConfigSvc configSvc;

	@Autowired
	RejectSvc rejectSvc;

	String menuID = "/content/banner";
	static Logger logger = LoggerFactory.getLogger(BannerCtrl.class);

	// -------------------------------------------------- FRONTEND SCRIPT
	// ------------------------------

	@RequestMapping(value = "frontend/all", method = RequestMethod.GET)
	public RestResponse frontendBannerAllWithPublishDate(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "lang", required = false) String lang, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			SimpleDateFormat sdfFormat = new SimpleDateFormat(configSvc.findOneByNameTypeString("date_format"));

			List<HashMap> bannerDto = new ArrayList<>();

			lang = (lang.equalsIgnoreCase("") ? "id" : lang);

			if (lang.equalsIgnoreCase("EN") || lang.equalsIgnoreCase("en")) {

				bannerDto = bannerSvc.frontendBannerEnWithPublishDate();

			} else if (lang.equalsIgnoreCase("ID") || lang.equalsIgnoreCase("id")) {

				bannerDto = bannerSvc.frontendBannerIndoWithPublishDate();

			}
			if (bannerDto.size() > 0) {
				restResponse.setStatus(100);
				restResponse.setMessage("Success");
				restResponse.setDatas(bannerDto);
				restResponse.setCounter(counter);
			} else {
				restResponse.setStatus(10);
				restResponse.setMessage("Data Not Found");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "preview", method = RequestMethod.GET)
	public RestResponse previewBannerAll(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "lang", required = false) String lang,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;

			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {

				List<HashMap> bannerDto = new ArrayList<>();
				if (lang.equalsIgnoreCase("EN") || lang.equalsIgnoreCase("en")) {
					bannerDto = bannerSvc.frontendPreviewBannerEng();
				} else {
					bannerDto = bannerSvc.frontendPreviewBannerId();
				}

				if (bannerDto.size() > 0) {
					restResponse.setStatus(100);
					restResponse.setMessage("Success");
					restResponse.setDatas(bannerDto);
					restResponse.setCounter(counter);
				} else {
					restResponse.setStatus(10);
					restResponse.setMessage("Data Not Found");
				}

			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "preview/{id}", method = RequestMethod.GET)
	public RestResponse previewBannerById(@PathVariable("id") String idPreview,
			@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "lang", required = false) String lang, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;

			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);

			List<HashMap> bannerDto = new ArrayList<>();
			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {

				HashMap aboutChubbLifeDto = new HashMap();
				if (lang.equalsIgnoreCase("EN") || lang.equalsIgnoreCase("en")) {
					aboutChubbLifeDto = bannerSvc.frontendBannerEng(idPreview);
				} else {
					aboutChubbLifeDto = bannerSvc.frontendBannerId(idPreview);
				}
				bannerDto.add(aboutChubbLifeDto);

				if (aboutChubbLifeDto.size() > 0) {
					restResponse.setStatus(100);
					restResponse.setMessage("Success");
					restResponse.setDatas(bannerDto);
					restResponse.setCounter(counter);
				} else {
					restResponse.setStatus(10);
					restResponse.setMessage("Data Not Found");
				}

			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}
	// ------------------------------------ FRONTEND SCRIPT
	// -----------------------------------------

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public RestResponse findAllBanner(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("read").toString().equalsIgnoreCase("1")) {

					List<Object[]> bannerDtos = (List<Object[]>) bannerSvc.findAllRedis(menuID);

					if (bannerDtos.size() > 0) {
						restResponse.setStatus(100);
						restResponse.setMessage("Success");
						restResponse.setDatas(bannerDtos);
						restResponse.setCounter(counter);
					} else {
						restResponse.setStatus(10);
						restResponse.setMessage("Data Not Found");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "allApproval", method = RequestMethod.GET)
	public RestResponse findAllApprovalBanner(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("read").toString().equalsIgnoreCase("1")) {
					List<Object[]> bannerDtos = (List<Object[]>) bannerSvc.findAllRedisWithApproval(approvalNoUser,
							menuID);
					if (bannerDtos.size() > 0) {
						restResponse.setStatus(100);
						restResponse.setMessage("Success");
						restResponse.setDatas(bannerDtos);
						restResponse.setCounter(counter);
					} else {
						restResponse.setStatus(10);
						restResponse.setMessage("Data Not Found");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "byId/{id}", method = RequestMethod.GET)
	public RestResponse findOneById(@PathVariable("id") String id,
			@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("read").toString().equalsIgnoreCase("1")) {
					BannerDto bannerDto = (BannerDto) bannerSvc.findOneEditById(id);

					List<RejectDto> listReject = rejectSvc.findAllByContentId(id);
					HashMap mapTemp = new HashMap<>();

					mapTemp.put("content", bannerDto);
					mapTemp.put("rejectNotes", listReject);
					if (bannerDto != null) {
						restResponse.setStatus(100);
						restResponse.setMessage("Success");
						restResponse.setDatas(mapTemp);
						restResponse.setCounter(counter);
					} else {
						restResponse.setStatus(10);
						restResponse.setMessage("Data Not Found");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public RestResponse save(@RequestBody BannerDto bannerDto,
			@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			SimpleDateFormat sdfFormat = new SimpleDateFormat(configSvc.findOneByNameTypeString("date_format"));
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("created").toString().equalsIgnoreCase("1")) {

					// GenerateId
					String uuidGenerate = SecurityUtil.uuidGenerate();
					bannerDto.setId(uuidGenerate);
					bannerDto.setRowStatus("2");
					bannerDto.setRowTimeUpdate(sdfFormat.parse(SecurityUtil.createdDate()));
					bannerDto.setRowUserId(idUser);
					bannerDto.setApprovalNo(Integer.parseInt(approvalNoUser));
					bannerDto.setCreateUserId(idUser);
					bannerDto.setPublishUserId(idUser);
					bannerDto.setCreateDate(sdfFormat.parse(SecurityUtil.createdDate()));
					bannerDto.setLastUpdateDate(sdfFormat.parse(SecurityUtil.createdDate()));
					bannerSvc.save(bannerDto);

					redisDao.deleteAll("BANNER");

					restResponse.setStatus(100);
					restResponse.setMessage("Success");
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RestResponse update(@RequestBody BannerDto bannerDto,
			@RequestParam(value = "id", required = false) String idBanner,
			@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {

			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			SimpleDateFormat sdfFormat = new SimpleDateFormat(configSvc.findOneByNameTypeString("date_format"));
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("updated").toString().equalsIgnoreCase("1")) {

					BannerDto bannerDtoOld = bannerSvc.findOneById(idBanner);
					String rowStatus = bannerDtoOld.getRowStatus();
					String createdUser = bannerDtoOld.getCreateUserId();

					if (rowStatus.equalsIgnoreCase("2") && createdUser.equalsIgnoreCase(idUser)) {

						if (bannerDtoOld.getId() == null || bannerDtoOld.getId() == null) {
							bannerDto.setApprovalNo(Integer.parseInt(approvalNoUser));
							bannerDto.setApprovalUserId(idUser);
							bannerDto.setPublishUserId(idUser);
							bannerDto.setRowStatus("2");
							bannerDto.setCreateUserId(idUser);
						} else {
							bannerDto.setCreateUserId(bannerDtoOld.getCreateUserId());
							bannerDto.setApprovalNo(bannerDtoOld.getApprovalNo());
							bannerDto.setRowStatus(bannerDtoOld.getRowStatus());
							bannerDto.setApprovalUserId(bannerDtoOld.getApprovalUserId());
							bannerDto.setCreateDate(bannerDtoOld.getCreateDate());
							bannerDto.setDateStart(bannerDtoOld.getDateStart());
						}

						bannerDto.setId(idBanner);
						bannerDto.setRowTimeUpdate(sdfFormat.parse(SecurityUtil.createdDate()));
						bannerDto.setRowUserId(idUser);
						bannerDto.setLastUpdateDate(sdfFormat.parse(SecurityUtil.createdDate()));

						bannerSvc.update(bannerDto, idBanner);

						redisDao.deleteAll("BANNER");

						restResponse.setStatus(100);
						restResponse.setMessage("Success");
					} else {
						restResponse.setStatus(0);
						restResponse.setMessage("You're Not Authorized For This Method");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "updateStatus", method = RequestMethod.GET)
	public RestResponse updateStatus(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "id", required = false) String idBanner, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("updated").toString().equalsIgnoreCase("1")) {

					String upStatus = bannerSvc.updateStatus(idBanner, approvalNoUser, idUser);

					if (upStatus.equals("1")) {
						HashMap map = new HashMap();
						map.put("id", idBanner);
						map.put("approval_no", approvalNoUser);
						map.put("parentId", parentID);
						map.put("menuId", menuID);

						redisDao.deleteAll("BANNER");

						restResponse.setDatas(map);
						restResponse.setStatus(100);
						restResponse.setMessage("Success");
					} else {
						restResponse.setStatus(10);
						restResponse.setMessage("Data Not Found");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public RestResponse deleteBanner(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("deleted").toString().equalsIgnoreCase("1")) {
					BannerDto bannerDelete = bannerSvc.findOneById(id);
					String rowStatus = notNull(bannerDelete.getRowStatus());
					String createdUserId = bannerDelete.getCreateUserId();
					if (rowStatus.equalsIgnoreCase("2") && createdUserId.equalsIgnoreCase(idUser)) {
						String deleteBanner = bannerSvc.deleteBanner(id);
						if (deleteBanner.equals("1")) {
							restResponse.setStatus(100);
							restResponse.setMessage("Success");

							HashMap map = new HashMap();
							map.put("id", id);

							redisDao.deleteAll("BANNER");

						} else {
							restResponse.setStatus(10);
							restResponse.setMessage("Data Not Found");
						}

					} else {
						restResponse.setStatus(0);
						restResponse.setMessage("You're Not Authorized For This Method");
					}

				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public RestResponse searchAllBanner(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "searchName", required = false) String searchName, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {

				List<Object[]> objBanner = (List<Object[]>) redisDao.get("BANNER", "all");
				System.out.println("Size Banner");
				System.out.println(objBanner.size());
				System.out.println(objBanner);
				List<Object[]> listSeacrh = new ArrayList();
				if (objBanner.size() > 0) {
					System.out.println("More than 0 size banner");
					for (Object[] obj : objBanner) {
						System.out.println("obj");
						System.out.println(obj);
						String title = (String) obj[1];
						String description = (String) obj[3];

						System.out.println("Search Name: " + searchName);
						System.out.println("Title      : " + title);
						System.out.println("Description: " + description);
					}
				}

				if (listSeacrh.size() > 0) {
					restResponse.setStatus(100);
					restResponse.setDatas(listSeacrh);
					restResponse.setCounter(listSeacrh.size());
					restResponse.setMessage("Success");
				} else {
					restResponse.setStatus(10);
					restResponse.setMessage("Data Not Found");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "delete/redis", method = RequestMethod.GET)
	public RestResponse deleteRedis(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {

		RestResponse restResponse = new RestResponse();
		try {
			long counter = 1;
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {

				redisDao.deleteAll("BANNER");

				restResponse.setStatus(100);
				restResponse.setMessage("Success");
				restResponse.setCounter(counter);
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	// 20 JUNI 2018
	@RequestMapping(value = "rejectStatus", method = RequestMethod.GET)
	public RestResponse rejectStatusBanner(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "id", required = false) String idBanner, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {

				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("deleted").toString().equalsIgnoreCase("1")) {
					String rejectAbout = bannerSvc.rejectBanner(idBanner, idUser);
					if (rejectAbout.equals("1")) {

						HashMap map = new HashMap();
						map.put("id", idBanner);

						// refresh redis
						redisDao.deleteAll("BANNER");

						restResponse.setStatus(100);
						restResponse.setMessage("Success");
					} else {
						restResponse.setStatus(10);
						restResponse.setMessage("Data Not Found");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "rejectRemark", method = RequestMethod.GET)
	public RestResponse rejectRemarkPrivacy(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "id", required = false) String idBanner,
			@RequestParam(value = "note", required = false) String note, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalReject = bannerSvc.getApprovalReject(idBanner, menuID);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);
			SimpleDateFormat sdfFormat = new SimpleDateFormat(configSvc.findOneByNameTypeString("date_format"));

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {

				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("deleted").toString().equalsIgnoreCase("1")) {

					int appNoUser = Integer.parseInt(mapPrivilege.get("approvalno").toString());
					BannerDto banner = bannerSvc.findOneById(idBanner);

					if (banner.getRowStatus().equals("1") && (appNoUser < 100)
							&& (banner.getApprovalNo() >= appNoUser)) {
						restResponse.setStatus(0);
						restResponse.setMessage("You're Not Authorized For This Method...");
					} else {
						String rejectBanner = bannerSvc.rejectRemarkBanner(idBanner, approvalReject, menuID, idUser);
						if (rejectBanner.equals("1")) {

							HashMap map = new HashMap();
							map.put("id", idBanner);
							banner.setRejectDate(sdfFormat.parse(SecurityUtil.createdDate()));
							banner.setRejectUserId(idUser);

							RejectDto rejectDto = new RejectDto();
							// GenerateId
							String uuidGenerate = SecurityUtil.uuidGenerate();
							rejectDto.setId(uuidGenerate);
							rejectDto.setContentId(idBanner);
							rejectDto.setRowStatus(1);
							rejectDto.setRejectNote(note);
							rejectDto.setRowTimeUpdate(sdfFormat.parse(SecurityUtil.createdDate()));
							rejectDto.setCreateDate(sdfFormat.parse(SecurityUtil.createdDate()));
							rejectDto.setCreateUserId(idUser);
							rejectSvc.save(rejectDto);

							// refresh redis
							redisDao.deleteAll("BANNER");

							restResponse.setStatus(100);
							restResponse.setMessage("Success");
						} else {
							restResponse.setStatus(10);
							restResponse.setMessage("Data Not Found");
						}
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}
			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	@RequestMapping(value = "pending", method = RequestMethod.GET)
	public RestResponse findAllDataDocument(@RequestParam(value = "appid", required = false) String appId,
			@RequestParam(value = "appkey", required = false) String appKey,
			@RequestParam(value = "token", required = false) String token, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		try {
			String rstApplication = applicationSvc.checkApplication(appId, appKey);
			String rstToken = tokenSvc.checkToken(token);
			TokenDto tokenDto = tokenSvc.findTokenByToken(token);
			String idUser = tokenDto.getUserId();
			String parentID = tokenDto.getGroupId();
			String approvalNoUser = bannerSvc.getApprovalValue(parentID, menuID);
			HashMap mapPrivilege = tokenSvc.checkedPrivilege(token, menuID);

			if (!rstToken.equalsIgnoreCase("") && Integer.valueOf(rstToken) < 4
					&& rstApplication.equalsIgnoreCase("true")) {
				if (mapPrivilege.get("result").toString().equalsIgnoreCase("true")
						&& mapPrivilege.get("deleted").toString().equalsIgnoreCase("1")) {

					List<Object[]> bannerDtos = (List<Object[]>) bannerSvc.pending(Integer.valueOf(approvalNoUser));

					if (bannerDtos.size() > 0) {
						restResponse.setStatus(100);
						restResponse.setMessage("Success");
						restResponse.setDatas(bannerDtos);
					} else {
						restResponse.setStatus(10);
						restResponse.setMessage("Data Not Found");
					}
				} else {
					restResponse.setStatus(0);
					restResponse.setMessage("You're Not Authorized For This Method");
				}

			} else {
				restResponse.setStatus(0);
				restResponse.setMessage("You're Not Authorized For This Method");
			}
		} catch (Exception ex) {
			restResponse.setStatus(0);
			restResponse.setMessage("Process Failed");
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return restResponse;
	}

	private String notNull(String s) {
		if (s != null) {
			return s.trim();
		}
		return "";
	}
}
