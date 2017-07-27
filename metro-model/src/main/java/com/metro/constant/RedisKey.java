package com.metro.constant;

public class RedisKey {

	/**
	 * 
	 */
	public static final String Activity_temp_prefix= "activity_temp_";
	/**
	 * 活动草稿保存 key需要加创建者的id
	 */
	public static final String Activity_save_draft_prefix="activity_save_draft_";	
	
	/**
	 * 活动信息存贮:key:需要加cityId value:存放活动Id和activityVo(包含Team信息)
	 */
	public static final String Activity_prefix = "activity_";
	/**
	 * 活动浏览量计数key:需要activityId value:活动的浏览量,每查询一次需要在这里+1
	 */
	public static final String Activity_viewcount_add_prefix = "activity_viewcount_add_";
	/**
	 * 活动浏览量持久化到数据库的队列 key:key value:activityId
	 */
	public static final String Activity_viewcount_db = "activity_viewcount_db";
	/**
	 * 活动加入的列表需加 key:activityId:里面存放想要加入这个Id的team信息 hash结构
	 */
	public static final String Activity_join_prefix = "activity_join_";
	/**
	 * 活动浏览量持久化到数据库的队列 key:key value: field:活动创建teamId v-value:想要参加活动的teamId
	 */
	public static final String Activity_join_push_prefix = "activity_join_push_";
	/**
	 * 活动浏览量持久化到数据库的队列
	 *  key:key 
	 *  <p>field:活动创建teamId</p>
	 *  <p>value:想要参加活动的teamId</p>
	 */
	public static final String Activity_join_push_list = "activity_join_push_list";
	/**
	 * 解除团队关系在规定时间内拒绝所有参加活动的请求将相关Activity_join中的status置为-1
	 */
	public static final String Team_disband_refuse_task = "team_disband_refuse_task";
	/**
	 * 用户评分表记录用户评分的总分数和评分人数
	 * <p>
	 * field1key : userId+ScoreCount记录总分数
	 * </p>
	 * <p>
	 * field2key : userId+Count记录总投票人数
	 * </p>
	 */
	public static final String User_Appraise_count = "user_appraise_count";
	/**
	 * 用户评价查询的记录 Key : key+userId
	 * <p>
	 * fieldkey : 待评价用户Id+活动Id 
	 * </p>
	 * <p>
	 * value : 要评价的team的Id
	 * </p>
	 */
	public static final String ACTIVITY_WAITFOR_EVALUATE_PREFIX = "activity_waitfor_evaluate_";
	/***
	 * 未注册用户邀请
	 */
	public final static String non_user_inv = "non_user_inv";
	
	/***
	 * 用户邀请数
	 * 拼接user_id
	 */
	public final static String user_inv_num = "user_inv_num";

	/***
	 * 用户好友
	 */
	public final static String yd_user_friends = "yd_user_friends";

	/***
	 * 推荐好友
	 */
	public final static String yd_recommend_team = "yd_recommend_team";

	/***
	 * 用户动态 key拼接：user_id
	 */
	public final static String yd_user_dynamic = "yd_user_dynamic";
	
	/***
	 * 动态圈子 key拼接：user_id
	 */
	public final static String yd_user_moments = "yd_user_moments";
	
	/***
	 * 动态圈子最新消息 key拼接：user_id
	 */
	public final static String yd_user_moments_msg="yd_user_moments_msg";

	/***
	 * 友搭动态队列
	 */
	public final static String yd_wait_process_dynamic = "yd_wait_process_dynamic";

	/***
	 * 动态喜欢 key拼接为：user_dynamic_id
	 */
	public final static String yd_user_dynamic_like = "yd_user_dynamic_like";

	/***
	 * 动态评论 key拼接为：user_dynamic_id
	 */
	public final static String yd_user_dynamic_comment = "yd_user_dynamic_comment";

	/***
	 * 用户昵称头像信息
	 */
	public final static String user_show = "usershow_";
	/**
	 * 用户登陆信息
	 */
	public final static String user_login = "user_login_";
	/**
	 * 手机号对应的用户信息
	 */
	public final static String user_phone = "user_phone_";
	/**
	 * 缓存用户操作数据，以便后面去重，key
	 */
	public final static String user_op_data_key = "user_op_data";
	/**
	 * 
	 */
	public final static String User_evaluate_count = "user_evaluate_count";
	/**
	 * 用户发布活动即为用户增加此队列增加此队列需要为两个用户都增加以便查询
	 * </br>key : key+userId
	 * </br>filed : activityId使用activityId作为key防止同一团队发布多个活动
	 * </br>value : teamId
	 */
	public final static String TEAM_PUBLISHACTIVITY_CACHE = "team_publishactivity_cache";
//	/***
//	 * 用户喜欢的类型标签
//	 * 拼接user_id
//	 */
//	public final static String yd_user_like_type = "yd_user_like_type";
	/**
	 * evaluationTask的最后结束时间用于查询过期活动使用
	 */
	public final static String Evaluation_endTimeEnd = "evaluation_endtimeend";
	
	/***
	 * 用户通讯录
	 * 拼接user_id
	 */
	public final static String yd_user_communication = "yd_user_communication";
	
	/***
	 * 通讯录队列
	 */
	public final static String yd_wait_process_communication = "yd_wait_process_communication";
	/**
	 * <h1>用户位置信息 </h1>
	 * key :　key+userId</br>
	 * field---value : lng---经度</br>
	 * field---value : lat---纬度</br>
	 * field---value : geoHash---geoHash</br>
	 */
	public final static String USER_LOCATION_CITYID_PREFIX = "user_location_cityid_";
	
	/***
	 * 用户已确认的邀请列表
	 * 拼接user_id
	 */
	public final static String yd_user_offer_list = "yd_user_offer_list";
	
	/***
	 * 团队推送纪录保存
	 */
	public final static String yd_user_team_push = "yd_user_team_push";
	
	/***
	 * 消息
	 * 拼接user_id
	 */
	public final static String yd_message_user = "yd_message_user";
	
	/***
	 * yd_message_user下的field
	 * 留言板上一次查询时间
	 * 拼接chatroomid
	 */
	public final static String chatroom_searchTime = "chatroom_searchTime";
	
	/***
	 * yd_message_user下的field
	 * 接收到的offer消息查询
	 * 拼接offer_id
	 */
	public final static String receive_offer_searchTime = "receive_offer_searchTime";
	
	/***
	 * 未注册用户邀请
	 */
	public final static String yd_non_user_inv = "yd_non_user_inv";

	/**
	 * offer待评价列表
	 */
	public final static String OFFER_WAITFOR_EVALUATE_PREFIX="offer_waitfor_evaluate_";
	public static final String USER_PROCESSING_ACTIVITY_PREFIX = "user_processing_activity_";
	public static final String USER_PROCESSING_ACTIVITY_JOIN_PREFIX = "user_processing_activity_join_";
	public static final String YD_SUCCESS_ACTIVITY_PREFIX = "yd_success_activity_";
	/**
	 * key+soureType+sourceId
	 */
	public static final String YD_TEAMMEET = "yd_teammeet_";
	public static final String USER_QUICK_MATCH_RESULT_LIST_PREFIX = "user_quick_match_result_list_";
	public static String ACTIVITY_NEWMESSAGE = "activity_newmessage";
	public static String USER_LOCATION_PREFIX = "user_location_";
	public static String USER_QUICK_MATCH_RESULT_PREFIX = "user_quick_match_result_";
	
	public static String QUICK_MATCH_LAST_RESULT_SINGLE = "quick_match_last_result_single_";
	public static String QUICK_MATCH_LAST_RESULT_PARTNER = "quick_match_last_result_partner_";
	/**
	 * redis设置过期时间的key
	 */
	public static final String SORTEDSETEXPIRE = "sortedsetexpire";
	public static final String HASHEXPIRE = "hashexpire";
	public static final String YD_OPENUSER_FRIEND_PREFIX = "yd_openuser_friend_";
	public static final String OpenUser_WeChat = "WX";
	public static final String OpenUser_Weixin = "WX";
	public static final String OpenUser_QQ = "QQ";
	public static final String YD_USERDYN_SHARECOUNT = "yd_userdyn_sharecount";
	
	/***
	 * 环信注册计数
	 */
	public static final String yd_easemo_index = "yd_easemo_index";
	
	/***
	 * 环信注册失败结果集(id结果集)
	 */
	public static final String yd_easemo_faild = "yd_easemo_faild";
	
	/***
	 * 环信用户注册 人工检查
	 */
	public static final String yd_easemo_user_create_artificial_check = "";
	public static final String YD_USERDYN_SHARECOUNT_INC = "yd_userdyn_sharecount_inc";
	public static final String USER_ZMXY_CERTIFICATION = "user_zmxy_certification_";
	public static final String ZMXY_REQUEST_TIME_LIMIT_PREFIX = "zmxy_request_time_limit_";
	
	/***
	 * 分享页面参数缓存
	 * 拼接code(code来源为微信，为唯一标识)
	 */
	public static final String yd_share_page_cache = "yd_share_page_cache";
	
	/***
	 * 微信小程序登录
	 * 拼接js_code
	 */
	public static final String yd_wx_sp_login_session = "yd_wx_sp_login_session";
	
}
